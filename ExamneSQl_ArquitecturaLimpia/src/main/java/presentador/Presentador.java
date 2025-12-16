package presentador;

import casos_uso.CasoDos;
import casos_uso.CasoUno;

public class Presentador {
	private CasoUno casoUno;
	private CasoDos casoDos;
	private  Vista vista;
	
	public Presentador(CasoUno casoUno, CasoDos casoDos) {
		this.casoUno=casoUno;
		this.casoDos=casoDos;
		vista = new Vista(this);
		
		empezar();
		
	}

	private void empezar() {
		
		casoUno.probar();
		vista.bucleAplicacion();
		
	}
	
	

}
