name := """Lola"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.lihaoyi" %% 		"upickle" 				% "0.3.6",
  "com.typesafe.slick"%%"slick"%"3.1.0"
  ,"org.slf4j"%"slf4j-nop"%"1.6.4"
  ,"com.h2database"%"h2"%"1.3.148"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
