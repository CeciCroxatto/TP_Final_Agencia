package edu.usal.negocio.dao.implementaciones.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.usal.controller.LineaAereaController;
import edu.usal.negocio.dao.interfaces.PasajeroFrecuenteDAO;
import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.util.ConnectionDB;

public class PasajeroFrecuenteDAOImpleSQL implements PasajeroFrecuenteDAO {

	final String INSERT = "insert into PasajeroFrecuente (NRO_PF, LINAERID, CATEGORIA, clienteid) SELECT ?, ?, ?, cliente.IDCLIENTE  FROM cliente where cliente.cuil = ?";
	final String SELECT = "SELECT * FROM PasajeroFrecuente pf inner join LineaAerea la on la.IDLAEREA = pf.LINAERID WHERE CLIENTEID = ?";
	final String DELETE = "DELETE PasajeroFrecuente where NRO_PF = ?";
	final String UPDATE = "UPDATE PasajeroFrecuente SET LINAERID = ?, CATEGORIA = ? where NRO_PF = ?";

	public PasajeroFrecuenteDAOImpleSQL() {

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public String vecesPasajeroFrecuente(String nroPF) {

		Connection con = null;
		ResultSet res = null;
		String cantidad = null;
		PreparedStatement ps = null;

		try {
			con = ConnectionDB.getConnection();
//			Statement q = con.createStatement();
//			res = q.executeQuery("Select count(*) as columna from pasajeroFrecuente where NRO_PF = " + nroPF);

			ps = con.prepareStatement("Select count(*) as columna from pasajeroFrecuente where NRO_PF = ? ");
			ps.setString(1, nroPF);

			res = ps.executeQuery();

			while (res.next()) {
				cantidad = res.getString("columna");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();

			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}

		return cantidad;

	}

	public int agregarPasajeroFrecuente(PasajeroFrecuente pasajeroFrecuente, String cuil, Connection con) {
		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(INSERT);
			// NRO_PF, LINAERID, CATEGORIA cuil

			ps.setString(1, pasajeroFrecuente.getNroPF());
			ps.setString(2, pasajeroFrecuente.getLineaAerea().getIdLAerea());
			ps.setString(3, pasajeroFrecuente.getCategoria());
			ps.setString(4, cuil);
			registrosModificados = ps.executeUpdate();

			System.out.println("Pasajero Frecuente agregado");

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

	public PasajeroFrecuente conseguirPasajeroFrecuente(int iDCliente, Connection con, LineaAereaController linAerContr)
			throws IOException {

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
				lDatos.add("No se encontro un Pasajero Frecuente con ese Numero de CUIL");
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

		LineaAerea lineaAerea = linAerContr.conseguirLineaAerea_porNombre(lDatos.get(5));

//		public PasajeroFrecuente(String nroPF, LineaAerea lineaAerea, String categoria) 
		return new PasajeroFrecuente(lDatos.get(0), lineaAerea, lDatos.get(2));

	}

	public int bajarPasajeroFrecuente(PasajeroFrecuente pasajeroFrecuente, Connection con) throws IOException {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(DELETE);

			ps.setString(1, pasajeroFrecuente.getNroPF());
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

	public int modificarPasajeroFrecuente(PasajeroFrecuente pasajeroFrecuente, Connection con) {
		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, pasajeroFrecuente.getLineaAerea().getIdLAerea());
			ps.setString(2, pasajeroFrecuente.getCategoria());
			ps.setString(3, pasajeroFrecuente.getNroPF());
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
