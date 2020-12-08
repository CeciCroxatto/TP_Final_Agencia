package edu.usal.negocio.dao.interfaces;

import edu.usal.controller.DireccionController;
import edu.usal.controller.PasajeroFrecuenteController;
import edu.usal.controller.PasaporteController;
import edu.usal.controller.TelefonoController;
import edu.usal.negocio.dto.Cliente;

import java.util.ArrayList;
import java.util.List;

public interface ClienteDAO {

	/*
	 * 
	 * Funciones que usan la GUI y sin Manager
	 *
	 */

	public String crearClienteGUI(String nombre, String apellido, String dni, String cuil, String nroPasaporte,
			String paisPasaporte, String provinciaPasaporte, String autoridadPasaporte, String fechaEmisionS,
			String vencimientoS, String telefpers, String telefcelul, String teleflabor, String fechNac, String nroPF,
			String nombreLA, String categoriaPF, String email, String calleDir, String calleAlt, String ciudadDir,
			String paisDir, String provDir, String cpDir);

	public ArrayList<String> consultarCliente_porCUILGUI(String cuil);

	public String borrarCliente_porCUILGUI(String cuil);

	public String modificarCliente_porCUILGUI(String nombre, String apellido, String dni, String cuil,
			String nroPasaporte, String paisPasaporte, String provinciaPasaporte, String autoridadPasaporte,
			String fechaEmisionS, String vencimientoS, String telefpers, String telefcelul, String teleflabor,
			String fechNac, String nroPF, String nombreLA, String categoriaPF, String email, String calleDir,
			String calleAlt, String ciudadDir, String paisDir, String provDir, String cpDir);

	/*
	 * 
	 * Funciones en desuso
	 *
	 */
	public List<Cliente> cargarClientes(PasaporteController pasapContr, TelefonoController telefContr,
			PasajeroFrecuenteController pasajFContr, DireccionController direcContr);
}
