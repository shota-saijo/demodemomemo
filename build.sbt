name := """demodemomemo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

PlayKeys.externalizeResources := false
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.projectlombok" % "lombok" % "1.16.10",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.6.0",
  "org.webjars" % "jquery" % "3.2.1",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "org.webjars.bower" % "bootstrap-validator" % "0.11.9",
  "org.webjars" % "Eonasdan-bootstrap-datetimepicker" % "4.17.43",
  "org.webjars.bower" % "iCheck" % "1.0.2"
)

dependencyOverrides += "org.webjars" % "jquery" % "3.2.1"
