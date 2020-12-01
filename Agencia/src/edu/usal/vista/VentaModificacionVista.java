package edu.usal.vista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usal.controller.FormaDePagoController;
import edu.usal.negocio.dto.FormaDePago;

public class VentaModificacionVista {

	public VentaModificacionVista() {
	}

	public void formulario_de_modificacion_por_CUILyNROVueloyFechaVenta(HttpServletRequest request,
			HttpServletResponse response, FormaDePagoController formPagContr) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Modificacion de Venta</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Modificacion de Venta</b>");
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
		out.append("<b>Modificacion de Venta</b>");
		out.append("</th>");
		out.append("<tr>");

		out.append("<th colspan=\"2\">");
		out.append("<b>Datos de busqueda</b>");
		out.append("</th>");

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
		out.append("<label for=\"2\">Numero de Vuelo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Numero de Vuelo\"");
		out.append("name=\"numeroVuelo\"pattern=\"[A-Z]{2}-[0-9]{4}\" title=\"2 mayusculas - 4 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">Fecha de la Venta: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"3\" placeholder=\"mm-dd-yyyy\"");
		out.append(
				"name=\"fechVenta\"pattern=\"^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<th colspan=\"2\">");
		out.append("<b>Datos a modificar</b>");
		out.append("</th>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"4\">Forma de pago: </label>");
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
		out.append("<label for=\"5\">Importe del Vuelo </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"5\" placeholder=\"Ingresar importe del vuelo\"");
		out.append("name=\"importe_vueloS\"pattern=\"^\\d*(\\.\\d{0,2})?$\" title=\"Hasta 2 decimales\" required>");
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
