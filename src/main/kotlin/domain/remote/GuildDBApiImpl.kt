package domain.remote

import core.util.database.DatabaseUtilImpl
import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto

class GuildDBApiImpl(
    private val databaseUtil: DatabaseUtilImpl
) : GuildDBApi {
    override suspend fun getGuildDB(id: Long): GuildDBDto {
        return databaseUtil
            .postgrestClient
            .from<GuildDBDto>("guilds")
            .select()
            .eq(GuildDBDto::id, id)
            .limit(1)
            .single()
            .executeAndGetSingle()
    }

    private fun updateChannel(guildId: Long, updateData: Map<String, Any?>) {
        databaseUtil
            .postgrestClient
            .from<GuildDBDto>("guilds")
            .update(updateData)
            .eq(GuildDBDto::id, guildId)
            .execute()
    }

    override suspend fun updateChannelData(guildId: Long, data: Map<String, Any?>) {
        updateChannel(guildId = guildId, updateData = data)
    }

    override suspend fun updateChannelFieldData(fieldName: String, guildId: Long, channelId: Long?) {
        val updateData = mapOf(fieldName to channelId)
        updateChannel(guildId = guildId, updateData = updateData)
    }

    override suspend fun insertGuildToDB(guildDB: GuildDBDto) {
        databaseUtil
            .postgrestClient
            .from<GuildDBDto>("guilds")
            .insert(guildDB)
            .execute()
    }

    override suspend fun removeGuildFromDB(guildId: Long) {
        databaseUtil
            .postgrestClient
            .from<GuildDBDto>("guilds")
            .delete()
            .eq(GuildDBDto::id, guildId)
            .execute()
    }
}