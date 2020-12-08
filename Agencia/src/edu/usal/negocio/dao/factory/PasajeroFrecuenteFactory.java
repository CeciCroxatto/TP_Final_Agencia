package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.sql.PasajeroFrecuenteDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.PasajeroFrecuenteDAO;

public class PasajeroFrecuenteFactory {

	public static PasajeroFrecuenteDAO getImplementacion(String imple) {
		if (imple.equals("SQL")) {
			return new PasajeroFrecuenteDAOImpleSQL();
		}

		return null;
	}

}
