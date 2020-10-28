package edu.usal.controller;

import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dao.factory.LineaAereaFactory;
import edu.usal.negocio.dao.interfaces.LineaAereaDAO;

import java.util.List;
import java.util.ArrayList;



public class LineaAereaController {
	
	
	private List<LineaAerea> lLineaAereas = null;	
	
	public LineaAereaController() {
		this.lLineaAereas = new ArrayList<LineaAerea>();
	}
	
	public List<LineaAerea> getlLineaAereas() {
		return lLineaAereas;
	}


	public void setlLineaAereas(List<LineaAerea> lLineaAereas) {
		this.lLineaAereas = lLineaAereas;
	}

	
	

	public void cargarLineaAereas(String implementacion, AlianzaController alianContr) {
		
		
		LineaAereaDAO lineaAereaDAO = LineaAereaFactory.getImplementacion(implementacion);

		this.lLineaAereas = lineaAereaDAO.cargarLineaAereas(alianContr);
		
		
		
		//de tabla parametrica
	}
	
	
	public void guardarLineaAereas(String implementacion) {
		
		LineaAereaDAO lineaAereaDAO = LineaAereaFactory.getImplementacion(implementacion);
		
		lineaAereaDAO.guardarLineaAereas(this.lLineaAereas);
				
	}
	
	

	
	public LineaAerea conseguirLineaAerea(String idLAerea) {

		for (LineaAerea lineaAerea : this.lLineaAereas) {
			if (lineaAerea.getIdLAerea().equals(idLAerea)) {
				return lineaAerea;
			}
		}
		return null;
	}
	
}
