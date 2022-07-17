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
            description = translationsProvider.translate(key = "core.extensions.command.chat.ping_chat_command.description", replacements = arrayOf(selfBot.mention))

            action {
                val latency = Clock.System.now() - message.timestamp
                message.reply {
                    content = translationsProvider.translate(
                        key = "core.extensions.command.chat.ping_chat_command.content",
                        replacements = arrayOf(
                            selfBot.mention,
                            latency.inWholeMilliseconds
                        ),
                    )
                }
            }
        }
    }
}