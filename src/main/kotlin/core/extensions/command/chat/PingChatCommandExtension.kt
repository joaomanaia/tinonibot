package core.extensions.command.chat

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.chatCommand
import dev.kord.core.behavior.reply
import domain.repository.guild.GuildRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.datetime.Clock
import org.koin.core.component.inject

class PingChatCommandExtension : Extension() {
    private val guildRepository: GuildRepository by inject()

    override val name: String
        get() = "ping"

    override suspend fun setup() {
        val selfBot = kord.getSelf()

        chatCommand {
            name = "ping"
            description = "Recebe a latência do ${selfBot.mention}"

            action {
                val latency = Clock.System.now() - message.timestamp

                message.reply {
                    content = "\uD83C\uDFD3 Pong! A latência do ${selfBot.mention} é ${latency.inWholeMilliseconds}ms."
                }
            }
        }
    }
}