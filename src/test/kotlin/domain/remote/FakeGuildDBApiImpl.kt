package domain.remote

import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto

class FakeGuildDBApiImpl : GuildDBApi {
    private val guilds = mutableListOf<GuildDBDto>()

    override suspend fun getGuildDB(id: Long): GuildDBDto {
        return guilds.find { guild ->
            guild.id == id
        } ?: throw NullPointerException("Guild not found")
    }

    override suspend fun updateWelcomeMessageChannel(guildId: Long, channelId: Long?) {
        val guildDBDtoIndex = guilds.indexOfFirst { guild -> guild.id == guildId }
        val guildDBDto = GuildDBDto(
            id = guildId,
            welcomeMessageChannelId = channelId
        )
        guilds[guildDBDtoIndex] = guildDBDto
    }

    override suspend fun insertGuildToDB(guildDB: GuildDBDto) {
        guilds.add(guildDB)
    }

    override suspend fun removeGuildFromDB(guildId: Long) {
        guilds.removeAll { it.id == guildId }
    }
}