ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

lazy val root = (project in file("."))
  .settings(
    name := "zio-pets-learn"
  )

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "2.1-RC1",
  "io.getquill" %% "quill-zio" % "4.8.0",
  "io.getquill" %% "quill-jdbc-zio" % "4.8.1",
  "com.h2database" % "h2" % "2.2.224",

  "dev.zio" %% "zio-schema"          % "0.4.15",
  "dev.zio" %% "zio-schema-derivation" % "0.4.15",

  "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided"

)
