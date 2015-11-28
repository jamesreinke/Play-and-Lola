package controllers

import slick.driver.H2Driver.api._

import play.api.mvc._

import maliki.interface._

class Login extends Controller {

	class User(tag: Tag) extends Table[(Int, String, String)](tag, "Users") {
		def id = column[Int]("id", O.PrimaryKey)
		def email = column[String]("name")
		def password = column[String]("password")
		def * = (id, email, password)
	}

	def renderIndex = {
		val centered = Map("class" -> "col-md-6 col-md-offset-3")
		val inputStyle = Map("background-color" -> "lightblue")
		val email = el("input", attributes = centered, style = inputStyle)
		val password = el("input", attributes = centered, style = inputStyle)
		val submit = el("button", text = "Submit", attributes = centered)
		el("div", attributes = centered, items = List(email, password, submit))
	}

	def index = Action {
		Send(Create(renderIndex))
	}

}