package edu.usal.negocio.dao.implementaciones.file;

import edu.usal.controller.ClienteController;
import edu.usal.controller.FormaDePagoController;
import edu.usal.controller.VueloController;
import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dto.Venta;
import edu.usal.negocio.dto.Cliente;
import edu.usal.negocio.dto.Vuelo;
import edu.usal.negocio.dto.FormaDePago;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.usal.util.FileIO;
import edu.usal.util.IOGeneral;



public class VentaDAOImpleFile implements VentaDAO{
	
	private List<Venta> lVentaDAO;
	
	public List<Venta> getlVentaDAO() {
		return lVentaDAO;
	}

	public void setlVentaDAO(List<Venta> lVentaDAO) {
		this.lVentaDAO = lVentaDAO;
	}
	
	
	
	
	@Override
	public List<Venta> cargarVentas(ClienteController clientContr
			, VueloController vueloContr, FormaDePagoController formaDPContr){

		this.lVentaDAO = new ArrayList();
		FileIO IOfile = new FileIO();
		String[] splitted = null;
		
		int idVenta = 0;
		int idCliente = 0;
		int idVuelo = 0;
		Date fecha = null;
		int idPago = 0;
		
		Cliente cliente = null;
		Vuelo vuelo = null;
		FormaDePago formaDePago = null;
			

		for (String s : IOfile.cargarLista("Venta")) {

			//# int idVenta, int idCliente, int idVuelo, Date fecha, int idPago
			splitted = s.split("[,]", 0);
			
			idVenta = Integer.parseInt(splitted[0]);
			idCliente = Integer.parseInt(splitted[1]);
			idVuelo = Integer.parseInt(splitted[2]);
			fecha = IOGeneral.convertirADate(splitted[3]);
			idPago = Integer.parseInt(splitted[4]);
			
			cliente = clientContr.conseguirCliente(idCliente);
			vuelo = vueloContr.conseguirVuelo(idVuelo);
			formaDePago = formaDPContr.conseguirFormaDePago(idPago);
				
//			public Venta(int idVenta, Cliente cliente, Vuelo vuelo, Date fecha
//			, FormaDePago formaDePago)
			lVentaDAO.add(new Venta(idVenta, cliente, vuelo, fecha, formaDePago));	
		}
		return this.lVentaDAO;
	}

	
	
	@Override
	public void guardarVentas(List<Venta> lVentas) {
		
		FileIO IOfile = new FileIO();
		List<String> lString = new ArrayList();
		
		lString.add("# int idVenta, int idCliente, int idVuelo, Date fecha, int idPago");
		
		for (Venta v : lVentas) {
			
			lString.add(v.getIdVenta() + "," + v.getCliente().getIdCliente()
					 + "," + v.getVuelo().getIdVuelo()
					 + "," + v.getFecha().getDate() + "/" + (v.getFecha().getMonth()+1)
					 + "/" + v.getFecha().getYear() + "," + v.getFormaDePago().getidPago());
		}
		
		IOfile.guardarLista("Venta", lString);

	}
	
	
	
}
