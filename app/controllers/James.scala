package controllers

import play.api._
import play.api.mvc._
import lola.interface._
import modules._


class James extends Controller {

	def index = Action {
		Ok("Hello!")
	}

	def genTable = Action {

		val input = el("input", style = Map("height" -> "50px", "width" -> "100%"))
		val head = List("Name", "Phone Number", "# of Kidneys", "Age")
		val body = List(
			List("James Reinke", "(925)359-7786", "2", "24"),
			List("Bell Tuttle", "Fuck Off", "2", "20"),
			List("Bryan Reinke", "1-800-Causmic", "2", "26"))

		val t = table(head, body)

		Ok(Encode(List(new Create(t),new Create(input)) ++ 
			List(OnKeyUp(input, new Post("/modifyTable", List(input, t))))))
	}

	def modifyTable = Action {
		implicit request => {
			request.body.asText match {
				case Some(text) => {
					Decode(text) match {
						// We decoded two items
						case List(input,t) =>  {
							table.filter(input.value, t)
							Ok(Encode(List(new Update(t)))) // Update the table
						}
					}
				}
				case None => BadRequest("No Text Response Found")
			}
		}
	}

}