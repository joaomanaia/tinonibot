package domain.remote

import core.util.data.merge
import data.mappers.toGuildDBDto
import data.mappers.toMap
import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto

class FakeGuildDBApiImpl : GuildDBApi {
    private val guilds = mutableListOf<GuildDBDto>()

    override suspend fun getGuildDB(id: Long): GuildDBDto {
        return guilds.find { guild ->
            guild.id == id
        } ?: throw NullPointerException("Guild not found")
    }

    override suspend fun updateChannelData(guildId: Long, data: Map<String, Any?>) {
        val currentGuildDTO = guilds.find { guild -> guild.id == guildId }

        if (currentGuildDTO != null) {
            val guildDBDto = currentGuildDTO merge data.toGuildDBDto()

            val guildDBDtoIndex = guilds.indexOfFirst { guild -> guild.id == guildId }
            guilds[guildDBDtoIndex] = guildDBDto
        }
    }

    override suspend fun updateChannelFieldData(fieldName: String, guildId: Long, channelId: Long?) {
        val currentGuildDTO = guilds.find { guild -> guild.id == guildId }

        currentGuildDTO
            ?.toMap()
            ?.toMutableMap()
            ?.apply {
                this[fieldName] = channelId
            }?.toGuildDBDto()
            ?.also { currentGuildDTOUpdated ->
                val guildDBDtoIndex = guilds.indexOfFirst { guild -> guild.id == guildId }
                guilds[guildDBDtoIndex] = currentGuildDTOUpdated
            }
    }

    override suspend fun insertGuildToDB(guildDB: GuildDBDto) {
        guilds.add(guildDB)
    }

    override suspend fun removeGuildFromDB(guildId: Long) {
        guilds.removeAll { it.id == guildId }
    }
}