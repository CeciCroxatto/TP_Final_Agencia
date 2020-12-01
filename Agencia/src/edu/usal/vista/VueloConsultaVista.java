package edu.usal.vista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VueloConsultaVista {

	public VueloConsultaVista() {
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
}
