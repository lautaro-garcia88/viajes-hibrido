package ar.edu.tadp.viajes

import util.control.Breaks._

import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.modulo._

class ViajeBuilder(modulo: IModuloExterno) {

  //criterio: (List[List[Tramo]]=> List[Tramo]
  def buildViaje(origen: Direccion, destino: Direccion): Option[Viaje] = {

    val recorridos = (for {
      (t, parada) <- this.modulo.getTransportesCercanos(origen)
    } yield {
      getRecorridos(t, parada, destino).get
    }) flatten

    recorridos match {
      case _ :: _ => Some(new Viaje(recorridos(0), origen, destino))
      case Nil => None
    }
  }

  private def getRecorridos(transOrg: Transporte, org: Direccion, dest: Direccion): Option[List[List[Tramo]]] = {
    if (llegaTransporteHasta(transOrg, dest)) {
      Some(List(List(Tramo(transOrg, org, dest))))
    } else {
      val recorridos = for {
        (t, parada) <- this.modulo.getTransportesCercanos(dest)
        if (this.modulo.combinan(t, transOrg)._1)
      } yield {
        val recorrido = getRecorridos(t, parada, dest) match {
          case Some(List(list @ _ :: _)) => list
          case None => Nil
        }
        Tramo(transOrg, parada, dest) :: recorrido
      }
      Some(recorridos)
    }
  }

  def llegaTransporteHasta(transporte: Transporte, dest: Direccion): Boolean = {
    this.modulo.getTransportesCercanos(dest).exists { case (t, subida) => t equals transporte }
  }

}