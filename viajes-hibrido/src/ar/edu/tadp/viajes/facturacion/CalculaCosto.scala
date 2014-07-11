package ar.edu.tadp.viajes.facturacion

import ar.edu.tadp.viajes.modulo.ModuloExternoDependency
import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.transporte._

object CalculaCosto extends ICalculador with ModuloExternoDependency {

  def calcular(tramos: List[Tramo]): Float = {

    tramos.foldLeft((0.0f, 0)) {
      case ((amount, pos), tramo) =>
        val agregado = tramo match {
          case Tramo(Subte(_), _, _) if (pos > 0) => tramos(pos - 1).transporte match {
            case Subte(_) => 0.0f
            case _ => 4.5f
          }
          case Tramo(Subte(_), _, _) => 4.5f
          case Tramo(tren @ Tren(_), origen, destino) =>
            val count = this.modulo.getEstacionesIntermedias(tren, origen, destino).size
            if (count <= 5)
              2.0f
            else if (count <= 8)
              3.5f
            else
              4.75f
          case tr @ Tramo(Colectivo(_), _, _) =>
            val distancia = getDistanciaTramo(tr)
            if (distancia < 3000)
              2.5f
            else if (distancia >= 3 && distancia <= 6)
              2.75f
            else
              2.85f
          case _ => 0.0f
        }

        (amount + agregado, pos + 1)
    }._1
  }

  private def getDistanciaTramo(tramo: Tramo): Float = {
    val estaciones = this.modulo.getEstacionesIntermedias(tramo.transporte, tramo.origen, tramo.destino)

    estaciones.foldLeft((0.0f, 0)) {
      case ((distanciaAcum, pos), estacion) =>
        var distanciaAgregada = 0.0f
        if (pos > 0) {
          val distanciaAgregada = this.modulo.getDistanciaEntre(estacion, estaciones(pos - 1), tramo.transporte)
        }
        (distanciaAcum + distanciaAgregada, pos + 1)
    }._1
  }

}