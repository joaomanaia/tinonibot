package data.mappers

import data.remote.guild.GuildDBDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class GuildDBDtoMappersTest {
    @Test
    fun `guildDBDto to map test`() {
        val guildDBDto = GuildDBDto(
            id = 0,
            welcomeMessageChannelId = 1,
            verificationChannelId = 2,
            verifiedRoleId = 3
        )

        val guildDBDtoMap = guildDBDto.toMap()

        val expectedMap: Map<String, Any?> = mapOf(
            GuildDBDto::id.name to 0L,
            GuildDBDto::welcomeMessageChannelId.name to 1L,
            GuildDBDto::verificationChannelId.name to 2L,
            GuildDBDto::verifiedRoleId.name to 3L
        )

        assertEquals(expected = expectedMap, actual = guildDBDtoMap)
    }

    @Test
    fun `map to guildDBDto test`() {
        val guildDBDtoMap: Map<String, Any?> = mapOf(
            GuildDBDto::id.name to 0L,
            GuildDBDto::welcomeMessageChannelId.name to 1L,
            GuildDBDto::verificationChannelId.name to 2L,
            GuildDBDto::verifiedRoleId.name to 3L
        )

        val guildDBDto = guildDBDtoMap.toGuildDBDto()

        val guildDBDtoExpected = GuildDBDto(
            id = 0,
            welcomeMessageChannelId = 1,
            verificationChannelId = 2,
            verifiedRoleId = 3
        )

        assertEquals(expected = guildDBDtoExpected, actual = guildDBDto)
    }
}