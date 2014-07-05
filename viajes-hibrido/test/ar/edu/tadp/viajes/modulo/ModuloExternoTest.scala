package ar.edu.tadp.viajes.modulo

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.transporte.Colectivo
import ar.edu.tadp.viajes.Direccion


class ModuloExternoTest {
  
  @Test
  def combinacionColectivos {
    
    var combinan : ( Boolean, Direccion ) = null
    
    combinan = ModuloExterno.combinan(Colectivo(25), Colectivo(135))
    
    Assert.assertEquals(true,combinan._1)
    Assert.assertEquals(Direccion("Bermudez",100,"Devoto"),combinan._2)
    
    combinan = ModuloExterno.combinan(Colectivo(107), Colectivo(25))
    
    Assert.assertEquals(false,combinan._1)
    Assert.assertEquals(null,combinan._2)
    
    combinan = ModuloExterno.combinan(Colectivo(107), Colectivo(53))
    
    Assert.assertEquals(true,combinan._1)
    Assert.assertEquals(Direccion("Bermudez", 600, "Devoto"),combinan._2)
    
  } 
	
}