package ar.edu.tadp.viajes.criterio

import ar.edu.tadp.viajes.Recorrido
import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.modulo._
import ar.edu.tadp.viajes.transporte._

object Criterio {

  def modulo: IModuloExterno = ModuloExterno
  //type criterio = ( List[Recorrido] ) => (Recorrido)

  def menorCosto(list: List[Recorrido]): Recorrido = {
    val (posMenorCosto, _) = list.foldLeft((-1, 0.0f)) {
      case ((pos, menorCosto), recorrido) => {
        var costo = this.calcularCosto(recorrido.getTramos)
        if (costo < menorCosto) {
          (list.indexOf(recorrido), costo)
        } else {
          (pos, menorCosto)
        }
      }
    }
    list(posMenorCosto)
  }

  private def calcularCosto(l: List[Tramo]): Float = {

    val asd = l match {
      case List(Tramo(Subte(_), _, _), Tramo(Subte(_), _, _)) => 4.5f
      case List(Tramo(Subte(_), _, _), b) => 4.5f + calcularCosto(List(b))
      case List(Tramo(t @ Tren(_), origen, destino), b) =>
        val size = this.modulo.getEstacionesIntermedias(t, origen, destino).size
        val amount = if (size <= 5)
          2.0f
        else if (size <= 8)
          3.5f
        else
          4.75f
        amount + calcularCosto(List(b))
      case List(Tramo(c @ Colectivo(_), _, _), b) => getDistanciaTramos(l)
      case Nil => 0.0f
    }
    /*
    .foldLeft((0.0f)) {
      case (acum, tramo @ Tramo(transporte, origen, destino)) => {
        transporte match {
          case Colectivo(_) =>
          case Subte(_) => 
          case Tren(_) =>
        }
        
        
        (acum,tramo)
      }
    }
    * 
    */
    0.0f
  }

  private def getDistanciaTramos(l: List[Tramo]): Float = {

    0.0f
  }
}