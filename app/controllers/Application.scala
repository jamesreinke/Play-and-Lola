package controllers

import play.api._
import play.api.mvc._
import upickle.default._
import lola.interface._
import modules._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index(""))
  }

  def home = Action {
  	
    val a = Article("James Wins the Election", 
            "10-12-2020", 
            "James Reinke", 
            "It was an amazing election where James just barely won the vote at 79%..." * 20)
  	Ok(
  		Encode(
	  		List(
          new Create(a),
          new OnClick(a, new FadeOut(a, 2000)))))
  }

}
