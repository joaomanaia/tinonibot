package core.extensions.command.slash.moderation

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.PublicSlashCommand
import com.kotlindiscord.kord.extensions.commands.application.slash.group
import com.kotlindiscord.kord.extensions.commands.application.slash.publicSubCommand
import com.kotlindiscord.kord.extensions.commands.converters.impl.channel
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import core.common.Common
import dev.kord.common.entity.ChannelType
import dev.kord.core.kordLogger
import domain.repository.guild.GuildRepository
import org.koin.core.component.inject

class UpdateWelcomeChannelCommandExtension : Extension() {
    private val guildRepository: GuildRepository by inject()

    override val name: String
        get() = "updateWelcomeChannel"

    inner class UpdateWelcomeChannelArguments : Arguments() {
        val channel by channel {
            name = "channel"
            description = "Channel to receive welcome messages"
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

                    updateWelcomeChannelAction()
                }
            }
        }
    }

    private suspend fun PublicSlashCommand<UpdateWelcomeChannelArguments>.updateWelcomeChannelAction() {
        action {
            try {
                val guildId = guild?.id?.value?.toLong() ?: throw NullPointerException("Guild is null")
                val channel = arguments.channel

                if (channel.type != ChannelType.GuildText)
                    throw IllegalArgumentException("Channel must be text channel")

                guildRepository.updateWelcomeMessageChannel(guildId, channel.id)

                respond {
                    content = "Welcome channel updated to ${arguments.channel.mention}!"
                }
            } catch (e: Exception) {
                kordLogger.error(e.localizedMessage)

                respond {
                    content = "**Error:** ${e.localizedMessage}"
                }
            }
        }
    }
}