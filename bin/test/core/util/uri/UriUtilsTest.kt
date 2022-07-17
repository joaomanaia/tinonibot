package core.util.uri

import java.net.URI
import kotlin.test.Test
import kotlin.test.assertEquals

internal class UriUtilsTest {
    @Test
    fun `string to uri test`() {
        val string = "test"
        val uri = string.toUri()

        assertEquals(
            expected = URI("test"),
            actual = uri
        )
    }
}