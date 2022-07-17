package core.extensions.command.slash.moderation

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.PublicSlashCommand
import com.kotlindiscord.kord.extensions.commands.application.slash.group
import com.kotlindiscord.kord.extensions.commands.application.slash.publicSubCommand
import com.kotlindiscord.kord.extensions.commands.converters.impl.channel
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import com.kotlindiscord.kord.extensions.utils.hasPermission
import core.common.Common
import dev.kord.common.entity.ChannelType
import dev.kord.common.entity.Permission
import dev.kord.core.kordLogger
import domain.repository.guild.channel.VerifyMemberRepository
import domain.repository.guild.channel.WelcomeMemberRepository
import org.koin.core.component.inject

class UpdateChannelCommandExtension : Extension() {
    private val welcomeMemberRepository: WelcomeMemberRepository by inject()

    private val verifyMemberRepository: VerifyMemberRepository by inject()

    override val name: String
        get() = "updateChannel"

    inner class UpdateWelcomeChannelArguments : Arguments() {
        val welcomeChannel by channel {
            name = "welcome"
            description = "Welcome channel to new members"
        }
    }

    inner class UpdateVerificationChannelArguments : Arguments() {
        val verificationChannel by channel {
            name = "verification"
            description = "Channel to verify new members"
        }
    }

    override suspend fun setup() {
        publicSlashCommand {
            name = "update"
            description = "Commands for updating"

            //TODO: Remove in production
            guild(Common.TEST_GUILD_ID)

            group("channel") {
                description = "Commands for updating channels"

                publicSubCommand(::UpdateWelcomeChannelArguments) {
                    name = "welcome"
                    description = "Update welcome channel to database"

                    requirePermission(Permission.ManageChannels)
                    allowInDms = false

                    updateWelcomeChannelAction()
                }

                publicSubCommand(::UpdateVerificationChannelArguments) {
                    name = "verification"
                    description = "Update verification channel to database"

                    requirePermission(Permission.ManageChannels)
                    allowInDms = false

                    action {  }
                }
            }
        }
    }

    private suspend fun PublicSlashCommand<UpdateWelcomeChannelArguments>.updateWelcomeChannelAction() {
        action {
            try {
                val member = member?.asMember() ?: throw NullPointerException(translationsProvider.get("null_values.member"))

                if (!member.hasPermission(Permission.ManageChannels))
                    throw RuntimeException(translationsProvider.get("user_does_not_have_permission.manage_channels"))

                val guildId = guild?.id?.value?.toLong() ?: throw NullPointerException(translationsProvider.get("null_values.guild"))
                val channel = arguments.welcomeChannel

                if (channel.type != ChannelType.GuildText)
                    throw IllegalArgumentException(translationsProvider.get("channel.error.text_channel"))

                welcomeMemberRepository.updateChannel(guildId, channel.id)

                respond {
                    content = translationsProvider.translate(
                        key = "core.extensions.command.slash.moderation.update_welcome_channel.welcome_channel_updated_to",
                        replacements = arrayOf(arguments.welcomeChannel.mention)
                    )
                }
            } catch (e: Exception) {
                kordLogger.error("UpdateWelcomeChannel: ${e.localizedMessage}")

                respond {
                    content = "**Error:** ${e.localizedMessage}"
                }
            }
        }
    }
}