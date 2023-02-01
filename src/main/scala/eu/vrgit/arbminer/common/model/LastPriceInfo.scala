package eu.vrgit.arbminer.common.model

import eu.vrgit.arbminer.common.model.TransactionDirection.{B2Q, Q2B}

import scala.math.BigDecimal.RoundingMode

case class LastPriceInfo(lastAsk: LastAsk, lastBid: LastBid) {
  def initialTransfer(direction: TransactionDirection, amount: BigDecimal, scale: Int): BigDecimal = {
    val result = direction match {
      case B2Q => amount * lastBid
      case Q2B => amount * (BigDecimal(1) / lastAsk)
    }
    result.setScale(scale, RoundingMode.HALF_UP)
  }
}
