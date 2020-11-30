package edu.usal.controller;

import edu.usal.negocio.dto.Aeropuerto;
import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dto.Vuelo;
import edu.usal.negocio.dao.factory.VueloFactory;
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
	
	public int modificarVuelo(int idVuelo, String numeroVuelo, int asientosTotales, int asientosDisponibles, String idAeropuertoSalida,
			String idAeropuertoLlegada, String fechSalidaS, String fechLlegadaS, String horasVuelo, String idLAerea) {

		VueloDAOImpleSQL vueloDAOImpleSQL = new VueloDAOImpleSQL();
		return vueloDAOImpleSQL.modificarVuelo(idVuelo, numeroVuelo, asientosTotales, asientosDisponibles, idAeropuertoSalida,
				idAeropuertoLlegada, fechSalidaS, fechLlegadaS, horasVuelo, idLAerea);

	}
	
	
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			formularioAlta(request, response);
			break;
		case "Baja":
			formularioBaja_por_NroVueloYFechaSalida(request, response);
			break;
		case "Consulta":
			formulario_de_consulta_por_NroVueloYFechaSalida(request, response);
			break;
		case "Modificacion":
			formulario_de_modificacion_por_ID(request, response);
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

	public void formularioAlta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Alta de Vuelo</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Alta de Vuelo</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
		out.append("<article>");
		out.append("<form action=\"VueloServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Alta de Vuelo</b>");
		out.append("</th>");
		out.append("<tr>");

//		out.append("<tr>");
//		out.append("<td>");
//		out.append("<label for=\"1\">Linea Aerea: </label>");
//		out.append("</td>");
//		out.append("<td>");
//		out.append("<select name=\"idLAerea\">");
//		this.linAerContr = new LineaAereaController();
//		this.linAerContr.cargarLineaAereas("SQL");
//		for (LineaAerea lineaAerea : this.linAerContr.getlLineaAereas()) {
//			out.append("<option value=\"");
//			out.append(lineaAerea.getIdLAerea());
//			out.append("\">");
//			out.append(lineaAerea.getIdLAerea());
//		}
//		out.append("</select>");
//		out.append("</td>");
//		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Linea Aerea: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingrese ID de Linea Aerea que exista\"");
		out.append("name=\"idLAerea\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\">Numero de Vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Numero de Vuelo\"");
		out.append("name=\"numeroVuelo\"pattern=\"^[0-9]{4}$\" title=\"4 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">Cantidad de Asientos Totales: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"3\" placeholder=\"Ingresar asientos totales\"");
		out.append("name=\"asientosTotales\"pattern=\"^[0-9]{0,3}$\" title=\"Hasta 3 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"4\">Cantidad de Asientos Disponibles: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"4\" placeholder=\"Ingresar asientos disponibles\"");
		out.append("name=\"asientosDisponibles\"pattern=\"^[0-9]{0,3}$\" title=\"Hasta 3 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"5\">Aeropuerto de Salida: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"aeropuertoSalida\">");
		this.aeropContr = new AeropuertoController();
		this.aeropContr.cargarAeropuertos();
		for (Aeropuerto aeropuerto : this.aeropContr.getlAeropuertos()) {
			out.append("<option value=\"");
			out.append(aeropuerto.getIdAeropuerto());
			out.append("\">");
			out.append(aeropuerto.getIdAeropuerto());
		}
		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"6\">Aeropuerto de Llegada: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"aeropuertoLlegada\">");
		for (Aeropuerto aeropuerto : this.aeropContr.getlAeropuertos()) {
			out.append("<option value=\"");
			out.append(aeropuerto.getIdAeropuerto());
			out.append("\">");
			out.append(aeropuerto.getIdAeropuerto());
		}
		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"7\">Fecha de Salida: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"7\" placeholder=\"mm-dd-yyyy\"");
		out.append(
				"name=\"fechSalidaS\"pattern=\"^(0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])[-](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"8\">Fecha de Llegada: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"8\" placeholder=\"mm-dd-yyyy\"");
		out.append(
				"name=\"fechLlegadaS\"pattern=\"^(0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])[-](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"9\">Horas de Vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"9\" placeholder=\"Ingresar tiempo de vuelo\"");
		out.append(
				"name=\"horasVuelo\"pattern=\"([01]?[0-9]|2[0-3]):[0-5][0-9]\" title=\"En formato hh:mm\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"boton\" value=\"Dar de alta\">");
		out.append("</th>");
		out.append("</tr>");
		out.append("</table>");
		out.append("</article>");
		out.append("</form>");
		out.append("<a href=Inicio.html>Volver al Inicio</a>");
		out.append("</article>");
		out.append("<hr>");
		out.append("</body>");
		out.append("</html>");
	}

	public void formularioBaja_por_NroVueloYFechaSalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Baja de Vuelo</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Baja de Vuelo</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
		out.append("<article>");
		out.append("<form action=\"VueloServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Baja de Vuelo</b>");
		out.append("</th>");
		out.append("<tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Numero de Vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Numero de Vuelo\"");
		out.append("name=\"numeroVuelo\"pattern=\"[A-Z]{2}-[0-9]{4}\" title=\"2 mayusculas - 4 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\">Fecha de Salida: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"6\" placeholder=\"mm-dd-yyyy\"");
		out.append(
				"name=\"fechSalidaS\"pattern=\"^(0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])[-](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"boton\" value=\"Dar de baja\">");
		out.append("</th>");
		out.append("</tr>");
		out.append("</table>");
		out.append("</article>");
		out.append("</form>");
		out.append("<a href=Inicio.html>Volver al Inicio</a>");
		out.append("</article>");
		out.append("<hr>");
		out.append("</body>");
		out.append("</html>");

	}

	public void formulario_de_consulta_por_NroVueloYFechaSalida(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Consulta de Vuelo</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Consulta de Vuelo</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
		out.append("<article>");
		out.append("<form action=\"VueloServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Consulta de Vuelo</b>");
		out.append("</th>");
		out.append("<tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Numero de Vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Numero de Vuelo\"");
		out.append("name=\"numeroVuelo\"pattern=\"[A-Z]{2}-[0-9]{4}\" title=\"2 mayusculas - 4 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\">Fecha de Salida: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"6\" placeholder=\"mm-dd-yyyy\"");
		out.append(
				"name=\"fechSalidaS\"pattern=\"^(0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])[-](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"boton\" value=\"Consultar\">");
		out.append("</th>");
		out.append("</tr>");
		out.append("</table>");
		out.append("</article>");
		out.append("</form>");
		out.append("<a href=Inicio.html>Volver al Inicio</a>");
		out.append("</article>");
		out.append("<hr>");
		out.append("</body>");
		out.append("</html>");

	}

	public void formulario_de_modificacion_por_ID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Modificacion de Vuelo</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Modificacion de Vuelo</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
		out.append("<article>");
		out.append("<form action=\"VueloServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Modificacion de Vuelo</b>");
		out.append("</th>");
		out.append("<tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"0\">ID del vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingrese ID del vuelo\"");
		out.append("name=\"idVuelo\" required>");
		out.append("</td>");
		out.append("</tr>");

