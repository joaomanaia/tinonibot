package data.remote.guild

interface GuildDBApi {
    suspend fun getGuildDB(id: Long): GuildDBDto

    suspend fun updateWelcomeMessageChannel(
        guildId: Long,
        channelId: Long?
    )

    suspend fun insertGuildToDB(guildDB: GuildDBDto)

    suspend fun removeGuildFromDB(guildId: Long)
}