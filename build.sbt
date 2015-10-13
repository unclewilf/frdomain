name := "frdomain"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
    "org.scala-lang"  % "scala-library" % "2.11.7",
    "org.scala-lang"  % "scala-reflect" % "2.11.7",
    "org.scalaz"     %% "scalaz-core"   % "7.1.4",
    "org.scalatest"  %% "scalatest"     % "2.2.5"  % Test,
    "org.scalacheck" %% "scalacheck"    % "1.12.5" % Test
)
    