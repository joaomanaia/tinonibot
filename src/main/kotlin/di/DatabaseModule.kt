package di

import core.util.database.DatabaseUtil
import org.koin.dsl.module
import core.util.database.DatabaseUtilImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import java.net.URI

fun getDatabaseModule(
    supabaseUri: URI,
    supabaseApiKey: String,
) = module {
    single {
        DatabaseUtilImpl(
            supabaseUri = supabaseUri,
            supabaseApiKey = supabaseApiKey
        )
    } bind DatabaseUtil::class
}