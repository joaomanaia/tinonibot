package data.repository

import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto
import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.Member
import domain.remote.FakeGuildDBApiImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class GuildRepositoryImplTest {
    @Test
    fun `get welcome message channel returns 1`() = runTest {
        val guildDBApi = mockk<GuildDBApi>(relaxed = true)
        val guildRepository = GuildRepositoryImpl(guildDBApi)

        coEvery {
            guildDBApi.getGuildDB(0)
        } returns GuildDBDto(0, 1)

        val welcomeMessageChannel = guildRepository.getWelcomeMessageChannel(0)

        assertEquals(
            expected = Snowflake(1),
            actual = welcomeMessageChannel
        )

        coVerify(exactly = 1) { guildDBApi.getGuildDB(0) }
    }

    @Test
    fun `update welcome message channel`() = runTest {
        val guildDBApi = FakeGuildDBApiImpl()
        guildDBApi.insertGuildToDB(
            GuildDBDto(0, 1)
        )

        val guildRepository = GuildRepositoryImpl(guildDBApi)

        guildRepository.updateWelcomeMessageChannel(0, Snowflake(2))

        assertEquals(
            expected = 2,
            actual = guildDBApi.getGuildDB(0).welcomeMessageChannelId
        )
    }

    @Test
    fun `remove welcome message channel`() = runTest {
        val guildDBApi = FakeGuildDBApiImpl()
        guildDBApi.insertGuildToDB(
            GuildDBDto(0, 1)
        )

        val guildRepository = GuildRepositoryImpl(guildDBApi)

        guildRepository.removeWelcomeMessageChannel(0)

        assertEquals(
            expected = null,
            actual = guildDBApi.getGuildDB(0).welcomeMessageChannelId
        )
    }
}