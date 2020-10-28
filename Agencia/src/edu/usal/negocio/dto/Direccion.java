package edu.usal.negocio.dto;

public class Direccion {

	private int idDireccion;
	private String calle;
	private String altura;
	private String ciudad;
	private Pais pais;
	private String cp;

	public Direccion() {

	}

	public Direccion(int idDireccion, String calle, String altura, String ciudad, Pais pais, String cp) {
		
		this.idDireccion = idDireccion;
		this.calle = calle;
		this.altura = altura;
		this.ciudad = ciudad;
		this.pais = pais;
		this.cp = cp;
	}

	
	
	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
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

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

}
