package controllers

import play.api._
import play.api.mvc._
import upickle.default._
import lola.interface._
import modules._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index(""))
  }

  def home = Action {
  	val header = List("Firstname", "Lastname", "Email")
    val table = List(
      List("John", "Doe", "john@example.com"),
      List("Mary", "Moe", "mary@example.com"),
      List("July", "Dooley", "july@example.com")
      )
    Ok(Encode(new Create(DatabaseView(header = header, table = table))))
  }

}
