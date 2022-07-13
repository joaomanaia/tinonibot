package model.guild

import dev.kord.common.entity.Snowflake

data class GuildDB(
    val id: Snowflake,
    val welcomeMessageChannelId: Snowflake?
)