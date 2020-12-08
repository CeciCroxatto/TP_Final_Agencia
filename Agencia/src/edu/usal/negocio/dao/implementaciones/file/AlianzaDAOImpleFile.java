package edu.usal.negocio.dao.implementaciones.file;

import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.AlianzaDAO;
import edu.usal.negocio.dto.Alianza;
import edu.usal.util.FileIO;

public class AlianzaDAOImpleFile implements AlianzaDAO {

	private List<Alianza> lAlianzaDAO;

	@Override
	public List<Alianza> cargarAlianzas() {

		this.lAlianzaDAO = new ArrayList<Alianza>();
		FileIO IOfile = new FileIO();
		String idAlianza = null;

		for (String s : IOfile.cargarLista("Alianza")) {
			idAlianza = s.substring(0, 3);
			lAlianzaDAO.add(new Alianza(idAlianza, s));
		}

		return this.lAlianzaDAO;
	}
}
