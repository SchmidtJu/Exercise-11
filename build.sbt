organization := "de.innfactory.fhrosenheim"
name := "event-sourcing"

scalaVersion := "2.12.6"
libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http"   % "10.1.5",
    "com.typesafe.akka" %% "akka-stream" % "2.5.19",
    "com.typesafe.akka"          %% "akka-persistence" % "2.5.19",
    "org.iq80.leveldb"            % "leveldb"          % "0.7",
    "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8",
    "io.circe" %% "circe-core" % "0.10.0",
    "io.circe" %% "circe-generic" % "0.10.0",
    "io.circe" %% "circe-parser" % "0.10.0",
    "de.heikoseeberger" %% "akka-http-circe" % "1.22.0",
    "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5" % "test",
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

// for persistence query add this
// libraryDependencies += "com.typesafe.akka" %% "akka-persistence-query" % "2.5.19"

// for rdbc
// http://docs.api.rdbc.io/scala/gettingstarted/
//  "io.rdbc" %% "rdbc-api-scala" % "0.0.82"
//  "io.rdbc.pgsql" %% "pgsql-transport-netty" % "0.4.0.1"