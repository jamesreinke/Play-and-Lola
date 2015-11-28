package modules

import lola.interface._


/*
	A bootstrap function with auxiliary functions.
*/
object Table {

	def apply(head: List[String], body: List[List[String]]): Node = {
		val h = el("thead", items = head map { x => el("th", text = x) })
		val b = el("tbody", items = body map { x => Row(x) })
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
/* 
	An example of a class implementation with mutable functions.
*/
class Column(width: Int) {

	val node = el("div", attributes = Map("class" -> s"col-md-${width}"))

	def addItem(neu: Node): Unit = {
		node.items = node.items :+ neu
	}

}

/*
	A grid of tiles consisting of other nodes.
*/
object Tiles {

	def apply(nodes: List[Node]): Node = {
		val tiles = nodes map { x => el("div", attributes = Map("class" -> s"col-md-6"), style = Map("padding" -> "10px")) }
		el("div", attributes = Map("class" -> s"row"), items = tiles)
	}
}


abstract class Nodable {

	val render: Node

}


object Tableu {

	def apply(h: List[String], b: List[List[String]]): Node = {
		(new Tableu(h, b)).render
	}
	// do we want this type of unapply method?  Method we should check the syntax of the parser
	// 		To decide if this is truly a good design change.  What about Monads?  Do we understand those?
	def apply(n: Node): Tableu = {
		new Tableu(List(), List())
	}

}
class Tableu(h: List[String], b: List[List[String]]) {

	val head = el("thead", items = h map { x => el("th", text = x) })
	val body = el("tbody", items = b map { x => (new Row(x)).render })

	val render = el("table", attributes = Map("class"->"table"), items = List(head, body))

	def filter(s: String, table: Tableu): Unit = {
		for(row <- table.body.items){
			row.style += ("display" -> "none")
			for(col <- row.items) if(col.text contains s) row.style += ("display" -> "")
		}
	} 

	def addRow(table: Node, items: List[String]): Unit = table.items = table.items :+ (new Row(items)).render

	class Row(cols: List[String]) extends Nodable {

		val render = el("tr", items = (for(col <- cols) yield el("td", text = col)).toList)

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




