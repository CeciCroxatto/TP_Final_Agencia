package edu.usal.controller;

import edu.usal.negocio.dao.factory.ClienteFactory;
import edu.usal.negocio.dao.implementaciones.sql.ClienteDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dto.Alianza;
import edu.usal.negocio.dto.Cliente;
import edu.usal.negocio.dto.Direccion;
import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.negocio.dto.Pasaporte;
import edu.usal.negocio.dto.Telefono;
import edu.usal.util.ConnectionDB;
import edu.usal.util.IOGeneral;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClienteController extends HttpServlet {

	private List<Cliente> lClientes = null;
	private PasaporteController pasapContr;
	private TelefonoController telefContr;
	private PasajeroFrecuenteController pasajFContr;
	private DireccionController direcContr;
	

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
			System.out.println(c.getIdCliente() + "\t" + c.getNombre() 
			 + "\t" + c.getApellido()
			 + "\t" + c.getDni() + "\t" + c.getPasaporte().getnroPasaporte() 
			 + "\t" + c.getCuil()
			 + "\t" + c.getFechNac() + "\t" + c.getTelefono().getIdTelefono()
			 + "\t" + c.getPasajeroFrecuente().getNroPF()
			 + "\t" + c.getEmail() + "\t" + c.getDireccion().getIdDireccion());
		}
	}
	
	
	public void guardarClientes(String implementacion) {
		
		ClienteDAO clienteDAO = ClienteFactory.getImplementacion(implementacion);
		
		clienteDAO.guardarClientes(this.lClientes);
		
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
		
		// con el cuil en la base consigo el numero de pasaporte
		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		nroPasaporte = clienteDAOImpleSQL.nroPasaporte_por_CUIL(cuil);
		
		// en java con el numero de pasaporte consigo la fecha de emision		
		
		if(nroPasaporte != null) {
			this.pasapContr.cargarPasaportes();
			fechaEmision = this.pasapContr.conseguirFechaDeEmision(nroPasaporte);
			
			return fechaEmision;
		}


		return fechaEmision;
		
		
	}
	
	public Date fechaVencimientoPasaporte_por_CUIL(String cuil) {
		
		Date fechaEmision = null;
		String nroPasaporte = null;
		
		// con el cuil en la base consigo el numero de pasaporte
		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		nroPasaporte = clienteDAOImpleSQL.nroPasaporte_por_CUIL(cuil);
		
		// en java con el numero de pasaporte consigo la fecha de emision		
		
		if(nroPasaporte != null) {
			this.pasapContr.cargarPasaportes();
			fechaEmision = this.pasapContr.conseguirFechaDeVencimiento(nroPasaporte);
			
			return fechaEmision;
		}


		return fechaEmision;
		
		
	}
	
	
	
	
	
	public int crearCliente(String nombre, String apellido, String dni, 
			String cuil, String nroPasaporte, String fechNac, String email, 
			int idTelefono, String nroPF, int idDireccion) {
		
		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		return clienteDAOImpleSQL.crearCliente(nombre, apellido, dni, cuil, nroPasaporte, 
				fechNac, email, idTelefono, nroPF, idDireccion);

	}
	
	public int bajarCliente(String cuil) {

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		return clienteDAOImpleSQL.bajarCliente(cuil);

	}
	
	public String consultarCliente(String cuil) {

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		return clienteDAOImpleSQL.consultarCliente(cuil);

	}
	
	public int modificarCliente(String nombre, String apellido, String dni, 
			String cuil, String nroPasaporte, String fechNac, String email, 
			int idTelefono, String nroPF, int idDireccion) {

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		return clienteDAOImpleSQL.modificarCliente(nombre, apellido, dni, cuil, nroPasaporte, 
				fechNac, email, idTelefono, nroPF, idDireccion);

	}
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			formularioAlta(request, response);
			break;
		case "Baja":
			formularioBaja_por_CUIL(request, response);
			break;
		case "Consulta":
			formulario_de_consulta(request, response);
			break;
		case "Modificacion":
			formulario_de_modificacion(request, response);
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
			
