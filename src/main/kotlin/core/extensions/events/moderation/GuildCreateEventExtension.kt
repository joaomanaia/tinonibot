package core.extensions.events.moderation

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.event
import dev.kord.core.event.guild.GuildCreateEvent

class GuildCreateEventExtension : Extension() {
    override val name: String
        get() = "guildCreateEvent"

    override suspend fun setup() {
        event<GuildCreateEvent> {
            action {

            }
        }
    }
}