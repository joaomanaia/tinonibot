package data.repository

import data.mappers.toGuildDB
import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto
import dev.kord.common.entity.Snowflake
import domain.repository.guild.GuildRepository

class GuildRepositoryImpl(
    private val guildDBApi: GuildDBApi
) : GuildRepository {
    override suspend fun getWelcomeMessageChannel(guildId: Long): Snowflake {
        val guildDB = guildDBApi
            .getGuildDB(guildId)
            .toGuildDB()

        return guildDB.welcomeMessageChannelId ?: throw NullPointerException("welcomeMessageChannelId is null")
    }

    override suspend fun updateWelcomeMessageChannel(guildId: Long, channelId: Snowflake) {
        guildDBApi.updateWelcomeMessageChannel(guildId, channelId.value.toLong())
    }

    override suspend fun removeWelcomeMessageChannel(guildId: Long) {
        guildDBApi.updateWelcomeMessageChannel(guildId, null)
    }

    override suspend fun getWelcomeMessageForMember(
        memberMention: String,
        guildName: String
    ): String {
        return "Welcome $memberMention to server $guildName!"
    }

    override suspend fun createGuildToDatabase(guildId: Long) {
        val guildDBDto = GuildDBDto(
            id = guildId,
            welcomeMessageChannelId = null
        )
        guildDBApi.insertGuildToDB(guildDBDto)
    }

    override suspend fun deleteGuildFromDatabase(guildId: Long) {
        guildDBApi.removeGuildFromDB(guildId)
    }
}