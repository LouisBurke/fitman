package com.louisburke.datamodel

import java.sql.Timestamp

import slick.driver.H2Driver.api._
import java.time.LocalDateTime

import com.louisburke.datamodel.ColumnDataMapper._

object DataModel {

  case class Task(
                   title: String,
                   description: String = "",
                   createdAt: LocalDateTime = LocalDateTime.now(),
                   dueBy: LocalDateTime,
                   tags: Set[String] = Set(),
                   id: Long = 0L)

  class TaskTable(tag: Tag) extends Table[Task](tag, "tasks") {
    def title = column[String]("title")

    def description = column[String]("description")

    def createdAt = column[LocalDateTime]("createdAt")(localDateTimeColumnType)

    def dueBy = column[LocalDateTime]("dueBy")(localDateTimeColumnType)

    def tags = column[Set[String]]("tags")

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    override def * = (title, description, createdAt, dueBy, tags, id) <> (Task.tupled, Task.unapply)
  }

  lazy val Tasks = TableQuery[TaskTable]

  val createTaskTableAction = Tasks.schema.create
  
}

object ColumnDataMapper {

  implicit val localDateTimeColumnType = MappedColumnType.base[LocalDateTime, Timestamp](
    ldt => Timestamp.valueOf(ldt),
    t => t.toLocalDateTime
  )

  implicit val setStringColumnType = MappedColumnType.base[Set[String], String](
    tags => tags.mkString(","),
    tagsString => tagsString.split(",").toSet
  )
}