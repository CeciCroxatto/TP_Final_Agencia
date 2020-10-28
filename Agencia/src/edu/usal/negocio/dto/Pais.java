package edu.usal.negocio.dto;

public class Pais {

	protected int idPais;
	protected String descripcion;
	
	
	
	public Pais(){
		
	}
	
	
	public Pais(int idPais, String descripcion) {
		this.idPais = idPais;
		this.descripcion = descripcion;
	}

	
	
	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
