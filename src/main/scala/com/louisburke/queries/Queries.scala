package com.louisburke.queries

import java.time.LocalDateTime

import com.louisburke.datamodel.ColumnDataMapper._
import com.louisburke.datamodel.DataModel._
import com.louisburke.datamodel.Priority
import com.louisburke.datamodel.Priority._

import slick.driver.H2Driver.api._

object Queries {

  val selectAllTasksQuery: Query[TaskTable, Task, Seq] = Tasks

  val selectAllTaskTitleQuery: Query[Rep[String], String, Seq] = Tasks.map(_.title)

  val selectMultipleColumnsQuery: Query[(Rep[String], Rep[Priority], Rep[LocalDateTime]), (String, Priority, LocalDateTime), Seq] = Tasks.map(t => (t.title, t.priority, t.createdAt))

  val selectHighPriorityTasksQuery: Query[Rep[String], String, Seq] = Tasks.filter(_.priority === Priority.HIGH).map(_.title)
}
