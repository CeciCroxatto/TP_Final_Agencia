package edu.usal.negocio.dao.interfaces;

import edu.usal.controller.AeropuertoController;
import edu.usal.controller.LineaAereaController;
import edu.usal.negocio.dto.Vuelo;

import java.util.List;

public interface VueloDAO {

	public List<Vuelo> cargarVuelos(AeropuertoController aeropContr, LineaAereaController lineaAContr);

}
