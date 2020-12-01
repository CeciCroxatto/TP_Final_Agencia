package edu.usal.controller;

import edu.usal.negocio.dto.Aeropuerto;
import edu.usal.negocio.dto.Pais;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;
import edu.usal.negocio.dto.Provincia;

import java.util.List;
import java.util.ArrayList;

public class AeropuertoController {

	private List<Aeropuerto> lAeropuertos = null;

	public AeropuertoController() {
		this.lAeropuertos = new ArrayList<Aeropuerto>();

	}

	public List<Aeropuerto> getlAeropuertos() {
		return lAeropuertos;
	}

	public void setlAeropuertos(List<Aeropuerto> lAeropuertos) {
		this.lAeropuertos = lAeropuertos;
	}

	public void cargarAeropuertos() {

		this.lAeropuertos = new ArrayList();

		Provincia provincia1 = new Provincia("CHU", "Chubut");
		Pais pais1 = new PaisArgentina(054, "Argentina", provincia1);
		this.lAeropuertos.add(new Aeropuerto("PMY", "Puerto Madryn", pais1)); // Aeropuerto El Tehuelche

		Provincia provincia2 = new Provincia("LP", "La Pampa");
		Pais pais2 = new PaisArgentina(054, "Argentina", provincia2);
		this.lAeropuertos.add(new Aeropuerto("RSA", "Santa Rosa", pais2)); // Aeropuerto de Santa Rosa

		Pais pais3 = new PaisOtro(055, "Brasil", "Florianopolis");
		this.lAeropuertos.add(new Aeropuerto("FLN", "Florianopolis", pais3));

		Pais pais4 = new PaisOtro(598, "Uruguay", "Montevideo");
		this.lAeropuertos.add(new Aeropuerto("ATI", "Artigas", pais4));

		Pais pais5 = new PaisOtro(598, "Uruguay", "Colonia");
		this.lAeropuertos.add(new Aeropuerto("CYR", "Colonia del Sacramento", pais5));

		Provincia provincia6 = new Provincia("COR", "Cordoba");
		Pais pais6 = new PaisArgentina(054, "Argentina", provincia6);
		this.lAeropuertos.add(new Aeropuerto("COR", "Cordoba", pais6));
	}

	public Aeropuerto conseguirAeropuerto(String idAeropuerto) {

		for (Aeropuerto aeropuerto : this.lAeropuertos) {
			if (aeropuerto.getIdAeropuerto().equals(idAeropuerto)) {
				return aeropuerto;
			}
		}
		return null;
	}

}
