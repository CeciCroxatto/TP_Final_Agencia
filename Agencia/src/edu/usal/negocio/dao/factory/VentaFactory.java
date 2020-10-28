package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.file.VentaDAOImpleFile;
import edu.usal.negocio.dao.interfaces.VentaDAO;


public class VentaFactory {
	
	public static VentaDAO getImplementacion(String imple) {
		if (imple.equals("file")) {
			return new VentaDAOImpleFile();
		}
		return null;
	}

}
