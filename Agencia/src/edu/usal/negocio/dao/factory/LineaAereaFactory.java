package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementaciones.file.LineaAereaDAOImpleFile;
import edu.usal.negocio.dao.interfaces.LineaAereaDAO;



public class LineaAereaFactory {

	
	public static LineaAereaDAO getImplementacion(String imple) {
		if (imple.equals("file")) {
			return new LineaAereaDAOImpleFile();
		}
		return null;
	}
	
	
}
