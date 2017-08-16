package console


import queries._
import impl.queries._


/** Aplicação que executa o projeto ScalaWorldBank.
 */
object Application extends App {
  // Exemplo de entrada de args:
  // args[0]: "br;us:NY.GDP.MKTP.CD"
  // args[1]: "cl;jp:NY.GDP.MKTP.CD"
  for (arg <- args) {
    val query = QueryBuilder.getQuery(arg)
    val queryResult = new WorldBankQueryResolver().Resolve(query)
    queryResult show
  }
}