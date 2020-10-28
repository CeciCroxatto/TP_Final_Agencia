package edu.usal.negocio.dto;

public class Alianza {
	
	private String idAlianza;
	private String nombre;
	
	
	
	public Alianza() {

	}
	
	
	public Alianza(String idAlianza, String nombre) {
		this.idAlianza = idAlianza;
		this.nombre = nombre;
	}

	


	public String getIdAlianza() {
		return idAlianza;
	}


	public void setIdAlianza(String idAlianza) {
		this.idAlianza = idAlianza;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
