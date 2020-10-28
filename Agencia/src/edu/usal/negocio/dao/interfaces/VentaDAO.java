package edu.usal.negocio.dao.interfaces;

import edu.usal.controller.ClienteController;
import edu.usal.controller.VueloController;
import edu.usal.controller.FormaDePagoController;
import edu.usal.negocio.dto.Venta;

import java.util.List;



public interface VentaDAO {
	
	public List<Venta> cargarVentas(ClienteController clientContr
			, VueloController vueloContr, FormaDePagoController formaDPContr);
	
	public void guardarVentas(List<Venta> lVentas);
	
	
}
