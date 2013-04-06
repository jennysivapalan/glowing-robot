package com.proj

import org.scalatra.ScalatraServlet
import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats

class Dispatcher extends ScalatraServlet {

  get("/") {
    "hello world"
  }

  get("/ons/:ons") {
    val ons_code = params("ons")
    val source = scala.io.Source.fromFile("/Users/lindseydew/dev/hackdays/gov-hack-day/glowing-robot/src/main/scala/com.proj/cuts2012v2.json")
    val lines = source mkString
    implicit val formats = DefaultFormats
    val json = parse(lines)
//    json.extract[Data]
    json.values
  //  json \\ "ONS code"

  }

  case class Data(data:List[Borough])
  case class Borough(
                      childrenInPov: Double,
                      publicSector: Double,
                      oldOnsCode: String,
                      name:String,
                      ons: String,
                      cutPerHead: String,
                      youthBenefits:Double,
                      benefitClaimants: Double,
                      averageIndex: Double
                      )

}
