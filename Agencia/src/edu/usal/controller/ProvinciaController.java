package edu.usal.controller;

import edu.usal.negocio.dao.factory.ProvinciaFactory;
import edu.usal.negocio.dao.interfaces.ProvinciaDAO;
import edu.usal.negocio.dto.Provincia;
import edu.usal.util.IOGeneral;

import java.util.List;
import java.util.ArrayList;

public class ProvinciaController {

	private List<Provincia> lProvincias = null;

	public ProvinciaController() {
		this.lProvincias = new ArrayList<Provincia>();
	}

	public List<Provincia> getlProvincias() {
		return lProvincias;
	}

	public void setlProvincias(List<Provincia> lProvincias) {
		this.lProvincias = lProvincias;
	}

	public void cargarProvincias(String implementacion) {

		ProvinciaDAO provinciaDAO = ProvinciaFactory.getImplementacion(implementacion);

		this.lProvincias = provinciaDAO.cargarProvincias();

	}

	public void imprimirListaProvincias() {
		IOGeneral.println("\nId\tDescripcion");
		for (Provincia p : this.lProvincias) {
			System.out.println(p.getIdProv() + "\t" + p.getDescripcion());
		}
	}

}