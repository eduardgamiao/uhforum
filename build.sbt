name := "uhforum"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.h2database" % "h2" % "1.3.167"
)     

play.Project.playJavaSettings
