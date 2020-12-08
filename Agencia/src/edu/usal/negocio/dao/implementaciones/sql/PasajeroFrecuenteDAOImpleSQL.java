package edu.usal.negocio.dao.implementaciones.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.usal.negocio.dao.interfaces.PasajeroFrecuenteDAO;
import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.util.ConnectionDB;

public class PasajeroFrecuenteDAOImpleSQL implements PasajeroFrecuenteDAO {

	final String INSERT = "insert into PasajeroFrecuente (NRO_PF, LINAERID, CATEGORIA, clienteid) SELECT ?, ?, ?, cliente.IDCLIENTE  FROM cliente where cliente.cuil = ?";

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

		try {
			con = ConnectionDB.getConnection();
			Statement q = con.createStatement();
			res = q.executeQuery("Select count(*) as columna from pasajeroFrecuente where NRO_PF = " + nroPF);

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

	@Override
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

}
