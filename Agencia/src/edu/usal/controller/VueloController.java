package edu.usal.controller;

import edu.usal.negocio.dto.Aeropuerto;
import edu.usal.negocio.dto.Vuelo;
import edu.usal.vista.VueloAltaVista;
import edu.usal.vista.VueloBajaVista;
import edu.usal.vista.VueloConsultaVista;
import edu.usal.vista.VueloModificacionVista;
import edu.usal.negocio.dao.factory.VueloFactory;
import edu.usal.negocio.dao.implementaciones.file.VueloDAOImpleFile;
import edu.usal.negocio.dao.implementaciones.sql.VueloDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.VueloDAO;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VueloController extends HttpServlet {

	private List<Vuelo> lVuelos = null;
	private LineaAereaController linAerContr;
	private AeropuertoController aeropContr;
	private VueloAltaVista vuAltaVista = null;
	private VueloBajaVista vuBajaVista = null;
	private VueloConsultaVista vuConsultaVista = null;
	private VueloModificacionVista vuModificacionVista = null;

	public VueloController() {
		this.lVuelos = new ArrayList<Vuelo>();
		this.linAerContr = new LineaAereaController();
		this.aeropContr = new AeropuertoController();
	}

	public List<Vuelo> getlVuelos() {
		return lVuelos;
	}

	public void setlVuelos(List<Vuelo> lVuelos) {
		this.lVuelos = lVuelos;
	}

	public void cargarVuelos(String implementacion) {

		this.linAerContr = new LineaAereaController();
		this.linAerContr.cargarLineaAereas(implementacion);
		this.aeropContr = new AeropuertoController();
		this.aeropContr.cargarAeropuertos();

		VueloDAO vueloDAO = VueloFactory.getImplementacion(implementacion);

		this.lVuelos = vueloDAO.cargarVuelos(this.aeropContr, this.linAerContr);

	}

	public void guardarVuelos(String implementacion) {

		VueloDAO vueloDAO = VueloFactory.getImplementacion(implementacion);

		if (vueloDAO instanceof VueloDAOImpleFile)
			((VueloDAOImpleFile) vueloDAO).guardarVuelos(this.lVuelos);

	}

	public Vuelo conseguirVuelo(int idVuelo) {

		for (Vuelo telefono : this.lVuelos) {
			if (telefono.getIdVuelo() == idVuelo) {
				return telefono;
			}
		}
		return null;
	}

	public Date conseguirFechaDeSalida_por_ID(int idVuelo) {

		Date fechaSalida = null;

		VueloDAOImpleSQL vueloDAOImpleSQL = new VueloDAOImpleSQL();
		fechaSalida = vueloDAOImpleSQL.conseguirFechaDeSalida_por_ID(idVuelo);

		return fechaSalida;

	}

	public Date conseguirFechaDeLlegada_por_ID(int idVuelo) {

		Date fechaSalida = null;

		VueloDAOImpleSQL vueloDAOImpleSQL = new VueloDAOImpleSQL();
		fechaSalida = vueloDAOImpleSQL.conseguirFechaDeLlegada_por_ID(idVuelo);

		return fechaSalida;

	}

	public int crearVuelo(String numeroVuelo, int asientosTotales, int asientosDisponibles, String idAeropuertoSalida,
			String idAeropuertoLlegada, String fechSalidaS, String fechLlegadaS, String horasVuelo, String idLAerea) {

		VueloDAOImpleSQL vueloDAOImpleSQL = new VueloDAOImpleSQL();
		return vueloDAOImpleSQL.crearVuelo(numeroVuelo, asientosTotales, asientosDisponibles, idAeropuertoSalida,
				idAeropuertoLlegada, fechSalidaS, fechLlegadaS, horasVuelo, idLAerea);

	}

	public int bajarVuelo(String numeroVuelo, String fechSalidaS) {

		VueloDAOImpleSQL vueloDAOImpleSQL = new VueloDAOImpleSQL();
		return vueloDAOImpleSQL.bajarVuelo(numeroVuelo, fechSalidaS);

	}

	public String consultarVuelo(String numeroVuelo, String fechSalidaS) {

		VueloDAOImpleSQL vueloDAOImpleSQL = new VueloDAOImpleSQL();
		return vueloDAOImpleSQL.consultarVuelo(numeroVuelo, fechSalidaS);

	}

	public int modificarVuelo(int idVuelo, String numeroVuelo, int asientosTotales, int asientosDisponibles,
			String idAeropuertoSalida, String idAeropuertoLlegada, String fechSalidaS, String fechLlegadaS,
			String horasVuelo, String idLAerea) {

		VueloDAOImpleSQL vueloDAOImpleSQL = new VueloDAOImpleSQL();
		return vueloDAOImpleSQL.modificarVuelo(idVuelo, numeroVuelo, asientosTotales, asientosDisponibles,
				idAeropuertoSalida, idAeropuertoLlegada, fechSalidaS, fechLlegadaS, horasVuelo, idLAerea);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			this.vuAltaVista = new VueloAltaVista();
			this.aeropContr = new AeropuertoController();
			this.aeropContr.cargarAeropuertos();
			vuAltaVista.formularioAlta(request, response, aeropContr);
			break;
		case "Baja":
			this.vuBajaVista = new VueloBajaVista();
			vuBajaVista.formularioBaja_por_NroVueloYFechaSalida(request, response);
			break;
		case "Consulta":
			this.vuConsultaVista = new VueloConsultaVista();
			vuConsultaVista.formulario_de_consulta_por_NroVueloYFechaSalida(request, response);
			break;
		case "Modificacion":
			this.vuModificacionVista = new VueloModificacionVista();
			this.aeropContr = new AeropuertoController();
			this.aeropContr.cargarAeropuertos();
			vuModificacionVista.formulario_de_modificacion_por_ID(request, response, aeropContr);
			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int registrosAlterados = -20;
		String valor = request.getParameter("boton"); // name
		response.setContentType("text/html");

		String numeroVuelo = null;
		int asientosTotales = -6;
		int asientosDisponibles = -6;
		String idAeropuertoSalida = null;
		String idAeropuertoLlegada = null;
		String fechSalidaS = null;
		String fechLlegadaS = null;
		Date fechSalidaD = null;
		Date fechLlegadaD = null;
		String horasVuelo = null;
		String idLAerea = null;

		switch (valor) {

		case "Dar de alta": // value

			numeroVuelo = request.getParameter("numeroVuelo");
			asientosTotales = Integer.parseInt(request.getParameter("asientosTotales"));
			asientosDisponibles = Integer.parseInt(request.getParameter("asientosDisponibles"));
			idAeropuertoSalida = request.getParameter("aeropuertoSalida");
			idAeropuertoLlegada = request.getParameter("aeropuertoLlegada");
			fechSalidaS = request.getParameter("fechSalidaS");
			fechLlegadaS = request.getParameter("fechLlegadaS");
			try {
				fechSalidaD = new SimpleDateFormat("mm-dd-yyyy").parse(fechSalidaS);
				fechLlegadaD = new SimpleDateFormat("mm-dd-yyyy").parse(fechLlegadaS);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			horasVuelo = request.getParameter("horasVuelo");
			idLAerea = request.getParameter("idLAerea");

			registrosAlterados = -20;
			if (asientosDisponibles <= asientosTotales) {
				if (!idAeropuertoSalida.matches(idAeropuertoLlegada)) {
					if (fechLlegadaD.compareTo(fechSalidaD) >= 0) {

						registrosAlterados = crearVuelo(idLAerea + "-" + numeroVuelo, asientosTotales,
								asientosDisponibles, idAeropuertoSalida, idAeropuertoLlegada, fechSalidaS, fechLlegadaS,
								horasVuelo, idLAerea);

						if (registrosAlterados > 0) {
							response.getWriter().append("Vuelo guardado en la Base correctamente").println();
						} else {
							response.getWriter().append("No se pudo ejecutar la operación").println();
						}

					} else {
						response.getWriter().append("No puede ser el dia de Salida posterior al día de Llegada")
								.println();
					}
				} else {
					response.getWriter().append("No puede ser el mismo Aeropuerto de Salida que de Llegada").println();
				}
			} else {
				response.getWriter().append("No puede haber más asientos disponibles que asientos totales").println();
			}

			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Dar de baja":
			numeroVuelo = request.getParameter("numeroVuelo");
			fechSalidaS = request.getParameter("fechSalidaS");
			registrosAlterados = -20;
			registrosAlterados = bajarVuelo(numeroVuelo, fechSalidaS);
			if (registrosAlterados > 0) {
				response.getWriter().append("Vuelo dado de baja de la Base correctamente").println();
			} else {
				response.getWriter().append("No se pudo ejecutar la operación").println();
				response.getWriter().append("No existe un Vuelo con ese Numero para esa Fecha de Salida.").println();
			}
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Consultar":
			numeroVuelo = request.getParameter("numeroVuelo");
			fechSalidaS = request.getParameter("fechSalidaS");
			String texto = consultarVuelo(numeroVuelo, fechSalidaS);
			response.getWriter().append(texto).println();
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Modificar":

			int idVuelo = Integer.parseInt(request.getParameter("idVuelo"));
			numeroVuelo = request.getParameter("numeroVuelo");
			asientosTotales = Integer.parseInt(request.getParameter("asientosTotales"));
			asientosDisponibles = Integer.parseInt(request.getParameter("asientosDisponibles"));
			idAeropuertoSalida = request.getParameter("aeropuertoSalida");
			idAeropuertoLlegada = request.getParameter("aeropuertoLlegada");
			fechSalidaS = request.getParameter("fechSalidaS");
			fechLlegadaS = request.getParameter("fechLlegadaS");
			try {
				fechSalidaD = new SimpleDateFormat("mm-dd-yyyy").parse(fechSalidaS);
				fechLlegadaD = new SimpleDateFormat("mm-dd-yyyy").parse(fechLlegadaS);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			horasVuelo = request.getParameter("horasVuelo");
			idLAerea = request.getParameter("idLAerea");

			registrosAlterados = -20;
			if (asientosDisponibles <= asientosTotales) {
				if (!idAeropuertoSalida.matches(idAeropuertoLlegada)) {
					if (fechLlegadaD.compareTo(fechSalidaD) >= 0) {

						registrosAlterados = modificarVuelo(idVuelo, idLAerea + "-" + numeroVuelo, asientosTotales,
								asientosDisponibles, idAeropuertoSalida, idAeropuertoLlegada, fechSalidaS, fechLlegadaS,
								horasVuelo, idLAerea);

						if (registrosAlterados > 0) {
							response.getWriter().append("Vuelo actualizado en la Base correctamente").println();
						} else {
							response.getWriter().append("No se pudo ejecutar la operación").println();
						}

					} else {
						response.getWriter().append("No puede ser el dia de Salida posterior al día de Llegada")
								.println();
					}
				} else {
					response.getWriter().append("No puede ser el mismo Aeropuerto de Salida que de Llegada").println();
				}
			} else {
				response.getWriter().append("No puede haber más asientos disponibles que asientos totales").println();
			}

			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		}

	}

}
