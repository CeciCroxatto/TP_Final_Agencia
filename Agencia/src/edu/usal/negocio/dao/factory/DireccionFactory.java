package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.sql.DireccionDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.DireccionDAO;

public class DireccionFactory {

	public static DireccionDAO getImplementacion(String imple) {
		if (imple.equals("SQL")) {
			return new DireccionDAOImpleSQL();
		}

		return null;
	}

}
