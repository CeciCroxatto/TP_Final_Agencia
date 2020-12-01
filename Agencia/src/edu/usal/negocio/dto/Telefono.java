package edu.usal.negocio.dto;

public class Telefono {

	private int idTelefono;
	private String personal;
	private String celular;
	private String laboral;

	public Telefono() {

	}

	public Telefono(int idTelefono, String personal, String celular, String laboral) {
		this.idTelefono = idTelefono;
		this.personal = personal;
		this.celular = celular;
		this.laboral = laboral;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getLaboral() {
		return laboral;
	}

	public void setLaboral(String laboral) {
		this.laboral = laboral;
	}

	public int getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}

}
