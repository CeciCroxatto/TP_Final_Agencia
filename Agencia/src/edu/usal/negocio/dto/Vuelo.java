package edu.usal.negocio.dto;

import java.util.Date;


public class Vuelo {
	
	private int idVuelo;
	private String numeroVuelo;
	private int asientosTotales;
	private int asientosDisponibles;
	private Aeropuerto aeropuertoSalida;
	private Aeropuerto aeropuertoLlegada;
	private Date fechSalida;
	private Date fechLlegada;
	private String horasVuelo;
	private LineaAerea lineaAereaVuelo;

	public Vuelo() {

	}
	
	
	public Vuelo(int idVuelo, String numeroVuelo, int asientosTotales, int asientosDisponibles,
			 Date fechSalida, Date fechLlegada,
			String horasVuelo) {
		this.idVuelo = idVuelo;
		this.numeroVuelo = numeroVuelo;
		this.asientosTotales = asientosTotales;
		this.asientosDisponibles = asientosDisponibles;
		this.aeropuertoSalida = new Aeropuerto();
		this.aeropuertoLlegada = new Aeropuerto();
		this.fechSalida = fechSalida;
		this.fechLlegada = fechLlegada;
		this.horasVuelo = horasVuelo;
		this.lineaAereaVuelo = new LineaAerea();
	}


	public int getIdVuelo() {
		return idVuelo;
	}


	public void setIdVuelo(int idVuelo) {
		this.idVuelo = idVuelo;
	}


	public String getNumeroVuelo() {
		return numeroVuelo;
	}


	public void setNumeroVuelo(String numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}


	public int getAsientosTotales() {
		return asientosTotales;
	}


	public void setAsientosTotales(int asientosTotales) {
		this.asientosTotales = asientosTotales;
	}


	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}


	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}


	public Aeropuerto getAeropuertoSalida() {
		return aeropuertoSalida;
	}


	public void setAeropuertoSalida(Aeropuerto aeropuertoSalida) {
		this.aeropuertoSalida = aeropuertoSalida;
	}


	public Aeropuerto getAeropuertoLlegada() {
		return aeropuertoLlegada;
	}


	public void setAeropuertoLlegada(Aeropuerto aeropuertoLlegada) {
		this.aeropuertoLlegada = aeropuertoLlegada;
	}


	public Date getFechSalida() {
		return fechSalida;
	}


	public void setFechSalida(Date fechSalida) {
		this.fechSalida = fechSalida;
	}


	public Date getFechLlegada() {
		return fechLlegada;
	}


	public void setFechLlegada(Date fechLlegada) {
		this.fechLlegada = fechLlegada;
	}


	public String getHorasVuelo() {
		return horasVuelo;
	}


	public void setHorasVuelo(String horasVuelo) {
		this.horasVuelo = horasVuelo;
	}


	public LineaAerea getLineaAereaVuelo() {
		return lineaAereaVuelo;
	}


	public void setLineaAereaVuelo(LineaAerea lineaAereaVuelo) {
		this.lineaAereaVuelo = lineaAereaVuelo;
	}
	
	
	
	public void RegistrarAeropuertoSalida(String id, String ciudad, Pais pais) {
		this.aeropuertoSalida = new Aeropuerto(id, ciudad, pais);
	}
	
	public void RegistrarAeropuertoLlegada(String id, String ciudad, Pais pais) {
		this.aeropuertoLlegada = new Aeropuerto(id, ciudad, pais);
	}
	
	public void RegistrarLineaAerea(String idLineaAerea, String nombre, Alianza alianza) {
		this.lineaAereaVuelo = new LineaAerea(idLineaAerea, nombre, alianza);
	}
	
	
	
	
	
	
	

}
