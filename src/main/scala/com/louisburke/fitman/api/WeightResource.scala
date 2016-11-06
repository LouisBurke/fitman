package com.louisburke.fitman.api

import com.twitter.finatra.http.Controller
import scala.collection.mutable
import org.joda.time.Instant

case class Weight(
                   user: String,
                   weight: Int,
                   status: Option[String],
                   postedAt: Instant = Instant.now()
                 )

class WeightResource extends Controller {

  val db = mutable.Map[String, List[Weight]]()

  post("/weights") { weight: Weight =>
    val weightsForUser = db.get(weight.user) match {
      case Some(weights) => weights :+ weight
      case None => List(weight)
    }
    db.put(weight.user, weightsForUser)
    response.created.location(s"/weights/${weight.user}")
  }

}