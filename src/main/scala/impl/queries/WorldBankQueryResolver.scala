package impl.queries


import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global

import queries._
import utils._
import worldBank._


/** Classe para resolver consultas de dados de indicadores de país por
 *  ano do WorldBank.
 */
class WorldBankQueryResolver extends QueryResolver with CountryIndicator {

  /** Método para resolução da consulta solicitada através do método de
   *  otimização usando cache.
   *
   *  @param query instância de Query a ser consultada.
   *  @return instância de QueryResult contendo o resultado da
   *          consulta solicitada.
   */
  def Resolve(query: Query) =
    Memoize(InternalResolve _)(query)

  /** Método para resolução interna: determina qual método para
   *  resolução de consulta será utilizada com base no tipo de
   *  consulta solicitada, utilizando pattern matching.
   *
   *  @param query instância de Query a ser consultada.
   *  @return instância de QueryResult contendo o resultado da
   *          consulta solicitada.
   */
  private[this] def InternalResolve(query: Query): QueryResult = {
    query match {
      case plot: PlotQuery => resolvePlotQuery(plot)
      case _               => null
    }
  }

  /** Resolve a consulta para geração de gráfico.
   *
   *  @param query instância de PlotQuery a ser consultada.
   *  @return instância de BokehQueryResult para apresentar o resultado
   *          solicitado.
   */
  private[this] def resolvePlotQuery(query: PlotQuery): BokehQueryResult = {
    val values = for (
      country <- query.countries;
      indicator <- query.indicators
    ) yield getValuesByYear(country, indicator)

    new BokehQueryResult(query.toString, values)
  }

}