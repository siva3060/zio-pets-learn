ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

lazy val root = (project in file("."))
  .settings(
    name := "zio-pets-learn"
  )

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "2.1-RC1",
  "io.getquill" %% "quill-zio" % "3.9.0",
  "io.getquill" %% "quill-jdbc-zio" % "3.9.0",
)
