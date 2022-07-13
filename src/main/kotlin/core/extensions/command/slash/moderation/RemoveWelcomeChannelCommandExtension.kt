package core.extensions.command.slash.moderation

import com.kotlindiscord.kord.extensions.commands.application.slash.group
import com.kotlindiscord.kord.extensions.commands.application.slash.publicSubCommand
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import core.common.Common
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
            description = "Commands for removing"

            //TODO: Remove in production
            guild(Common.TEST_GUILD_ID)

            group("channel") {
                description = "Commands for removing channels from database"

                publicSubCommand {
                    name = "welcome"
                    description = "Remove welcome channel from database"

                    action {
                        try {
                            val guildId = guild?.id?.value?.toLong() ?: throw NullPointerException("Guild is null")
                            guildRepository.removeWelcomeMessageChannel(guildId)

                            respond {
                                content = "Welcome channel removed!"
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
        }
    }
}