package pasarela_datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import entidades.Interfaz_pasarela;

public class Pasarela_Sql implements Interfaz_pasarela {

	@Override
	public void probar() throws SQLException {

		String sql = "{Call mediaSalario(?,?)}";
		try (Connection con = GestorBD.conectar(); CallableStatement stmt = con.prepareCall(sql)) {

			stmt.registerOutParameter(1, java.sql.Types.DOUBLE);
			stmt.registerOutParameter(2, java.sql.Types.INTEGER);

			stmt.execute();
			double promedio = stmt.getDouble(1);
			int empleados = stmt.getInt(2);

			String salida = "Salaro Promedio: " + promedio + ", de " + empleados + " empleados";
			System.out.println(salida);

		}
		listar();

		String sql2 = "{call agregar(?,?)}";
		try (Connection con = GestorBD.conectar(); CallableStatement stmt = con.prepareCall(sql2)) {

			stmt.setString(1, "Juanito");
			stmt.setInt(2, 5555);

			stmt.execute();

		}
		listar();
		
		
		String sql3 = "{call buscar(?)}";
		try (Connection con = GestorBD.conectar(); CallableStatement stmt = con.prepareCall(sql3)) {

			stmt.setInt(1, 1);

			var rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				System.out.println("Nombre con id 1 " + rs.getString("nombre"));
			}
		}
		
		
		String sql4 = "{call actualizar (?,?,?)}";
		try (Connection con = GestorBD.conectar(); CallableStatement stmt = con.prepareCall(sql4)) {
			
			stmt.setInt(1, 1);
			stmt.setString(2, "Manolito");
			stmt.setInt(3, 8888);
			
			stmt.execute();
		}
		listar();
		
		
		String sql5 = "{?= call listarNombres()}";
		try (Connection con = GestorBD.conectar(); CallableStatement stmt = con.prepareCall(sql5)) {
			
			stmt.registerOutParameter(1, Types.VARCHAR);
			
			stmt.execute();
			String a =stmt.getString(1);
			System.out.println(a);
		}
		
		String sql6 = "{?= call contarEmplados()}";
		try (Connection con = GestorBD.conectar(); CallableStatement stmt = con.prepareCall(sql6)) {
			
			stmt.registerOutParameter(1, Types.INTEGER);
			
			stmt.execute();
			int a =stmt.getInt(1);
			System.out.println(a);
		}
		

		String sql7 = "{call agregar(?,?)}";
		try (Connection con = GestorBD.conectar(); CallableStatement stmt = con.prepareCall(sql7)) {

			stmt.setString(1, "pepito");
			stmt.setInt(2, 0);

			stmt.execute();

		}
		listar();
		
		String sql8 = "{?= call listar_empleados_json()}"; 
		try (Connection con = GestorBD.conectar(); 
		   
		     CallableStatement stmt = con.prepareCall(sql8)) { 
		    
		   
		    stmt.registerOutParameter(1, java.sql.Types.LONGVARCHAR); 
		    
		  
		    stmt.execute(); 
		    
		  
		    String a = stmt.getString(1);
		    System.out.println(a);
		}
		
		
		String sql9 = "{call insertarSeguro(?,?)}"; 
		try (Connection con = GestorBD.conectar(); 
		   
		     CallableStatement stmt = con.prepareCall(sql9)) { 
		    
		   
			stmt.setString(1, "maria");
			stmt.setInt(2, -1);

			stmt.execute();
		    
		  
		   
		}
		
		listar();
	}

	private void listar() throws SQLException {
		String sql1 = "{Call listar}";
		try (Connection con = GestorBD.conectar(); CallableStatement stmt = con.prepareCall(sql1)) {
			var rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("nombre") + " " + rs.getInt("salario"));
			}

		}

	}

}
