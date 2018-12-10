package de.innfactory.eventsourcing

import java.time.Instant

import akka.actor.{Actor, Props}
import de.innfactory.eventsourcing.IoTActor._

object IoTActor {


  /* Useful functions */

  def name = "IoTSensorData"

  def props = Props(new IoTActor)

  def now = Instant.now.getEpochSecond

  /* State */
  case class IoTSensordata(data: Vector[SensorData] = Vector.empty)

  trait SensorData {
    val deviceId: Long
  }

  case class Temperature(override val deviceId: Long, sensorName: String, timestamp: Long, data: Double) extends SensorData

  case class Pressure(override val deviceId: Long, sensorName: String, timestamp: Long, data: Double) extends SensorData

  /* Commands */
  sealed trait Cmd {
    val deviceId: Long
  }

  case class Ask(override val deviceId: Long = 0L) extends Cmd

  /* Events */
  sealed trait Evt {
    val deviceId: Long
  }

  /* Other messages */
  case class Hello(text: String)


}

class IoTActor extends Actor {

  private var state = IoTSensordata()

  //sample to add state = state.data :+ Temperature(1, "sensor1", now, 24.3)

  override def receive: Receive = {
    case _: Ask => sender ! Hello("OK - Nothing implemented! ;-)")
  }
}
