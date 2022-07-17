package data.repository

import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto
import domain.repository.guild.GuildRepository

class GuildRepositoryImpl(
    private val guildDBApi: GuildDBApi
) : GuildRepository {
    override suspend fun createGuildToDatabase(guildId: Long) {
        val guildDBDto = GuildDBDto(
            id = guildId,
            welcomeMessageChannelId = null
        )
        guildDBApi.insertGuildToDB(guildDBDto)
    }

    override suspend fun deleteGuildFromDatabase(guildId: Long) {
        guildDBApi.removeGuildFromDB(guildId)
    }
}