package di

import data.remote.guild.GuildDBApi
import data.repository.GuildRepositoryImpl
import domain.remote.GuildDBApiImpl
import domain.repository.guild.GuildRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val guildDBRepositoryModule = module {
    singleOf(::GuildDBApiImpl) bind GuildDBApi::class

    singleOf(::GuildRepositoryImpl) bind GuildRepository::class
}