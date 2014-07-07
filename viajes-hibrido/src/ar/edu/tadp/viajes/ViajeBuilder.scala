package ar.edu.tadp.viajes

import util.control.Breaks._

import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.modulo._

class ViajeBuilder(origen: Direccion, destino: Direccion) {

  private var _modulo: IModuloExterno = null
  private var _tramosViaje: List[Tramo] = List.empty

  def modulo = this._modulo
  def modulo(unModulo: IModuloExterno) = this._modulo = unModulo

  def getTramosViaje = this._tramosViaje
  def getTramosViaje(listaTramos: List[Tramo]) = this._tramosViaje = listaTramos

  def armarRecorrido(criterio: (List[List[Tramo]]) => List[Tramo]): Option[List[Tramo]] = {
	/*
    cercanosOrigen  = this.modulo.getTransportesCercanos(origen)
    cercanosDestino = this.modulo.getTransportesCercanos(destino)
    */
    None
  }
  
  private def getRecorrido(transporteOrigen: Transporte, origen: Direccion, destino: Direccion ) : Option[List[Tramo]] = {
    None
  }

}