package impl.queries

import queries._
import io.continuum.bokeh._
import io.continuum.bokeh.Tools._
import math.{ Pi => pi, sin }

class BokehQueryResult(val data: List[(List[Int], List[Double])]) extends QueryResult {

  override def show() = {

    val xdr = new DataRange1d()
    val ydr = new DataRange1d()

    val plot = new Plot().x_range(xdr).y_range(ydr)

    val xaxis = new LinearAxis().plot(plot)
    val yaxis = new LinearAxis().plot(plot)
    plot.below <<= (xaxis :: _)
    plot.left <<= (yaxis :: _)

    val pantool = new PanTool().plot(plot)
    val wheelzoomtool = new WheelZoomTool().plot(plot)

    plot.renderers := List(xaxis, yaxis) ::: getRenderers()
    plot.tools := List(pantool, wheelzoomtool)

    val document = new Document(plot)
    val html = document.save("plot.html")
    html.view()    
  }

  private def getRenderers() =
    for (item <- data) yield getRenderer(item)

  
  //TODO: Criar legenda por país
  private def getRenderer(valueByDate: (List[Int], List[Double])): Renderer = {
    object source extends ColumnDataSource {
      val x = column(valueByDate._1.map { x => x toDouble })
      val y = column(valueByDate._2)
    }

    import source.{ x, y }

    val line = new Line().x(x).y(y).line_color(Color.Blue)
    new GlyphRenderer()
      .data_source(source)
      .glyph(line)
  }

}