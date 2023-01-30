package eu.vrgit.arbminer.model

import java.time.LocalDateTime

opaque type Symbol = String;
object Symbol{
}
opaque type Base = String;
opaque type Quote = String;

case class Order(price: BigDecimal, quantity: BigDecimal)

case class OrderBook(timeStamp: LocalDateTime, bids: Seq[Order], asks: Seq[Order])

case class LastPriceInfo(lastAsk: BigDecimal, lastBid: BigDecimal)

case class CoinPair(symbol: Symbol,
                    base: Base,
                    quote: Quote,
                    lastPriceInfo: LastPriceInfo,
                    orderBook: OrderBook){


}
