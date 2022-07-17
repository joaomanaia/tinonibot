package core.util.database

import io.supabase.postgrest.PostgrestDefaultClient
import java.net.URI

class DatabaseUtilImpl(
    private val supabaseUri: URI,
    private val supabaseApiKey: String,
) : DatabaseUtil {
    override val postgrestClient = PostgrestDefaultClient(
        uri = supabaseUri,
        headers = mapOf(
            "Authorization" to supabaseApiKey,
            "apikey" to supabaseApiKey
        )
    )
}