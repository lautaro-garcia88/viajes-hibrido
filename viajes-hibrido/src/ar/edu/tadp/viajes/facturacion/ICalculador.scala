package ar.edu.tadp.viajes.facturacion

import ar.edu.tadp.viajes.Tramo

trait ICalculador{
  def calcular(tramos: List[Tramo]): Float
}