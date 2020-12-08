package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.sql.PasaporteDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.PasaporteDAO;

public class PasaporteFactory {

	public static PasaporteDAO getImplementacion(String imple) {
		if (imple.equals("SQL")) {
			return new PasaporteDAOImpleSQL();
		}

		return null;
	}

}
