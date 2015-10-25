package modules

import lola.interface._

/*
	Example: Primitive node
*/
object Article {


	private def col(width: Int): Map[String,String] = {
		Map("class" -> s"col-md-${width}")
	}

	def apply(title: String, date: String, author: String, content: String): Node = {
		val t = el("h1", attributes = col(12), style = Map("text-align" -> "center"), text = title)
		val d = el("div", attributes = col(2), style = Map("float" -> "right"), text = date)
		val a = el("div", style = Map("float"->"right"), attributes = col(2), text = author)
		val c = el("div", attributes = col(12), text = content, style = Map("padding" -> "50px"))
		el("div", attributes = col(12), items = List(t, d, a, c))
	}
}

/*
	Turns an object into a database view.  Plans to add features such as search filter, page toggling...
*/
object DatabaseView {

	def apply(header: List[String] = List(), table: List[List[String]]): Node = {
		val labels = el("tr", items = header map { x => el("th", text = x) })
		el(
			"table", 
			attributes = Map("class" -> "table"),
			items = labels +: (table map { row => el("tr", items = row map { col => el("td", text = col) }) })
		)
	}
}