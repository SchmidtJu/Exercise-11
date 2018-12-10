package de.innfactory.eventsourcing

import akka.actor.ActorRef
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, pathEndOrSingleSlash}
import akka.util.Timeout
import de.innfactory.eventsourcing.IoTActor.{Ask, Hello}
import akka.pattern._

import scala.concurrent.{ExecutionContext, Future}


class IoTRouter(dataRef : ActorRef)(implicit executionContext: ExecutionContext, timeout: Timeout) {
  val route =
    pathEndOrSingleSlash {
      get {
        complete({
          (dataRef ? Ask()).asInstanceOf[Future[Hello]].map { result =>
            HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>${result.text}</h1>")
          }
        })
      }
    }

}