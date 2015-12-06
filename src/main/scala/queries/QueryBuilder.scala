package queries

object QueryBuilder {
  
  def getQuery(s:String) : Query = {
    val splitted = s split ("\\s+") map(_.toUpperCase) toList
    
    s split ("\\s+") map(_.toUpperCase) toList match {
      case "QUERY:" :: List(_*) => getPureQuery(s)
      case _ => getPlotQuery(s)
    }
  }

  private def getPureQuery(s: String): PureQuery =
    PureQuery(s.substring(5))
  
  private def getPlotQuery(s: String): PlotQuery = {
    val splitted = s split ':' 
    val countries = splitted(0) split ';' map(_.trim) toList
    val indicators = splitted(1) split ';' map(_.trim) toList
    
    PlotQuery(countries, indicators)
  }  
  
}