package edu.usal.controller;

import edu.usal.negocio.dao.factory.VentaFactory;
import edu.usal.negocio.dao.implementaciones.sql.VentaDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dto.Cliente;
import edu.usal.negocio.dto.FormaDePago;
import edu.usal.negocio.dto.Venta;
import edu.usal.negocio.dto.Vuelo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VentaController extends HttpServlet {

	private List<Venta> lVentas = null;
	private ClienteController clienContr;
	private VueloController vuelContr;
	private FormaDePagoController formPagContr;

	public VentaController() {
		this.lVentas = new ArrayList<Venta>();
		this.clienContr = new ClienteController();
		this.vuelContr = new VueloController();
		this.formPagContr = new FormaDePagoController();
	}

	public List<Venta> getlVentas() {
		return lVentas;
	}

	public void setlVentas(List<Venta> lVentas) {
		this.lVentas = lVentas;
	}

	public void cargarVentas(String implementacion, ClienteController clientContr, VueloController vueloContr,
			FormaDePagoController formaDPContr) {

		VentaDAO VentaDAO = VentaFactory.getImplementacion(implementacion);

		this.lVentas = VentaDAO.cargarVentas(clientContr, vueloContr, formaDPContr);

	}

	public void guardarVentas(String implementacion) {

		VentaDAO ventaDAO = VentaFactory.getImplementacion(implementacion);

		ventaDAO.guardarVentas(this.lVentas);

	}

	public Venta conseguirVenta(int idVenta) {

		for (Venta c : this.lVentas) {
			if (c.getIdVenta() == idVenta) {
				return c;
			}
		}
		return null;
	}

	public String crearVenta(String cuil, int idVuelo, int idPago, double importe_vuelo, double importe_total) {

		VentaDAOImpleSQL ventaDAOImpleSQL = new VentaDAOImpleSQL();
		return ventaDAOImpleSQL.crearVenta(cuil, idVuelo, idPago, importe_vuelo, importe_total);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			formularioAlta(request, response);
			break;
//		case "Baja":
//			formularioBaja_por_CUIL(request, response);
//			break;
//		case "Consulta":
//			formulario_de_consulta(request, response);
//			break;
//		case "Modificacion":
//			formulario_de_modificacion(request, response);
//			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String texto = "vacio";
		String valor = request.getParameter("boton"); // name
		response.setContentType("text/html");

		String cuil = null;
		String idVueloS = null;
		String idPagoS = null;
		String importe_vueloS = null;
		int idVuelo = -5;
		int idPago = -55;
		double importe_vuelo = -555;
		double importe_total = -5555;
		double factorDeInteres = 0;

		switch (valor) {

		case "Dar de alta": // value
			cuil = request.getParameter("cuil");
			idVueloS = request.getParameter("idVueloS");
			idVuelo = Integer.parseInt(idVueloS);
			idPagoS = request.getParameter("idPagoS");
			idPago = Integer.parseInt(idPagoS);
			importe_vueloS = request.getParameter("importe_vueloS");
			importe_vuelo = Double.parseDouble(importe_vueloS);

			factorDeInteres = this.formPagContr.FactorDeInteres(idPago);
			importe_total = importe_vuelo * factorDeInteres;

			texto = "vacio";

			// dado el cuil, consigo fecha de emision de pasaporte
//			Date fechaEmisionPasaporte = this.clienContr.fechaEmisionPasaporte_por_CUIL(cuil);
			
			Date fechaEmisionPasaporte = Calendar.getInstance().getTime();
			fechaEmisionPasaporte.setDate(13); //dia
			fechaEmisionPasaporte.setMonth(2-1);//mes
			fechaEmisionPasaporte.setYear(00);


			if (fechaEmisionPasaporte != null) {

				// dado el idVuelo, consigo fecha de salida
//				Date fechaSalida = this.vuelContr.conseguirFechaDeSalida_por_ID(idVuelo);
				Date fechaSalida =  Calendar.getInstance().getTime();
				fechaSalida.setDate(13); //dia
				fechaSalida.setMonth(2-1);//mes
				fechaSalida.setYear(01);

				if (fechaSalida != null) {
					
					if (fechaSalida.compareTo(fechaEmisionPasaporte) > 0) {

//						Date fechaVencimientoPasaporte = this.clienContr.fechaVencimientoPasaporte_por_CUIL(cuil);
						Date fechaVencimientoPasaporte =  Calendar.getInstance().getTime();
						fechaVencimientoPasaporte.setYear(2000);
						
//						Date fechaLlegada = this.vuelContr.conseguirFechaDeLlegada_por_ID(idVuelo);
						Date fechaLlegada =  Calendar.getInstance().getTime();
						
						long diff = fechaVencimientoPasaporte.getTime() - fechaLlegada.getTime();
						
						
						
						if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 6 * 30) {

							texto = crearVenta(cuil, idVuelo, idPago, importe_vuelo, importe_total);

						} else {
							response.getWriter().append(
									"El vencimiento del Pasaporte debe ser por lo menos 6 meses despues de la fecha de llegada")
									.println();
						}

					} else {
						response.getWriter()
								.append("La fecha de salida tiene que ser posterior a la emision del pasaporte")
								.println();
					}

					if (texto.matches("OK")) {
						response.getWriter().append("Venta guardada en la Base correctamente").println();
					} else {
						response.getWriter().append("No se pudo ejecutar la operación").println();
						response.getWriter().append(texto).println();
					}

				} else {
					response.getWriter().append("El ID de Vuelo ingresado no existe").println();
				}

			} else {
				response.getWriter().append("El CUIL ingresado no corresponde a un Cliente").println();
			}

			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

//		case "Dar de baja":
//			cuil = request.getParameter("cuil");
//			registrosAlterados = -20;
//			registrosAlterados = bajarCliente(cuil);
//			if (registrosAlterados > 0) {
//				response.getWriter().append("Cliente dado de baja de la Base correctamente").println();
//			} else {
//				response.getWriter().append("No se pudo ejecutar la operación").println();
//				response.getWriter().append("No existe un Cliente con ese CUIL.").println();
//			}
//			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
//			break;
//
//		case "Consultar":
//			cuil = request.getParameter("cuil");
//			String texto = consultarCliente(cuil);
//			response.getWriter().append(texto).println();
//			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
//			break;
//					
//		case "Modificar":
//			
//			nombre = request.getParameter("nombre");
//			apellido = request.getParameter("apellido");
//			dni = request.getParameter("dni");
//			cuil = request.getParameter("cuil");
//			nroPasaporte = request.getParameter("nroPasaporte");
//			fechNac = request.getParameter("fechNac");  
//			email = request.getParameter("email");
//			idTelefono = Integer.parseInt(request.getParameter("idTelefono"));
//			nroPF = request.getParameter("nroPF");
//			idDireccion = Integer.parseInt(request.getParameter("idDireccion"));
//			
//			registrosAlterados = -20;
//			registrosAlterados = modificarCliente(nombre, apellido, dni, cuil, nroPasaporte, 
//					fechNac, email, idTelefono, nroPF, idDireccion);
//
//			if (registrosAlterados > 0) {
//				response.getWriter().append("Datos actualizados en la Base correctamente").println();
//			} else {
//				response.getWriter().append("No existe Cliente con ese CUIL").println();
//			}
//			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
//			
//			break;
//			
//			
		}

	}

	public void formularioAlta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

