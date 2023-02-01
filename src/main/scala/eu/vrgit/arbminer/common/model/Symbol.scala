package eu.vrgit.arbminer.common.model

import eu.vrgit.arbminer.common.model.TransactionDirection.{B2Q, Q2B}

case class Symbol(base: Coin, quote: Coin) {
  def getSymbol: String = String.join("/", base, quote)
}
