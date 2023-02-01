package eu.vrgit.arbminer.common.model

import TransactionDirection.{B2Q, Q2B}

import java.time.LocalDateTime

object CommonType{
  val ORDER_BOOK_LIMIT = 20
  val FIAT_LINKED_COINS: Seq[String] = Seq("BUSD", "USDT", "EURT", "BGBP", "GBPT")
}
private def bigDecimalCheck(in: BigDecimal) = in.signum >= 0


opaque type Coin <: String = String
opaque type Price <: BigDecimal = BigDecimal
opaque type Quantity <: BigDecimal = BigDecimal
opaque type LastAsk <: BigDecimal = BigDecimal
opaque type LastBid <: BigDecimal = BigDecimal
opaque type Timestamp <: LocalDateTime = LocalDateTime

object Coin{
  def apply(coin: String): Coin = {
    assert(coin.nonEmpty)
    coin
  }
}

object Price {
  def apply(price: BigDecimal): Price = {
    assert(bigDecimalCheck(price))
    price
  }
}

object Quantity {
  def apply(quantity: BigDecimal): Quantity = {
    assert(bigDecimalCheck(quantity))
    quantity
  }
}

object LastAsk {
  def apply(quantity: BigDecimal): Quantity = {
    assert(bigDecimalCheck(quantity))
    quantity
  }
}

object LastBid {
  def apply(quantity: BigDecimal): Quantity = {
    assert(bigDecimalCheck(quantity))
    quantity
  }
}






