name := """demodemomemo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

PlayKeys.externalizeResources := false
TwirlKeys.templateImports += "models.entity._"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.mindrot" % "jbcrypt" % "0.3m"
)

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.6.0",
  "org.webjars" % "jquery" % "3.2.1",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "org.webjars.bower" % "bootstrap-validator" % "0.11.9",
  "org.webjars" % "Eonasdan-bootstrap-datetimepicker" % "4.17.43",
  "org.webjars" % "prettyCheckable" % "1.3",
  "org.webjars" % "marked" % "0.3.2-1",
  "org.webjars.npm" % "bootstrap-markdown" % "2.9.0",
  "org.webjars" % "bootstrap-select" % "1.12.2",
  "org.webjars.npm" % "autosize" % "4.0.0"
)

dependencyOverrides += "org.webjars" % "jquery" % "3.2.1"
