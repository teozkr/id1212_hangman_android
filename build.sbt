import android.SdkLayout

scalaVersion in ThisBuild := "2.11.12"

lazy val sharedSettings = Seq(
  libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "1.7.25",
    "org.slf4j" % "slf4j-android" % "1.7.25",
    // "com.google.inject" % "guice" % "4.1.0"
    "org.scalatest" %% "scalatest" % "3.0.4" % "test",
    "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
  ),
  javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil,
  exportJars := true
)

enablePlugins(AndroidApp)
android.useSupportVectors

versionCode := Some(1)
version := "0.1-SNAPSHOT"

instrumentTestRunner :=
  "android.support.test.runner.AndroidJUnitRunner"

platformTarget := "android-27"
minSdkVersion := "21"

libraryDependencies ++= Seq(
  "com.android.support" % "appcompat-v7" % "25.3.1",
  aar("com.android.support.constraint" % "constraint-layout" % "1.1.0-beta3"),
  "com.sdsmdg.harjot" % "vectormaster" % "1.1.1",
  "io.taig.android" %% "toolbelt" % "0.4.9",
  "com.android.support.test" % "runner" % "0.5" % "androidTest",
  "com.android.support.test.espresso" % "espresso-core" % "2.2.2" % "androidTest"
)

resolvers += "google support libraries" at "https://maven.google.com/"
resolvers += Resolver.jcenterRepo

lazy val proto = project.settings(sharedSettings: _*)
lazy val clientShared = project.settings(sharedSettings: _*).dependsOn(proto)
lazy val hangmanAndroid = project
  .in(file("."))
  .settings(sharedSettings: _*)
  .dependsOn(clientShared)
  .aggregate(proto, clientShared)
