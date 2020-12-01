package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.file.ProvinciaDAOImpleFile;
import edu.usal.negocio.dao.interfaces.ProvinciaDAO;

public class ProvinciaFactory {

	public static ProvinciaDAO getImplementacion(String imple) {
		if (imple.equals("file")) {
			return new ProvinciaDAOImpleFile();
		}

		return null;
	}

}
