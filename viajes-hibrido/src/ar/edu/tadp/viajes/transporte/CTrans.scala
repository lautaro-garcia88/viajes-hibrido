package ar.edu.tadp.viajes.transporte

import ar.edu.tadp.viajes.modulo.CDirs


object CTrans {
	def col25 = Transporte(Colectivo("25"),List(
	    CDirs.A_000,
	    CDirs.A_200,
	    CDirs.A_700
    ))
    
    def col53 = Transporte(Colectivo("53"),List(
		CDirs.A_200,
		CDirs.B_400
    ))
    
    def col107 = Transporte(Colectivo("107"),List(
	    CDirs.A_700,
	    CDirs.B_400
    ))
    
    def col135 = Transporte(Colectivo("135"),List(
	    CDirs.B_400,
	    CDirs.BC_200,
	    CDirs.C_200,
	    CDirs.C_700
    ))
    
    def trenA = Transporte(Tren("A"),List(
	    CDirs.A_000,
	    CDirs.B_000,
	    CDirs.C_000,
	    CDirs.B_000,
	    CDirs.A_000
    ))
    
    def subteB = Transporte(Subte("B"),List(
	    CDirs.B_000,
	    CDirs.B_400,
	    CDirs.B_000
    ))
    
}