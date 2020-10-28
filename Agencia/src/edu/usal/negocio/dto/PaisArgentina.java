package edu.usal.negocio.dto;

public class PaisArgentina extends Pais{
	
	private Provincia provincia;

	
	public PaisArgentina() {
		super();
	}
	
	
	public PaisArgentina(int idPais, String descripcion, Provincia provincia) {
		super(idPais, descripcion);
		this.provincia = provincia;
	}


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	
	//cargar lista de provincias de txt
	

}
