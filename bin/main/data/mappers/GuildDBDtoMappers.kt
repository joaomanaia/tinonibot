package data.mappers

import data.remote.guild.GuildDBDto

fun GuildDBDto.toMap(): Map<String, Any?> = mapOf(
    GuildDBDto::id.name to id,
    GuildDBDto::welcomeMessageChannelId.name to welcomeMessageChannelId,
    GuildDBDto::verificationChannelId.name to verificationChannelId,
    GuildDBDto::verifiedRoleId.name to verifiedRoleId
)

fun Map<String, Any?>.toGuildDBDto(): GuildDBDto = GuildDBDto(
    id = get(GuildDBDto::id.name) as Long,
    welcomeMessageChannelId = get(GuildDBDto::welcomeMessageChannelId.name) as Long?,
    verificationChannelId = get(GuildDBDto::verificationChannelId.name) as Long?,
    verifiedRoleId = get(GuildDBDto::verifiedRoleId.name) as Long?
)