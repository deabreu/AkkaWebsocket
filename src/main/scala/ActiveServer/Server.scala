package ActiveServer

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.HttpApp
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory


object Server extends HttpApp {
  def routes =
    path(""){
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "teste de linguaguem"))
    } ~
    path(Segment) {
      inputString: String =>
        get {
          complete(
            HttpEntity(ContentTypes.`text/html(UTF-8)`, s"<h1>The Return of the String</h1><p>$inputString</p>")
          )
        }
    } ~
    path (""/ "hello"){
      (get & parameter('name)){
          name =>
        complete (
          HttpEntity(ContentTypes.`text/html(UTF-8)`,s"<h1 style=${'"'}text-align:center;${'"'}> Hello </h1><p>$name</p>")
          )
        }
      }
}

object MyRunner extends App{

  Server.startServer("0.0.0.0",8088, ServerSettings(ConfigFactory.load))
}

class CheckInner {
  var count : Integer = 0
  def getCount : Integer = count
  def increase : Unit = {
    count = count + 1
  }
}