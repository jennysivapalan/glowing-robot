package com.proj

import org.scalatra.ScalatraServlet
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats


class Dispatcher extends ScalatraServlet {

  get("/check") {
    val ons = params.get("ons") getOrElse halt(400)
    val source = scala.io.Source.fromFile("/home/administrator/dev/glowing-robot/src/main/scala/com.proj/cuts2012v2.json")
    val lines = source mkString
    implicit val formats = DefaultFormats
    val json = parse(lines)
    val boroughs =  json \ "data" match {
        case JArray(x) => x map (y => y match {
	 case JObject(z) => {
          val borough = Borough(z(0)._2.extract[Double], 
                  z(1)._2.extract[Double],
                  z(2)._2.extract[String],
                  z(3)._2.extract[String],
                  z(4)._2.extract[String],
                  z(5)._2.extract[String], 
                  z(6)._2.extract[Double],
                  z(7)._2.extract[Double],
                  z(8)._2.extract[Double])
           borough
         }
	})
    }
 
    boroughs filter (b=> b.ons == ons)
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
