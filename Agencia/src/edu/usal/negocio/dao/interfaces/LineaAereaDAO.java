package edu.usal.negocio.dao.interfaces;

import edu.usal.controller.AlianzaController;
import edu.usal.negocio.dto.LineaAerea;

import java.util.List;


public interface LineaAereaDAO {
	
	public List<LineaAerea> cargarLineaAereas(AlianzaController alianContr);
	
	public void guardarLineaAereas(List<LineaAerea> lLineaAereas);

}
