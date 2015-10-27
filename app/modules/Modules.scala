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