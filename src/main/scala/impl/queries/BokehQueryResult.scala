package impl.queries


import math.{ Pi => pi, sin }

import io.continuum.bokeh._
import io.continuum.bokeh.Tools._

import queries._


/** Classe para gerar arquivo HTML contendo o gráfico solicitado.
 *
 *  @param name o nome do arquivo do gráfico a ser gerado
 *  @param data uma List[(List[Int], List[Double])] utilizada para
 *         passar 2-tuplas, sendo que cada tupla contem os dados de
 *         uma curva a ser plotada.
 */
class BokehQueryResult(val name: String,
                       val data: List[(List[Int], List[Double])])
  extends QueryResult {

  /** Método utilizado para calcular as curvas cujos conjuntos de dados
   *  foram passados por data; gerar o gráfico a partir dos
   *  renderizadores das curvas; salvar o arquivo HTML; e exibir
   *  o arquivo salvo.
   */
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
    val html = document.save(name + ".html")
    html.view()
  }

  /** Iterador de conjunto de dados para obter renderizador de cada curva.
   */
  private def getRenderers() =
    for (item <- data) yield getRenderer(item)

  /** Gera o renderizador de uma curva com base nos valores por data.
   *
   *  @param valueByDate uma 2-tupla composta por duas listas:
   *         uma List[Int] para indicar os valores de ano, a serem
   *         plotados no eixo x; e
   *         uma List[Double] para indicar os valores requisitados do
   *         ano no eixo y.
   *  @return instância de Renderer para gerar uma curva.
   */
  private def getRenderer(valueByDate: (List[Int], List[Double])): Renderer = {

    /** Objeto singleton para representar as coordenadas
     *  a serem plotadas do gráfico.
     */
    object source extends ColumnDataSource {
      val x = column(valueByDate._1.map { x => x toDouble })
      val y = column(valueByDate._2)
    }

    import source.{ x, y }

    //TODO: Criar legenda por país
    val line = new Line().x(x).y(y).line_color(Color.Blue)
    new GlyphRenderer()
      .data_source(source)
      .glyph(line)
  }

}