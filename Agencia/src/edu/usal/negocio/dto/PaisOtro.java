package edu.usal.negocio.dto;

public class PaisOtro extends Pais {
	
	private String provEstado;

	public PaisOtro() {
		super();
	}

	public PaisOtro(int idPais, String descripcion, String provEstado) {
		super(idPais, descripcion);
		this.provEstado = provEstado;
	}

	public String getProvEstado() {
		return provEstado;
	}

	public void setProvEstado(String provincia) {
		this.provEstado = provincia;
	}

}
