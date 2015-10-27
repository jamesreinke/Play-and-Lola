package controllers

import play.api._
import play.api.mvc._
import upickle.default._
import lola.interface._

class Application extends Controller {

  import modules._

  def index = Action {
    Ok(views.html.index(""))
  }

  def home = Action {

    val node = el("p",
     text = "", 
     attributes = Map("class" -> "col-md-6"),
    style = Map("margin-bottom" -> "100px"))

    val input = el(
      "input", 
      attributes = Map("class" -> "input col-md-6"), 
      style = Map("width" -> "100%"))

    val button = el(
      "button", 
      text = "Table",
      attributes = Map("class" -> "btn btn-primary"), 
      style = Map("margin" -> "auto", "display" -> "block"))

    val container = el(
      "div", 
      attributes = Map("class" -> "col-md-6 col-md-offset-3"), 
      items = List(node, input))

      Send(
        Clear(), 
        Create(button), 
        Create(container), 
        OnClick(button, Get("/genTable")), 
        OnKeyUp(input, Post("/change", List(input, node))))

  }

  def change = Action {
    implicit request => {
      request.body.asText match {
        case Some(text) => {
            Decode(text) match {
              case List(a, b) => {
                b.text = a.value
                Send(Update(b))
              }
            }
         }
        case None => BadRequest("Did not receive a node.")
      }
    }
  }
}
