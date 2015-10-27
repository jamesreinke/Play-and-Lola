package controllers

import play.api._
import play.api.mvc._
import lola.interface._
import modules._

/*
	An example application showing how to generate a table and input object.  The input object sends an Ajax request
		onKeyUp, where the server modifies the table given the value and then sends an update command.
*/
class James extends Controller {

	def index = Action {
		Ok("Hello!")
	}
	/*
		Generates a table with header.  See modules for table module.
	*/
	def genTable = Action {

		val input = el(
			"input", 
			style = Map("height" -> "50px", "width" -> "100%"))

		val button = el(
			"button", 
			text = "Home", 
			attributes = Map("class" -> "btn btn-primary"), 
			style = Map("margin" -> "auto", "display" -> "block"))

		val head = List("Name", "Phone Number", "# of Kidneys", "Age")

		var body = List(
			List("James Reinke", "(925) 359-7786", "2", "24"),
			List("Belle Tuttle", "Fuck Off", "2", "20"),
			List("Bryan Reinke", "1-800-Causmic", "2", "26"))

		val t = table(head, body)

		val container = el("div", 
			attributes = Map("class" -> "col-md-6 col-md-offset-3"), 
			items = List(t, input))

		Send(
			Clear(),
			Create(button),
			OnClick(button, Get("/home")),
			Create(container),
			OnKeyUp(input, Post("/modifyTable", List(input, t))))
	}
	/*
		Decodes an input and table and filters the table rows given the input's value.
	*/
	def modifyTable = Action {
		implicit request => {
			request.body.asText match {
				case Some(text) => {
					Decode(text) match {
						// We decoded two items
						case List(input,t) =>  {
							table.filter(input.value, t)
							Send(Update(t)) // Update the table
						}
					}
				}
				case None => BadRequest("No Text Response Found")
			}
		}
	}

}