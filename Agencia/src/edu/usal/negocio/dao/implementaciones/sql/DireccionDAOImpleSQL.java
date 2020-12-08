package edu.usal.negocio.dao.implementaciones.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.usal.negocio.dao.interfaces.DireccionDAO;
import edu.usal.negocio.dto.Direccion;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;

public class DireccionDAOImpleSQL implements DireccionDAO {

	final String INSERT = "insert into Direccion (calle, altura, ciudad, PAISID, provincia,	 cp, clienteid) SELECT ?, ?, ?, ?, ?, ?, cliente.IDCLIENTE  FROM cliente where cliente.cuil = ?";

	public DireccionDAOImpleSQL() {

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	@Override
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

}
