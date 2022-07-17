package core.util.database

import io.supabase.postgrest.PostgrestDefaultClient

interface DatabaseUtil {
    val postgrestClient: PostgrestDefaultClient
}