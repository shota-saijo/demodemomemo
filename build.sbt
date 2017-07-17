name := """demodemomemo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

PlayKeys.externalizeResources := false
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa
)

libraryDependencies ++= Seq(
    "org.projectlombok" % "lombok" % "1.16.10",
    "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
)
