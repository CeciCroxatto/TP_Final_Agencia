package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.file.AlianzaDAOImpleFile;
import edu.usal.negocio.dao.interfaces.AlianzaDAO;

public class AlianzaFactory {

	public static AlianzaDAO getImplementacion(String imple) {
		if (imple.equals("file")) {
			return new AlianzaDAOImpleFile();
		}

		return null;
	}
}
