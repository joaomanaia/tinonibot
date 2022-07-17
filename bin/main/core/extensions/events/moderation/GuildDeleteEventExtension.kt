package core.extensions.events.moderation

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import dev.kord.core.event.guild.GuildDeleteEvent
import dev.kord.core.kordLogger
import domain.repository.guild.GuildRepository
import org.koin.core.component.inject

class GuildDeleteEventExtension : Extension() {
    private val guildRepository: GuildRepository by inject()

    override val name: String
        get() = "deleteCreateEvent"

    override suspend fun setup() {
        event<GuildDeleteEvent> {
            action {
                try {
                    val guildId = event.guildId.value.toLong()

                    guildRepository.deleteGuildFromDatabase(guildId)
                } catch (e: Exception) {
                    kordLogger.error("GuildDeleteEvent: ${e.localizedMessage}")
                }
            }
        }
    }
}