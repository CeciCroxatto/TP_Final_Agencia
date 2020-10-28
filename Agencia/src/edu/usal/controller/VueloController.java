package edu.usal.controller;

import edu.usal.negocio.dto.Vuelo;
import edu.usal.negocio.dao.factory.VueloFactory;
import edu.usal.negocio.dao.interfaces.VueloDAO;

import java.util.List;
import java.util.ArrayList;



public class VueloController {
	
	private List<Vuelo> lVuelos = null;
	
	public VueloController() {
		this.lVuelos = new ArrayList<Vuelo>();
	}

	
	public List<Vuelo> getlVuelos() {
		return lVuelos;
	}

	public void setlVuelos(List<Vuelo> lVuelos) {
		this.lVuelos = lVuelos;
	}





	public void cargarVuelos(String implementacion
			, AeropuertoController aeropContr, LineaAereaController lineaAContr) {
		
		
		VueloDAO vueloDAO = VueloFactory.getImplementacion(implementacion);

		this.lVuelos = vueloDAO.cargarVuelos(aeropContr, lineaAContr);
		
		
		
		//de tabla parametrica
	}
	
	
	public void guardarVuelos(String implementacion) {
		
		VueloDAO vueloDAO = VueloFactory.getImplementacion(implementacion);
		
		vueloDAO.guardarVuelos(this.lVuelos);
				
	}
	

	
	public Vuelo conseguirVuelo(int idVuelo) {

		for (Vuelo telefono : this.lVuelos) {
			if (telefono.getIdVuelo() == idVuelo) {
				return telefono;
			}
		}
		return null;
	}





}
