package edu.usal.controller;

import edu.usal.negocio.dao.factory.VentaFactory;
import edu.usal.negocio.dao.implementaciones.file.VentaDAOImpleFile;
import edu.usal.negocio.dao.implementaciones.sql.VentaDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dto.FormaDePago;
import edu.usal.negocio.dto.Venta;
import edu.usal.vista.VentaAltaVista;
import edu.usal.vista.VentaBajaVista;
import edu.usal.vista.VentaConsultaVista;
import edu.usal.vista.VentaModificacionVista;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VentaController extends HttpServlet {

	private List<Venta> lVentas = null;
	private ClienteController clienContr;
	private VueloController vuelContr;
	private FormaDePagoController formPagContr;
	private VentaAltaVista veAltaVista = null;
	private VentaBajaVista veBajaVista = null;
	private VentaConsultaVista veConsultaVista = null;
	private VentaModificacionVista veModificacionVista = null;

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

	public void cargarVentas(String implementacion) {

		VentaDAO VentaDAO = VentaFactory.getImplementacion(implementacion);

		this.clienContr = new ClienteController();
		clienContr.cargarClientes(implementacion);
		this.vuelContr = new VueloController();
		vuelContr.cargarVuelos(implementacion);
		this.formPagContr = new FormaDePagoController();
		formPagContr.cargarFormaDePagos();

		this.lVentas = VentaDAO.cargarVentas(this.clienContr, this.vuelContr, this.formPagContr);

	}

	public void guardarVentas(String implementacion) {

		VentaDAO ventaDAO = VentaFactory.getImplementacion(implementacion);

		if (ventaDAO instanceof VentaDAOImpleFile)
			((VentaDAOImpleFile) ventaDAO).guardarVentas(this.lVentas);

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

	public String bajarVenta(String cuil, String numeroVuelo) {

		VentaDAOImpleSQL ventaDAOImpleSQL = new VentaDAOImpleSQL();
		return ventaDAOImpleSQL.bajarVenta(cuil, numeroVuelo);

	}

	public String consultarVenta(String cuil, String numeroVuelo) {

		VentaDAOImpleSQL ventaDAOImpleSQL = new VentaDAOImpleSQL();
		return ventaDAOImpleSQL.consultarVenta(cuil, numeroVuelo);

	}

	public String modificarVenta(String cuil, String numeroVuelo, String fechVenta, int idPago, double importe_vuelo,
			double importe_total) {

		VentaDAOImpleSQL ventaDAOImpleSQL = new VentaDAOImpleSQL();
		return ventaDAOImpleSQL.modificarVenta(cuil, numeroVuelo, fechVenta, idPago, importe_vuelo, importe_total);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			this.veAltaVista = new VentaAltaVista();
			this.formPagContr = new FormaDePagoController();
			this.formPagContr.cargarFormaDePagos();
			veAltaVista.formularioAlta(request, response, formPagContr);
			break;
		case "Baja":
			this.veBajaVista = new VentaBajaVista();
			veBajaVista.formularioBaja_por_CUILyNROVuelo(request, response);
			break;
		case "Consulta":
			this.veConsultaVista = new VentaConsultaVista();
			veConsultaVista.formulario_de_consulta_por_CUILyNROVuelo(request, response);
			break;
		case "Modificacion":
			this.veModificacionVista = new VentaModificacionVista();
			this.formPagContr = new FormaDePagoController();
			this.formPagContr.cargarFormaDePagos();
			veModificacionVista.formulario_de_modificacion_por_CUILyNROVueloyFechaVenta(request, response,
					formPagContr);
			break;

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
		String numeroVuelo = "ZZ-0000";

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

//			Date fechaEmisionPasaporte = this.clienContr.fechaEmisionPasaporte_por_CUIL(cuil);

			Date fechaEmisionPasaporte = Calendar.getInstance().getTime();
			fechaEmisionPasaporte.setDate(13);
			fechaEmisionPasaporte.setMonth(2 - 1);
			fechaEmisionPasaporte.setYear(00);

			if (fechaEmisionPasaporte != null) {

//				Date fechaSalida = this.vuelContr.conseguirFechaDeSalida_por_ID(idVuelo);
				Date fechaSalida = Calendar.getInstance().getTime();
				fechaSalida.setDate(13);
				fechaSalida.setMonth(2 - 1);
				fechaSalida.setYear(01);

				if (fechaSalida != null) {

					if (fechaSalida.compareTo(fechaEmisionPasaporte) > 0) {

//						Date fechaVencimientoPasaporte = this.clienContr.fechaVencimientoPasaporte_por_CUIL(cuil);
						Date fechaVencimientoPasaporte = Calendar.getInstance().getTime();
						fechaVencimientoPasaporte.setYear(2000);

//						Date fechaLlegada = this.vuelContr.conseguirFechaDeLlegada_por_ID(idVuelo);
						Date fechaLlegada = Calendar.getInstance().getTime();

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

		case "Dar de baja":
			cuil = request.getParameter("cuil");
			numeroVuelo = request.getParameter("numeroVuelo");
			texto = "vacio";
			texto = bajarVenta(cuil, numeroVuelo);

			if (texto.matches("OK")) {
				response.getWriter().append("Venta dada de baja en la Base correctamente").println();
			} else {
				response.getWriter().append("No se pudo ejecutar la operación").println();
				response.getWriter().append(texto).println();
			}

			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Consultar":
			cuil = request.getParameter("cuil");
			numeroVuelo = request.getParameter("numeroVuelo");
			texto = "vacio";
			texto = consultarVenta(cuil, numeroVuelo);

			response.getWriter().append(texto).println();
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Modificar":
			String fechVenta = null;
			cuil = request.getParameter("cuil");
			numeroVuelo = request.getParameter("numeroVuelo");
			fechVenta = request.getParameter("fechVenta");
			idPago = Integer.parseInt(request.getParameter("idPagoS"));
			importe_vuelo = Double.parseDouble(request.getParameter("importe_vueloS"));

			factorDeInteres = this.formPagContr.FactorDeInteres(idPago);
			importe_total = importe_vuelo * factorDeInteres;

			texto = "vacio";
			texto = modificarVenta(cuil, numeroVuelo, fechVenta, idPago, importe_vuelo, importe_total);

			if (texto.matches("OK")) {
				response.getWriter().append("Venta actualizada en la Base correctamente").println();
			} else {
				response.getWriter().append("No se pudo ejecutar la operación").println();
				response.getWriter().append(texto).println();
			}

			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		}

	}

}
