package worldBank


import scalaj.http.Http
import org.json4s._
import org.json4s.native.JsonMethods._

import Helper._


/** Trait para fornecer método de obtenção de dados de indicadores de
 *  um país segmentado por ano.
 */
trait CountryIndicator {

  /** Obtém os indicadores de um país segmentado por ano.
   *
   *  @param country é o código ISO 3166-1 do país a ser consultado.
   *  @param indicator é o código do indicador a ser solicitado.
   *  @return uma 2-tupla contendo uma List[Int] para indicar
   *          os valores de ano e uma List[Double] para indicar
   *          os valores do indicador solicitado do país para cada ano.
   */
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