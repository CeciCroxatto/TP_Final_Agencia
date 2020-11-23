package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.file.ClienteDAOImpleFile;
import edu.usal.negocio.dao.implementaciones.sql.ClienteDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.ClienteDAO;


public class ClienteFactory {

	public static ClienteDAO getImplementacion(String imple) {
		if (imple.equals("file")) {
			return new ClienteDAOImpleFile();

		}
		
		if (imple.equals("SQL")) {
			return new ClienteDAOImpleSQL();
		}
		
		return null;
	}
}