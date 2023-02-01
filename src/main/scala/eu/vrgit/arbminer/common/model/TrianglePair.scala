package eu.vrgit.arbminer.common.model

import scala.math.BigDecimal.RoundingMode

case class TrianglePair(startCoin: Coin, basePair: CoinPair, middlePair: CoinPair, endPair: CoinPair) {
  private val INITIAL_AMOUNT: Int = 1
  private lazy val c1Coin = basePair.getOtherCoin(startCoin)
  private lazy val c2Coin = middlePair.getOtherCoin(c1Coin)
  private lazy val baseDirection = basePair.getDirection(startCoin)
  private lazy val middleDirection = middlePair.getDirection(c1Coin)
  private lazy val endDirection = endPair.getDirection(c2Coin)

  private lazy val initialBaseValue: BigDecimal = basePair.lastPriceInfo.initialTransfer(baseDirection, INITIAL_AMOUNT, basePair.scale)
  private lazy val initialMiddleValue: BigDecimal = middlePair.lastPriceInfo.initialTransfer(middleDirection, initialBaseValue, middlePair.scale)
  private lazy val initialEndValue: BigDecimal = endPair.lastPriceInfo.initialTransfer(endDirection, initialMiddleValue, endPair.scale)
  private lazy val initialProfit: BigDecimal = initialEndValue - INITIAL_AMOUNT
  private lazy val initialProfitPercentage: BigDecimal = ((initialProfit / INITIAL_AMOUNT) * 100).setScale(4)

  def isPotentiallyProfitable: Boolean = initialProfit.compare(0) >= 0

  def getInitialCalculation: Calculation =
    Calculation(1,
      baseDirection,
      initialBaseValue,
      middleDirection,
      initialMiddleValue,
      endDirection,
      initialEndValue,
      initialProfit,
      initialProfitPercentage)

  def getDepthCalculation(amount: BigDecimal): Calculation = {
    val depthBaseValue: BigDecimal = basePair.lastPriceInfo.initialTransfer(baseDirection, amount, basePair.scale)
    val depthMiddleValue: BigDecimal = middlePair.lastPriceInfo.initialTransfer(middleDirection, depthBaseValue, middlePair.scale)
    val depthEndValue: BigDecimal = endPair.lastPriceInfo.initialTransfer(endDirection, depthMiddleValue, endPair.scale)
    val depthProfit: BigDecimal = depthEndValue - amount
    val depthProfitPercentage: BigDecimal = ((depthProfit / amount) * 100).setScale(4)

    Calculation(amount,
      baseDirection,
      depthBaseValue,
      middleDirection,
      depthMiddleValue,
      endDirection,
      depthEndValue,
      depthProfit,
      depthProfitPercentage)
  }


  def getParticipatingCoins: String = {
    String.join("-",
      startCoin,
      c1Coin,
      c2Coin,
      startCoin
    )
  }
}
