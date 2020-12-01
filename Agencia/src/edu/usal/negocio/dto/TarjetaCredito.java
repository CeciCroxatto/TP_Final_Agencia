package edu.usal.negocio.dto;

public class TarjetaCredito extends FormaDePago {

	private int cuotas;

	public TarjetaCredito() {
		super();
	}

	public TarjetaCredito(int idFormaDePago, String tipo, int cuotas) {
		super(idFormaDePago, tipo);
		this.cuotas = cuotas;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

}
