package ar.edu.tadp.viajes

import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.modulo._
import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.Tramo

class ViajeBuilder(origen: Direccion, destino: Direccion) {
  
  private var _modulo : IModuloExterno = null
  private var _tramosViaje : List[Tramo] = List.empty
  
  def modulo = this._modulo
  def modulo(unModulo: IModuloExterno ) = this._modulo = unModulo
  
  def getTramosViaje = this._tramosViaje
  def getTramosViaje(listaTramos : List[Tramo] ) = this._tramosViaje = listaTramos
  
  def armarRecorrido(criterio: (List[List[Tramo]]) => List[Tramo]): Option[List[Tramo]] = {

    None
  }
  
  private def armarRecorrido(transOrigen:Transporte, destino:Direccion) : List[Tramo] = {
    
    
    
    
    List.empty
  }
  
  
}