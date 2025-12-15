package pasarela_datos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public class GestorBD {

	public static Connection conectar() throws SQLException {

		return DriverManager.getConnection("jdbc:mysql://localhost/", "root", "Andres!");

	}

	public static void correrScriptCreacion() {

		try (Connection conexion = conectar()) {

			Reader reader = new FileReader("src/main/resources/scriptExamen.sql");

			ScriptRunner scriptRunner = new ScriptRunner(conexion);

			scriptRunner.setLogWriter(null);
			scriptRunner.setAutoCommit(false);
			scriptRunner.runScript(reader);

			conexion.commit();

		} catch (SQLException | FileNotFoundException e) {

			e.printStackTrace();
		}

	}

}
