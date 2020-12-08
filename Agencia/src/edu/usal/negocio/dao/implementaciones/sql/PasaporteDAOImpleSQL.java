package edu.usal.negocio.dao.implementaciones.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import edu.usal.negocio.dao.interfaces.PasaporteDAO;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;
import edu.usal.negocio.dto.Pasaporte;
import edu.usal.util.ConnectionDB;

public class PasaporteDAOImpleSQL implements PasaporteDAO {

	final String INSERT = "insert into Pasaporte(IDPASAPORTE, PAISID, PROVINCIA, AUTORIDAD, FECEMISION,VTO,cliente) SELECT ?,?,?,?,?,?, cliente.IDCLIENTE  FROM cliente where cliente.cuil = ?";

	public PasaporteDAOImpleSQL() {

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public String vecesPasaporte(String nroPasaporte) {

		Connection con = null;
		ResultSet res = null;
		String cantidad = null;

		try {
			con = ConnectionDB.getConnection();
			Statement q = con.createStatement();
			res = q.executeQuery("Select count(*) as columna  from pasaporte where IDPASAPORTE = " + nroPasaporte);

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
	public int agregarPasaporte(Pasaporte pasaporte, String cuil, Connection con) {
		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(INSERT);
			// IDPASAPORTE, PAISID, PROVINCIA, AUTORIDAD, FECEMISION, VTO, cuil

			ps.setString(1, pasaporte.getnroPasaporte());
			ps.setString(2, Integer.toString(pasaporte.getPais().getIdPais()));

			if (pasaporte.getPais().getDescripcion().matches("Argentina")) {
				ps.setString(3, ((PaisArgentina) pasaporte.getPais()).getProvincia().getDescripcion());
			} else {
				ps.setString(3, ((PaisOtro) pasaporte.getPais()).getProvEstado());
			}

			ps.setString(4, pasaporte.getAutoridad());
			ps.setString(5, new SimpleDateFormat("MM-dd-yyyy").format(pasaporte.getFechEmision()));
			ps.setString(6, new SimpleDateFormat("MM-dd-yyyy").format(pasaporte.getVencimiento()));
			ps.setString(7, cuil);
			registrosModificados = ps.executeUpdate();

			System.out.println("Pasaporte agregado");

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

}
