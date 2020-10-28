package edu.usal.negocio.dao.interfaces;

import edu.usal.controller.DireccionController;
import edu.usal.controller.PasajeroFrecuenteController;
import edu.usal.controller.PasaporteController;
import edu.usal.controller.TelefonoController;
import edu.usal.negocio.dto.Cliente;

import java.util.List;


public interface ClienteDAO {
	
	public List<Cliente> cargarClientes(PasaporteController pasapContr
			, TelefonoController telefContr, PasajeroFrecuenteController pasajFContr
			, DireccionController direcContr);
	
	public void guardarClientes(List<Cliente> lClientes);

}
