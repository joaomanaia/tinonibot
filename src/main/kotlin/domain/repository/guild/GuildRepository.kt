package domain.repository.guild

import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.Guild
import dev.kord.core.entity.Member

interface GuildRepository {
    suspend fun getWelcomeMessageChannel(guildId: Long): Snowflake

    suspend fun updateWelcomeMessageChannel(
        guildId: Long,
        channelId: Snowflake
    )

    suspend fun removeWelcomeMessageChannel(guildId: Long)

    suspend fun getWelcomeMessageForMember(
        memberMention: String,
        guildName: String
    ): String

    suspend fun createGuildToDatabase(guildId: Long)

    suspend fun deleteGuildFromDatabase(guildId: Long)
}