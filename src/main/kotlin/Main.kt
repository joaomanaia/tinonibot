import com.kotlindiscord.kord.extensions.ExtensibleBot
import dev.kord.common.entity.PresenceStatus
import dev.kord.gateway.PrivilegedIntent
import kotlinx.datetime.Instant
import core.extensions.command.chat.PingChatCommandExtension
import dev.kord.gateway.Intent

@OptIn(PrivilegedIntent::class)
suspend fun main(args: Array<String>) {
    val botToken = args
        .getOrNull(0)
        ?.toString() ?: throw NullPointerException("Bot token is null")

    val bot = ExtensibleBot(botToken) {
        presence {
            playing("Internet Explorer")
            since = Instant.fromEpochMilliseconds(0)
            status = PresenceStatus.Online
        }

        intents {
            +Intent.MessageContent
        }

        chatCommands {
            enabled = true
        }

        extensions {
            add(::PingChatCommandExtension)
        }
    }

    bot.start()
}