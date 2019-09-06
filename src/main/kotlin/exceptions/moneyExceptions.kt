package exceptions

class CurrencyNotSupportedException(message: String): Exception(message)
class NotEnoughMoneyException(message: String): Exception(message)
class InvalidInputException(): Exception()