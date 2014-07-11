package ar.edu.tadp.viajes

import org.junit.Before
import org.junit.Test
import org.junit.Assert
import ar.edu.tadp.viajes.modulo._
import ar.edu.tadp.viajes.transporte._

class ViajeBuilderTest {

  val builder = new ViajeBuilder

  @Test
  def llegaTransporteHasta {
    Assert.assertFalse(builder.llegaTransporteHasta(Tren("A"), CDirs.A_700))
    Assert.assertTrue(builder.llegaTransporteHasta(Tren("A"), CDirs.A_000))
    Assert.assertTrue(builder.llegaTransporteHasta(Colectivo("25"), CDirs.A_700))
  }

  @Test
  def armarViaje1 {

    val recorridos = builder.getPosibleRecorridos(CDirs.A_000, CDirs.A_700)

    Assert.assertEquals(1, recorridos.size)

    Assert.assertEquals(
      List(Tramo(Colectivo("25"), CDirs.A_000, CDirs.A_700)), recorridos(0).getTramos)
  }
  
  @Test
  def armarViaje2 {

    val recorridos = builder.getPosibleRecorridos(CDirs.A_200, CDirs.B_400)

    Assert.assertEquals(2, recorridos.size)

    Assert.assertEquals(
      List(
          Tramo(Colectivo("25"), CDirs.A_200, CDirs.A_700),
          Tramo(Colectivo("107"), CDirs.A_700, CDirs.B_400)
     ), recorridos(0).getTramos)
     
     Assert.assertEquals(
      List(
          Tramo(Colectivo("53"), CDirs.A_200, CDirs.B_400)
      ), recorridos(1).getTramos)
  }
  
  
  @Test
  def armarViaje3 {

    val recorridos = builder.getPosibleRecorridos(CDirs.B_400, CDirs.C_700)

    Assert.assertEquals(1, recorridos.size)

    Assert.assertEquals(
      List(
          Tramo(Colectivo("135"), CDirs.B_400, CDirs.C_700)
     ), recorridos(0).getTramos)
  }
  
  
  @Test
  def armarViaje4 {

    val recorridos = builder.getPosibleRecorridos(CDirs.A_200, CDirs.C_700)

    Assert.assertEquals(1, recorridos.size)

    Assert.assertEquals(
      List(
          Tramo(Colectivo("53"), CDirs.A_200, CDirs.B_400),
          Tramo(Colectivo("135"), CDirs.B_400, CDirs.C_700)
     ), recorridos(0).getTramos)
  }
}