name := "Sample_Slick"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.postgresql" % "postgresql" % "9.4.1212",
  "mysql" % "mysql-connector-java" % "5.1.6",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.h2database" % "h2" % "1.4.193"
)
//libraryDependencies += "org.scoverage" % "scalac-scoverage-plugin_2.11" % "1.3.0"
