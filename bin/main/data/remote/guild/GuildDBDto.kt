package data.remote.guild

data class GuildDBDto(
    val id: Long,
    val welcomeMessageChannelId: Long? = null,
    val verificationChannelId: Long? = null,
    val verifiedRoleId: Long? = null
)