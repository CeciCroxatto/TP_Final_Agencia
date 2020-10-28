package edu.usal.negocio.dto;

public class Provincia {

	private String idProv;
	private String descripcion;

	public Provincia() {
	}

	public Provincia(String idProv, String descripcion) {
		this.idProv = idProv;
		this.descripcion = descripcion;
	}

	
	
	public String getIdProv() {
		return idProv;
	}

	public void setId(String idProv) {
		this.idProv = idProv;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
