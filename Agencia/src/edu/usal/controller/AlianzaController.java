package edu.usal.controller;

import edu.usal.negocio.dao.factory.AlianzaFactory;
import edu.usal.negocio.dao.interfaces.AlianzaDAO;
import edu.usal.negocio.dto.Alianza;
import edu.usal.util.IOGeneral;

import java.util.List;
import java.util.ArrayList;

public class AlianzaController {

	private List<Alianza> lAlianzas = null;

	public AlianzaController() {
		this.lAlianzas = new ArrayList<Alianza>();
	}

	public List<Alianza> getlAlianzas() {
		return lAlianzas;
	}

	public void setlAlianzas(List<Alianza> lAlianzas) {
		this.lAlianzas = lAlianzas;
	}

	public void cargarAlianzas() {

		AlianzaDAO AlianzaDAO = AlianzaFactory.getImplementacion("file");

		this.lAlianzas = AlianzaDAO.cargarAlianzas();

	}

	public Alianza conseguirAlianza(String idAlianza) {

		for (Alianza alianza : this.lAlianzas) {
			if (alianza.getIdAlianza().equals(idAlianza)) {
				return alianza;
			}
		}
		return null;
	}

	public void imprimirListaAlianzas() {
		IOGeneral.println("\nID\tNombre");
		for (Alianza a : this.lAlianzas) {
			System.out.println(a.getIdAlianza() + "\t" + a.getNombre());
		}
	}
}
