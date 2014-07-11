package ar.edu.tadp.viajes

import util.control.Breaks._

import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.modulo._

class ViajeBuilder extends ModuloExternoDependency {

  private type criterioRecorrido = List[Recorrido] => Recorrido
  
  def armarViaje(origen: Direccion, destino: Direccion) = {
    val recorrido = getPosibleRecorridos(origen,destino)(0)
    new Viaje(recorrido,origen,destino)
  }
  
  def armarViaje(origen: Direccion, destino: Direccion,fCriterio: criterioRecorrido) = {
    val recorrido = fCriterio(getPosibleRecorridos(origen,destino))
    new Viaje(recorrido,origen,destino)
  }
  
  def getPosibleRecorridos(origen: Direccion, destino: Direccion): List[Recorrido] = {
    
    this.modulo.getTransportesCercanos(origen) map {
      case (transOrg, paradaOrg) =>
        if (llegaTransporteHasta(transOrg, destino)) {
          List(Recorrido(List(Tramo(transOrg, paradaOrg, destino))))
        } else {
          this.modulo.getTransportesCercanos(destino) map {
            case (transDest, paradaDest) =>
              this.modulo.combinan(transDest, transOrg) match {

                case (true, Some(combinacionDir)) if (paradaOrg != combinacionDir) => Some(Recorrido(List(
                  Tramo(transOrg, paradaOrg, combinacionDir),
                  Tramo(transDest, combinacionDir, destino))))

                case (_, _) => None
              }
          } flatten
        }
    } flatten
  }

  def llegaTransporteHasta(transporte: Transporte, dest: Direccion): Boolean = {
    this.modulo.getTransportesCercanos(dest).exists { case (t, subida) => t equals transporte }
  }

}