package edu.usal.negocio.dao.implementaciones.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.usal.controller.PaisController;
import edu.usal.negocio.dao.interfaces.DireccionDAO;
import edu.usal.negocio.dto.Direccion;
import edu.usal.negocio.dto.Pais;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;
import edu.usal.util.ConnectionDB;

public class DireccionDAOImpleSQL implements DireccionDAO {

	final String INSERT = "insert into Direccion (calle, altura, ciudad, PAISID, provincia,	 cp, clienteid) SELECT ?, ?, ?, ?, ?, ?, cliente.IDCLIENTE  FROM cliente where cliente.cuil = ?";
	final String SELECT = "SELECT * FROM Direccion d  inner join pais pa on d.PAISID = pa.IDPAIS WHERE CLIENTEID = ?";
	final String DELETE = "DELETE Direccion where IDDIRECCION = ?";
	final String UPDATE = "UPDATE Direccion SET CALLE = ?, ALTURA = ?, CIUDAD = ?, PAISID = ?, PROVINCIA = ?, CP = ? where IDDIRECCION = ?";

	public DireccionDAOImpleSQL() {

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public int agregarDireccion(Direccion direccion, String cuil, Connection con) {
		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(INSERT);
			// calle, altura, ciudad, PAISID, provincia, cp, cuil

			ps.setString(1, direccion.getCalle());
			ps.setString(2, direccion.getAltura());
			ps.setString(3, direccion.getCiudad());
			ps.setString(4, Integer.toString(direccion.getPais().getIdPais()));

			if (direccion.getPais().getDescripcion().matches("Argentina")) {
				ps.setString(5, ((PaisArgentina) direccion.getPais()).getProvincia().getDescripcion());
			} else {
				ps.setString(5, ((PaisOtro) direccion.getPais()).getProvEstado());
			}

			ps.setString(6, direccion.getCp());
			ps.setString(7, cuil);
			registrosModificados = ps.executeUpdate();

			System.out.println("Direccion agregada");

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

	public Direccion conseguirDireccion(int iDCliente, Connection con, PaisController paisContr) throws IOException {

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
				lDatos.add("No se encontro una Direccion con ese Numero de CUIL");
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

		Pais pais = paisContr.conseguirPais_porDescripciones(lDatos.get(9), lDatos.get(5));

//			public Direccion(int idDireccion, String calle, String altura, String ciudad, Pais pais, String cp) 
		return new Direccion(Integer.parseInt(lDatos.get(0)), lDatos.get(1), lDatos.get(2), lDatos.get(3), pais,
				lDatos.get(6));

	}

	public int bajarDireccion(Direccion direccion, Connection con) throws IOException {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, direccion.getIdDireccion());
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

	public int modificarDireccion(Direccion direccion, Connection con) {
		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, direccion.getCalle());
			ps.setString(2, direccion.getAltura());
			ps.setString(3, direccion.getCiudad());
			ps.setString(4, Integer.toString(direccion.getPais().getIdPais()));

			if (direccion.getPais().getDescripcion().matches("Argentina")) {
				ps.setString(5, ((PaisArgentina) direccion.getPais()).getProvincia().getDescripcion());
			} else {
				ps.setString(5, ((PaisOtro) direccion.getPais()).getProvEstado());
			}

			ps.setString(6, direccion.getCp());
			ps.setInt(7, direccion.getIdDireccion());
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
