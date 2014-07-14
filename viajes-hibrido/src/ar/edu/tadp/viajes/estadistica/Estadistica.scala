package ar.edu.tadp.viajes.estadistica

import ar.edu.tadp.viajes.Viaje
import java.util.HashMap
import ar.edu.tadp.viajes.modulo.ModuloExternoDependency
import ar.edu.tadp.viajes.Tramo

object Estadistica extends ModuloExternoDependency {

  type tFFiltro = (Viaje) => Boolean
  type tFSumarizacion = (List[Viaje]) => Float

  def sumarizar(viajes: List[Viaje], fSum: tFSumarizacion, fFiltro: tFFiltro): Float = {
    this.sumarizar(viajes, fSum, List(fFiltro))
  }

  def sumarizar(viajes: List[Viaje], fSum: tFSumarizacion, filtros: List[tFFiltro]): Float = {
    val viajesFiltrados = viajes.filter(
      viaje => filtros.forall(f => f(viaje)))

    this.sumarizar(viajesFiltrados, fSum)
  }

  def sumarizar(viajes: List[Viaje], fSum: tFSumarizacion): Float = {
    fSum(viajes) match {
      case value if value.isNaN => 0.0f
      case value @ _ => value
    }
  }
}