package register

interface Register {

    fun acceptCurrency(currency: Array<Int>): Array<Int>

    fun removeCurrency(currency: Array<Int>): Array<Int>

    fun showTotal(): Array<Int>

    fun giveChange(amount: Int): Array<Int>


}