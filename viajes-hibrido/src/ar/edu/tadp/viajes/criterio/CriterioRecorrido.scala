package ar.edu.tadp.viajes.criterio

import ar.edu.tadp.viajes.Recorrido
import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.facturacion._

object Criterio {

  private abstract class TipoCriterio
  private case object Tiempo extends TipoCriterio
  private case object Costo extends TipoCriterio

  private var _calculaCosto: ICalculador = CalculaCosto
  private var _calculaTiempo: ICalculador = CalculaTiempo

  def calculaCosto = this._calculaCosto
  def calculaCosto(mod: ICalculador) = this._calculaCosto = mod

  def calculaTiempo = this._calculaTiempo
  def calculaTiempo(mod: ICalculador) = this._calculaTiempo = mod

  def menorCosto(recorridos: List[Recorrido]): Recorrido = menorValor(recorridos, Costo)
  def menorTiempo(recorridos: List[Recorrido]): Recorrido = menorValor(recorridos, Tiempo)

  private def menorValor(list: List[Recorrido], tCriterio: TipoCriterio): Recorrido = {
    val (posMin, _) = list.foldLeft((-1, 0.0f)) {
      case ((pos, min), recorrido) => {

        val valor = tCriterio match {
          case Tiempo => this.calculaTiempo.calcular(recorrido.getTramos)
          case Costo => this.calculaCosto.calcular(recorrido.getTramos)
        }

        if (valor < min) {
          (list.indexOf(recorrido), valor)
        } else {
          (pos, min)
        }
      }
    }
    list(posMin)
  }
}