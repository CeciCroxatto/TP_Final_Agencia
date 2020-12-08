package edu.usal.main;

import edu.usal.controller.*;
import edu.usal.vista.MenuVista;

public class Ejecutar {

	public static void main(String[] args) {

		System.out.println("\n\nBienvenido!!\n\nCargando datos, por favor espere");

		// Cliente CRUD

		@SuppressWarnings("unused")
		MenuVista ventana = new MenuVista();

		// de FILE
		ProvinciaController provContr = new ProvinciaController();
		AlianzaController alianContr = new AlianzaController();
		provContr.cargarProvincias();
		provContr.imprimirListaProvincias();
		alianContr.cargarAlianzas();
		alianContr.imprimirListaAlianzas();

		// de Base de datos
		PaisController paisContr = new PaisController();
		paisContr.cargarPaises();
		paisContr.imprimirListapaises();

		// PERSISTIBLES (en Archivos)

		// 1 Cliente FILE
//		ClienteController clientContr = new ClienteController();
//		clientContr.cargarClientes("file");
//		clientContr.imprimirListaClientes();
//		clientContr.guardarClientes("file");

		// 2 Lineas Aereas
//		LineaAereaController lineaAContr = new LineaAereaController();
//		lineaAContr.cargarLineaAereas("file");
//		lineaAContr.guardarLineaAereas("file");

		// 3 Vuelos
//		VueloController vueloContr = new VueloController();
//		vueloContr.cargarVuelos("file");
//		vueloContr.guardarVuelos("file");

		// 4 Ventas
//		VentaController ventaContr = new VentaController();
//		ventaContr.cargarVentas("file");
//		ventaContr.guardarVentas("file");

		// Alta de aeropuerto
//		AeropuertoController aeropContr = new AeropuertoController();
//		aeropContr.cargarAeropuertos();
//		aeropContr.imprimirListaAeropuertos();
//		aeropContr.crearAeropuerto();

	}

}
