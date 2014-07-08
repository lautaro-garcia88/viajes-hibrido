package ar.edu.tadp.viajes

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.modulo._
import ar.edu.tadp.viajes.transporte._

class ViajeBuilderTest {
	
  val builder = new ViajeBuilder(ModuloExterno)
  
  @Test
  def llegaTransporteHasta {
    Assert.assertFalse(builder.llegaTransporteHasta(Tren("A"), CDirs.A_700))
    Assert.assertTrue(builder.llegaTransporteHasta(Tren("A"), CDirs.A_000))
    Assert.assertTrue(builder.llegaTransporteHasta(Colectivo("25"), CDirs.A_700))
  }
  
  
  @Test
  def armarViaje1 {

    val viaje = builder.buildViaje(CDirs.A_000, CDirs.A_700)

    Assert.assertEquals(
        Tramo(
            Colectivo("25"),
            CDirs.A_000,
            CDirs.A_700
        ),
        viaje.get.getTramos(0)
    )
  }
}