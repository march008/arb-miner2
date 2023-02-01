package eu.vrgit.arbminer.common.model

import eu.vrgit.arbminer.common.model.TransactionDirection.{B2Q, Q2B}
import eu.vrgit.arbminer.common.model.CommonType

import java.math.MathContext
import scala.math.BigDecimal.RoundingMode

case class CoinPair(symbol: Symbol,
                    lastPriceInfo: LastPriceInfo,
                    orderBook: OrderBook,
                    scale:Int = 16) {

  def getDirection(baseCoin: Coin): TransactionDirection = if (symbol.base == baseCoin) B2Q else Q2B


  def isFiatLinked: Boolean =
    CommonType.FIAT_LINKED_COINS.contains(symbol.base) || CommonType.FIAT_LINKED_COINS.contains(symbol.quote)

  def getFiatLinkedCoin: Option[Coin] = {
    if (isFiatLinked)
      Some(
        if (CommonType.FIAT_LINKED_COINS.contains(symbol.base)) symbol.base else symbol.quote)
    else
      None
  }

  def getNonFiatLinkedCoin: Option[Coin] = getFiatLinkedCoin.map(getOtherCoin)


  def getOtherCoin(coin: Coin): Coin = if (symbol.base == coin) symbol.quote else symbol.base

  def containsCoin(coin: Coin): Boolean = symbol.base == coin || symbol.quote == coin
}