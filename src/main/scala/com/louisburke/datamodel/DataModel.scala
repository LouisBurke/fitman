package com.louisburke.datamodel

import slick.driver.PostgresDriver.api._
import java.time.LocalDateTime

import com.louisburke.datamodel.ColumnDataMapper._
import com.louisburke.datamodel.Priority.Priority

object DataModel {

  case class Task(
                   title: String,
                   description: String = "",
                   createdAt: LocalDateTime = LocalDateTime.now(),
                   dueBy: LocalDateTime,
                   tags: Set[String] = Set(),
                   priority: Priority = Priority.LOW,
                   id: Long = 0L)

  class TaskTable(tag: Tag) extends Table[Task](tag, "tasks") {
    def title = column[String]("title")

    def description = column[String]("description")

    def createdAt = column[LocalDateTime]("createdAt")(localDateTimeColumnType)

    def dueBy = column[LocalDateTime]("dueBy")(localDateTimeColumnType)

    def tags = column[Set[String]]("tags")(setStringColumnType)

    def priority = column[Priority]("priority")

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    override def * = (title, description, createdAt, dueBy, tags, priority, id) <> (Task.tupled, Task.unapply)
  }

  lazy val Tasks = TableQuery[TaskTable]

  val createTaskTableAction = Tasks.schema.create

  def insertTaskAction(tasks: Task*) = Tasks ++= tasks.toSeq

  val listTasksAction = Tasks.result
}