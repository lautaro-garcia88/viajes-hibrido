package ar.edu.tadp.viajes

import util.control.Breaks._

import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.modulo._

class ViajeBuilder(origen: Direccion, destino: Direccion) {

  private var _modulo: IModuloExterno = ModuloExterno

  def modulo = this._modulo
  def modulo(unModulo: IModuloExterno) = this._modulo = unModulo

  //criterio: (List[List[Tramo]]=> List[Tramo]
  def armarViaje(): Option[Viaje] = {

    val recorridos = (for {
      (t, parada) <- this.modulo.getTransportesCercanos(origen)
    } yield {
      getRecorridos(t, parada, destino).get
    }) flatten
    
    recorridos match {
      case _ :: _ => Some(new Viaje(recorridos(0),origen,destino)) 
      case Nil => None
	} 
  }

  private def getRecorridos(transOrg: Transporte, org: Direccion, dest: Direccion): Option[List[List[Tramo]]] = {
    if (llegaTransporteHasta(transOrg, dest)) {
      Some(List(List(Tramo(transOrg, org, dest)))) 
    } else {
      for {
        (t, parada) <- this.modulo.getTransportesCercanos(destino) 
        	if ( this.modulo.combinan(t, transOrg)._1 )
      } yield {
    	  getRecorridos(t,parada,dest).map( a => a )
      }
    }
    None
  }

  private def llegaTransporteHasta(transporte: Transporte, dest: Direccion): Boolean = {
    this.modulo.getTransportesCercanos(dest).exists { case (t, subida) => t equals transporte }
  }

}