package worldBank

import scalaj.http.Http
import org.json4s._
import org.json4s.native.JsonMethods._
import Helper._

trait CountryIndicator {

  def getValuesByYear(country: String, indicator: String): (List[Int], List[Double]) = {
    println(getIndicatorByCountryURL(country, indicator))
    
    val response =
      Http(getIndicatorByCountryURL(country, indicator))
        .param("per_page", "1000")
        .param("format", "json")
        .asString
        .body

    val json = parse(response, false, false)(1)

    (for (JString(str) <- json \ "date") yield str toInt,
      for (JString(str) <- json \ "value") yield str toDouble)
  }

}