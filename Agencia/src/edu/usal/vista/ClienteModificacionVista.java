package edu.usal.vista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usal.controller.DireccionController;
import edu.usal.controller.PasajeroFrecuenteController;
import edu.usal.controller.PasaporteController;
import edu.usal.controller.TelefonoController;
import edu.usal.negocio.dto.Direccion;
import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.negocio.dto.Pasaporte;
import edu.usal.negocio.dto.Telefono;

public class ClienteModificacionVista {

	public ClienteModificacionVista() {
	}

	public void formulario_de_modificacion(HttpServletRequest request, HttpServletResponse response,
			PasaporteController pasapContr, TelefonoController telefContr, PasajeroFrecuenteController pasajFContr,
			DireccionController direcContr) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Modificacion de Cliente</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Modificacion de Cliente</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
		out.append("<article>");
		out.append("<form action=\"ClienteServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Modificacion de Cliente</b>");
		out.append("</th>");
		out.append("<tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Nombre: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Nombre\"");
		out.append(
				"name=\"nombre\"pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\">Apellido: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Apellido\"");
		out.append(
				"name=\"apellido\"pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">DNI: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar DNI\"");
		out.append("name=\"dni\"pattern=\"^[0-9]{1,8}$\" title=\"Hasta 8 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"4\">CUIL: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar CUIL\"");
		out.append("name=\"cuil\"pattern=\"^[0-9]{11}$\" title=\"11 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"5\">Pasaporte: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"nroPasaporte\">");
		for (Pasaporte pasaporte : pasapContr.getlPasaportes()) {
			out.append("<option value=\"");
			out.append(pasaporte.getnroPasaporte());
			out.append("\">");
			out.append(pasaporte.getnroPasaporte());
		}
		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"6\">Fecha de Nacimiento: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"6\" placeholder=\"mm-dd-yyyy\"");
		out.append(
				"name=\"fechNac\"pattern=\"^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"7\">EMail: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"email\" name=\"email\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"8\">ID Telefono: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"idTelefono\">");
		for (Telefono telefono : telefContr.getlTelefonos()) {
			out.append("<option value=\"");
			out.append(Integer.toString(telefono.getIdTelefono()));
			out.append("\">");
			out.append(Integer.toString(telefono.getIdTelefono()));
		}
		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"9\">Numero de Pasajero Frecuente: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"nroPF\">");
		for (PasajeroFrecuente pasajeroFrecuente : pasajFContr.getlPasajeroFrecuentes()) {
			out.append("<option value=\"");
			out.append(pasajeroFrecuente.getNroPF());
			out.append("\">");
			out.append(pasajeroFrecuente.getNroPF());
		}
		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"10\">Direccion: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"idDireccion\">");
		for (Direccion direccion : direcContr.getlDirecciones()) {
			out.append("<option value=\"");
			out.append(Integer.toString(direccion.getIdDireccion()));
			out.append("\">");
			out.append(Integer.toString(direccion.getIdDireccion()));
		}
		out.append("</select>");
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
