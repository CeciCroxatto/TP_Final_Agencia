package edu.usal.negocio.dto;

public class PaisOtro extends Pais {

	private String estado;

	public PaisOtro() {
		super();
	}

	public PaisOtro(int idPais, String descripcion, String Estado) {
		super(idPais, descripcion);
		this.estado = Estado;
	}

}
