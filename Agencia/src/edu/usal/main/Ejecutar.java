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
		LineaAereaController lineaAContr = new LineaAereaController(alianContr);
		VueloController vueloContr = new VueloController();
		VentaController ventaContr = new VentaController();
	
		// de FILE
		provContr.cargarProvincias("file");
//		provContr.imprimirListaProvincias();
		alianContr.cargarAlianzas("file");
		alianContr.imprimirListaAlianzas();
		
		// Hardcodeados
		telefContr.cargarTelefonos();
		pasajFContr.cargarPasajerosFrecuentes();
		pasapContr.cargarPasaportes();
		direcContr.cargarDirecciones();
		aeropContr.cargarAeropuertos();
		formaDPContr.cargarFormaDePagos();
		
		// PERSISTIBLES (en Base de Datos)
		// 1 Cliente FILE
//		clientContr.cargarClientes("file",pasapContr,telefContr,pasajFContr,direcContr);
//		clientContr.imprimirListaClientes();
//		clientContr.guardarClientes("file");
		// 1 Cliente DB
		
				
		//2 Lineas Aereas		
		lineaAContr.cargarLineaAereas("file");
//		lineaAContr.guardarLineaAereas("file");	
//		lineaAContr.cargarLineaAereas("SQL",alianContr);
//		lineaAContr.guardarLineaAereas("SQL");	
//		lineaAContr.guardarNuevaLineaAerea(lineaAContr.conseguirLineaAerea("AA"));
		
		//3 Vuelos
//		vueloContr.cargarVuelos("file",aeropContr,lineaAContr);
//		vueloContr.guardarVuelos("file");
		
		//4 Ventas
//		ventaContr.cargarVentas("file", clientContr, vueloContr, formaDPContr);
//		ventaContr.guardarVentas("file");
		

		
	}

}
