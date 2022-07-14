package core.extensions.command.slash.moderation

import com.kotlindiscord.kord.extensions.commands.application.slash.group
import com.kotlindiscord.kord.extensions.commands.application.slash.publicSubCommand
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import com.kotlindiscord.kord.extensions.utils.hasPermission
import core.common.Common
import dev.kord.common.entity.Permission
import dev.kord.core.kordLogger
import domain.repository.guild.GuildRepository
import org.koin.core.component.inject

class RemoveWelcomeChannelCommandExtension : Extension() {
    private val guildRepository: GuildRepository by inject()

    override val name: String
        get() = "removeWelcomeChannel"

    override suspend fun setup() {
        publicSlashCommand {
            name = "remove"
            description = translationsProvider.get("core.extensions.command.slash.moderation.remove_welcome_channel.remove_command.description")

            //TODO: Remove in production
            guild(Common.TEST_GUILD_ID)

            group("channel") {
                description = translationsProvider.get("core.extensions.command.slash.moderation.remove_welcome_channel.group_channel.description")

                publicSubCommand {
                    name = "welcome"
                    description = translationsProvider.get("core.extensions.command.slash.moderation.remove_welcome_channel.remove_welcome_channel.description")

                    requirePermission(Permission.ManageChannels)
                    allowInDms = false

                    action {
                        try {
                            val member = member?.asMember() ?: throw NullPointerException(translationsProvider.get("null_values.member"))

                            if (!member.hasPermission(Permission.ManageChannels))
                                throw RuntimeException(translationsProvider.get("user_does_not_have_permission.manage_channels"))

                            val guildId = guild?.id?.value?.toLong() ?: throw NullPointerException(translationsProvider.get("null_values.guild"))

                            guildRepository.removeWelcomeMessageChannel(guildId)

                            respond {
                                content = translationsProvider.get("core.extensions.command.slash.moderation.remove_welcome_channel.content")
                            }
                        } catch (e: Exception) {
                            kordLogger.error("RemoveWelcomeChannel: ${e.localizedMessage}")

                            respond {
                                content = "**Error:** ${e.localizedMessage}"
                            }
                        }
                    }
                }
            }
        }
    }
}