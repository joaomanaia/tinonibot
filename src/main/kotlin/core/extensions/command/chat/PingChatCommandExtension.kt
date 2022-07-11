package core.extensions.command.chat

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.chatCommand
import dev.kord.core.behavior.reply
import kotlinx.datetime.Clock

class PingChatCommandExtension : Extension() {
    override val name: String
        get() = "ping"

    override suspend fun setup() {
        val selfBot = kord.getSelf()

        chatCommand {
            name = "ping"
            description = "Recebe a latência do bot"

            action {
                val latency = Clock.System.now() - message.timestamp

                message.reply {
                    content = "\uD83C\uDFD3 Pong! A latência do ${selfBot.mention} é ${latency.inWholeMilliseconds}ms."
                }
            }
        }
    }
}