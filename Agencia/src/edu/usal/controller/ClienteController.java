package edu.usal.controller;

import edu.usal.negocio.dao.factory.ClienteFactory;
import edu.usal.negocio.dao.implementaciones.file.ClienteDAOImpleFile;
import edu.usal.negocio.dao.implementaciones.sql.ClienteDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dto.Cliente;
import edu.usal.negocio.dto.Direccion;
import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.negocio.dto.Pasaporte;
import edu.usal.negocio.dto.Telefono;
import edu.usal.util.IOGeneral;
import edu.usal.vista.ClienteAltaVista;
import edu.usal.vista.ClienteBajaVista;
import edu.usal.vista.ClienteConsultaVista;
import edu.usal.vista.ClienteModificacionVista;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class ClienteController extends HttpServlet {

	private List<Cliente> lClientes = null;
	private PasaporteController pasapContr;
	private TelefonoController telefContr;
	private PasajeroFrecuenteController pasajFContr;
	private DireccionController direcContr;
	private ClienteAltaVista clAltaVista = null;
	private ClienteBajaVista clBajaVista = null;
	private ClienteConsultaVista clConsultaVista = null;
	private ClienteModificacionVista clModificacionVista = null;

	public ClienteController() {
		this.lClientes = new ArrayList<Cliente>();
		this.pasapContr = new PasaporteController();
		this.telefContr = new TelefonoController();
		this.pasajFContr = new PasajeroFrecuenteController();
		this.direcContr = new DireccionController();
	}

	public void cargarClientes(String implementacion) {

		ClienteDAO ClienteDAO = ClienteFactory.getImplementacion(implementacion);

		this.pasapContr = new PasaporteController();
		this.pasapContr.cargarPasaportes();
		this.telefContr = new TelefonoController();
		this.telefContr.cargarTelefonos();
		this.pasajFContr = new PasajeroFrecuenteController();
		this.pasajFContr.cargarPasajerosFrecuentes();
		this.direcContr = new DireccionController();
		this.direcContr.cargarDirecciones();

		this.lClientes = ClienteDAO.cargarClientes(this.pasapContr, this.telefContr, this.pasajFContr, this.direcContr);

	}

	public void imprimirListaClientes() {
//		int idCliente, String nombre, String apellido, String dni, 
//		String nroPasaporte, String cuil, Date fechNac, int idTelefono, 
//		String nroPF, String email, int idDireccion

		IOGeneral.println("\nidCliente\tnombre\tapellido\tdni"
				+ "\tnroPasaporte\tcuil\tfechNac\tidTelefono\tnroPF\temail\tidDireccion");

		for (Cliente c : this.lClientes) {
			System.out.println(c.getIdCliente() + "\t" + c.getNombre() + "\t" + c.getApellido() + "\t" + c.getDni()
					+ "\t" + c.getPasaporte().getnroPasaporte() + "\t" + c.getCuil() + "\t" + c.getFechNac() + "\t"
					+ c.getTelefono().getIdTelefono() + "\t" + c.getPasajeroFrecuente().getNroPF() + "\t" + c.getEmail()
					+ "\t" + c.getDireccion().getIdDireccion());

		}
	}

	public void guardarClientes(String implementacion) {

		ClienteDAO clienteDAO = ClienteFactory.getImplementacion(implementacion);

		if (clienteDAO instanceof ClienteDAOImpleFile)
			((ClienteDAOImpleFile) clienteDAO).guardarClientes(this.lClientes);

	}

	public Cliente conseguirCliente(int idCliente) {

		for (Cliente c : this.lClientes) {
			if (c.getIdCliente() == idCliente) {
				return c;
			}
		}
		return null;
	}

	public Date fechaEmisionPasaporte_por_CUIL(String cuil) {

		Date fechaEmision = null;
		String nroPasaporte = null;

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		nroPasaporte = clienteDAOImpleSQL.nroPasaporte_por_CUIL(cuil);

		if (nroPasaporte != null) {
			this.pasapContr.cargarPasaportes();
			fechaEmision = this.pasapContr.conseguirFechaDeEmision(nroPasaporte);

			return fechaEmision;
		}

		return fechaEmision;

	}

	public Date fechaVencimientoPasaporte_por_CUIL(String cuil) {

		Date fechaEmision = null;
		String nroPasaporte = null;

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		nroPasaporte = clienteDAOImpleSQL.nroPasaporte_por_CUIL(cuil);

		if (nroPasaporte != null) {
			this.pasapContr.cargarPasaportes();
			fechaEmision = this.pasapContr.conseguirFechaDeVencimiento(nroPasaporte);

			return fechaEmision;
		}

		return fechaEmision;

	}

	public int crearCliente(String nombre, String apellido, String dni, String cuil, String nroPasaporte,
			String fechNac, String email, int idTelefono, String nroPF, int idDireccion) {

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		return clienteDAOImpleSQL.crearCliente(nombre, apellido, dni, cuil, nroPasaporte, fechNac, email, idTelefono,
				nroPF, idDireccion);

	}

	public int bajarCliente(String cuil) {

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		return clienteDAOImpleSQL.bajarCliente(cuil);

	}

	public String consultarCliente(String cuil) {

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		return clienteDAOImpleSQL.consultarCliente(cuil);

	}

	public int modificarCliente(String nombre, String apellido, String dni, String cuil, String nroPasaporte,
			String fechNac, String email, int idTelefono, String nroPF, int idDireccion) {

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		return clienteDAOImpleSQL.modificarCliente(nombre, apellido, dni, cuil, nroPasaporte, fechNac, email,
				idTelefono, nroPF, idDireccion);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			this.clAltaVista = new ClienteAltaVista();
			this.pasapContr = new PasaporteController();
			this.pasapContr.cargarPasaportes();
			this.telefContr = new TelefonoController();
			this.telefContr.cargarTelefonos();
			this.pasajFContr = new PasajeroFrecuenteController();
			this.pasajFContr.cargarPasajerosFrecuentes();
			this.direcContr = new DireccionController();
			this.direcContr.cargarDirecciones();
			clAltaVista.formularioAlta(request, response, pasapContr, telefContr, pasajFContr, direcContr);
			break;
		case "Baja":
			this.clBajaVista = new ClienteBajaVista();
			clBajaVista.formularioBaja_por_CUIL(request, response);
			break;
		case "Consulta":
			this.clConsultaVista = new ClienteConsultaVista();
			clConsultaVista.formulario_de_consulta(request, response);
			break;
		case "Modificacion":
			this.clModificacionVista = new ClienteModificacionVista();
			this.pasapContr = new PasaporteController();
			this.pasapContr.cargarPasaportes();
			this.telefContr = new TelefonoController();
			this.telefContr.cargarTelefonos();
			this.pasajFContr = new PasajeroFrecuenteController();
			this.pasajFContr.cargarPasajerosFrecuentes();
			this.direcContr = new DireccionController();
			this.direcContr.cargarDirecciones();
			clModificacionVista.formulario_de_modificacion(request, response, pasapContr, telefContr, pasajFContr,
					direcContr);
			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int registrosAlterados = -20;
		String valor = request.getParameter("boton"); // name
		response.setContentType("text/html");

		String nombre = null;
		String apellido = null;
		String dni = null;
		String cuil = null;
		String nroPasaporte = null;
		String fechNac = null;
		String email = null;
		int idTelefono = 0;
		String nroPF = null;
		int idDireccion = 0;

		switch (valor) {

		case "Dar de alta": // value
			nombre = request.getParameter("nombre");
			apellido = request.getParameter("apellido");
			dni = request.getParameter("dni");
			cuil = request.getParameter("cuil");
			nroPasaporte = request.getParameter("nroPasaporte");
			fechNac = request.getParameter("fechNac");
			email = request.getParameter("email");
			idTelefono = Integer.parseInt(request.getParameter("idTelefono"));
			nroPF = request.getParameter("nroPF");
			idDireccion = Integer.parseInt(request.getParameter("idDireccion"));

			registrosAlterados = -20;
			registrosAlterados = crearCliente(nombre, apellido, dni, cuil, nroPasaporte, fechNac, email, idTelefono,
					nroPF, idDireccion);
			if (registrosAlterados > 0) {
				response.getWriter().append("Cliente guardado en la Base correctamente").println();
			} else {
				response.getWriter().append("No se pudo ejecutar la operación").println();
				response.getWriter().append("El CUIL ya está registrado").println();
			}
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Dar de baja":
			cuil = request.getParameter("cuil");
			registrosAlterados = -20;
			registrosAlterados = bajarCliente(cuil);
			if (registrosAlterados > 0) {
				response.getWriter().append("Cliente dado de baja de la Base correctamente").println();
			} else {
				response.getWriter().append("No se pudo ejecutar la operación").println();
				response.getWriter().append("No existe un Cliente con ese CUIL.").println();
			}
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Consultar":
			cuil = request.getParameter("cuil");
			String texto = consultarCliente(cuil);
			response.getWriter().append(texto).println();
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Modificar":

			nombre = request.getParameter("nombre");
			apellido = request.getParameter("apellido");
			dni = request.getParameter("dni");
			cuil = request.getParameter("cuil");
			nroPasaporte = request.getParameter("nroPasaporte");
			fechNac = request.getParameter("fechNac");
			email = request.getParameter("email");
			idTelefono = Integer.parseInt(request.getParameter("idTelefono"));
			nroPF = request.getParameter("nroPF");
			idDireccion = Integer.parseInt(request.getParameter("idDireccion"));

			registrosAlterados = -20;
			registrosAlterados = modificarCliente(nombre, apellido, dni, cuil, nroPasaporte, fechNac, email, idTelefono,
					nroPF, idDireccion);

			if (registrosAlterados > 0) {
				response.getWriter().append("Datos actualizados en la Base correctamente").println();
			} else {
				response.getWriter().append("No existe Cliente con ese CUIL").println();
			}
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();

			break;

		}

	}

	public List<Cliente> getlClientes() {
		return lClientes;
	}

	public void setlClientes(List<Cliente> lClientes) {
		this.lClientes = lClientes;
	}

}
