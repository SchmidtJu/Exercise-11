package de.innfactory.eventsourcing

import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.{ByteString, Timeout}
import org.scalatest.{Matchers, WordSpec}
import scala.concurrent.duration._


class IoTRouterTest extends WordSpec with Matchers with ScalatestRouteTest {

  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(5 seconds)

  val iotActor = system.actorOf(IoTActor.props, IoTActor.name)
  val ioTRouter = new IoTRouter(iotActor)
  val routes = ioTRouter.route

  "HTTP Server" should {
    "respond a greeting on the base path" in {
      Get() ~> routes ~> check {
        status.isSuccess() shouldEqual true
      }
    }

    "add a temperatur value for sensorx in device 1" in {
      val jsonRequest = ByteString(
        s"""
           |{
           |    "value":"24"
           |}
        """.stripMargin)

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/devices/1/temperature",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> routes ~> check {
        status.isSuccess() shouldEqual true
      }
    }

    "add a preasure value for sensorx in device 1" in {
      val jsonRequest = ByteString(
        s"""
           |{
           |    "value":"24"
           |}
        """.stripMargin)

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/devices/1/preasure",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> routes ~> check {
        status.isSuccess() shouldEqual true
      }
    }

    "add a pressure value for sensorx in device 1" in {
      val jsonRequest = ByteString(
        s"""
           |{
           |    "value":"24"
           |}
        """.stripMargin)

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/devices/1/pressure",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))

      postRequest ~> routes ~> check {
        status.isSuccess() shouldEqual true
      }
    }

    "get all data as json for sensor 1 without cqrs" in {
      //jourjob
      fail()
    }

    "get all data from cqrs materialized view" in {
      cancel()
    }
  }
}
