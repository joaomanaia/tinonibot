import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

private class Math {
    fun add(a: Int, b: Int) = a + b
}