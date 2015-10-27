package modules

import lola.interface._

/*
	A bootstrap function with auxiliary functions.
*/
object Table {

	def apply(head: List[String], body: List[List[String]]): Node = {
		val h = el("thead", items = head map { x => el("th", text = x) })
		val b = el("tobdy", items = body map { x => Row(x) })
		el("table", attributes = Map("class"->"table"), items = List(h, b))
	}
	/*
		Hide rows of a table, whose columns do not contain the string s.
	*/
	def filter(s: String, table: Node): Unit = {
		for(tbody <- table.items.tail) {
			for(row <- tbody.items) {
				row.style += ("display" -> "none")
				for(col <- row.items) if(col.text contains s) row.style += ("display" -> "")
			}
		}
	}

	def addRow(table: Node, items: List[String]): Unit = table.items = table.items :+ Row(items)


	object Row {

		def apply(cols: List[String]): Node = el("tr", items = (for(col <- cols) yield el("td", text = col)).toList)

	}

}


object Nav {
	/*
		Supply a list of strings along with their url's to navigate to.  Returns the navigation and initialization
			commands.
	*/
	def apply(items: List[(String,String)]): (Node, List[Command]) = {
		val tabs = for((tag, url) <- items) yield {
			el("button", text = tag, value = url, attributes = Map("class" -> "btn btn-default"))
		}
		val commands = for(tab <- tabs) yield {
			OnClick(tab, Get(tab.value)) // TODO: Add multiple commands as result of onclick event
		}
		val contents = el("ul", attributes = Map("class" -> "nav navbar-nav"), items = tabs)
		val nav = el("nav", attributes = Map("class" -> "navbar navbar-default"), items = List(contents))
		(nav, commands)
	}



}