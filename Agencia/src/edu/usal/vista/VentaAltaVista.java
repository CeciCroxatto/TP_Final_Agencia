package edu.usal.vista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usal.controller.FormaDePagoController;
import edu.usal.negocio.dto.FormaDePago;

public class VentaAltaVista {

	public VentaAltaVista() {
	}

	public void formularioAlta(HttpServletRequest request, HttpServletResponse response,
			FormaDePagoController formPagContr) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Alta de Venta</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Alta de Venta</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
		out.append("<article>");
		out.append("<form action=\"VentaServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Alta de Venta</b>");
		out.append("</th>");
		out.append("<tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">CUIL de Cliente: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingrese CUIL de Cliente\"");
		out.append("name=\"cuil\"pattern=\"^[0-9]{11}$\" title=\"11 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\">ID de Vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar ID de Vuelo\"");
		out.append("name=\"idVueloS\" pattern=\"^[0-9]$\" title=\"Solo digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">Forma de pago: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<select name=\"idPagoS\">");
		for (FormaDePago formaDePago : formPagContr.getlFormaDePagos()) {
			out.append("<option value=\"");
			out.append(String.valueOf(formaDePago.getidPago()));
			out.append("\">");
			out.append(String.valueOf(formaDePago.getidPago()));
		}
		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"4\">Importe del Vuelo </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"4\" placeholder=\"Ingresar importe del vuelo\"");
		out.append("name=\"importe_vueloS\"pattern=\"^\\d*(\\.\\d{0,2})?$\" title=\"Hasta 2 decimales\" required>");
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
