package edu.usal.negocio.dto;

public class PasajeroFrecuente {

	private String nroPF;
	private LineaAerea lineaAerea;
	private String categoria;

	public PasajeroFrecuente() {

	}

	public PasajeroFrecuente(String nroPF, LineaAerea lineaAerea, String categoria) {
		this.nroPF = nroPF;
		this.lineaAerea = lineaAerea;
		this.categoria = categoria;
	}

	public LineaAerea getLineaAerea() {
		return lineaAerea;
	}

	public void setLineaAerea(LineaAerea lineaAerea) {
		this.lineaAerea = lineaAerea;
	}

	public String getNroPF() {
		return nroPF;
	}

	public void setNroPF(String nroPF) {
		this.nroPF = nroPF;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
