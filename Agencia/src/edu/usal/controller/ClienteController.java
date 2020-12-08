package edu.usal.controller;

import edu.usal.manager.ClienteManager;
import edu.usal.negocio.dao.factory.ClienteFactory;
import edu.usal.negocio.dao.implementaciones.sql.ClienteDAOImpleSQL;
import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dto.Cliente;
import edu.usal.negocio.dto.Direccion;
import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.negocio.dto.Pasaporte;
import edu.usal.negocio.dto.Telefono;
import edu.usal.vista.ClienteAltaVista;
import edu.usal.vista.ClienteBajaVista;
import edu.usal.vista.ClienteCVista2;
import edu.usal.vista.ClienteConsultaVista;
import edu.usal.vista.ClienteDVista2;
import edu.usal.vista.ClienteModificacionVista;
import edu.usal.vista.ClienteRVista2;
import edu.usal.vista.ClienteUVista2;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClienteController extends HttpServlet implements ActionListener {

	private static final long serialVersionUID = 1L;

	private List<Cliente> lClientes = null;
	private PasaporteController pasapContr;
	private TelefonoController telefContr;
	private PasajeroFrecuenteController pasajFContr;
	private DireccionController direcContr;
	private PaisController paisContr;
	private ProvinciaController provContr;
	private LineaAereaController laContr;

	// vistas html
	private ClienteAltaVista clAltaVista = null;
	private ClienteBajaVista clBajaVista = null;
	private ClienteConsultaVista clConsultaVista = null;
	private ClienteModificacionVista clModificacionVista = null;

	// vistas gui
	private ClienteCVista2 clCVista = null;
	private ClienteRVista2 clRVista = null;
	private ClienteUVista2 clUVista = null;
	private ClienteDVista2 clDVista = null;

	public ClienteController() {
		this.lClientes = new ArrayList<Cliente>();
		this.pasapContr = new PasaporteController();
		this.telefContr = new TelefonoController();
		this.pasajFContr = new PasajeroFrecuenteController();
		this.direcContr = new DireccionController();
		this.paisContr = new PaisController();
		this.provContr = new ProvinciaController();
		this.laContr = new LineaAereaController();

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public Cliente crearClienteGUI(String implementacion, String nombre, String apellido, String dni, String cuil,
			String nroPasaporte, String paisPasaporte, String provinciaPasaporte, String autoridadPasaporte,
			String fechaEmisionS, String vencimientoS, String telefpers, String telefcelul, String teleflabor,
			String fechNac, String nroPF, String nombreLA, String categoriaPF, String email, String calleDir,
			String calleAlt, String ciudadDir, String paisDir, String provDir, String cpDir) {

		Cliente cliente = null;
		Date fechEmision = null;
		Date vencimiento = null;
		Date fechaNac = null;

		try {
			fechEmision = new SimpleDateFormat("MM-dd-yyyy").parse(fechaEmisionS);
			vencimiento = new SimpleDateFormat("MM-dd-yyyy").parse(vencimientoS);
			fechaNac = new SimpleDateFormat("MM-dd-yyyy").parse(fechNac);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Direccion direccion = this.direcContr.nuevaDireccion(calleDir, calleAlt, ciudadDir, paisDir, provDir, cpDir);
		Pasaporte pasaporte = this.pasapContr.nuevoPasaporte(nroPasaporte, paisPasaporte, provinciaPasaporte,
				autoridadPasaporte, fechEmision, vencimiento);
		Telefono telefono = this.telefContr.nuevoTelefono(telefpers, telefcelul, teleflabor);
		PasajeroFrecuente pasajeroFrecuente = this.pasajFContr.nuevoPasajeroFrecuente(nroPF, nombreLA, categoriaPF);
		cliente = this.nuevoCliente(nombre, apellido, dni, cuil, fechaNac, email);

		cliente.setPasaporte(pasaporte);
		cliente.setTelefono(telefono);
		cliente.setPasajeroFrecuente(pasajeroFrecuente);
		cliente.setDireccion(direccion);

		return cliente;

	}

	public int vecesCuil(String cuil, String implementacion) {

		ClienteDAO clienteDAO = ClienteFactory.getImplementacion(implementacion);

		return Integer.parseInt(((ClienteDAOImpleSQL) clienteDAO).vecesCuil(cuil));

	}

	public Cliente nuevoCliente(String nombre, String apellido, String dni, String cuil, Date fechaNac, String email) {
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDni(dni);
		cliente.setCuil(cuil);
		cliente.setFechNac(fechaNac);
		cliente.setEmail(email);

		return cliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 4 x btnLimpiar

		if (e.getSource() == this.clCVista.btnLimpiar) {
			this.clCVista.limpiarConConfirmacion();
		}

		if (e.getSource() == this.clRVista.btnLimpiar) {
			this.clRVista.limpiarConConfirmacion();
		}

		if (e.getSource() == this.clUVista.btnLimpiar) {
			this.clUVista.limpiarConConfirmacion();
		}

		if (e.getSource() == this.clDVista.btnLimpiar) {
			this.clDVista.limpiarConConfirmacion();
		}

	}

	/*
	 * 
	 * Vistas GUI
	 *
	 */

	public void crearVistas(ClienteManager clienteManager) {
		crearVistaC(clienteManager);
		crearVistaR(clienteManager);
		crearVistaU(clienteManager);
		crearVistaD(clienteManager);
	}

	public void crearVistaC(ClienteManager clienteManager) {
		this.clCVista = new ClienteCVista2(clienteManager);
	}

	public void crearVistaR(ClienteManager clienteManager) {
		this.clRVista = new ClienteRVista2(clienteManager);
	}

	public void crearVistaU(ClienteManager clienteManager) {
		this.clUVista = new ClienteUVista2(clienteManager);
	}

	public void crearVistaD(ClienteManager clienteManager) {
		this.clDVista = new ClienteDVista2(clienteManager);
	}

	/*
	 * 
	 * Cargar datos: tabla parametrica
	 *
	 */

	public void cargarPaises() {
		this.paisContr.cargarPaises();
	}

	public void cargarLineasAereas(String implementacion) {
		this.laContr.cargarLineaAereas(implementacion);
	}

	/*
	 * 
	 * Cargar datos: tabla archivo de texto
	 *
	 */

	public void cargarProvincias() {
		this.provContr.cargarProvincias();
	}

	/*
	 * 
	 * Funciones que usan la vista web
	 *
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			this.clAltaVista = new ClienteAltaVista();
			this.pasapContr = new PasaporteController();
//			this.pasapContr.cargarPasaportes();
			this.telefContr = new TelefonoController();
//			this.telefContr.cargarTelefonos();
			this.pasajFContr = new PasajeroFrecuenteController();
//			this.pasajFContr.cargarPasajerosFrecuentes();
			this.direcContr = new DireccionController();
//			this.direcContr.cargarDirecciones();
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
//			this.pasapContr.cargarPasaportes();
			this.telefContr = new TelefonoController();
//			this.telefContr.cargarTelefonos();
			this.pasajFContr = new PasajeroFrecuenteController();
//			this.pasajFContr.cargarPasajerosFrecuentes();
			this.direcContr = new DireccionController();
//			this.direcContr.cargarDirecciones();
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

	public Date fechaEmisionPasaporte_por_CUIL(String cuil) {

		Date fechaEmision = null;
		String nroPasaporte = null;

		ClienteDAOImpleSQL clienteDAOImpleSQL = new ClienteDAOImpleSQL();
		nroPasaporte = clienteDAOImpleSQL.nroPasaporte_por_CUIL(cuil);

		if (nroPasaporte != null) {
//			this.pasapContr.cargarPasaportes();
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
//			this.pasapContr.cargarPasaportes();
			fechaEmision = this.pasapContr.conseguirFechaDeVencimiento(nroPasaporte);

			return fechaEmision;
		}

		return fechaEmision;

	}

	/*
	 * 
	 * Funciones en desuso
	 *
	 */

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

//	public void cargarClientes(String implementacion) {
//
//		ClienteDAO ClienteDAO = ClienteFactory.getImplementacion(implementacion);
//
//		this.pasapContr = new PasaporteController();
//		this.pasapContr.cargarPasaportes();
//		this.telefContr = new TelefonoController();
//		this.telefContr.cargarTelefonos();
//		this.pasajFContr = new PasajeroFrecuenteController();
//		this.pasajFContr.cargarPasajerosFrecuentes();
//		this.direcContr = new DireccionController();
//		this.direcContr.cargarDirecciones();
//
//		this.lClientes = ClienteDAO.cargarClientes(this.pasapContr, this.telefContr, this.pasajFContr, this.direcContr);
//
//	}	

//	public void imprimirListaClientes() {
////		int idCliente, String nombre, String apellido, String dni, 
////		String nroPasaporte, String cuil, Date fechNac, int idTelefono, 
////		String nroPF, String email, int idDireccion
//
//		IOGeneral.println("\nidCliente\tnombre\tapellido\tdni"
//				+ "\tnroPasaporte\tcuil\tfechNac\tidTelefono\tnroPF\temail\tidDireccion");
//
//		for (Cliente c : this.lClientes) {
//			System.out.println(c.getIdCliente() + "\t" + c.getNombre() + "\t" + c.getApellido() + "\t" + c.getDni()
//					+ "\t" + c.getPasaporte().getnroPasaporte() + "\t" + c.getCuil() + "\t" + c.getFechNac() + "\t"
//					+ c.getTelefono().getIdTelefono() + "\t" + c.getPasajeroFrecuente().getNroPF() + "\t" + c.getEmail()
//					+ "\t" + c.getDireccion().getIdDireccion());
//
//		}
//	}

//	public void guardarClientes(String implementacion) {
//
//		ClienteDAO clienteDAO = ClienteFactory.getImplementacion(implementacion);
//
//		if (clienteDAO instanceof ClienteDAOImpleFile)
//			((ClienteDAOImpleFile) clienteDAO).guardarClientes(this.lClientes);
//
//	}

//	public Cliente conseguirCliente(int idCliente) {
//
//		for (Cliente c : this.lClientes) {
//			if (c.getIdCliente() == idCliente) {
//				return c;
//			}
//		}
//		return null;
//	}

	public List<Cliente> getlClientes() {
		return lClientes;
	}

	public void setlClientes(List<Cliente> lClientes) {
		this.lClientes = lClientes;
	}

	public PasaporteController getPasapContr() {
		return pasapContr;
	}

	public void setPasapContr(PasaporteController pasapContr) {
		this.pasapContr = pasapContr;
	}

	public TelefonoController getTelefContr() {
		return telefContr;
	}

	public void setTelefContr(TelefonoController telefContr) {
		this.telefContr = telefContr;
	}

	public PasajeroFrecuenteController getPasajFContr() {
		return pasajFContr;
	}

	public void setPasajFContr(PasajeroFrecuenteController pasajFContr) {
		this.pasajFContr = pasajFContr;
	}

	public DireccionController getDirecContr() {
		return direcContr;
	}

	public void setDirecContr(DireccionController direcContr) {
		this.direcContr = direcContr;
	}

	public PaisController getPaisContr() {
		return paisContr;
	}

	public void setPaisContr(PaisController paisContr) {
		this.paisContr = paisContr;
	}

	public ProvinciaController getProvContr() {
		return provContr;
	}

	public void setProvContr(ProvinciaController provContr) {
		this.provContr = provContr;
	}

	public ClienteAltaVista getClAltaVista() {
		return clAltaVista;
	}

	public void setClAltaVista(ClienteAltaVista clAltaVista) {
		this.clAltaVista = clAltaVista;
	}

	public ClienteBajaVista getClBajaVista() {
		return clBajaVista;
	}

	public void setClBajaVista(ClienteBajaVista clBajaVista) {
		this.clBajaVista = clBajaVista;
	}

	public ClienteConsultaVista getClConsultaVista() {
		return clConsultaVista;
	}

	public void setClConsultaVista(ClienteConsultaVista clConsultaVista) {
		this.clConsultaVista = clConsultaVista;
	}

	public ClienteModificacionVista getClModificacionVista() {
		return clModificacionVista;
	}

	public void setClModificacionVista(ClienteModificacionVista clModificacionVista) {
		this.clModificacionVista = clModificacionVista;
	}

	public ClienteCVista2 getClCVista() {
		return clCVista;
	}

	public void setClCVista(ClienteCVista2 clCVista) {
		this.clCVista = clCVista;
	}

	public LineaAereaController getLaContr() {
		return laContr;
	}

	public void setLaContr(LineaAereaController laContr) {
		this.laContr = laContr;
	}

	public ClienteRVista2 getClRVista() {
		return clRVista;
	}

	public void setClRVista(ClienteRVista2 clRVista) {
		this.clRVista = clRVista;
	}

	public ClienteDVista2 getClDVista() {
		return clDVista;
	}

	public void setClDVista(ClienteDVista2 clDVista) {
		this.clDVista = clDVista;
	}

	public ClienteUVista2 getClUVista() {
		return clUVista;
	}

	public void setClUVista(ClienteUVista2 clUVista) {
		this.clUVista = clUVista;
	}

}
