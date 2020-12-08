package edu.usal.negocio.dao.interfaces;

import edu.usal.controller.DireccionController;
import edu.usal.controller.PasajeroFrecuenteController;
import edu.usal.controller.PasaporteController;
import edu.usal.controller.TelefonoController;
import edu.usal.negocio.dto.Cliente;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface ClienteDAO {

	public int agregarCliente(Cliente cliente, Connection con) throws IOException;

	public List<Cliente> cargarClientes(PasaporteController pasapContr, TelefonoController telefContr,
			PasajeroFrecuenteController pasajFContr, DireccionController direcContr);

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

}
