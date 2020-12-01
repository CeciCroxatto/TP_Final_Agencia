package edu.usal.negocio.dto;

public class LineaAerea {

	private String idLAerea;
	private String nombre;
	private Alianza alianza;

	public LineaAerea() {

	}

	public LineaAerea(String idLAerea, String nombre, Alianza alianza) {
		this.idLAerea = idLAerea;
		this.nombre = nombre;
		this.alianza = alianza;
	}

	public String getIdLAerea() {
		return idLAerea;
	}

	public void setIdLAerea(String idLAerea) {
		this.idLAerea = idLAerea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Alianza getAlianza() {
		return alianza;
	}

	public void setAlianza(Alianza alianza) {
		this.alianza = alianza;
	}

}
