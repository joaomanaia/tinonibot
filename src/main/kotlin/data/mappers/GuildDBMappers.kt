package data.mappers

import data.remote.guild.GuildDBDto
import dev.kord.common.entity.Snowflake
import model.guild.GuildDB

fun GuildDBDto.toGuildDB(): GuildDB = GuildDB(
    id = Snowflake(id),
    welcomeMessageChannelId = welcomeMessageChannelId?.let { id -> Snowflake(id) }
)

fun GuildDB.toGuildDBDTO(): GuildDBDto = GuildDBDto(
    id = id.value.toLong(),
    welcomeMessageChannelId = welcomeMessageChannelId?.value?.toLong()
)