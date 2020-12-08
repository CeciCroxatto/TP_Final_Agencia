package edu.usal.negocio.dao.implementaciones.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.usal.negocio.dao.interfaces.TelefonoDAO;
import edu.usal.negocio.dto.Telefono;

public class TelefonoDAOImpleSQL implements TelefonoDAO {

	final String INSERT = "insert into Telefono(TPERSONAL, TCELULAR, TLABORAL, clienteid) SELECT ?,	?, ?, cliente.IDCLIENTE  FROM cliente where cliente.cuil = ?";

	public TelefonoDAOImpleSQL() {

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	@Override
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

}
