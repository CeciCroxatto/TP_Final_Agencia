package edu.usal.negocio.dto;

public class Aeropuerto {
	
	private String idAeropuerto;
	private String ciudad;
	private Pais pais;
	
	public Aeropuerto() {
		
	}
	
	public Aeropuerto(String idAeropuerto, String ciudad, Pais pais) {
		this.idAeropuerto = idAeropuerto;
		this.ciudad = ciudad;
		this.pais = pais;
	}

	
	
	public String getIdAeropuerto() {
		return idAeropuerto;
	}

	public void setIdAeropuerto(String idAeropuerto) {
		this.idAeropuerto = idAeropuerto;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	
	

}
