package knoldus

import com.typesafe.config.ConfigFactory
import akka.actor._

object RemoteApplication extends App {

  val configString = """akka {actor {
                provider = "akka.remote.RemoteActorRefProvider" }
             remote {netty { hostname = "127.0.0.1"}}}
              akka {
             remote.netty.port = 2559}"""
  val configuration = ConfigFactory.parseString(configString)
  val remoteSystem = ActorSystem("RemoteApplication", ConfigFactory.load(configuration))
  val remoteActorRef = remoteSystem.actorOf(Props[RemoteActor], "remote")
}

class RemoteActor extends Actor {
  def receive = {
    case msg: String =>
      println(msg)
  }
}

