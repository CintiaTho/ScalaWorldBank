package queries


/** Classe selada que representa uma consulta.
 */
sealed class Query

/** Classe de consulta atrelada somente aos resultados a serem obtidos.
 *
 *  @param urlSuffix String para indicar o sufixo da URL
 *         a ser consultada.
 */
case class PureQuery(
  val urlSuffix: String)
    extends Query

/** Classe de consulta atrelada a geração de gráfico dos dados
 *  solicitados.
 *
 *  @param countries List[String] contendo os códigos ISO 3166-1 dos
 *         paises a serem consultados.
 *  @param indicators List[String] contendo os códigos dos
 *         indicadores a serem solicitados.
 */
case class PlotQuery(
  val countries: List[String],
  val indicators: List[String])
    extends Query