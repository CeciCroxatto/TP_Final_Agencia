package edu.usal.negocio.dao.implementaciones.file;

import edu.usal.negocio.dao.interfaces.ProvinciaDAO;
import edu.usal.negocio.dto.Provincia;
import edu.usal.util.FileIO;

import java.util.ArrayList;
import java.util.List;

public class ProvinciaDAOImpleFile implements ProvinciaDAO {

	private List<Provincia> lProvinciaDAO;

	public List<Provincia> getlProvinciaDAO() {
		return lProvinciaDAO;
	}

	public void setlProvinciaDAO(List<Provincia> lProvinciaDAO) {
		this.lProvinciaDAO = lProvinciaDAO;
	}

	@Override
	public List<Provincia> cargarProvincias() {

		this.lProvinciaDAO = new ArrayList();
		FileIO IOfile = new FileIO();
		String[] splitted = null;

		for (String s : IOfile.cargarLista("Provincia")) {

			splitted = s.split("[,]", 0);
			lProvinciaDAO.add(new Provincia(splitted[0], splitted[1]));
		}

		return this.lProvinciaDAO;
	}

}
