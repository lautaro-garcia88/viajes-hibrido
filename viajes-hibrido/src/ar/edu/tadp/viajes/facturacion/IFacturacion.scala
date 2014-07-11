package ar.edu.tadp.viajes.facturacion

import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.descuento.Descuento

trait IFacturacion {
  type tipoFDescuento = (List[Tramo]) => Option[Descuento]
  
  def calcularCostoTotal(tramos: List[Tramo]) : Float
  def calcularCostoTotal(tramos: List[Tramo], fDescuento : tipoFDescuento ) : Float
}