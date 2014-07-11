package ar.edu.tadp.viajes.descuento

import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.Direccion

object Tarjeta {

  def Turismo(tramos: List[Tramo]): Option[Descuento] = {
    tramos match {
      case List(
        Tramo(_, _, Direccion(_, _, barrioA)),
        Tramo(_, _, Direccion(_, _, barrioB))
        ) if (barrioA equals barrioB) => Some(DescuentoPorcentual(0.10f))
      case List(Tramo(_, _, _)) => Some(DescuentoPorcentual(0.10f))
      case _ => None
    }
  }

  def Discapacitado(tramos: List[Tramo]): Option[Descuento] = {
    Some(DescuentoPorcentual(1.0f))
  }

  def YendoAlTrabajo(tramos: List[Tramo]): Option[Descuento] = {
    tramos match {
      case List(Tramo(_, _, Direccion(_, _, "Liniers")), Tramo(_, _, Direccion(_, _, "Centro"))) => Some(DescuentoFijo(1.5f))
      case List(Tramo(_, _, Direccion(_, _, "La Boca")), Tramo(_, _, Direccion(_, _, "Centro"))) => Some(DescuentoFijo(1.5f))
      case _ => None
    }
  }
}

