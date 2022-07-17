package data.repository.guild.channel

import data.mappers.toGuildDB
import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto
import dev.kord.common.entity.Snowflake
import domain.repository.guild.channel.VerifyMemberRepository

class VerifyMemberRepositoryImpl(
    private val guildDBApi: GuildDBApi
) : VerifyMemberRepository {
    override suspend fun getChannelId(guildId: Long): Snowflake {
        val guildDB = guildDBApi
            .getGuildDB(guildId)
            .toGuildDB()

        return guildDB.verificationChannelId ?: throw NullPointerException("verificationChannelId is null")
    }

    override suspend fun updateChannel(guildId: Long, channelId: Snowflake) {
        guildDBApi.updateChannelFieldData(
            fieldName = GuildDBDto::verificationChannelId.name,
            guildId = guildId,
            channelId = channelId.value.toLong()
        )
    }

    override suspend fun removeChannel(guildId: Long) {
        guildDBApi.updateChannelFieldData(
            fieldName = GuildDBDto::verificationChannelId.name,
            guildId = guildId,
            channelId = null
        )
    }
}