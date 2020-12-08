package edu.usal.negocio.dao.implementaciones.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.PaisDAO;
import edu.usal.negocio.dto.Pais;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;
import edu.usal.util.ConnectionDB;

public class PaisDAOImpleSQL implements PaisDAO {

	public PaisDAOImpleSQL() {

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	@Override
	public List<Pais> cargarPaises() {

		ArrayList<Pais> lpaises = new ArrayList<>();
		ResultSet res;

		try (Connection db = ConnectionDB.getConnection();
				Statement q = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
			res = q.executeQuery("Select * from Pais order by NOMBRE_PAIS asc");
			while (res.next()) {

				if (res.getString("NOMBRE_PAIS").matches("Argentina")) {
					PaisArgentina paisArgentina = new PaisArgentina();
					paisArgentina.setIdPais(res.getInt("IDPAIS"));
					paisArgentina.setDescripcion(res.getString("NOMBRE_PAIS"));
					lpaises.add(paisArgentina);
				} else {
					PaisOtro paisOtro = new PaisOtro();
					paisOtro.setIdPais(res.getInt("IDPAIS"));
					paisOtro.setDescripcion(res.getString("NOMBRE_PAIS"));
					lpaises.add(paisOtro);
				}

			}
			res.close();
//			db.close();
//			if (db.isClosed())
//				System.out.println("Conexion cerrada");

		} catch (Exception e) {
			e.printStackTrace();
		}
//			finally {
//	        rs.close();
//	        db.close();
//	    }

		return lpaises;

	}

}
