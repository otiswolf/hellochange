import exceptions.NotEnoughMoneyException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import register.RegisterImpl

internal class registerImplTest {


    @Test
    fun `test acceptCurrency returns correct list of values on empty register`() {
        val register = RegisterImpl()

        val testResults = register.acceptCurrency(arrayOf(1, 2, 3, 4, 5))
        assertTrue(arrayOf(68, 1, 2, 3, 4, 5).contentEquals(testResults))
    }

    @Test
    fun `test acceptCurrency returns correct list of values on non-empty register`() {
        val register = RegisterImpl(arrayOf(1,1,1,1,1))

        val testResults = register.acceptCurrency(arrayOf(1, 2, 3, 4, 5))
        assertTrue(arrayOf(106, 2, 3, 4, 5, 6).contentEquals(testResults))
    }

    @Test
    fun `test removeCurrency returns correct list of values on non-empty register`() {
        val register = RegisterImpl(arrayOf(2, 3, 4, 5, 6))

        val testResults = register.removeCurrency(arrayOf(1, 1, 1, 1, 1))
        assertTrue(arrayOf(68, 1, 2, 3, 4, 5).contentEquals(testResults))
    }

    @Test
    fun `test removeCurrency throws NotEnoughMoneyException when trying to overdraft money`() {
        val register = RegisterImpl(arrayOf(2, 3, 4, 5, 0))

        assertThrows(NotEnoughMoneyException::class.java) {
            register.removeCurrency(arrayOf(1, 1, 1, 1, 1))
        }
    }

    @Test
    fun `test showTotal shows correct amount of money in the register`() {
        val register = RegisterImpl(arrayOf(1, 2, 3, 4, 5))

        val testResults = register.showTotal()
        assertTrue(arrayOf(68, 1, 2, 3, 4, 5).contentEquals(testResults))
    }

    //Test ways to get change for $8 from different combinations of $13
    @Test
    fun `test giveChange gives $8 of change from $13 in register`() {
        var register = RegisterImpl(arrayOf(0, 0, 0, 0, 13))

        var testResults = register.giveChange(8)
        assertTrue(arrayOf(0, 0, 0, 0, 8).contentEquals(testResults))

        register = RegisterImpl(arrayOf(0, 0, 1, 2, 4))
        testResults = register.giveChange(8)
        assertTrue(arrayOf(0, 0, 1, 1, 1).contentEquals(testResults))

        register = RegisterImpl(arrayOf(0, 1, 0, 0, 3))
        assertThrows(NotEnoughMoneyException::class.java) {
            register.giveChange(8)
        }
    }

}