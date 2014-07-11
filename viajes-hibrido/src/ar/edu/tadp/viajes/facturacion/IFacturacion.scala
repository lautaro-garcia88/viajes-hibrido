package ar.edu.tadp.viajes.facturacion

import ar.edu.tadp.viajes.Tramo
import ar.edu.tadp.viajes.descuento.Descuento
import ar.edu.tadp.viajes.Viaje

trait IFacturacion {
  type tipoFDescuento = (List[Tramo]) => Option[Descuento]
  
  def calcularCostoTotal(viaje: Viaje) : Float
  def calcularCostoTotal(tramos: List[Tramo]) : Float
  
  def calcularCostoTotal(viaje: Viaje, fDescuento : tipoFDescuento ) : Float
  def calcularCostoTotal(tramos: List[Tramo], fDescuento : tipoFDescuento ) : Float
  
}