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
  	
    val a = for(a <- 1 until 20) yield Article("James Wins the Election", 
            "10-12-2020", 
            "James Reinke", 
            "It was an amazing election where James just barely won the vote at 79%..." * 20)
  	Ok(
  		Encode(
          (for(article <- a) yield new Create(article)).toList ++ 
          (for(article <- a) yield new OnClick(article, new SlideUp(article, 1000))).toList))
  }

}
