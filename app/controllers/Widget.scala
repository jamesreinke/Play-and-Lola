package controllers

import play.api._
import play.api.mvc._
import lola.interface._
import modules._


class Widget extends Controller {

	def init = Action {
		val (nav, commands): (Node, List[Command]) = Nav(List(("Home", "/home"), ("Table", "/table"), ("Widget", "/widget")))

		val widget = Widget(
			el(
				"h1", 
				text = "James Reinke Bitch"),
			width = 100,
			height = 50,
			x = 150,
			y = 200)
		Send(List(Clear(), Create(nav),Create(widget), OnClick(widget, Post("/widget/click", widget))) ++ commands)
	}


	def click = Action {
		implicit request => {
			val widget = Extract[Node](request)
			widget.style += ("display" -> "none")
			Send(Update(widget))
		}
	}

}