package ar.edu.tadp.viajes.facturacion

import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.modulo._
import ar.edu.tadp.viajes.descuento._
import ar.edu.tadp.viajes.Direccion

object Facturacion extends IFacturacion {

  var _calculaCosto: ICalculador = CalculaCosto

  def calculaCosto = _calculaCosto
  def calculaCosto(mod: ICalculador) = _calculaCosto = mod

  def calcularCostoTotal(tramos: List[Tramo]): Float = {
    this.calculaCosto.calcular(tramos)
  }

  def calcularCostoTotal(tramos: List[Tramo], fDescuento: tipoFDescuento): Float = {

    val valorCosto = this.calcularCostoTotal(tramos)

    val valorDescuento = fDescuento(tramos) match {
      case Some(DescuentoPorcentual(porcentaje)) => valorCosto * porcentaje
      case Some(DescuentoFijo(valor)) => valorCosto - valor
      case None => 0.0f
    }

    valorCosto - valorDescuento
  }
}