package webserver

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.ws._
import java.util.UUID.randomUUID
import akka.stream.scaladsl._
import play.api.libs.json._
import akka.http.scaladsl._
import akka.stream._
import akka.actor._


//  https://markatta.com/codemonkey/posts/chat-with-akka-http-websockets-old/


object GameServer {


  implicit val system: ActorSystem = ActorSystem("system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()


  def formatResponse(map: Map[String, JsValue]): List[TextMessage] =
    TextMessage(Source.single(Json.stringify(Json.toJson(map)))) :: Nil


  def flow: Flow[Message, Message, Any] = Flow[Message].mapConcat {
    case tm: TextMessage =>
      val parsed: JsValue = Json.parse(tm.getStrictText)
      val action = (parsed \ "action").as[String]
      handleAction(action, parsed)

    case bm: BinaryMessage =>
      bm.dataStream.runWith(Sink.ignore)
      Nil
  }


  def handleAction(action: String, parsed: JsValue): List[TextMessage] = action match {
    case "register" =>
      val nickname = (parsed \ "nickname").as[String]
      val id = randomUUID().toString
      println("New connection from, " + nickname + ".")
      formatResponse(Map[String, JsValue](
        "action" -> Json.toJson("new-game"),
        "id" -> Json.toJson(id)
      ))
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
