package ar.edu.tadp.viajes.modulo

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.transporte._
import ar.edu.tadp.viajes.Direccion

class ModuloExternoTest {

  @Test
  def combinaciones {

    var result = ModuloExterno.combinan(Colectivo("107"), Colectivo("25"))

    Assert.assertEquals(true, result._1)
    Assert.assertEquals(CDirs.A_700, result._2.get)

    result = ModuloExterno.combinan(Colectivo("25"), Colectivo("107"))

    Assert.assertEquals(true, result._1)
    Assert.assertEquals(CDirs.A_700, result._2.get)

    result = ModuloExterno.combinan(Tren("A"), Subte("B"))

    Assert.assertEquals(true, result._1)
    Assert.assertEquals(CDirs.B_000, result._2.get)

    result = ModuloExterno.combinan(Colectivo("107"), Tren("A"))

    Assert.assertEquals(false, result._1)
    Assert.assertEquals(None, result._2)

  }

  @Test
  def distanciasAPie {

    var distancia = ModuloExterno.getDistanciaEntre(CDirs.A_000, CDirs.A_200)
    Assert.assertEquals(200, distancia, 0.1f)

    distancia = ModuloExterno.getDistanciaEntre(CDirs.A_200, CDirs.A_000)
    Assert.assertEquals(200, distancia, 0.1f)

    distancia = ModuloExterno.getDistanciaEntre(CDirs.A_000, CDirs.B_400)
    Assert.assertEquals(500, distancia, 0.1f)

    distancia = ModuloExterno.getDistanciaEntre(CDirs.B_400, CDirs.A_000)
    Assert.assertEquals(500, distancia, 0.1f)

  }

  @Test
  def distanciasTransporte {
	  var distancia = ModuloExterno.getDistanciaEntre(CDirs.A_000, CDirs.A_700,Colectivo("25"))
	  Assert.assertEquals(700, distancia, 0.1f)
	  
	  distancia = ModuloExterno.getDistanciaEntre(CDirs.A_000, CDirs.C_000,Tren("A") )
	  Assert.assertEquals(200, distancia, 0.1f)
  }

}