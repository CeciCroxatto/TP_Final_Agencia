package edu.usal.negocio.dao.implementaciones.file;

import edu.usal.controller.AeropuertoController;
import edu.usal.controller.LineaAereaController;
import edu.usal.negocio.dao.interfaces.VueloDAO;
import edu.usal.negocio.dto.Vuelo;

import java.util.ArrayList;
import java.util.List;

public class VueloDAOImpleFile implements VueloDAO {

	private List<Vuelo> lVueloDAO;

	/*
	 * 
	 * Funciones en desuso
	 *
	 */

	@Override
	public List<Vuelo> cargarVuelos(AeropuertoController aeropContr, LineaAereaController lineaAContr) {

		this.lVueloDAO = new ArrayList<Vuelo>();
//		FileIO IOfile = new FileIO();
//		String[] splitted = null;
//
////		# int idVuelo, String numeroVuelo, int asientosTotales
////		, int asientosDisponibles, String idAeropuerto Salida, String idAeropuerto Llegada
////		, Date fechSalida, Date fechLlegada, String horasVuelo, String idLAerea
//
//		int idVuelo = 0;
//		String numeroVuelo = null;
//		int asientosTotales = 0;
//		int asientosDisponibles = 0;
//		String idAeropuertoSalida = null;
//		String idAeropuertoLlegada = null;
//		Date fechSalida = null;
//		Date fechLlegada = null;
//		String horasVuelo = null;
//		String idLAerea = null;
//
//		Aeropuerto aeropuertoSalida = null;
//		Aeropuerto aeropuertoLlegada = null;
//		LineaAerea lineaAerea = null;
//
//		for (String s : IOfile.cargarLista("Vuelo")) {
//
//			splitted = s.split("[,]", 0);
//
//			idVuelo = Integer.parseInt(splitted[0]);
//			numeroVuelo = splitted[1];
//			asientosTotales = Integer.parseInt(splitted[2]);
//			asientosDisponibles = Integer.parseInt(splitted[3]);
//			idAeropuertoSalida = splitted[4];
//			idAeropuertoLlegada = splitted[5];
//
//			try {
//				fechSalida = new SimpleDateFormat("dd/mm/yyyy").parse(splitted[6]);
//				fechLlegada = new SimpleDateFormat("dd/mm/yyyy").parse(splitted[7]);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//
//			horasVuelo = splitted[8];
//			idLAerea = splitted[9];
//
////			public Vuelo(int idVuelo, String numeroVuelo, int asientosTotales, int asientosDisponibles,
////					 Date fechSalida, Date fechLlegada, String horasVuelo)
//			lVueloDAO.add(new Vuelo(idVuelo, numeroVuelo, asientosTotales, asientosDisponibles, fechSalida, fechLlegada,
//					horasVuelo));
//
//			aeropuertoSalida = aeropContr.conseguirAeropuerto(idAeropuertoSalida);
//			lVueloDAO.get(lVueloDAO.size() - 1).setAeropuertoSalida(aeropuertoSalida);
//			aeropuertoLlegada = aeropContr.conseguirAeropuerto(idAeropuertoLlegada);
//			lVueloDAO.get(lVueloDAO.size() - 1).setAeropuertoLlegada(aeropuertoLlegada);
//			lineaAerea = lineaAContr.conseguirLineaAerea(idLAerea);
//			lVueloDAO.get(lVueloDAO.size() - 1).setLineaAereaVuelo(lineaAerea);
//		}

		return this.lVueloDAO;

	}

//	public void guardarVuelos(List<Vuelo> lVuelos) {
//
//		FileIO IOfile = new FileIO();
//		List<String> lString = new ArrayList();
//
//		lString.add("# int idVuelo, String numeroVuelo, int asientosTotales"
//				+ ", int asientosDisponibles, String idAeropuerto Salida"
//				+ ", String idAeropuerto Llegada, Date fechSalida, Date fechLlegada"
//				+ ", String horasVuelo, String idLAerea");
//		lString.add("# usando IATA Codes para idAeropuerto "
//				+ ", idLAerea: https://www.iata.org/en/publications/directories/code-search/");
//
//		for (Vuelo v : lVuelos) {
//
//			lString.add(v.getIdVuelo() + "," + v.getNumeroVuelo() + "," + v.getAsientosTotales() + ","
//					+ v.getAsientosDisponibles() + "," + v.getAeropuertoSalida().getIdAeropuerto() + ","
//					+ v.getAeropuertoLlegada().getIdAeropuerto() + "," + v.getFechSalida().getDate() + "/"
//					+ (v.getFechSalida().getMonth() + 1) + "/" + v.getFechSalida().getYear() + ","
//					+ v.getFechLlegada().getDate() + "/" + (v.getFechLlegada().getMonth() + 1) + "/"
//					+ v.getFechLlegada().getYear() + "," + v.getHorasVuelo() + ","
//					+ v.getLineaAereaVuelo().getIdLAerea());
//		}
//
//		IOfile.guardarLista("Vuelo", lString);
//
//	}

	public List<Vuelo> getlVueloDAO() {
		return lVueloDAO;
	}

	public void setlVueloDAO(List<Vuelo> lVueloDAO) {
		this.lVueloDAO = lVueloDAO;
	}

}
