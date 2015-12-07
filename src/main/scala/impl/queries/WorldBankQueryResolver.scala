package impl.queries

import queries._
import scala.concurrent.impl.Future
import scala.collection.mutable
import utils._
import worldBank._

class WorldBankQueryResolver extends QueryResolver with CountryIndicator {
  
  def Resolve(query: Query) : QueryResult = {
    val memo = Memorizer(InternalResolve _)
    memo(query)
  }
  
  private[this] def InternalResolve(query: Query) : QueryResult = {
    query match {
      case q : PlotQuery => null
      case _ => null
    }
  } 
}
