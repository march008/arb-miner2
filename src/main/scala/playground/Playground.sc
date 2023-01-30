import eu.vrgit.arbminer.model._

val symbol:Symbol = Symbol(Base("USDT"),Quote("BTC"))
symbol.base
symbol.getSymbol
val peti = symbol.isFiatLinked

symbol.getFiatLinkedCoin.get


