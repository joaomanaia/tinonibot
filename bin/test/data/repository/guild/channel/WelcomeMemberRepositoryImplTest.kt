package data.repository.guild.channel

import data.remote.guild.GuildDBApi
import data.remote.guild.GuildDBDto
import dev.kord.common.entity.Snowflake
import domain.remote.FakeGuildDBApiImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class WelcomeMemberRepositoryImplTest {
    @Test
    fun `get welcome message channel returns 1`() = runTest {
        val guildDBApi = mockk<GuildDBApi>(relaxed = true)
        val guildRepository = WelcomeMemberRepositoryImpl(guildDBApi)

        coEvery {
            guildDBApi.getGuildDB(0)
        } returns GuildDBDto(0, 1)

        val welcomeMessageChannel = guildRepository.getChannelId(0)

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

        val guildRepository = WelcomeMemberRepositoryImpl(guildDBApi)

        guildRepository.updateChannel(0, Snowflake(2))

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

        val guildRepository = WelcomeMemberRepositoryImpl(guildDBApi)

        guildRepository.removeChannel(0)

        assertEquals(
            expected = null,
            actual = guildDBApi.getGuildDB(0).welcomeMessageChannelId
        )
    }
}