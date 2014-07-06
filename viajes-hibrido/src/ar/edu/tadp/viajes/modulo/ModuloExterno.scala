package ar.edu.tadp.viajes.modulo

import util.control.Breaks._

import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.Combinacion

object ModuloExterno extends ModuloExternoI {

  override def getTransportesCercanos(direccion: Direccion): Option[List[(Transporte, Direccion)]] = direccion match {
    case Direccion("Calle A", altura, _) =>
      if (altura >= 0 && altura < 100) Some(List(
        (Tren("A"), CDirs.A_000),
        (Colectivo(25), CDirs.A_000)))
      else if (altura >= 100 && altura < 300) Some(List(
        (Colectivo(25), CDirs.A_200)))
      else if (altura >= 300 && altura < 500) None
      else if (altura >= 500 && altura < 1000) Some(List(
        (Colectivo(25), CDirs.A_700),
        (Colectivo(107), CDirs.A_700)))
      else None

    case Direccion("Calle B", altura, _) =>
      if (altura >= 0 && altura < 100) Some(List(
        (Tren("A"), CDirs.A_000),
        (Subte("B"), CDirs.A_000)))
      else if (altura >= 100 && altura < 300) None
      else if (altura >= 300 && altura < 500) Some(List(
        (Colectivo(135), CDirs.A_400),
        (Colectivo(107), CDirs.A_400),
        (Subte("B"), CDirs.A_400)))
      else if (altura >= 500 && altura < 1000) None
      else None

    case Direccion("Calle BC", altura, _) =>
      if (altura >= 0 && altura < 100) None
      else if (altura >= 100 && altura < 300) Some(List(
        (Colectivo(135), CDirs.BC_200)))
      else if (altura >= 300 && altura < 500) None
      else if (altura >= 500 && altura < 1000) None
      else None

    case Direccion("Calle C", altura, _) =>
      if (altura >= 0 && altura < 100) Some(List(
        (Tren("A"), CDirs.C_000)))
      else if (altura >= 100 && altura < 300) Some(List(
        (Colectivo(135), CDirs.C_200)))
      else if (altura >= 300 && altura < 500) None
      else if (altura >= 500 && altura < 1000) Some(List(
        (Colectivo(135), CDirs.C_700)))
      else None

    case _ => None
  }

  override def combinan(a: Transporte, b: Transporte): (Boolean, Option[Direccion]) = (a, b) match {
    case (Colectivo(25), Tren("A")) => (true, Some(CDirs.A_000))
    case (Tren("A"), Colectivo(25)) => (true, Some(CDirs.A_000))

    case (Colectivo(25), Colectivo(107)) => (true, Some(CDirs.A_700))
    case (Colectivo(107), Colectivo(25)) => (true, Some(CDirs.A_700))

    case (Tren("A"), Subte("B")) => (true, Some(CDirs.B_000))
    case (Subte("B"), Tren("A")) => (true, Some(CDirs.B_000))

    case (Colectivo(107), Subte("B")) => (true, Some(CDirs.B_400))
    case (Subte("B"), Colectivo(107)) => (true, Some(CDirs.B_400))
    case (Colectivo(135), Subte("B")) => (true, Some(CDirs.B_400))
    case (Subte("B"), Colectivo(135)) => (true, Some(CDirs.B_400))
    case (Colectivo(107), Colectivo(135)) => (true, Some(CDirs.B_400))
    case (Colectivo(135), Colectivo(107)) => (true, Some(CDirs.B_400))

    case (_, _) => (false, None)
  }

  override def getDistanciaEntre(origen: Direccion, destino: Direccion, transporte: Transporte): Float = {
    0
  }

  override def getDistanciaEntre(origen: Direccion, destino: Direccion): Float =
    Math.abs(origen.altura - destino.altura) + getDistanciaHorizontalAPie(origen, destino)

  def getDistanciaHorizontalAPie(origen: Direccion, destino: Direccion): Float =
    (origen, destino) match {
      case (Direccion("Calle A", _, _), Direccion("Calle B", _, _)) => 100
      case (Direccion("Calle A", _, _), Direccion("Calle BC", _, _)) => 50
      case (Direccion("Calle A", _, _), Direccion("Calle C", _, _)) => 200

      case (Direccion("Calle B", _, _), Direccion("Calle A", _, _)) => getDistanciaHorizontalAPie(destino, origen)
      case (Direccion("Calle B", _, _), Direccion("Calle BC", _, _)) => 50
      case (Direccion("Calle B", _, _), Direccion("Calle C", _, _)) => 100

      case (Direccion("Calle BC", _, _), Direccion("Calle A", _, _)) => getDistanciaHorizontalAPie(destino, origen)
      case (Direccion("Calle BC", _, _), Direccion("Calle B", _, _)) => getDistanciaHorizontalAPie(destino, origen)
      case (Direccion("Calle BC", _, _), Direccion("Calle C", _, _)) => 50

      case (Direccion("Calle C", _, _), Direccion("Calle A", _, _)) => getDistanciaHorizontalAPie(destino, origen)
      case (Direccion("Calle C", _, _), Direccion("Calle B", _, _)) => getDistanciaHorizontalAPie(destino, origen)
      case (Direccion("Calle C", _, _), Direccion("Calle C", _, _)) => getDistanciaHorizontalAPie(destino, origen)
      case (_, _) => 0
    }

}