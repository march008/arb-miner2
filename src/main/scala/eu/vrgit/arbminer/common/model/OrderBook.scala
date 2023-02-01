package eu.vrgit.arbminer.common.model

import eu.vrgit.arbminer.common.model.TransactionDirection.{B2Q, Q2B}

import scala.annotation.tailrec
import scala.math.BigDecimal.RoundingMode

case class OrderBook(timeStamp: Timestamp, bids: Seq[Order], asks: Seq[Order]) {
  def depthTransfer(direction: TransactionDirection, amount: BigDecimal, scale: Int): BigDecimal = {
    var result: BigDecimal = 0

    @tailrec
    def calcB2Q(bids: Seq[Order], accumulator: BigDecimal, remainingQuantity: BigDecimal, count: Int): BigDecimal =
      if (count > CommonType.ORDER_BOOK_LIMIT)
        return -bids.map(_.quantity).sum
      if (bids.head.quantity.compare(remainingQuantity) >= 0)
        accumulator + (bids.head.price * remainingQuantity)
      else
        calcB2Q(bids.tail,
          accumulator + (bids.head.price * bids.head.quantity),
          remainingQuantity - bids.head.quantity,
          count + 1)

    @tailrec
    def calcQ2B(asks: Seq[Order], accumulator: BigDecimal, remainingQuantity: BigDecimal, count: Int): BigDecimal =
      if (count > CommonType.ORDER_BOOK_LIMIT)
        return -asks.map(_.quantity).sum

      val availableQuantity = asks.head.quantity * asks.head.price
      if (availableQuantity.compare(remainingQuantity) >= 0)
        accumulator + (remainingQuantity / asks.head.price)
      else
        calcQ2B(asks.tail,
          accumulator + asks.head.quantity,
          remainingQuantity - availableQuantity,
          count + 1)

    result =
      direction match {
        case B2Q =>
          calcQ2B(bids, BigDecimal(0), amount, 0)
        case Q2B =>
          calcQ2B(asks, BigDecimal(0), amount, 0)
      }
    result.setScale(scale, RoundingMode.HALF_UP)
  }
}

