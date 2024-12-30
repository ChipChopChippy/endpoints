
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
       name := "endpoints"
  ).settings(
       libraryDependencies ++= List(
         "dev.zio" %% "zio" % "2.1.14",
         "dev.zio" %% "zio-http" % "3.0.1",
         "dev.zio" %% "zio-schema" % "1.5.0",
         "dev.zio" %% "zio-config-magnolia" % "4.0.2",
         "dev.zio" %% "zio-test" % "2.1.14" % Test,
         "dev.zio" %% "zio-test-sbt" % "2.1.14" % Test,
         "dev.zio" %% "zio-http-testkit" % "3.0.1" % Test
       )
  )