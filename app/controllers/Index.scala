package controllers

import play.api.mvc._



class Index extends Controller {

	def index = Action {
		Ok(views.html.index("Application..."))
	}

}