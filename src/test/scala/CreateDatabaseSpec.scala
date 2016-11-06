import com.louisburke.datamodel._
import com.louisburke.datamodel.DataModel._

import java.time.LocalDateTime

import org.scalatest.{FunSpec, Matchers}
import slick.driver.H2Driver.api._

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class CreateDatabaseSpec extends FunSpec with Matchers {

  describe("DataModel Spec") {

    it("should create database") {
      val db = Database.forConfig("taskydb")
      val result = Await.result(db.run(DataModel.createTaskTableAction), 2 seconds)
      println(result)
    }

    it("should insert single task into database") {
      val db = Database.forConfig("taskydb")
      val result = Await.result(
        db.run(
          DataModel.insertTaskAction(
            Task(title = "Learn Slick", dueBy = LocalDateTime.now().plusDays(1))
          )
        ),
        2 seconds
      )
      result should be(Some(1))
    }
  }
}