import akka.actor.ActorSystem
import akka.event.{LoggingAdapter, Logging}
import akka.http.Http
import akka.http.client.RequestBuilding
import akka.http.marshallers.sprayjson.SprayJsonSupport._
import akka.http.marshalling.ToResponseMarshallable
import akka.http.model.{HttpResponse, HttpRequest}
import akka.http.model.StatusCodes._
import akka.http.server.Directives._
import akka.http.unmarshalling.Unmarshal
import akka.stream.{ActorFlowMaterializer, FlowMaterializer}
import akka.stream.scaladsl.{Sink, Source}
import scala.concurrent.{ExecutionContextExecutor, Future}
import spray.json.DefaultJsonProtocol

case class User(id: Int, name: String, contact: Contact)
case class Contact(email: String)

trait Protocol extends DefaultJsonProtocol {
  implicit val contactFormat = jsonFormat1(Contact.apply)
  implicit val userFormat = jsonFormat3(User.apply)
}

trait UserService extends Protocol {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: FlowMaterializer

  val logger: LoggingAdapter

  val routes = {
    logRequestResult("ads-user-service") {
      pathPrefix("users") {
        (get & path(IntNumber)) { id =>
          complete {
            User(id, "Lorem", Contact("lorem@ipsum.com"))
          }
        }
      }
    }
  }
}

object UserServiceApp extends App with UserService {
  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorFlowMaterializer()

  override val logger = Logging(system, getClass)

  Http().bind(interface = "0.0.0.0", port = 9000).startHandlingWith(routes)
}
