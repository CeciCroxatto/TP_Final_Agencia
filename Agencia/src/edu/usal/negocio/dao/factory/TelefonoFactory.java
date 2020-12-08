package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.sql.TelefonoDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.TelefonoDAO;

public class TelefonoFactory {

	public static TelefonoDAO getImplementacion(String imple) {
		if (imple.equals("SQL")) {
			return new TelefonoDAOImpleSQL();
		}

		return null;
	}

}
