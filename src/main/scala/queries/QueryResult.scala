package queries


/** Classe abstrata para definir o resultado de uma consulta.
 */
abstract class QueryResult {

  /** Método abstrato para apresentar o resultado da consulta.
   */
  def show: Unit
}