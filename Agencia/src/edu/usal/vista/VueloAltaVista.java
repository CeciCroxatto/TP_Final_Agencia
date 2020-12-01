package edu.usal.vista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usal.controller.AeropuertoController;
import edu.usal.negocio.dto.Aeropuerto;

public class VueloAltaVista {

	public VueloAltaVista() {
	}

	public void formularioAlta(HttpServletRequest request, HttpServletResponse response,
			AeropuertoController aeropContr) throws ServletException, IOException {

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
		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Numero de Vuelo\"");
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
		for (Aeropuerto aeropuerto : aeropContr.getlAeropuertos()) {
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
		for (Aeropuerto aeropuerto : aeropContr.getlAeropuertos()) {
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
}
