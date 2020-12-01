package edu.usal.vista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usal.controller.AlianzaController;
import edu.usal.negocio.dto.Alianza;

public class LineaAereaAltaVista {

	public LineaAereaAltaVista() {

	}

	public void formularioAlta(HttpServletRequest request, HttpServletResponse response, AlianzaController alianContr)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Alta de Linea Aerea</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Alta de Linea Aerea</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
		out.append("<article>");
		out.append("<form action=\"LineaAereaServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Alta de Linea Aerea</b>");
		out.append("</th>");
		out.append("<tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Codigo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Codigo\"");
		out.append("name=\"idLAerea\" pattern=\"^[A-Z]{2}$\" title=\"Dos mayusculas\" required>");
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\"> Nombre: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Nombre\"");
		out.append(
				"name=\"nombre\" pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">Alianza: </label>");
		out.append("</td>");
		out.append("<td>");
//out.append("<input type=\"text\" id=\"3\" placeholder=\"Ingresar idAlianza\"name=\"idAlianza\" required>");
		out.append("<select name=\"idAlianza\">");

		for (Alianza alianza : alianContr.getlAlianzas()) {
			out.append("<option value=\"");
			out.append(alianza.getIdAlianza());
			out.append("\">");
			out.append(alianza.getIdAlianza());
		}

		out.append("</select>");
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
