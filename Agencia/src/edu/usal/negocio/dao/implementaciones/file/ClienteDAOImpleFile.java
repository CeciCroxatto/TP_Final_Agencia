package edu.usal.negocio.dao.implementaciones.file;

import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dto.Cliente;
import edu.usal.controller.DireccionController;
import edu.usal.controller.PasajeroFrecuenteController;
import edu.usal.controller.PasaporteController;
import edu.usal.controller.TelefonoController;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpleFile implements ClienteDAO {

	private List<Cliente> lClienteDAO;

	public List<Cliente> getlClienteDAO() {
		return lClienteDAO;
	}

	public void setlClienteDAO(List<Cliente> lClienteDAO) {
		this.lClienteDAO = lClienteDAO;
	}

	/*
	 * 
	 * Funciones en desuso
	 *
	 */

	@Override
	public String modificarCliente_porCUILGUI(String nombre, String apellido, String dni, String cuil,
			String nroPasaporte, String paisPasaporte, String provinciaPasaporte, String autoridadPasaporte,
			String fechaEmisionS, String vencimientoS, String telefpers, String telefcelul, String teleflabor,
			String fechNac, String nroPF, String nombreLA, String categoriaPF, String email, String calleDir,
			String calleAlt, String ciudadDir, String paisDir, String provDir, String cpDir) {

		// no implementada
		String string = "no implementada";
		return string;
	}

	@Override
	public String borrarCliente_porCUILGUI(String cuil) {
		// no implementada
		String string = "no implementada";
		return string;
	}

	@Override
	public ArrayList<String> consultarCliente_porCUILGUI(String cuil) {

		// no implementada
		ArrayList<String> lDatos = new ArrayList<>();
		lDatos.add("No se encontro un Cliente con ese Numero de CUIL");
		return lDatos;
	}

	@Override
	public String crearClienteGUI(String nombre, String apellido, String dni, String cuil, String nroPasaporte,
			String paisPasaporte, String provinciaPasaporte, String autoridadPasaporte, String fechaEmisionS,
			String vencimientoS, String telefpers, String telefcelul, String teleflabor, String fechNac, String nroPF,
			String nombreLA, String categoriaPF, String email, String calleDir, String calleAlt, String ciudadDir,
			String paisDir, String provDir, String cpDir) {

		// no implementada
		String string = "no implementada";
		return string;
	}

	@Override
	public List<Cliente> cargarClientes(PasaporteController pasapContr, TelefonoController telefContr,
			PasajeroFrecuenteController pasajFContr, DireccionController direcContr) {

		this.lClienteDAO = new ArrayList<Cliente>();
//		FileIO IOfile = new FileIO();
//		String[] splitted = null;
//		int idCliente = 0;
//		String nombre = null;
//		String apellido = null;
//		String dni = null;
//		String nroPasaporte = null;
//		String cuil = null;
//		Date fechNac = null;
//		int idTelefono = 0;
//		String nroPF = null;
//		String email = null;
//		int idDireccion = 0;
//
//		Pasaporte pasaporte = null;
//		Telefono telefono = null;
//		PasajeroFrecuente pasajeroFrecuente = null;
//		Direccion direccion = null;
//
//		for (String s : IOfile.cargarLista("Cliente")) {
//
//			splitted = s.split("[,]", 0);
//
//			idCliente = Integer.parseInt(splitted[0]);
//			nombre = splitted[1];
//			apellido = splitted[2];
//			dni = splitted[3];
//			nroPasaporte = splitted[4];
//			cuil = splitted[5];
//
//			try {
//				fechNac = new SimpleDateFormat("dd/mm/yyyy").parse(splitted[6]);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//
//			idTelefono = Integer.parseInt(splitted[7]);
//			nroPF = splitted[8];
//			email = splitted[9];
//			idDireccion = Integer.parseInt(splitted[10]);
//
//			lClienteDAO.add(new Cliente(idCliente, nombre, apellido, dni, cuil, fechNac, email));
//
//			pasaporte = pasapContr.conseguirPasaporte(nroPasaporte);
//			lClienteDAO.get(lClienteDAO.size() - 1).setPasaporte(pasaporte);
//			telefono = telefContr.conseguirTelefono(idTelefono);
//			lClienteDAO.get(lClienteDAO.size() - 1).setTelefono(telefono);
//			pasajeroFrecuente = pasajFContr.conseguirPasajeroFrecuente(nroPF);
//			lClienteDAO.get(lClienteDAO.size() - 1).setPasajeroFrecuente(pasajeroFrecuente);
//			direccion = direcContr.conseguirDireccion(idDireccion);
//			lClienteDAO.get(lClienteDAO.size() - 1).setDireccion(direccion);
//		}
		return this.lClienteDAO;
	}

//	public void guardarClientes(List<Cliente> lClientes) {
//
//		FileIO IOfile = new FileIO();
//		List<String> lString = new ArrayList<String>();
//
//		lString.add("#int idCliente, String nombre, String apellido" + ", String dni, String nroPasaporte, String cuil"
//				+ ", Date fechNac, int idTelefono, String nroPF, String email" + ", int idDireccion");
//
//		for (Cliente c : lClientes) {
//
//			lString.add(c.getIdCliente() + "," + c.getNombre() + "," + c.getApellido() + "," + c.getDni() + ","
//					+ c.getPasaporte().getnroPasaporte() + "," + c.getCuil() + "," + c.getFechNac().getDate() + "/"
//					+ (c.getFechNac().getMonth() + 1) + "/" + c.getFechNac().getYear() + ","
//					+ c.getTelefono().getIdTelefono() + "," + c.getPasajeroFrecuente().getNroPF() + "," + c.getEmail()
//					+ "," + c.getDireccion().getIdDireccion());
//		}
//
//		IOfile.guardarLista("Cliente", lString);
//
//	}

}
