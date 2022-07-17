package data.repository.guild.channel

import data.mappers.toGuildDB
import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto
import dev.kord.common.entity.Snowflake
import domain.repository.guild.channel.WelcomeMemberRepository

class WelcomeMemberRepositoryImpl(
    private val guildDBApi: GuildDBApi
) : WelcomeMemberRepository {
    override suspend fun getChannelId(guildId: Long): Snowflake {
        val guildDB = guildDBApi
            .getGuildDB(guildId)
            .toGuildDB()

        return guildDB.welcomeMessageChannelId ?: throw NullPointerException("welcomeMessageChannelId is null")
    }

    override suspend fun updateChannel(guildId: Long, channelId: Snowflake) {
        guildDBApi.updateChannelFieldData(
            fieldName = GuildDBDto::welcomeMessageChannelId.name,
            guildId = guildId,
            channelId = channelId.value.toLong()
        )
    }

    override suspend fun removeChannel(guildId: Long) {
        guildDBApi.updateChannelFieldData(
            fieldName = GuildDBDto::welcomeMessageChannelId.name,
            guildId = guildId,
            channelId = null
        )
    }
}