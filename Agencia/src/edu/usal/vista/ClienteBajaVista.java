package edu.usal.vista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClienteBajaVista {

	public ClienteBajaVista() {

	}

	public void formularioBaja_por_CUIL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Baja de Cliente</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Baja de Cliente</b>");
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
		out.append("<b>Baja de Cliente</b>");
		out.append("</th>");
		out.append("<tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">CUIL: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar CUIL\"");
		out.append("name=\"cuil\"pattern=\"^[0-9]{11}$\" title=\"11 digitos\" required>");
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

}
