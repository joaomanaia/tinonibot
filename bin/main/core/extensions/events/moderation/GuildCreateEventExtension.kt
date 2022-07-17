package core.extensions.events.moderation

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import dev.kord.core.behavior.getChannelOf
import dev.kord.core.entity.channel.TextChannel
import dev.kord.core.event.guild.GuildCreateEvent
import dev.kord.core.kordLogger
import domain.repository.guild.GuildRepository
import kotlinx.datetime.Clock
import org.koin.core.component.inject

class GuildCreateEventExtension : Extension() {
    private val guildRepository: GuildRepository by inject()
    override val name: String
        get() = "guildCreateEvent"

    override suspend fun setup() {
        val launchTime = Clock.System.now()

        event<GuildCreateEvent> {
            action {
                try {
                    val guild = event.guild
                    val guildId = guild.id.value.toLong()

                    // When the bot starts this event will register all guilds the bot is in,
                    // so we need to check if the guild the bot joined is new.
                    if (guild.joinedTime != null && guild.joinedTime!! > launchTime) {
                        guildRepository.createGuildToDatabase(guildId)
                    }
                } catch (e: Exception) {
                    kordLogger.error("GuildCreateEvent: ${e.localizedMessage}")
                }
            }
        }
    }
}