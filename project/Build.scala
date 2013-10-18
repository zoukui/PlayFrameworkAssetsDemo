import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "PlayFrameworkAssetsDemo"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      filters,
      cache
    )

    lazy val staticversion = Project("staticversion", file("staticversion")) settings (
      libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.0"
    )

    val main = play.Project(appName, appVersion, appDependencies).dependsOn(
      staticversion
    ).settings(
      // Add your own project settings here
      resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
      resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"
    )

}
