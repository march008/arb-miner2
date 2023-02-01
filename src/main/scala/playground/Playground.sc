import eu.vrgit.arbminer.common.model
import eu.vrgit.arbminer.common.model.Symbol
import eu.vrgit.arbminer.common.model.{Base, Quote}

val symbol = Symbol(Coin("USDT"), Coin("BTC"))
val symbol2 = Symbol(Coin("USDT"), Coin("BTC"))

val opt = symbol.getFiatLinkedCoin
//opt.fold(None)(symbol.getOtherCoin(_))
symbol.getNonFiatLinkedCoin


1+1+2

