package com.louisburke.fitman

import com.louisburke.fitman.api.WeightResource
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object FitmanApp extends FitmanServer

class FitmanServer extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add(new HelloController)
    router.add(new WeightResource)
  }
}

class HelloController extends Controller {

  get("/hello") { request: Request =>
    "Fitman says hello"
  }

}