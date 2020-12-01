package edu.usal.controller;

import java.util.List;
import java.util.ArrayList;

import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dto.Alianza;

public class PasajeroFrecuenteController {

	private List<PasajeroFrecuente> lPasajeroFrecuentes = null;

	public PasajeroFrecuenteController() {
		this.lPasajeroFrecuentes = new ArrayList<PasajeroFrecuente>();
	}

	public List<PasajeroFrecuente> getlPasajeroFrecuentes() {
		return lPasajeroFrecuentes;
	}

	public void setlPasajeroFrecuentes(List<PasajeroFrecuente> lPasajeroFrecuentes) {
		this.lPasajeroFrecuentes = lPasajeroFrecuentes;
	}

	public void cargarPasajerosFrecuentes() {

		this.lPasajeroFrecuentes = new ArrayList();

		this.lPasajeroFrecuentes.add(new PasajeroFrecuente("AA11111111",
				new LineaAerea("AA", "American Airlines", new Alianza("One", "Oneworld")), "Platinum"));
		this.lPasajeroFrecuentes.add(new PasajeroFrecuente("AA22222222",
				new LineaAerea("AA", "American Airlines", new Alianza("One", "Oneworld")), "Platinum"));
		this.lPasajeroFrecuentes.add(new PasajeroFrecuente("AR33333333",
				new LineaAerea("AR", "Aerolineas Argentinas", new Alianza("Sky", "SkyTeam")), "Plus"));
		this.lPasajeroFrecuentes.add(new PasajeroFrecuente("AR44444444",
				new LineaAerea("AR", "Aerolineas Argentinas", new Alianza("Sky", "SkyTeam")), "Plus"));
		this.lPasajeroFrecuentes.add(new PasajeroFrecuente("LA12121212",
				new LineaAerea("LA", "Latam", new Alianza("One", "Oneworld")), "Premium"));
		this.lPasajeroFrecuentes.add(new PasajeroFrecuente("LA13131313",
				new LineaAerea("LA", "Latam", new Alianza("One", "Oneworld")), "Classic"));
		this.lPasajeroFrecuentes.add(new PasajeroFrecuente("AN23232323",
				new LineaAerea("AN", "Air New Zealand", new Alianza("Sta", "Star Alliance")), "Classic"));
		this.lPasajeroFrecuentes.add(new PasajeroFrecuente("LR24242424",
				new LineaAerea("LR", "Lufthansa Regional", new Alianza("Sta", "Star Alliance")), "Classic"));
	}

	public PasajeroFrecuente conseguirPasajeroFrecuente(String nroPF) {

		for (PasajeroFrecuente pasajeroFrecuente : this.lPasajeroFrecuentes) {
			if (pasajeroFrecuente.getNroPF().equals(nroPF)) {
				return pasajeroFrecuente;
			}
		}
		return null;
	}

}