//			fechNac = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechNac"));
			fechNac = request.getParameter("fechNac");
  
			email = request.getParameter("email");
			idTelefono = Integer.parseInt(request.getParameter("idTelefono"));
			nroPF = request.getParameter("nroPF");
			idDireccion = Integer.parseInt(request.getParameter("idDireccion"));

			registrosAlterados = -20;
			registrosAlterados = crearCliente(nombre, apellido, dni, cuil, nroPasaporte, 
					fechNac, email, idTelefono, nroPF, idDireccion);
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
			registrosAlterados = modificarCliente(nombre, apellido, dni, cuil, nroPasaporte, 
					fechNac, email, idTelefono, nroPF, idDireccion);

			if (registrosAlterados > 0) {
				response.getWriter().append("Datos actualizados en la Base correctamente").println();
			} else {
				response.getWriter().append("No existe Cliente con ese CUIL").println();
			}
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			
			break;
			
			
		}
		
		
	}	
		

		public void formularioAlta(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			PrintWriter out = response.getWriter();

			out.append("<!DOCTYPE html>");
			out.append("<head>");
			out.append("<meta charset= \"utf-8\">");
			out.append("<title>Alta de Cliente</title>");
			out.append("</head>");
			out.append("<body>");
			out.append("<article class=\"Titulo\">");
			out.append("<h1>");
			out.append("<i>");
			out.append("<u>");
			out.append("<b>Alta de Cliente</b>");
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
			out.append("<b>Alta de Cliente</b>");
			out.append("</th>");
			out.append("<tr>");
			
			out.append("<tr>");
			out.append("<td>");
			out.append("<label for=\"1\">Nombre: </label>");
			out.append("</td>");
			out.append("<td>");
			out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Nombre\"");
			out.append("name=\"nombre\"pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
			out.append("</td>");
			out.append("</tr>");

			out.append("<tr>");
			out.append("<td>");
			out.append("<label for=\"2\">Apellido: </label>");
			out.append("</td>");
			out.append("<td>");
			out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Apellido\"");
			out.append("name=\"apellido\"pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
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
			this.pasapContr = new PasaporteController();
			this.pasapContr.cargarPasaportes();
			for (Pasaporte pasaporte : this.pasapContr.getlPasaportes()) {
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
			out.append("name=\"fechNac\"pattern=\"^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
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
			this.telefContr = new TelefonoController();
			this.telefContr.cargarTelefonos();
			for (Telefono telefono : this.telefContr.getlTelefonos()) {
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
			this.pasajFContr = new PasajeroFrecuenteController();
			this.pasajFContr.cargarPasajerosFrecuentes();
			for (PasajeroFrecuente pasajeroFrecuente : this.pasajFContr.getlPasajeroFrecuentes()) {
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
			this.direcContr = new DireccionController();
			this.direcContr.cargarDirecciones();
			for (Direccion direccion : this.direcContr.getlDirecciones()) {
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
	
		
		public void formulario_de_consulta(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();

			out.append("<!DOCTYPE html>");
			out.append("<head>");
			out.append("<meta charset= \"utf-8\">");
			out.append("<title>Consulta de Cliente</title>");
			out.append("</head>");
			out.append("<body>");
			out.append("<article class=\"Titulo\">");
			out.append("<h1>");
			out.append("<i>");
			out.append("<u>");
			out.append("<b>Consulta de Cliente</b>");
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
			out.append("<b>Consulta de Cliente</b>");
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
		
		

		public void formulario_de_modificacion(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
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
			out.append("name=\"nombre\"pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
			out.append("</td>");
			out.append("</tr>");

			out.append("<tr>");
			out.append("<td>");
			out.append("<label for=\"2\">Apellido: </label>");
			out.append("</td>");
			out.append("<td>");
			out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Apellido\"");
			out.append("name=\"apellido\"pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
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
			this.pasapContr = new PasaporteController();
			this.pasapContr.cargarPasaportes();
			for (Pasaporte pasaporte : this.pasapContr.getlPasaportes()) {
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
			out.append("name=\"fechNac\"pattern=\"^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$\" title=\"mm-dd-yyyy\" required>");
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
			this.telefContr = new TelefonoController();
			this.telefContr.cargarTelefonos();
			for (Telefono telefono : this.telefContr.getlTelefonos()) {
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
			this.pasajFContr = new PasajeroFrecuenteController();
			this.pasajFContr.cargarPasajerosFrecuentes();
			for (PasajeroFrecuente pasajeroFrecuente : this.pasajFContr.getlPasajeroFrecuentes()) {
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
			this.direcContr = new DireccionController();
			this.direcContr.cargarDirecciones();
			for (Direccion direccion : this.direcContr.getlDirecciones()) {
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
	
	

	public List<Cliente> getlClientes() {
		return lClientes;
	}



	public void setlClientes(List<Cliente> lClientes) {
		this.lClientes = lClientes;
	}
	
	

}
