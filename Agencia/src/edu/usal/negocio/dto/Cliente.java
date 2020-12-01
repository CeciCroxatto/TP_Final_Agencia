package edu.usal.negocio.dto;

import java.util.Date;

public class Cliente {

	private int idCliente;
	private String nombre;
	private String apellido;
	private String dni;
	private Pasaporte pasaporte;
	private String cuil;
	private Date fechNac;
	private String email;
	private Telefono telefono;
	private PasajeroFrecuente pasajeroFrecuente;
	private Direccion direccion;

	public Cliente() {

	}

	public Cliente(int idCliente, String nombre, String apellido, String dni, String cuil, Date fechNac, String email) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.pasaporte = new Pasaporte();
		this.cuil = cuil;
		this.fechNac = fechNac;
		this.email = email;
		this.telefono = new Telefono();
		this.pasajeroFrecuente = new PasajeroFrecuente();
		this.direccion = new Direccion();
	}

	public Cliente(int idCliente, String nombre, String apellido, String dni, Pasaporte pasaporte, String cuil,
			Date fechNac, String email, Telefono telefono, PasajeroFrecuente pasajeroFrecuente, Direccion direccion) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.pasaporte = pasaporte;
		this.cuil = cuil;
		this.fechNac = fechNac;
		this.email = email;
		this.telefono = telefono;
		this.pasajeroFrecuente = pasajeroFrecuente;
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Pasaporte getPasaporte() {
		return pasaporte;
	}

	public void setPasaporte(Pasaporte pasaporte) {
		this.pasaporte = pasaporte;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public Date getFechNac() {
		return fechNac;
	}

	public void setFechNac(Date fechNac) {
		this.fechNac = fechNac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}

	public PasajeroFrecuente getPasajeroFrecuente() {
		return pasajeroFrecuente;
	}

	public void setPasajeroFrecuente(PasajeroFrecuente pasajeroFrecuente) {
		this.pasajeroFrecuente = pasajeroFrecuente;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void RegistrarPasaporte(String nro, Pais pais, String autoridad, Date fechEmision, Date vencimiento) {
		this.pasaporte = new Pasaporte(nro, pais, autoridad, fechEmision, vencimiento);
	}

	public void RegistrarDireccion(int idDireccion, String calle, String altura, String ciudad, Pais pais, String cp) {
		this.direccion = new Direccion(idDireccion, calle, altura, ciudad, pais, cp);
	}

	public void RegistrarPasajeroFrecuente(String numero, LineaAerea lineaAerea, String categoria) {
		this.pasajeroFrecuente = new PasajeroFrecuente(numero, lineaAerea, categoria);
	}

	public void RegistrarTelefono(int idTelefono, String personal, String celular, String laboral) {
		this.telefono = new Telefono(idTelefono, personal, celular, laboral);
	}

}
