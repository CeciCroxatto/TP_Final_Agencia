package edu.usal.main;

import edu.usal.controller.*;



public class Ejecutar {

	public static void main(String[] args) {
	
		ProvinciaController provContr = new ProvinciaController();
		AlianzaController alianContr = new AlianzaController();
		
		TelefonoController telefContr = new TelefonoController();	
		PasajeroFrecuenteController pasajFContr = new PasajeroFrecuenteController();
		PasaporteController pasapContr = new PasaporteController();
		DireccionController direcContr = new DireccionController();
		
		AeropuertoController aeropContr = new AeropuertoController();
		FormaDePagoController formaDPContr = new FormaDePagoController();
		
		ClienteController clientContr = new ClienteController();
		LineaAereaController lineaAContr = new LineaAereaController();
		VueloController vueloContr = new VueloController();
		VentaController ventaContr = new VentaController();
	
		
		provContr.cargarProvincias("file");
		provContr.imprimirListaProvincias();
		alianContr.cargarAlianzas("file");
		alianContr.imprimirListaAlianzas();
		
		telefContr.cargarTelefonos();
		pasajFContr.cargarPasajerosFrecuentes();
		pasapContr.cargarPasaportes();
		direcContr.cargarDirecciones();
		
		aeropContr.cargarAeropuertos();
		formaDPContr.cargarFormaDePagos();
		

		clientContr.cargarClientes("file",pasapContr,telefContr,pasajFContr,direcContr);
//		clientContr.imprimirListaClientes();
		clientContr.guardarClientes("file");
				
		lineaAContr.cargarLineaAereas("file",alianContr);
		lineaAContr.guardarLineaAereas("file");
		
		vueloContr.cargarVuelos("file",aeropContr,lineaAContr);
		vueloContr.guardarVuelos("file");
		
		ventaContr.cargarVentas("file", clientContr, vueloContr, formaDPContr);
		ventaContr.guardarVentas("file");
		
		
	}

}
