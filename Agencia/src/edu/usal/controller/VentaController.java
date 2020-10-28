package edu.usal.controller;

import edu.usal.negocio.dao.factory.VentaFactory;
import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dto.Venta;
import edu.usal.util.IOGeneral;

import java.util.List;
import java.util.ArrayList;

public class VentaController {
	
	
	private List<Venta> lVentas = null;

	public VentaController() {
		this.lVentas = new ArrayList<Venta>();
	}
	
	

	public List<Venta> getlVentas() {
		return lVentas;
	}



	public void setlVentas(List<Venta> lVentas) {
		this.lVentas = lVentas;
	}	
	
	
	


	public void cargarVentas(String implementacion, ClienteController clientContr
			, VueloController vueloContr, FormaDePagoController formaDPContr) {

		VentaDAO VentaDAO = VentaFactory.getImplementacion(implementacion);

		this.lVentas = VentaDAO.cargarVentas(clientContr, vueloContr, formaDPContr);

	}
	
	
	public void guardarVentas(String implementacion) {
		
		VentaDAO ventaDAO = VentaFactory.getImplementacion(implementacion);
		
		ventaDAO.guardarVentas(this.lVentas);
		
	}
	
	
	public Venta conseguirVenta(int idVenta) {

		for (Venta c : this.lVentas) {
			if (c.getIdVenta() == idVenta) {
				return c;
			}
		}
		return null;
	}

}