//		out.append("<tr>");
//		out.append("<td>");
//		out.append("<label for=\"1\">CUIL de Cliente: </label>");
//		out.append("</td>");
//		out.append("<td>");
//		out.append("<select name=\"cuil\">");
//		this.clienContr = new ClienteController();
//		this.clienContr.cargarClientes("SQL");
//		for (Cliente cliente : this.clienContr.getlClientes()) {
//			out.append("<option value=\"");
//			out.append(String.valueOf(cliente.getCuil()));
//			out.append("\">");
//			out.append(String.valueOf(cliente.getCuil()));
//		}
//		out.append("</select>");
//		out.append("</td>");
//		out.append("</tr>");

		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">CUIL de Cliente: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingrese CUIL de Cliente que exista\"");
		out.append("name=\"cuil\"pattern=\"^[0-9]{11}$\" title=\"11 digitos\" required>");
		out.append("</td>");
		out.append("</tr>");

//		out.append("<tr>");
//		out.append("<td>");
//		out.append("<label for=\"2\">ID de Vuelo: </label>");
//		out.append("</td>");
//		out.append("<td>");
//		out.append("<select name=\"idVueloS\">");
//		this.vuelContr = new VueloController();
//		this.vuelContr.cargarVuelos("SQL");
//		for (Vuelo vuelo : this.vuelContr.getlVuelos()) {
//			out.append("<option value=\"");
//			out.append(String.valueOf(vuelo.getIdVuelo()));
//			out.append("\">");
//			out.append(String.valueOf(vuelo.getIdVuelo()));
//		}
//		out.append("</select>");
//		out.append("</td>");
//		out.append("</tr>");

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
		this.formPagContr = new FormaDePagoController();
		this.formPagContr.cargarFormaDePagos();
		for (FormaDePago formaDePago : this.formPagContr.getlFormaDePagos()) {
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
