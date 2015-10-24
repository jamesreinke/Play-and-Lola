package controllers

import play.api._
import play.api.mvc._
import upickle.default._
import lola.interface._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index(""))
  }

  def home = Action {
  	val style = Map("text-align" -> "center", "padding" -> "20px")
  	Ok(Encode(new Create(el("h1", sText = "It works!", style = style))))
  }

}
