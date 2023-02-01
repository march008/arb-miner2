package eu.vrgit.arbminer.common.model

import java.util.UUID

case class ArbitrageOpportunity(timestamp: Timestamp,
                                trianglePair: TrianglePair,
                                initialCalculation: Calculation,
                                depthCalculation: Calculation) {
  val id: UUID = UUID.randomUUID()
  //TODO
}
