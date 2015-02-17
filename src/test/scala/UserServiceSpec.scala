import akka.event.NoLogging
import akka.http.marshallers.sprayjson.SprayJsonSupport._
import akka.http.model.ContentTypes._
import akka.http.model.{HttpResponse, HttpRequest}
import akka.http.model.StatusCodes._
import akka.http.testkit.ScalatestRouteTest
import akka.stream.scaladsl.Flow
import org.scalatest._

class UserServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with UserService {
  override val logger = NoLogging

  val user = User(id=1234, name="Lorem", contact=Contact(email="lorem@ipsum.com"))

  "UserService" should "return an user for a given id" in {
     Get(s"/users/${user.id}") ~> routes ~> check {
       status shouldBe OK
       contentType shouldBe `application/json`
       responseAs[User] shouldBe user
     }
  }
}

