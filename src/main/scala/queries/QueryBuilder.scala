package queries


/** Objeto singleton para gerar as instâncias de consultas.
 */
object QueryBuilder {

  /** Gera uma instância de consulta de acordo com o padrão solicitado,
   *  utilizando mecanismo de pattern matching.
   *
   *  @param s String contendo a versão textual da consulta
   *         a ser realizada.
   *  @return uma instância de Query de acordo com o padrão de
   *          consulta solicitada.
   */
  def getQuery(s: String): Query = {
    val splitted = s split ("\\s+") map(_.toUpperCase) toList

    splitted map(_.toUpperCase) toList match {
      case "QUERY:" :: List(_*) => getPureQuery(s)
      case _ => getPlotQuery(s)
    }
  }

  /** Gera uma instância de PureQuery a partir da
   *  consulta textual original.
   *
   *  @param s String contendo a versão textual da consulta
   *         a ser realizada.
   *  @return uma instância de PureQuery de acordo com
   *          a consulta solicitada.
   */
  private def getPureQuery(s: String): PureQuery =
    PureQuery(s.substring(5))
    //Queries do tipo PureQuery não foram implementadas na versão apresentada

  /** Gera uma instância de PlotQuery a partir da
   *  consulta textual original.
   *
   *  @param s String contendo a versão textual da consulta
   *         a ser realizada.
   *  @return uma instância de PlotQuery de acordo com
   *          a consulta solicitada.
   */
  private def getPlotQuery(s: String): PlotQuery = {
    val splitted = s split ':'
    val countries = splitted(0) split ';' map(_.trim) toList
    val indicators = splitted(1) split ';' map(_.trim) toList

    PlotQuery(countries, indicators)
  }
}