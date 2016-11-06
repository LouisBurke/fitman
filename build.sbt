name := "Kronenbroject"

description := "A simple task manager for humans merged with Finatra project to create something hideous"

version := "1.0"

scalaVersion := "2.11.7"

lazy val versions = new {
  val finatra = "2.1.2"
  val logback = "1.1.3"
  val guice = "4.0"
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "Twitter Maven" at "https://maven.twttr.com"
)

libraryDependencies += "com.twitter.finatra" % "finatra-http_2.11" % versions.finatra
libraryDependencies += "com.twitter.finatra" % "finatra-jackson_2.11" % versions.finatra
libraryDependencies += "com.twitter.finatra" % "finatra-slf4j_2.11" % versions.finatra
libraryDependencies += "ch.qos.logback" % "logback-classic" % versions.logback

libraryDependencies += "com.twitter.finatra" % "finatra-http_2.11" % versions.finatra % "test"
libraryDependencies += "com.twitter.inject" % "inject-server_2.11" % versions.finatra % "test"
libraryDependencies += "com.twitter.inject" % "inject-app_2.11" % versions.finatra % "test"
libraryDependencies += "com.twitter.inject" % "inject-core_2.11" % versions.finatra % "test"
libraryDependencies += "com.twitter.inject" %% "inject-modules" % versions.finatra % "test"
libraryDependencies += "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test"
libraryDependencies += "com.twitter.finatra" % "finatra-jackson_2.11" % versions.finatra % "test"

libraryDependencies += "com.twitter.finatra" % "finatra-http_2.11" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter.inject" % "inject-server_2.11" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter.inject" % "inject-app_2.11" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter.inject" % "inject-core_2.11" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter.inject" % "inject-modules_2.11" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.google.inject.extensions" % "guice-testlib" % versions.guice % "test" classifier "tests"
libraryDependencies += "com.twitter.finatra" % "finatra-jackson_2.11" % versions.finatra % "test"  classifier "tests"

libraryDependencies += "com.typesafe.slick" %% "slick" % "3.1.1"
libraryDependencies += "com.h2database" % "h2" % "1.4.191"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
libraryDependencies += "org.specs2" %% "specs2" % "2.3.12" % "test"