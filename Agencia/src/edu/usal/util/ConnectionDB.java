package edu.usal.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

	public static Connection con = null;
	private String url = null;

	public static Connection getConnection() {
		if (con == null) {
			new ConnectionDB();
		}
		return con;
	}

	public ConnectionDB() {

		// cargar propiedades
		PropertiesUtil prop = null;
		try {
			prop = new PropertiesUtil();

		} catch (IOException e) {
			IOGeneral.println(e.toString());
		}

		url = prop.url();
		try {
			System.out.println("...cargando driver");
			Class.forName(prop.driver());
			System.out.println("Driver cargado");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el driver: " + e);
		}

		try {
			System.out.println("...Conectando al DB");
			con = DriverManager.getConnection(url, prop.user(), prop.password());

			System.out.println("Conexion exitosa");
		} catch (SQLException e) {
			System.out.println("Error al conectarme a la DB: " + e);
		}
	}

	public static void RollBack(Connection con) throws Exception {
		try {
			if (con != null) {
				con.rollback();
			} else {
				throw new Exception("No se pudo realizar el RollBack en la DB");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
