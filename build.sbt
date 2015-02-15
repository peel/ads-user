name := "ads-user"
version := "1.0"
scalaVersion := "2.11.5"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV       = "2.3.9"
  val akkaStreamV = "1.0-M2"
  val specsV  = "2.4.15"
  Seq(
    "com.typesafe.akka" %% "akka-actor"                        % akkaV,
    "com.typesafe.akka" %% "akka-stream-experimental"          % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental"       % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental"            % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-testkit-experimental"    % akkaStreamV,
    "org.specs2"        %% "specs2-core"                       % specsV             %     "test"
  )
}

Revolver.settings