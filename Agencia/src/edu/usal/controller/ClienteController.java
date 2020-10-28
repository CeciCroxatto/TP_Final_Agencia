package edu.usal.controller;

import edu.usal.negocio.dao.factory.ClienteFactory;
import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dto.Cliente;
import edu.usal.util.IOGeneral;

import java.util.List;
import java.util.ArrayList;

public class ClienteController {

	private List<Cliente> lClientes = null;

	public ClienteController() {
		this.lClientes = new ArrayList<Cliente>();
	}
	
	

	public List<Cliente> getlClientes() {
		return lClientes;
	}



	public void setlClientes(List<Cliente> lClientes) {
		this.lClientes = lClientes;
	}


	
	

	public void cargarClientes(String implementacion, PasaporteController pasapContr
			, TelefonoController telefContr, PasajeroFrecuenteController pasajFContr
			, DireccionController direcContr) {

		ClienteDAO ClienteDAO = ClienteFactory.getImplementacion(implementacion);

		this.lClientes = ClienteDAO.cargarClientes(pasapContr, telefContr, pasajFContr, direcContr);

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

}
