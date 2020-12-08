package edu.usal.controller;

import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.factory.PaisFactory;
import edu.usal.negocio.dao.interfaces.PaisDAO;
import edu.usal.negocio.dto.Pais;
import edu.usal.util.IOGeneral;

public class PaisController {

	private List<Pais> lpaises = null;
	private String[] lPaisesDescrip = null;

	/*
	 * 
	 * Cargar datos: tabla parametrica
	 *
	 */

	public void cargarPaises() {

		PaisDAO paisDAO = PaisFactory.getImplementacion("SQL");

		this.lpaises = paisDAO.cargarPaises();

		int cantPaises = this.lpaises.size();
		this.lPaisesDescrip = new String[cantPaises];

		for (int i = 0; i < cantPaises; i++) {
			this.lPaisesDescrip[i] = this.lpaises.get(i).getDescripcion();
		}
//		System.out.println("Paises cargados");

	}

	public Pais conseguirPais_porDescripcion(String descripcion) {

		cargarPaises();

		for (Pais pais : this.lpaises) {
			if (pais.getDescripcion().equals(descripcion)) {
				return pais;
			}
		}
		return null;
	}

	/*
	 * 
	 * Funciones en desuso
	 *
	 */

	public void imprimirListapaises() {
		IOGeneral.println("\nId Pais\tDescripcion");
		for (Pais p : this.lpaises) {
			System.out.println(p.getIdPais() + "\t" + p.getDescripcion());
		}
	}

	public String[] getlPaisesDescrip() {
		return lPaisesDescrip;
	}

	public void setlPaisesDescrip(String[] lPaisesDescrip) {
		this.lPaisesDescrip = lPaisesDescrip;
	}

	public PaisController() {
		this.lpaises = new ArrayList<Pais>();
	}

	public List<Pais> getLpaises() {
		return lpaises;
	}

	public void setLpaises(List<Pais> lpaises) {
		this.lpaises = lpaises;
	}

}
