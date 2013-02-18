package knoldus

import com.typesafe.config.ConfigFactory
import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props

object LookupApplication extends App {
  val configString = """
           akka {actor {
                provider = "akka.remote.RemoteActorRefProvider"
              }
      remote {netty {hostname = "127.0.0.1"}}}
          akka {remote.netty.port = 2557 }
        """
  val configuration = ConfigFactory.parseString(configString)
  val system = ActorSystem("Remote", ConfigFactory.load(configuration))
  val remoteActorReference = system.actorFor("akka://RemoteApplication@" + "127.0.0.1" + ":" + 2559 + "/user/remote")
  remoteActorReference ! "Hello from Knolders."
  println("I sent a Message")
}
