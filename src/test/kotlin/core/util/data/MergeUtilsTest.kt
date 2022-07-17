package core.util.data

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MergeUtilsTest {
    data class DataClassTest(
        val a: String?,
        val b: Int?
    )

    object EmptyClassTest

    @Test
    fun `a merge b classes test`() {
        val a = DataClassTest(null, 1)
        val b = DataClassTest("b", 2)

        val c = a merge b

        assertEquals(
            expected = DataClassTest("b", 1),
            actual = c
        )
    }

    @Test
    fun `b merge a classes test`() {
        val a = DataClassTest(null, 1)
        val b = DataClassTest("b", 2)

        val c = b merge a

        assertEquals(
            expected = DataClassTest("b", 2),
            actual = c
        )
    }

    @Test
    fun `a merge b classes test, throw IllegalArgumentException (must have primary constructor)`() {
        val a = EmptyClassTest
        val b = EmptyClassTest

        val exception = assertThrows<IllegalArgumentException> {
            a merge b
        }
        assertEquals(
            expected = "merge type must have a primary constructor",
            actual = exception.localizedMessage
        )
    }
}