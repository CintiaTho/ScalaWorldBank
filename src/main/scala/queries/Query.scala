package queries

sealed class Query

case class PureQuery(
  val urlSuffix: String)
    extends Query

case class PlotQuery(
  val countries: List[String],
  val indicators: List[String])
    extends Query