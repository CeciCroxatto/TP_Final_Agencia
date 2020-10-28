package edu.usal.negocio.dto;

import java.util.Date;

public class Venta {
	
	private int idVenta;
	private Cliente cliente;
	private Vuelo vuelo;
	private Date fecha;
	private FormaDePago formaDePago;
	
	public Venta() {

	}
	
	public Venta(int idVenta, Cliente cliente, Vuelo vuelo, Date fecha, FormaDePago formaDePago) {
		this.idVenta = idVenta;
		this.cliente = cliente;
		this.vuelo = vuelo;
		this.fecha = fecha;
		this.formaDePago = formaDePago;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	
	
	
	

}
