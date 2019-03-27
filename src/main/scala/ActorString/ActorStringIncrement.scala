package ActorString

import akka.actor.{Actor, ActorLogging, Props}


object ActorString{
  case class MyString(val input:String)
}

object ActorStringIncrement {
  var count:Integer = 0

  def Increment = count+= 1

  def props = Props[ActorStringIncrement]

  def apply: ActorStringIncrement = new ActorStringIncrement()
}


class ActorStringIncrement extends Actor with ActorLogging {

  import ActorString.MyString

  def receive = {
    case gotIt:MyString =>
      ActorStringIncrement.Increment
      sender() ! "Hello"
  }

}
