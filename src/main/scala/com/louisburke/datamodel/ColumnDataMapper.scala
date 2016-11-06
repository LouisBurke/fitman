package com.louisburke.datamodel

import java.sql.Timestamp
import java.time.LocalDateTime

import slick.driver.H2Driver.api._
import com.louisburke.datamodel.Priority.Priority

object ColumnDataMapper {

  implicit val localDateTimeColumnType = MappedColumnType.base[LocalDateTime, Timestamp](
    ldt => Timestamp.valueOf(ldt),
    t => t.toLocalDateTime
  )

  implicit val setStringColumnType = MappedColumnType.base[Set[String], String](
    tags => tags.mkString(","),
    tagsString => tagsString.split(",").toSet
  )

  implicit val priorityMapper = MappedColumnType.base[Priority, Int](
    p => p.id,
    v => Priority(v)
  )
}