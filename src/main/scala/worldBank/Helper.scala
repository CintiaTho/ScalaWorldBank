package worldBank


/** Objeto singleton para auxiliar na montagem da URL a ser consultada.
 */
object Helper {
  /** Atributo contendo a URL base a ser consultada.
   */
  val baseURL = "http://api.worldbank.org"

  /** Gera a URL a ser consultada para obter os indíces por país.
   *  @param country é o código ISO 3166-1 do país a ser consultado.
   *  @param indicator é o código do indicador a ser solicitado.
   *  @return String da consulta a ser realizada.
   */
  def getIndicatorByCountryURL(country: String, indicator: String) =
    s"$baseURL/countries/$country/indicators/$indicator"
}