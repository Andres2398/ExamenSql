package casos_uso;

import java.sql.SQLException;

import entidades.Interfaz_pasarela;

public class CasoUno {
	Interfaz_pasarela interfaz;
	
	public CasoUno(Interfaz_pasarela ip) {
		interfaz = ip;
	}

	public void probar() {
		try {
			interfaz.probar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
