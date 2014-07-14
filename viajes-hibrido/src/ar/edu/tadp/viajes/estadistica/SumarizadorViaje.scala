package ar.edu.tadp.viajes.estadistica

import ar.edu.tadp.viajes.Viaje
import ar.edu.tadp.viajes.modulo.ModuloExterno
import ar.edu.tadp.viajes.modulo.ModuloExternoDependency
import ar.edu.tadp.viajes.facturacion._

object SumarizadorViaje {

  var _calculaTiempo: ICalculador = CalculaTiempo

  def calculaTiempo = _calculaTiempo
  def calculaTiempo(mod: ICalculador) = _calculaTiempo = mod

  def facturacionTotal(viajes: List[Viaje]): Float = {
    viajes.foldLeft(0.0f) {
      (acum, viaje) => acum + viaje.getCosto
    }
  }

  def cantidadViajes(viajes: List[Viaje]): Float = {
    viajes.size
  }

  def costoPromedio(viajes: List[Viaje]): Float = {
    try {
      this.facturacionTotal(viajes) / this.cantidadViajes(viajes)
    } catch {
      case e: ArithmeticException => {
        return 0.0f
      }
    }
  }

  def tiempoPromedio(viajes: List[Viaje]): Float = {
    val tiempoTotal = viajes.foldLeft(0.0f) {
      (acum, viaje) => acum + this.calculaTiempo.calcular(viaje.getRecorrido.getTramos)
    }
    try {
      tiempoTotal / this.cantidadViajes(viajes)
    } catch {
      case e: ArithmeticException => {
        return 0.0f
      }
    }

  }
}