package utils


import scala.collection.mutable


/** Classe para utilizar a técnica de memoization que permite utilizar
 *  valores previamente armazenados caso existam em memória.
 *
 *  @param f uma função que mapeia o tipo genérico de entrada T para
 *         o tipo genérico de saída R.
 */
class Memoize[-T, +R](f: T => R) extends (T => R) {

  /** Atributo utilizado para armazenar os valores previamente
   *  calculados.
   */
  private[this] val vals = mutable.Map.empty[T, R]

  /** Método que calcula o mapeamento solicitado somente se o valor
   *  não estiver previamente armazenado.
   *
   *  @param x valor do tipo genérico T a ser mapeado pela função f.
   *  @return o valor calculado para x.
   */
  def apply(x: T): R = {
    // Verifica se o valor calculado está armazenado.
    if (vals.contains(x)) {
      // Caso esteja, utiliza o valor calculado previamente.
      vals(x)
    } else {
      // Caso não esteja, inicialmente calcula o valor f(x).
      val y = f(x)
      // Armazena em memória.
      vals += ((x, y))
      // Entrega o valor calculado.
      y
    }
  }
}

/** Objeto singleton que entrega para utilização do mecanismo de
 *  memoization.
 */
object Memoize {

  /** Método para utilizar o mecanismo de memoization.
   *
   *  @param f uma função que mapeia o tipo genérico de entrada T para
   *         o tipo genérico de saída R.
   */
  def apply[T, R](f: T => R) = new Memoize(f)
}