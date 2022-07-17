package domain.repository.guild

interface GuildRepository {
    suspend fun createGuildToDatabase(guildId: Long)

    suspend fun deleteGuildFromDatabase(guildId: Long)
}