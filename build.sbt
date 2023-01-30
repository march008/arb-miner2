ThisBuild / scalaVersion     := "3.2.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "eu.vrgit"
ThisBuild / organizationName := "vrgit"

val zioVersion="2.0.6"

lazy val root = (project in file("."))
  .settings(
    name := "arb-miner2",
    libraryDependencies ++= Seq(
      "dev.zio"               %% "zio"              % zioVersion,
      "dev.zio"               %% "zio-kafka"        % zioVersion,
      "dev.zio"               %% "zio-http"         % "0.0.4",
      "dev.zio"               %% "zio-json"         % "0.4.2",
      "com.github.ghostdogpr" %% "caliban"          % "2.0.2",
      "com.github.ghostdogpr" %% "caliban-zio-http" % "2.0.2",

      "dev.zio" %% "zio-test" % zioVersion,
      "dev.zio" %% "zio-test-sbt" % zioVersion,
      "dev.zio" %% "zio-test-junit" % zioVersion
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
