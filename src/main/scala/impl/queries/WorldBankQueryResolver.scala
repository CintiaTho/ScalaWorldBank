package impl.queries

import queries._
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import utils._
import worldBank._

class WorldBankQueryResolver extends QueryResolver with CountryIndicator {

  def Resolve(query: Query) =
    Memoize(InternalResolve _)(query)

  private[this] def InternalResolve(query: Query): QueryResult = {
    query match {
      case plot: PlotQuery => resolvePlotQuery(plot)
      case _               => null
    }
  }

  private[this] def resolvePlotQuery(query: PlotQuery) = {

    val values = for (
      country <- query.countries;
      indicator <- query.indicators
    ) yield getValuesByYear(country, indicator)
    
    new BokehQueryResult(values)
  }

}

