package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.sql.PaisDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.PaisDAO;

public class PaisFactory {

	public static PaisDAO getImplementacion(String imple) {
		if (imple.equals("SQL")) {
			return new PaisDAOImpleSQL();
		}

		return null;
	}

}
