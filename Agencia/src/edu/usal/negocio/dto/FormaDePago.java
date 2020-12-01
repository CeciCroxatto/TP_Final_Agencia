package edu.usal.negocio.dto;

public class FormaDePago {

	protected int idPago;
	protected String tipo;

	public FormaDePago() {

	}

	public FormaDePago(int idPago, String tipo) {

		this.idPago = idPago;
		this.tipo = tipo;
	}

	public int getidPago() {
		return idPago;
	}

	public void setidPago(int idPago) {
		this.idPago = idPago;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
