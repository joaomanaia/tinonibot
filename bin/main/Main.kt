import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.i18n.SupportedLocales
import dev.kord.common.entity.PresenceStatus
import dev.kord.gateway.PrivilegedIntent
import kotlinx.datetime.Instant
import core.extensions.command.chat.PingChatCommandExtension
import core.extensions.command.slash.moderation.RemoveWelcomeChannelCommandExtension
import core.extensions.command.slash.moderation.UpdateChannelCommandExtension
import core.extensions.events.moderation.GuildCreateEventExtension
import core.extensions.events.moderation.GuildDeleteEventExtension
import core.extensions.events.moderation.MemberJoinEventExtension
import core.util.uri.toUri
import dev.kord.gateway.Intent
import di.guildDBRepositoryModule
import di.getDatabaseModule

@OptIn(PrivilegedIntent::class)
suspend fun main(args: Array<String>) {
    val botToken = args
        .getOrNull(0)
        ?.toString() ?: throw NullPointerException("Bot token is null")

    val supabaseUri = args
        .getOrNull(1)
        ?.toString()
        ?.toUri() ?: throw NullPointerException("Supabase uri is null")

    val supabaseApiKey = args
        .getOrNull(2)
        ?.toString() ?: throw NullPointerException("Supabase api key is null")

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

        i18n {
            interactionUserLocaleResolver()
            interactionGuildLocaleResolver()

            defaultLocale = SupportedLocales.ENGLISH
        }

        extensions {
            // Chat commands
            add(::PingChatCommandExtension)

            // Slash commands
            add(::UpdateChannelCommandExtension)
            add(::RemoveWelcomeChannelCommandExtension)

            // Events
            add(::MemberJoinEventExtension)
            add(::GuildCreateEventExtension)
            add(::GuildDeleteEventExtension)
        }
    }

    bot
        .getKoin()
        .loadModules(
            listOf(
                guildDBRepositoryModule,
                getDatabaseModule(
                    supabaseUri = supabaseUri,
                    supabaseApiKey = supabaseApiKey
                )
            )
        )

    bot.start()
}