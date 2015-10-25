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

		val head = List("Name", "Phone Number", "# of Kidneys", "Age")
		val body = List(
			List("James Reinke", "(925)359-7786", "2", "24"),
			List("Bell Tuttle", "Fuck Off", "2", "20"),
			List("Bryan Reinke", "1-800-Causmic", "2", "26"))

		val t = table(head, body)

		Ok(Encode(List(new Create(t))))
	}

}