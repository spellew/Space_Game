package webserver

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.ws._
import akka.stream.scaladsl._
import akka.http.scaladsl._
import akka.stream._
import akka.actor._


object GameServer {


  implicit val system: ActorSystem = ActorSystem("system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()


  def flow: Flow[Message, Message, Any] = Flow[Message].mapConcat {
    case tm: TextMessage =>
      TextMessage(Source.single("Hello, from Scala, ") ++ tm.textStream ++ Source.single("!")) :: Nil

    case bm: BinaryMessage =>
      bm.dataStream.runWith(Sink.ignore)
      Nil
  }


  def main(args: Array[String]): Unit = {

    val dir = System.getProperty("user.dir")
    val static = "/src/webserver/static/"

    val route =
      pathPrefix("") {
        getFromDirectory(dir + static)
      } ~
      path("") {
        getFromFile(dir + static + "index.html")
      } ~
      pathPrefix("io") {
        handleWebSocketMessages(flow)
      }

    Http().bindAndHandle(
      route,
      "localhost",
      8080
    )

  }


}
