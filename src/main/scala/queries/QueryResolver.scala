package queries


/** Trait que define a assinatura do método abstrato Resolve.
 */
trait QueryResolver {

  /** Método abstrato para resolução de consulta.
   *
   *  @param query instância de Query a ser consultada.
   *  @return uma instância de QueryResult contendo o resultado.
   */
  def Resolve(query: Query): QueryResult;
}