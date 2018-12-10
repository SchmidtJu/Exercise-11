package de.innfactory.eventsourcing

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.duration._
import scala.io.StdIn

// for serialisation or deseralization use circe import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object Server {

  def main(args: Array[String]) {

    implicit val system = ActorSystem("iotsystem")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher
    implicit val timeout = Timeout(5 seconds)

    val iotActor = system.actorOf(IoTActor.props, IoTActor.name)
    val ioTRouter = new IoTRouter(iotActor)


    val bindingFuture = Http().bindAndHandle(ioTRouter.route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }


}
