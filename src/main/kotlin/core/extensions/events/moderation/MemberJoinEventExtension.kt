package core.extensions.events.moderation

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import core.common.Common
import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.getChannelOf
import dev.kord.core.entity.channel.TextChannel
import dev.kord.core.event.guild.MemberJoinEvent
import dev.kord.core.kordLogger
import domain.repository.guild.GuildRepository
import org.koin.core.component.inject

class MemberJoinEventExtension : Extension() {
    private val guildRepository: GuildRepository by inject()

    override val name: String
        get() = "memberJoinEvent"

    override suspend fun setup() {
        event<MemberJoinEvent> {
            action {
                try {
                    val guildId = event.guildId.value.toLong()
                    val welcomeChannelId = guildRepository.getWelcomeMessageChannel(guildId)

                    val member = event.member

                    val guildName = member.guild.asGuildOrNull()?.name.orEmpty()

                    val welcomeMessage = translationsProvider.translate(
                        key = "core.extensions.events.moderation.member_join_event.member_joined_message",
                        replacements = arrayOf(member.mention, guildName)
                    )

                    event
                        .guild
                        .getChannelOf<TextChannel>(welcomeChannelId)
                        .createMessage(welcomeMessage)
                } catch (e: Exception) {
                    kordLogger.error("MemberJoinEvent: ${e.localizedMessage}")
                }
            }
        }
    }
}