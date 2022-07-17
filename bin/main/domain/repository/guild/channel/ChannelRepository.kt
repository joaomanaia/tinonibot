package domain.repository.guild.channel

import dev.kord.common.entity.Snowflake

sealed interface ChannelRepository {
    suspend fun getChannelId(guildId: Long): Snowflake

    suspend fun updateChannel(
        guildId: Long,
        channelId: Snowflake
    )

    suspend fun removeChannel(guildId: Long)
}