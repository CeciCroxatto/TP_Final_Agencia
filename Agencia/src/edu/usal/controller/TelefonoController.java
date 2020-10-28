package edu.usal.controller;

import edu.usal.negocio.dto.Telefono;

import java.util.List;
import java.util.ArrayList;



public class TelefonoController {
	
	
	private List<Telefono> lTelefonos = null;
	
	
	public TelefonoController() {
		this.lTelefonos = new ArrayList<Telefono>();
	}

	
	
	public List<Telefono> getlTelefonos() {
		return lTelefonos;
	}

	public void setlTelefonos(List<Telefono> lTelefonos) {
		this.lTelefonos = lTelefonos;
	}
	
	
	public void cargarTelefonos() {

		this.lTelefonos = new ArrayList();
		this.lTelefonos.add( new Telefono(01,"4444-1111","154555-2221","4666-3331"));
		this.lTelefonos.add( new Telefono(02,"4444-1112","154555-2222","4666-3332"));
		this.lTelefonos.add( new Telefono(03,"4444-1113","154555-2223","4666-3333"));
		this.lTelefonos.add( new Telefono(04,"4444-1114","154555-2224","4666-3334"));
		this.lTelefonos.add( new Telefono(05,"4444-1115","154555-2225","4666-3335"));
		this.lTelefonos.add( new Telefono(06,"4444-1116","154555-2226","4666-3336"));
	}
	
	
	public Telefono conseguirTelefono(int idTelefono) {

		for (Telefono t : this.lTelefonos) {
			if (t.getIdTelefono() == idTelefono) {
				return t;
			}
		}
		return null;
	}
	
	

}
