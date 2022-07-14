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

    override suspend fun updateWelcomeMessageChannel(guildId: Long, channelId: Long?) {
        val updateData = mapOf(GuildDBDto::welcomeMessageChannelId.name to channelId)

        databaseUtil
            .postgrestClient
            .from<GuildDBDto>("guilds")
            .update(updateData)
            .eq(GuildDBDto::id, guildId)
            .execute()
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