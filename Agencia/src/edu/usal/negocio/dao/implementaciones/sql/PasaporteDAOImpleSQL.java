package edu.usal.negocio.dao.implementaciones.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.usal.controller.PaisController;
import edu.usal.negocio.dao.interfaces.PasaporteDAO;
import edu.usal.negocio.dto.Pais;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;
import edu.usal.negocio.dto.Pasaporte;
import edu.usal.util.ConnectionDB;

public class PasaporteDAOImpleSQL implements PasaporteDAO {

	final String INSERT = "insert into Pasaporte(IDPASAPORTE, PAISID, PROVINCIA, AUTORIDAD, FECEMISION,VTO,cliente) SELECT ?,?,?,?,?,?, cliente.IDCLIENTE  FROM cliente where cliente.cuil = ?";
	final String SELECT = "SELECT * FROM Pasaporte p inner join pais pa on p.PAISID = pa.IDPAIS WHERE p.cliente = ?";
	final String DELETE = "DELETE Pasaporte where IDPASAPORTE = ?";
	final String UPDATE = "UPDATE Pasaporte SET PAISID = ?, PROVINCIA = ?, AUTORIDAD = ?, FECEMISION = ?, VTO = ? where IDPASAPORTE = ?";

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
		PreparedStatement ps = null;

		try {
			con = ConnectionDB.getConnection();
//			Statement q = con.createStatement();
			
			ps = con.prepareStatement("Select count(*) as cantidad from pasaporte where IDPASAPORTE = ? ");
			ps.setString(1, nroPasaporte);
			
			res = ps.executeQuery();
			
			
//			res = q.executeQuery("Select count(*) from pasaporte where IDPASAPORTE = " + nroPasaporte);

			while (res.next()) {
				cantidad = res.getString("cantidad");
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
			ps.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(pasaporte.getFechEmision()));
			ps.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(pasaporte.getVencimiento()));
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

	public Pasaporte conseguirPasaporte(int iDCliente, Connection con, PaisController paisContr) throws IOException {

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
				lDatos.add("No se encontro un Pasaporte con ese Numero de CUIL");
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

		Pais pais = paisContr.conseguirPais_porDescripciones(lDatos.get(8), lDatos.get(2));

		Date fechEmision = null;
		Date vencimiento = null;

		try {
			fechEmision = new SimpleDateFormat("yyyy-MM-dd").parse(lDatos.get(4));
			vencimiento = new SimpleDateFormat("yyyy-MM-dd").parse(lDatos.get(5));
		} catch (ParseException e) {
			e.printStackTrace();
		}

//		public Pasaporte(String nroPasaporte, Pais pais, String autoridad, Date fechEmision, Date vencimiento) 
		return new Pasaporte(lDatos.get(0), pais, lDatos.get(3), fechEmision, vencimiento);

	}

	public int bajarPasaporte(Pasaporte pasaporte, Connection con) throws IOException {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(DELETE);

			ps.setString(1, pasaporte.getnroPasaporte());
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

	public int modificarPasaporte(Pasaporte pasaporte, Connection con) {
		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(UPDATE);

			ps.setInt(1, pasaporte.getPais().getIdPais());

			if (pasaporte.getPais().getDescripcion().matches("Argentina")) {
				ps.setString(2, ((PaisArgentina) pasaporte.getPais()).getProvincia().getDescripcion());
			} else {
				ps.setString(2, ((PaisOtro) pasaporte.getPais()).getProvEstado());
			}

			ps.setString(3, pasaporte.getAutoridad());
			ps.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(pasaporte.getFechEmision()));
			ps.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(pasaporte.getVencimiento()));
			ps.setString(6, pasaporte.getnroPasaporte());
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

}
