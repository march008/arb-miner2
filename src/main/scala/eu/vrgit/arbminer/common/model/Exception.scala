package eu.vrgit.arbminer.common.model

sealed trait Exception {
  def getMessage: String
}

