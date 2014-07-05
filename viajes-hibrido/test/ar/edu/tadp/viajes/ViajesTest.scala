package ar.edu.tadp.viajes

import org.junit.Before
import org.junit.Test
import org.junit.Assert

import ar.edu.tadp.viajes.Viaje


class ViajesTest {

  @Test
  def testViaje() = {
    var viaje = new Viaje(new Direccion("Calderon",1,"Devoto"),new Direccion("Cuenca",1,"Villa del Parque"))
    var recorrido = viaje.armarRecorrido()
    Assert.assertEquals(1, recorrido.length)
  }
	
}