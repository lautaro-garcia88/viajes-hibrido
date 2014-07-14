package ar.edu.tadp.viajes.estadistica

import ar.edu.tadp.viajes.Viaje
import ar.edu.tadp.viajes.transporte._
import scala.reflect.runtime.universe._
import ar.edu.tadp.viajes.transporte._
import scala.reflect.ClassTag

object FiltroViaje {

  def porZona(zona: String)(viaje: Viaje): Boolean = {
    viaje.getRecorrido.getTramos.exists(tramo => tramo.origen.barrio == zona | tramo.destino.barrio == zona)
  }

  def porCompania(compania: String)(viaje: Viaje): Boolean = {
    viaje.getRecorrido.getTramos.exists(tramo => tramo.transporte.compania == compania)
  }

  def porTipoTransporte[T <: Transporte: ClassTag](viaje: Viaje): Boolean =
    viaje.getRecorrido.getTramos.exists(tramo => tramo.transporte match {
      case _: T => true
      case _ => false
    })

  def porLineaTransporte(transporte: Transporte)(viaje: Viaje): Boolean = {
    viaje.getRecorrido.getTramos.exists(tramo => tramo.transporte equals transporte)
  }

}