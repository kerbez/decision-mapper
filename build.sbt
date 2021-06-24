name := "decision-mapper"

version := "0.1"

scalaVersion := "2.12.12"

lazy val circeVersion     = "0.13.0"

libraryDependencies ++= Seq(
  "io.circe"  %% "circe-core"     % circeVersion,
  "io.circe"  %% "circe-generic"  % circeVersion,
  "io.circe"  %% "circe-parser"   % circeVersion
)
