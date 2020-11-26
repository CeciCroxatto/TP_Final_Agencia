package edu.usal.negocio.dto;

import java.util.Date;

public class Venta {
	
	private int idVenta;
	private Cliente cliente;
	private Vuelo vuelo;
	private Date fecha;
	private FormaDePago formaDePago;
	private double importe_vuelo;
	private double importe_total;
	
	public Venta() {

	}
	
	public Venta(int idVenta, Cliente cliente, Vuelo vuelo, Date fecha, 
			FormaDePago formaDePago, double importe_vuelo, double importe_total) {
		this.idVenta = idVenta;
		this.cliente = cliente;
		this.vuelo = vuelo;
		this.fecha = fecha;
		this.formaDePago = formaDePago;
		this.importe_vuelo = importe_vuelo;
		this.importe_total = importe_total;
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

	public double getImporte_vuelo() {
		return importe_vuelo;
	}

	public void setImporte_vuelo(double importe_vuelo) {
		this.importe_vuelo = importe_vuelo;
	}

	public double getImporte_total() {
		return importe_total;
	}

	public void setImporte_total(double importe_total) {
		this.importe_total = importe_total;
	}
	
	
	
	
	

}
