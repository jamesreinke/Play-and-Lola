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
      List("July", "Dooley", "july@example.com"),
      List("Belle", "Tuttle", "btuts@silkemail.com"),
      List("James", "Reinke", "JaMez@silkemail.com")
      )
    val database = DatabaseView(header = header, table = table)
    val input = el("input", style=Map("margin" -> "25px"))
    Ok(
      Encode(
        List(
          new Create(input),
          new OnKeyUp(input, new Post("/database/filter", database)),
          new Create(database))))
  }

  def databaseFilter = Action { 
    implicit request => {
      val db = Decode(request.body.asText.getOrElse(""))
      Ok(Encode(DatabaseView.filter(db, "John")))
    }
  }

}
