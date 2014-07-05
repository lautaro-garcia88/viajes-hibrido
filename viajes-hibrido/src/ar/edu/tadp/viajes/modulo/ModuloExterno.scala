package ar.edu.tadp.viajes.modulo

import scala.collection.mutable.HashMap
import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.transporte._
import util.control.Breaks._
import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.DireccionTransporte
import ar.edu.tadp.viajes.Combinacion

object ModuloExterno extends ModuloExternoI {

  implicit def combinaciones = HashMap[Transporte, HashMap[Transporte, Direccion]](
    Colectivo(25) -> HashMap(
      Colectivo(107) -> CDirs.A_700,
      Tren("A") -> CDirs.A_000),
    Colectivo(107) -> HashMap(
      Colectivo(135) -> CDirs.B_400,
      Subte("B") -> CDirs.B_400),
    Tren("A") -> HashMap(
      Subte("B") -> CDirs.B_000))

  override def getTransportesCercanos(direccion: Direccion): Array[DireccionTransporte] = {

    direccion.calle match {
      case "Calle A" => {
        if (direccion.numero >= 0 && direccion.numero < 200) {
          Array[DireccionTransporte](
            DireccionTransporte(Tren("A"), CDirs.A_000),
            DireccionTransporte(Colectivo(25), CDirs.A_000))

        } else if (direccion.numero >= 200 && direccion.numero < 400) {
          Array[DireccionTransporte](
            DireccionTransporte(Colectivo(25), CDirs.A_200))

        } else if (direccion.numero >= 700 && direccion.numero < 1000) {
          Array[DireccionTransporte](
            DireccionTransporte(Colectivo(107), CDirs.A_700),
            DireccionTransporte(Colectivo(25), CDirs.A_700))

        } else {
          new Array[DireccionTransporte](0)
        }
      }
      case "Calle B" => {
        if (direccion.numero >= 0 && direccion.numero < 200) {
          Array[DireccionTransporte](
            DireccionTransporte(Tren("A"), CDirs.B_000),
            DireccionTransporte(Subte("A"), CDirs.B_000))

        } else if (direccion.numero >= 400 && direccion.numero < 700) {
          Array[DireccionTransporte](
            DireccionTransporte(Colectivo(107), CDirs.B_400),
            DireccionTransporte(Colectivo(135), CDirs.B_400),
            DireccionTransporte(Subte("B"), CDirs.B_400))

        } else {
          new Array[DireccionTransporte](0)
        }
      }
      case "Calle CB" => {
        Array[DireccionTransporte](
          DireccionTransporte(Colectivo(135), CDirs.BC_200))
      }

      case "Calle C" => {
        if (direccion.numero >= 0 && direccion.numero < 200) {
          Array[DireccionTransporte](
            DireccionTransporte(Tren("A"), CDirs.C_000))

        } else if (direccion.numero >= 200 && direccion.numero < 400) {
          Array[DireccionTransporte](
            DireccionTransporte(Colectivo(135), CDirs.C_200))
        } else if (direccion.numero >= 700 && direccion.numero < 1000) {
          Array[DireccionTransporte](
            DireccionTransporte(Colectivo(135), CDirs.C_700))
        } else {
          new Array[DireccionTransporte](0)
        }
      }
    }
  }

  override def combinan(a: Transporte, b: Transporte): (Boolean, Direccion) = {

    var direccion: Direccion = null

    direccion = combinaciones.get(a).get(b)
    if (direccion == null ) direccion = combinaciones.get(b).get(a)
    
    (direccion != null, direccion)
  }

  override def getDistanciaEntre(origen: Direccion, destino: Direccion, transporte: Transporte): Float = {
    return 0
  }

}