package edu.usal.controller;

import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.factory.PaisFactory;
import edu.usal.negocio.dao.interfaces.PaisDAO;
import edu.usal.negocio.dto.Pais;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;
import edu.usal.util.IOGeneral;

public class PaisController {

	private List<Pais> lpaises;
	private String[] lPaisesDescrip;
	private ProvinciaController provContr;

	public PaisController() {
		this.lpaises = new ArrayList<Pais>();
		this.lPaisesDescrip = new String[0];
		this.provContr = new ProvinciaController();

	}

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

	public Pais conseguirPais_porDescripciones(String descripcionPais, String descripcionProvincia) {

		Pais pais = conseguirPais_porDescripcion(descripcionPais);

		for (Pais p : this.lpaises) {
			if (p.getDescripcion().equals(descripcionPais)) {

				if (descripcionPais.matches("Argentina")) {
					((PaisArgentina) pais)
							.setProvincia(this.provContr.conseguirProvincia_porDescripcion(descripcionProvincia));
				} else {
					((PaisOtro) pais).setProvEstado(descripcionProvincia);
				}

			}
		}
		return pais;
	}

	public String conseguirProvEstado_descripcion(Pais pais) {

		String descripcion = null;

		if (pais.getDescripcion().matches("Argentina")) {
			descripcion = ((PaisArgentina) pais).getProvincia().getDescripcion();
		} else {
			descripcion = ((PaisOtro) pais).getProvEstado();
		}

		return descripcion;
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

	public List<Pais> getLpaises() {
		return lpaises;
	}

	public void setLpaises(List<Pais> lpaises) {
		this.lpaises = lpaises;
	}

}
