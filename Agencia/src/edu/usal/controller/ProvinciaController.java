package edu.usal.controller;

import edu.usal.negocio.dao.factory.ProvinciaFactory;
import edu.usal.negocio.dao.interfaces.ProvinciaDAO;
import edu.usal.negocio.dto.Provincia;
import edu.usal.util.IOGeneral;

import java.util.List;
import java.util.ArrayList;

public class ProvinciaController {

	private List<Provincia> lProvincias = null;
	private String[] lProvinciasDescrip = null;

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public Provincia conseguirProvincia_porDescripcion(String descripcion) {

		cargarProvincias();

		for (Provincia provincia : this.lProvincias) {
			if (provincia.getDescripcion().equals(descripcion)) {
				return provincia;
			}
		}
		return null;
	}

	/*
	 * 
	 * Cargar datos: tabla archivo de texto
	 *
	 */

	public void cargarProvincias() {

		ProvinciaDAO provinciaDAO = ProvinciaFactory.getImplementacion("file");

		this.lProvincias = provinciaDAO.cargarProvincias();

		int cantPaises = this.lProvincias.size();
		this.lProvinciasDescrip = new String[cantPaises];

		for (int i = 0; i < cantPaises; i++) {
			this.lProvinciasDescrip[i] = this.lProvincias.get(i).getDescripcion();
		}

	}

	/*
	 * 
	 * Funciones en desuso
	 *
	 */

	public void imprimirListaProvincias() {
		IOGeneral.println("\nId\tDescripcion");
		for (Provincia p : this.lProvincias) {
			System.out.println(p.getIdProv() + "\t" + p.getDescripcion());
		}
	}

	public String[] getlProvinciasDescrip() {
		return lProvinciasDescrip;
	}

	public void setlProvinciasDescrip(String[] lProvinciasDescrip) {
		this.lProvinciasDescrip = lProvinciasDescrip;
	}

	public ProvinciaController() {
		this.lProvincias = new ArrayList<Provincia>();
	}

	public List<Provincia> getlProvincias() {
		return lProvincias;
	}

	public void setlProvincias(List<Provincia> lProvincias) {
		this.lProvincias = lProvincias;
	}

}