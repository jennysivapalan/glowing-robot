package com.proj

import org.scalatra.ScalatraServlet

class Dispatcher extends ScalatraServlet {

  get("/") {
    "hello world"
  }

}
