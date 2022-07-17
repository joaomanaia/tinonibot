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
            welcomeMessageChannelId = 1,
            verificationChannelId = 2,
            verifiedRoleId = 3
        )

        val guildDB = guildDBDto.toGuildDB()

        assertEquals(
            expected = GuildDB(
                id = Snowflake(0),
                welcomeMessageChannelId = Snowflake(1),
                verificationChannelId = Snowflake(2),
                verifiedRoleId = Snowflake(3)
            ),
            actual = guildDB
        )
    }

    @Test
    fun `guildDBDto to guildDB, welcomeMessageChannelId null value test`() {
        val guildDBDto = GuildDBDto(
            id = 0,
            welcomeMessageChannelId = null,
            verificationChannelId = null,
            verifiedRoleId = null
        )

        val guildDB = guildDBDto.toGuildDB()

        assertEquals(
            expected = GuildDB(
                id = Snowflake(0),
                welcomeMessageChannelId = null,
                verificationChannelId = null,
                verifiedRoleId = null
            ),
            actual = guildDB
        )
    }

    @Test
    fun `guildDB to guildDBDTO test`() {
        val guildDB = GuildDB(
            id = Snowflake(0),
            welcomeMessageChannelId = Snowflake(1),
            verificationChannelId = Snowflake(2),
            verifiedRoleId = Snowflake(3)
        )

        val guildDBDTO = guildDB.toGuildDBDTO()

        assertEquals(
            expected = GuildDBDto(
                id = 0,
                welcomeMessageChannelId = 1,
                verificationChannelId = 2,
                verifiedRoleId = 3
            ),
            actual = guildDBDTO
        )
    }

    @Test
    fun `guildDB to guildDBDTO, welcomeMessageChannelId null value test`() {
        val guildDB = GuildDB(
            id = Snowflake(0),
            welcomeMessageChannelId = null,
            verificationChannelId = null,
            verifiedRoleId = null
        )

        val guildDBDTO = guildDB.toGuildDBDTO()

        assertEquals(
            expected = GuildDBDto(
                id = 0,
                welcomeMessageChannelId = null,
                verificationChannelId = null,
                verifiedRoleId = null
            ),
            actual = guildDBDTO
        )
    }
}