package data.remote.guild

interface GuildDBApi {
    suspend fun getGuildDB(id: Long): GuildDBDto

    suspend fun updateChannelData(
        guildId: Long,
        data: Map<String, Any?>
    )

    suspend fun updateChannelFieldData(
        fieldName: String,
        guildId: Long,
        channelId: Long?
    )

    suspend fun insertGuildToDB(guildDB: GuildDBDto)

    suspend fun removeGuildFromDB(guildId: Long)
}