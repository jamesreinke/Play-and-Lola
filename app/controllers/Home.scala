package controllers

import play.api._
import play.api.mvc._
import upickle.default._
import lola.interface._

import slick.driver.H2Driver.api._
import scala.concurrent._
import scala.util.{Success, Failure}
import ExecutionContext.Implicits.global

class Home extends Controller {

  import modules._

  def index = Action {
    Ok(views.html.index(""))
  }

  class Users(tag: Tag) extends slick.driver.H2Driver.api.Table[(Int, String, String)](tag, "Users"){
    def id = column[Int]("id", O.PrimaryKey)
    def email = column[String]("email")
    def password = column[String]("password")
    def * = (id, email, password)
  }

  def home = Action {
      val email = el(
        "input", 
        attributes = Map(
          "class" -> "row col-md-8 col-md-offset-2", 
          "placeholder" -> "email"))
      val password = el(
        "input", 
        attributes = Map("class" -> "row col-md-8 col-md-offset-2", "placeholder" -> "password"))
      val submit = el(
        "button",
        text = "Submit",
        attributes = Map("class" -> "row col-md-12 btn btn-default"))
      val home = el(
        "div",
        attributes = Map("class" -> "col-md-6 col-md-offset-3"), 
        items = List(email, password, submit))
      Send(Create(home), OnClick(submit, Post("/change", email, password)))
  }

  def change = Action {
    implicit request => {
      val (email, password) = Extract[Node,Node](request)
      val db = Database.forConfig("h2mem1")
      val table = TableQuery[Users]
      try{
        val insertActions = DBIO.seq(
          table.schema.create,
          table += (1, email.value, password.value)
          )
        db.run(insertActions).onSuccess{case _ => println("Fuck Yeah")}
      } finally db.close()
      Send(Clear())
    }
  }
  
}
