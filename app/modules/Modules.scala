package modules

import lola.interface._

/*
	An example how primitive node values can be used...
*/
object Article {


	private def col(width: Int): Map[String,String] = {
		Map("class" -> s"col-md-${width}")
	}

	def apply(title: String, date: String, author: String, content: String): Node = {
		val t = el("h1", attributes = col(12), style = Map("text-align" -> "center"), sText = title)
		val d = el("div", attributes = col(2), style = Map("float" -> "right"), sText = date)
		val a = el("div", style = Map("float"->"right"), attributes = col(2), sText = author)
		val c = el("div", attributes = col(12), sText = content, style = Map("padding" -> "50px"))
		el("div", attributes = col(12), items = List(t, d, a, c))
	}
}