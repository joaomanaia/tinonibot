package data.mappers

import data.remote.guild.GuildDBDto
import dev.kord.common.entity.Snowflake
import model.guild.GuildDB
import kotlin.test.Test
import kotlin.test.assertEquals

internal class GuildDBMappersTest {
    @Test
    fun `guildDBDto to guildDB test`() {
        val guildDBDto = GuildDBDto(
            id = 0,
            welcomeMessageChannelId = 1
        )

        val guildDB = guildDBDto.toGuildDB()

        assertEquals(
            expected = GuildDB(
                id = Snowflake(0),
                welcomeMessageChannelId = Snowflake(1)
            ),
            actual = guildDB
        )
    }

    @Test
    fun `guildDBDto to guildDB, welcomeMessageChannelId null value test`() {
        val guildDBDto = GuildDBDto(
            id = 0,
            welcomeMessageChannelId = null
        )

        val guildDB = guildDBDto.toGuildDB()

        assertEquals(
            expected = GuildDB(
                id = Snowflake(0),
                welcomeMessageChannelId = null
            ),
            actual = guildDB
        )
    }

    @Test
    fun `guildDB to guildDBDTO test`() {
        val guildDB = GuildDB(
            id = Snowflake(0),
            welcomeMessageChannelId = Snowflake(1)
        )

        val guildDBDTO = guildDB.toGuildDBDTO()

        assertEquals(
            expected = GuildDBDto(
                id = 0,
                welcomeMessageChannelId = 1
            ),
            actual = guildDBDTO
        )
    }

    @Test
    fun `guildDB to guildDBDTO, welcomeMessageChannelId null value test`() {
        val guildDB = GuildDB(
            id = Snowflake(0),
            welcomeMessageChannelId = null
        )

        val guildDBDTO = guildDB.toGuildDBDTO()

        assertEquals(
            expected = GuildDBDto(
                id = 0,
                welcomeMessageChannelId = null
            ),
            actual = guildDBDTO
        )
    }
}