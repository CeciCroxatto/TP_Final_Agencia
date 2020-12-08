package edu.usal.negocio.dao.implementaciones.sql;

import java.util.ArrayList;
import java.util.List;

import edu.usal.util.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dto.Alianza;
import edu.usal.controller.AlianzaController;
import edu.usal.negocio.dao.interfaces.LineaAereaDAO;

public class LineaAereaDAOImpleSQL implements LineaAereaDAO {

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	@Override
	public List<LineaAerea> cargarLineaAereas(AlianzaController alianContr) {

		ArrayList<LineaAerea> listaLineaAereas = new ArrayList<>();
		ResultSet res;
		Alianza alianza = null;

		try (Connection con = ConnectionDB.getConnection(); Statement q = con.createStatement();) {

			res = q.executeQuery("Select * from LineaAerea order by NOMBRE asc");
			while (res.next()) {

				alianza = alianContr.conseguirAlianza(res.getString("ALIANZA"));

				listaLineaAereas.add(new LineaAerea(res.getString("IDLAEREA"), res.getString("NOMBRE"), alianza));
			}
			res.close();
			con.close();
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaLineaAereas;

	}

	/*
	 * 
	 * Funciones que usan la GUI y sin Manager
	 *
	 */

	public int crearLineaAerea(LineaAerea lineaAerea) {

		Connection con = ConnectionDB.getConnection();
		Statement stm = null;
		int registrosAlterados = -302;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();

			try {
				registrosAlterados = stm.executeUpdate(
						"INSERT INTO LineaAerea VALUES('" + lineaAerea.getIdLAerea() + "','" + lineaAerea.getNombre()
								+ "','" + lineaAerea.getAlianza().getIdAlianza() + "','" + "1" + "')");
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					stm.close();
					ConnectionDB.RollBack(con);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			con.commit();
			con.close();
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return registrosAlterados;

	}

	public int bajarLineaAerea(String idLAerea) {
		Connection con = ConnectionDB.getConnection();
		Statement stm = null;
		int registrosAlterados = -22;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();

			registrosAlterados = stm
					.executeUpdate("update LineaAerea set ESTADO = 0 where IDLAEREA = '" + idLAerea + "'");

			con.commit();
			con.close();
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return registrosAlterados;

	}

	public String consultarLineaAerea(String idLAerea) {

		ResultSet res = null;
		String texto = "No se encontro una Linea Aerea con ese ID";

		try (Connection con = ConnectionDB.getConnection();
				Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			con.setAutoCommit(false);

			res = stm.executeQuery("Select * from LineaAerea where IDLAEREA = '" + idLAerea + "'");

			while (res.next()) {

				if (res != null) {

					String estado = null;
					if (res.getInt("ESTADO") == 0) {
						estado = "La Linea Aerea se encuentra dada de baja";
					} else {
						estado = "La Linea Aerea se encuentra dada de alta";
					}

					texto = "ID: " + res.getString("IDLAEREA") + "<br>" + " Nombre: " + res.getString("NOMBRE") + "<br>"
							+ "Alianza:" + res.getString("ALIANZA") + "<br>" + "Estado: " + estado + "<br>";
				}
			}

			res.close();
			con.close();
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			System.out.println("No se pudo conectar a la base ");
			e.printStackTrace();
			try {
//				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return texto;
	}

	public int verificarID(String idLAerea) {
		Connection con = ConnectionDB.getConnection();
		Statement stm = null;
		ResultSet res;
		int existe = -4;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();

			res = stm.executeQuery("select * from LineaAerea where IDLAEREA = '" + idLAerea + "'");

			while (res.next()) {

				existe = 1;

			}

			res.close();
			con.close();
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return existe;
	}

	public int modificarLineaAerea(String idLAerea, String nombre, String idAlianza, int estadoBoolean) {
		Connection con = ConnectionDB.getConnection();
		Statement stm = null;
		int registrosAlterados = -13;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();

			registrosAlterados = stm.executeUpdate("update LineaAerea set NOMBRE = '" + nombre + "', " + "ALIANZA = '"
					+ idAlianza + "', " + "ESTADO = " + estadoBoolean + " where IDLAEREA = '" + idLAerea + "'");

			con.commit();
			con.close();
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return registrosAlterados;

	}

}
