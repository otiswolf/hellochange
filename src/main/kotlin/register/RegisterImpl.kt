package register

import exceptions.NotEnoughMoneyException

class RegisterImpl(private var registerValues: Array<Int> = arrayOf(0, 0, 0, 0, 0) ): Register {

    private var total = getTotal()

    override fun acceptCurrency(currency: Array<Int>): Array<Int> {

        for (i in 0..4) {
            registerValues[i] += currency[i]
        }

        total = getTotal()

        return this.showTotal()
    }

    override fun removeCurrency(currency: Array<Int>): Array<Int> {

        //check if register contains enough money
        for (i in 0..4) {
            if (registerValues[i] < currency[i])  {
                when (i) {
                    0 -> throw NotEnoughMoneyException("Not enough $20 bills")
                    1 -> throw NotEnoughMoneyException("Not enough $10 bills")
                    2 -> throw NotEnoughMoneyException("Not enough $5 bills")
                    3 -> throw NotEnoughMoneyException("Not enough $2 bills")
                    4 -> throw NotEnoughMoneyException("Not enough $1 bills")
                }
            }
        }

        //remove money
        for (i in 0..4) {
            registerValues[i] -= currency[i]
        }

        total = getTotal()

        return this.showTotal()
    }

    override fun showTotal(): Array<Int> {
        return arrayOf(total) + registerValues
    }

    override fun giveChange(amount: Int): Array<Int> {

        var differenceRemaining = amount
        val returnValues = arrayOf(0, 0, 0, 0, 0)

        for (i in 0..4) {
            when (i) {
                0 -> {
                    while(true) {
                        if (differenceRemaining < 20 || registerValues[i] <1) break
                        differenceRemaining -= 20
                        registerValues[i] -= 1
                        returnValues[i] += 1
                    }
                }
                1 -> {
                    while(true) {
                        if (differenceRemaining < 10 || registerValues[i] <1) break
                        differenceRemaining -= 10
                        registerValues[i] -= 1
                        returnValues[i] += 1
                    }
                }
                2 -> {
                    while(true) {
                        if (differenceRemaining < 5 || registerValues[i] <1) break
                        differenceRemaining -= 5
                        registerValues[i] -= 1
                        returnValues[i] += 1
                    }
                }
                3 -> {
                    while(true) {
                        if (differenceRemaining < 2 || registerValues[i] <1) break
                        differenceRemaining -= 2
                        registerValues[i] -= 1
                        returnValues[i] += 1
                    }
                }
                4 -> {
                    while(true) {
                        if (differenceRemaining < 1 || registerValues[i] <1) break
                        differenceRemaining -= 1
                        registerValues[i] -= 1
                        returnValues[i] += 1
                    }
                }

            }
        }

        if (differenceRemaining == 0) return returnValues

        throw NotEnoughMoneyException("Cannot make change for given amount: $amount")
    }

    internal fun getTotal(): Int {
        return registerValues[4] + registerValues[3]*2 + registerValues[2]*5 + registerValues[1]*10 + registerValues[0]*20
    }

}