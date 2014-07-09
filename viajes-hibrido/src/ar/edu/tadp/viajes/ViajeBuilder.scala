package ar.edu.tadp.viajes

import util.control.Breaks._

import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.modulo._

class ViajeBuilder(modulo: IModuloExterno) {

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

  def cumpleCriterio(recorrido: Recorrido): Boolean = {
    true
  }

  def llegaTransporteHasta(transporte: Transporte, dest: Direccion): Boolean = {
    this.modulo.getTransportesCercanos(dest).exists { case (t, subida) => t equals transporte }
  }

}