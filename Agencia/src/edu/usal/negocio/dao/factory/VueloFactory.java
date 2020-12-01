package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.file.VueloDAOImpleFile;
import edu.usal.negocio.dao.interfaces.VueloDAO;

public class VueloFactory {

	public static VueloDAO getImplementacion(String imple) {
		if (imple.equals("file")) {
			return new VueloDAOImpleFile();
		}
		return null;
	}
}
