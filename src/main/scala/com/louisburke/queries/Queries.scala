package com.louisburke.queries

import java.time.{LocalDate, LocalDateTime}

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

  def findAllTasksPageQuery(skip: Int, limit: Int) = Tasks.drop(skip).take(limit)

  val selectTasksSortedByDueDateDescQuery = Tasks.sortBy(_.dueBy.desc)

  val findAllDueTasks = Tasks.filter(_.dueBy >= LocalDate.now().atStartOfDay())

  val selectAllTaskTitlesDueToday = Tasks
    .filter(_.dueBy > LocalDate.now().atStartOfDay())
    .filter(_.dueBy < LocalDate.now().atStartOfDay().plusDays(1))
    .map(_.title)

  val selectTasksBetweenTodayAndSameDateNextMonthQuery = Tasks.filter(t => t.dueBy.between(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)))

  val selectAllTasksDueToday = Tasks
    .filter(_.dueBy > LocalDate.now().atStartOfDay())
    .filter(_.dueBy < LocalDate.now().atStartOfDay().plusDays(1))

  val checkIfAnyHighPriorityTaskExistsToday = selectAllTasksDueToday.filter(_.priority === Priority.HIGH).exists
}
