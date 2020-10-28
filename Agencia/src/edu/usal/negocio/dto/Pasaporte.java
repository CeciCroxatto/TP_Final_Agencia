package edu.usal.negocio.dto;

import java.util.Date;


public class Pasaporte {
	
	private String nroPasaporte;
	private Pais pais;
	private String autoridad;
	private Date fechEmision;
	private Date vencimiento;
	
	public Pasaporte() {

	}
	
	public Pasaporte(String nroPasaporte, Pais pais, String autoridad, Date fechEmision, Date vencimiento) {
		this.nroPasaporte = nroPasaporte;
		this.pais = pais;
		this.autoridad = autoridad;
		this.fechEmision = fechEmision;
		this.vencimiento = vencimiento;
	}

	
	
	public String getnroPasaporte() {
		return nroPasaporte;
	}

	public void setnroPasaporte(String nroPasaporte) {
		this.nroPasaporte = nroPasaporte;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getAutoridad() {
		return autoridad;
	}

	public void setAutoridad(String autoridad) {
		this.autoridad = autoridad;
	}

	public Date getFechEmision() {
		return fechEmision;
	}

	public void setFechEmision(Date fechEmision) {
		this.fechEmision = fechEmision;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	
	
	

}
