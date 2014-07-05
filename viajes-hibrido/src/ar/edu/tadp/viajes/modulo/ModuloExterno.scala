package ar.edu.tadp.viajes.modulo

import scala.collection.mutable.HashMap
import ar.edu.tadp.viajes.Direccion
import ar.edu.tadp.viajes.transporte.Transporte
import ar.edu.tadp.viajes.transporte.Colectivo
import util.control.Breaks._

object ModuloExterno extends ModuloExternoI {

  implicit def combinaciones = Array[(Transporte, Transporte, Direccion)](
    (Colectivo(25), Colectivo(135), Direccion("Bermudez", 100, "Devoto")),
    (Colectivo(107), Colectivo(53), Direccion("Bermudez", 600, "Devoto")))

  override def getTransportes(direccion: Direccion): Array[(Transporte, Direccion)] = {

    var transportes = direccion.barrio match {

      case "Devoto" => Array[(Transporte, Direccion)](
        (Colectivo(25), new Direccion("Bermudez", 1000, "Devoto")),
        (Colectivo(107), new Direccion("Bermudez", 1000, "Devoto")))

      case "Villa del Parque" => Array[(Transporte, Direccion)](
        (Colectivo(135), new Direccion("Nazca", 1525, "Villa del Parque")),
        (Colectivo(53), new Direccion("Cuenca", 1000, "Villa del Parque")))
    }

    transportes
  }

  override def combinan(a: Transporte, b: Transporte): (Boolean, Direccion) = {

    var combina = false
    var direccion : Direccion = null

    breakable {
      for( combi <- combinaciones ) {
        if (combi._1.equals(a) && combi._2.equals(b) ||
          combi._2.equals(b) && combi._1.equals(a)) {
          combina = true
          direccion = combi._3
          break
        }
      }
    }

    (combina, direccion)
  }
  
  override def getDistanciaEntre(origen: Direccion,destino :Direccion,transporte: Transporte) : Float = {
    
   return 0 
  }

}