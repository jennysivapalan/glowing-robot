name := "Project name"

scalaVersion := "2.10.0"

seq(webSettings :_*)

libraryDependencies ++= Seq(
    "org.scalatra" % "scalatra-json_2.10" % "2.2.0",
    "org.scalatra" % "scalatra_2.10" % "2.2.0",
    "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided" artifacts (Artifact("javax.servlet", "jar", "jar")),
    "org.json4s" % "json4s-native_2.10" % "3.2.4"
    )
