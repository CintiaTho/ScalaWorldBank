package impl.queries

import queries._
import io.continuum.bokeh._
import io.continuum.bokeh.Tools._
import math.{ Pi => pi, sin }

class BokehQueryResult(val dates: List[Int], val values: List[Double]) extends QueryResult {

  override def show() = {
    object source extends ColumnDataSource {
      val x = column(dates map(_.toDouble))
      val y = column(values)
    }

    import source.{ x, y }

    val xdr = new DataRange1d()
    val ydr = new DataRange1d()

    val plot = new Plot().x_range(xdr).y_range(ydr).tools(Pan | WheelZoom)

    val xaxis = new LinearAxis().plot(plot).location(Location.Below).typeName("")
    val yaxis = new LinearAxis().plot(plot).location(Location.Left)
    plot.below <<= (xaxis :: _)
    plot.left <<= (yaxis :: _)

    val glyph = new Circle().x(x).y(y).size(5).fill_color(Color.Red).line_color(Color.Black)
    val circle = new GlyphRenderer().data_source(source).glyph(glyph)

    plot.renderers := List(xaxis, yaxis, circle)

    val document = new Document(plot)
    val html = document.save("sample.html")
    println(s"Wrote ${html.file}. Open ${html.url} in a web browser.")
    html.view()
  }
}