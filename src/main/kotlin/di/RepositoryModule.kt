package di

import data.remote.guild.GuildDBApi
import data.repository.GuildRepositoryImpl
import data.repository.guild.channel.VerifyMemberRepositoryImpl
import data.repository.guild.channel.WelcomeMemberRepositoryImpl
import domain.remote.GuildDBApiImpl
import domain.repository.guild.GuildRepository
import domain.repository.guild.channel.VerifyMemberRepository
import domain.repository.guild.channel.WelcomeMemberRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val guildDBRepositoryModule = module {
    singleOf(::GuildDBApiImpl) bind GuildDBApi::class

    singleOf(::GuildRepositoryImpl) bind GuildRepository::class

    singleOf(::WelcomeMemberRepositoryImpl) bind WelcomeMemberRepository::class

    singleOf(::VerifyMemberRepositoryImpl) bind VerifyMemberRepository::class
}