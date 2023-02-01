package eu.vrgit.arbminer.common.model

case class Calculation(baseQuantity: BigDecimal,
                       baseDirection: TransactionDirection,
                       baseConversion: BigDecimal,
                       middleDirection: TransactionDirection,
                       middleConversion: BigDecimal,
                       endDirection: TransactionDirection,
                       endConversion: BigDecimal,
                       profit: BigDecimal,
                       percentage: BigDecimal)
