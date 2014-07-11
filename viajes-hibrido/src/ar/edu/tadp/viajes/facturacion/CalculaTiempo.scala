package ar.edu.tadp.viajes.facturacion

import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.modulo._
import ar.edu.tadp.viajes.transporte._

object CalculaTiempo extends ICalculador with ModuloExternoDependency {

  def velocidadColectivo = 15.0f / 60.0f

  def calcular(tramos: List[Tramo]): Float = {
    calcularTiempoTramos(tramos) + calcularTiempoCombinaciones(tramos)
  }

  def calcularTiempoTramos(tramos: List[Tramo]): Float = {
    tramos.foldLeft(0.0f) {
      case (minutosAcum, tramo) =>

        val estacionesIntermedias = this.modulo.getEstacionesIntermedias(
          tramo.transporte, tramo.origen, tramo.destino)

        val minutosAgregados = tramo.transporte match {
          case Tren(_) => (estacionesIntermedias.size - 1) * 3
          case Subte(_) => (estacionesIntermedias.size - 1) * 2
          case colectivo @ Colectivo(_) =>
            estacionesIntermedias.foldLeft((0.0f, 0)) {
              case ((acumColectivo, pos), estacion) =>
                var minColectivo = 0.0f
                if (pos > 0) {
                  minColectivo = this.modulo.getDistanciaEntre(estacion, estacionesIntermedias(pos - 1), colectivo) * 60 / 1500
                }
                (acumColectivo + minColectivo, pos + 1)

            }._1
          case _ => 0.0f
        }
        minutosAcum + minutosAgregados
    }
  }

  def calcularTiempoCombinaciones(tramos: List[Tramo]): Float = {
    tramos match {
      case List(Tramo(Subte(_), _, _), Tramo(Subte(_), _, _)) => 4.0f
      case List(Tramo(Subte(_), _, _), Tramo(Tren(_), _, _)) |
        List(Tramo(Tren(_), _, _), Tramo(Subte(_), _, _)) => 5.0f
      case List(Tramo(Tren(_), _, _), Tramo(Tren(_), _, _)) => 4.0f
      case _ => tramos.foldLeft((0.0f, 0)) {
        case ((acumCombinacion, pos), tramo) =>

          var agregadoCombinacion = 0.0f
          if (pos > 0) {
            agregadoCombinacion = this.modulo.getDistanciaEntre(tramos(pos - 1).destino, tramo.origen)
          }

          (acumCombinacion + agregadoCombinacion, pos + 1)
      }._1
    }
  }
}