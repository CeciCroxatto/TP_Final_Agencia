package edu.usal.negocio.dao.implementaciones.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.usal.negocio.dao.interfaces.TelefonoDAO;
import edu.usal.negocio.dto.Telefono;
import edu.usal.util.ConnectionDB;

public class TelefonoDAOImpleSQL implements TelefonoDAO {

	final String INSERT = "insert into Telefono(TPERSONAL, TCELULAR, TLABORAL, clienteid) SELECT ?,	?, ?, cliente.IDCLIENTE  FROM cliente where cliente.cuil = ?";
	final String SELECT = "SELECT * FROM Telefono WHERE CLIENTEID = ?";
	final String DELETE = "DELETE Telefono where IDTELEFONO = ?";
	final String UPDATE = "UPDATE Telefono SET TPERSONAL = ?, TCELULAR = ?, TLABORAL = ? where IDTELEFONO = ?";

	public TelefonoDAOImpleSQL() {

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public int agregarTelefono(Telefono telefono, String cuil, Connection con) {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(INSERT);
			// TPERSONAL, TCELULAR, TLABORAL, cuil

			ps.setString(1, telefono.getPersonal());
			ps.setString(2, telefono.getCelular());
			ps.setString(3, telefono.getLaboral());
			ps.setString(4, cuil);
			registrosModificados = ps.executeUpdate();

			System.out.println("Telefono agregado");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return registrosModificados;
	}

	public Telefono conseguirTelefono(int iDCliente, Connection con) throws IOException {

		ArrayList<String> lDatos = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet res = null;

		try {
			ps = con.prepareStatement(SELECT);

			ps.setInt(1, iDCliente);

			res = ps.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			int cantColumnas = rsmd.getColumnCount();
			boolean bandera = true;

			if (res.next()) {
				while (bandera) {

					if (res != null) {
						for (int i = 1; i <= cantColumnas; i++) {
							lDatos.add(res.getString(i));
						}
						bandera = res.next();

					}
				}
			} else {
				lDatos.add("No se encontro un Telefono con ese Numero de CUIL");
			}

//			con.close();
//		if (con.isClosed())
//			System.out.println("Conexion cerrada");

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

//		public Telefono(int idTelefono, String personal, String celular, String laboral)
		return new Telefono(Integer.parseInt((lDatos.get(0))), lDatos.get(1), lDatos.get(2), lDatos.get(3));

	}

	public int bajarTelefono(Telefono telefono, Connection con) throws IOException {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, telefono.getIdTelefono());
			registrosModificados = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				ps.close();

			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}

		return registrosModificados;
	}

	public int modificarTelefono(Telefono telefono, Connection con) {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(UPDATE);
			// TPERSONAL, TCELULAR, TLABORAL, cuil

			ps.setString(1, telefono.getPersonal());
			ps.setString(2, telefono.getCelular());
			ps.setString(3, telefono.getLaboral());
			ps.setInt(4, telefono.getIdTelefono());
			registrosModificados = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				ps.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return registrosModificados;
	}

}
