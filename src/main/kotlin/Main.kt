import core.common.Common
import dev.kord.core.Kord
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

@OptIn(PrivilegedIntent::class)
suspend fun main() {
    val kord = Kord(Common.BOT_TOKEN)

    kord.login {
        intents += Intent.MessageContent
    }
}