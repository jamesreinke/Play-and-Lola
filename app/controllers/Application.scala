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
    val node = el("p", text = "", attributes = Map("class" -> "col-md-6"), style = Map("margin-bottom" -> "100px"))
    val input = el("input", attributes = Map("class" -> "input col-md-6"), style = Map("width" -> "100%"))

    val cms = 
      List(new Create(node), new Create(input), new OnKeyUp(input, new Post("/change", List(input, node))))

    Ok(Encode(cms))
  }

  def change = Action {
    implicit request => {
      request.body.asText match {
        case Some(text) => {
            Decode(text) match {
              case List(a, b) => {
                b.text = a.value
                Ok(Encode(List(new Update(b))))
              }
            }
         }
        case None => BadRequest("Did not receive a node.")
      }
    }
  }
}