//		out.append("<tr>");
//		out.append("<td>");
//		out.append("<label for=\"1\">Linea Aerea: </label>");
//		out.append("</td>");
//		out.append("<td>");
//		out.append("<select name=\"idLAerea\">");
//		this.linAerContr = new LineaAereaController();
//		this.linAerContr.cargarLineaAereas("SQL");
//		for (LineaAerea lineaAerea : this.linAerContr.getlLineaAereas()) {
//			out.append("<option value=\"");
//			out.append(lineaAerea.getIdLAerea());
//			out.append("\">");
//			out.append(lineaAerea.getIdLAerea());
//		}
//		out.append("</select>");
//		out.append("</td>");
//		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Linea Aerea: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingrese ID de Linea Aerea que exista\"");
		out.append("name=\"idLAerea\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\">Numero de Vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Numero de Vuelo\"");
		out.append("name=\"numeroVuelo\"pattern=\"^[0-9]{4}$\" title=\"4 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">Cantidad de Asientos Totales: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar asientos totales\"");
		out.append("name=\"asientosTotales\"pattern=\"^[0-9]{0,3}$\" title=\"Hasta 3 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"4\">Cantidad de Asientos Disponibles: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar asientos disponibles\"");
		out.append("name=\"asientosDisponibles\"pattern=\"^[0-9]{0,3}$\" title=\"Hasta 3 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"5\">Aeropuerto de Salida: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"aeropuertoSalida\">");
		this.aeropContr = new AeropuertoController();
		this.aeropContr.cargarAeropuertos();
		for (Aeropuerto aeropuerto : this.aeropContr.getlAeropuertos()) {
			out.append("<option value=\"");
			out.append(aeropuerto.getIdAeropuerto());
			out.append("\">");
			out.append(aeropuerto.getIdAeropuerto());
		}
		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"6\">Aeropuerto de Llegada: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"aeropuertoLlegada\">");
		for (Aeropuerto aeropuerto : this.aeropContr.getlAeropuertos()) {
			out.append("<option value=\"");
			out.append(aeropuerto.getIdAeropuerto());
			out.append("\">");
			out.append(aeropuerto.getIdAeropuerto());
		}
		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"7\">Fecha de Salida: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"6\" placeholder=\"mm-dd-yyyy\"");
		out.append(
				"name=\"fechSalidaS\"pattern=\"^(0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])[-](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"8\">Fecha de Llegada: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"6\" placeholder=\"mm-dd-yyyy\"");
		out.append(
				"name=\"fechLlegadaS\"pattern=\"^(0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])[-](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"9\">Horas de Vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"6\" placeholder=\"Ingresar tiempo de vuelo\"");
		out.append(
				"name=\"horasVuelo\"pattern=\"([01]?[0-9]|2[0-3]):[0-5][0-9]\" title=\"En formato hh:mm\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"boton\" value=\"Modificar\">");
		out.append("</th>");
		out.append("</tr>");
		out.append("</table>");
		out.append("</article>");
		out.append("</form>");
		out.append("<a href=Inicio.html>Volver al Inicio</a>");
		out.append("</article>");
		out.append("<hr>");
		out.append("</body>");
		out.append("</html>");

	}

}
