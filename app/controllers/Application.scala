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
    val node = el("p", text = "whatever")
    val input = el("input", attributes = Map("class" -> "input"))

    val cms = 
      List(new Create(node), new OnClick(node, new Post("/change", List(node)))) ++
      List(new Create(input), new OnKeyUp(input, new Post("/change", List(node))))

    Ok(Encode(cms))
  }

  def change = Action {
    implicit request => {
      request.body.asText match {
        case Some(text) => {
          val p = Decode(text).head
          p.style = Map("text-align" -> "center")
          p.text = "Do not deny me!  " + p.text
           Ok(Encode(new Update(p)))
         }
        case None => BadRequest("Did not receive a node.")
      }
    }
  }
}
