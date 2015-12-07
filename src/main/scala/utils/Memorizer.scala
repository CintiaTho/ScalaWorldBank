package utils

class Memorizer[-T, +R](f: T => R) extends (T => R) {
  import scala.collection.mutable
  private[this] val vals = mutable.Map.empty[T, R]

  def apply(x: T): R = {
    if (vals.contains(x)) {
      vals(x)
    } else {
      val y = f(x)
      vals += ((x, y))
      y
    }
  }
}

object Memorizer {
  def apply[T, R](f: T => R) = new Memorizer(f)
}