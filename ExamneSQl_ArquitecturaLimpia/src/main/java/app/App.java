package app;

import casos_uso.CasoDos;
import casos_uso.CasoUno;
import entidades.Interfaz_pasarela;
import pasarela_datos.GestorBD;
import pasarela_datos.Pasarela_Sql;
import presentador.Presentador;

public class App {
	
	public static void main(String[] args) {
		
		GestorBD.correrScriptCreacion();
		
		Interfaz_pasarela ip = new Pasarela_Sql();
		CasoUno casoUno = new CasoUno(ip);
		CasoDos casoDos = new CasoDos(ip);
		
		new Presentador(casoUno,casoDos);
		
		
	}

}
