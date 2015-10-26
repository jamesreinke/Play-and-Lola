package modules

import lola.interface._


object table {

	def apply(head: List[String], body: List[List[String]]): Node = {
		val h = el("thead", items = head map { x => el("th", text = x) })
		val b = el("tobdy", items = body map { x => row(x) })
		el("table", attributes = Map("class"->"table"), items = List(h, b))
	}
	
	def filter(s: String, table: Node): Node = {
		for(row <- table.items.tail//skip headings) {
			for(col <- row){
				// add logic here to hide the individual rows in the table
			}
		}
	}

}

object row {

	def apply(cols: List[String]): Node = el("tr", items = (for(col <- cols) yield el("td", text = col)).toList)

}