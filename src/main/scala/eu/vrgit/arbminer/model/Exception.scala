package eu.vrgit.arbminer.model

sealed trait Exception {
  def getMessage: String;
}

