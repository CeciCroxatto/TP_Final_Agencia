package edu.usal.main;

import edu.usal.controller.*;

public class Ejecutar {

	public static void main(String[] args) {

		// de FILE
		ProvinciaController provContr = new ProvinciaController();
		AlianzaController alianContr = new AlianzaController();
		provContr.cargarProvincias("file");
		provContr.imprimirListaProvincias();
		alianContr.cargarAlianzas("file");
		alianContr.imprimirListaAlianzas();

		// PERSISTIBLES (en Archivos)

		// 1 Cliente FILE
		ClienteController clientContr = new ClienteController();
		clientContr.cargarClientes("file");
		clientContr.imprimirListaClientes();
		clientContr.guardarClientes("file");

		// 2 Lineas Aereas
		LineaAereaController lineaAContr = new LineaAereaController();
		lineaAContr.cargarLineaAereas("file");
		lineaAContr.guardarLineaAereas("file");

		// 3 Vuelos
		VueloController vueloContr = new VueloController();
		vueloContr.cargarVuelos("file");
		vueloContr.guardarVuelos("file");

		// 4 Ventas
		VentaController ventaContr = new VentaController();
		ventaContr.cargarVentas("file");
		ventaContr.guardarVentas("file");

	}

}
