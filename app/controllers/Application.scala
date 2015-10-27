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
     style = Map("margin-bottom" -> "100px"))

    val input = el(
      "input", 
      attributes = Map("class" -> "input col-md-6"), 
      style = Map("width" -> "100%"))

    val (nav, commands): (Node, List[Command]) = Nav(List(("Home", "/home"), ("Table", "/table")))

    val container = el(
      "div", 
      attributes = Map("class" -> "col-md-6 col-md-offset-3"), 
      items = List(node, input))

      Send(List(
        Clear(), 
        Create(nav), 
        Create(container), 
        OnKeyUp(input, Post("/change", input, node))) ++ commands)

  }

  def change = Action {
    implicit request => {
      val (input, node) = Extract[Node,Node](request)
      node.text = input.value
      Send(Update(node))
    }
  }
}
