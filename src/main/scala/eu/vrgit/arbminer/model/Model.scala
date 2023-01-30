package eu.vrgit.arbminer.model

import eu.vrgit.arbminer.model.TransactionDirection.{B2Q, Q2B}

import java.time.LocalDateTime

def bigDecimalCheck(in: BigDecimal) = in.signum >= 0

val fiatLinkedCoins = Seq("BUSD", "USDT", "EURT", "BGBP", "GBPT")

opaque type Coin <: String = String
opaque type Base <: Coin = Coin
opaque type Quote <: Coin = Coin
opaque type Price <: BigDecimal = BigDecimal
opaque type Quantity <: BigDecimal = BigDecimal
opaque type LastAsk <: BigDecimal = BigDecimal
opaque type LastBid <: BigDecimal = BigDecimal
opaque type Timestamp <: LocalDateTime = LocalDateTime

case class Symbol(base: Base, quote: Quote) {
  def getSymbol: String = String.join("/", base, quote)

  def isFiatLinked: Boolean =
    fiatLinkedCoins.contains(base) || fiatLinkedCoins.contains(quote)

  def getFiatLinkedCoin: Option[String] = {
    if (isFiatLinked)
      Some(
        if (fiatLinkedCoins.contains(base)) base else quote)
    else
      None
  }
}

object Base {
  def apply(base: String): Base = {
    assert(base.nonEmpty)
    base
  }
}

object Quote {
  def apply(quote: String): Quote = {
    assert(quote.nonEmpty)
    quote
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

case class Order(price: Price, quantity: Quantity)

case class OrderBook(timeStamp: Timestamp, bids: Seq[Order], asks: Seq[Order])

case class LastPriceInfo(lastAsk: LastAsk, lastBid: LastBid)

case class CoinPair(symbol: Symbol,
                    base: Base,
                    quote: Quote,
                    lastPriceInfo: LastPriceInfo,
                    orderBook: OrderBook) {

  def initialTransfer(direction: TransactionDirection, quantity: Quantity): BigDecimal = {
    direction match {
      case B2Q => quantity * this.lastPriceInfo.lastBid
      case Q2B => quantity * (BigDecimal(1) / this.lastPriceInfo.lastAsk)
    }
  }

  def getOtherCoin(coin: Coin): Coin = if (base == coin) quote else base

  def containsCoin(coin:Coin):Boolean = base == coin || quote == coin


}
