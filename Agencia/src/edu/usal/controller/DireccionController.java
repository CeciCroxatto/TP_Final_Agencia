package edu.usal.controller;

import edu.usal.negocio.dto.Direccion;
import edu.usal.negocio.dto.Pais;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;
import edu.usal.negocio.dto.Provincia;

import java.util.List;
import java.util.ArrayList;

public class DireccionController {

	private List<Direccion> lDirecciones = null;

	public DireccionController() {
		this.lDirecciones = new ArrayList<Direccion>();
	}

	public List<Direccion> getlDirecciones() {
		return lDirecciones;
	}

	public void setlDirecciones(List<Direccion> lDirecciones) {
		this.lDirecciones = lDirecciones;
	}

	public void cargarDirecciones() {

		this.lDirecciones = new ArrayList();

		Provincia provincia1 = new Provincia("CHU", "Chubut");
		Pais pais1 = new PaisArgentina(054, "Argentina", provincia1);
		this.lDirecciones.add(new Direccion(001, "Sarmiento", "1800", "Prov Chubut", pais1, "CZ1424"));

		Provincia provincia2 = new Provincia("LP", "La Pampa");
		Pais pais2 = new PaisArgentina(054, "Argentina", provincia2);
		this.lDirecciones.add(new Direccion(002, "Rio de Janeiro", "555", "Pampa", pais2, "CZ0424"));

		Pais pais3 = new PaisOtro(055, "Brasil", "Camboriu");
		this.lDirecciones.add(new Direccion(003, "Rua 125", "105 - Casa", "Balneario Camborio", pais3, "BZ0424"));

		Pais pais4 = new PaisOtro(598, "Uruguay", "Montevideo");
		this.lDirecciones.add(new Direccion(004, "Margarita", "2455 6to C", "Mvido", pais4, "UZ0424"));

		Pais pais5 = new PaisOtro(598, "Uruguay", "Colonia");
		this.lDirecciones.add(new Direccion(005, "Azul", "455", "Conomia de Sacramento", pais5, "UZ0424"));

		Provincia provincia6 = new Provincia("COR", "Cordoba");
		Pais pais6 = new PaisArgentina(054, "Argentina", provincia6);
		this.lDirecciones.add(new Direccion(006, "San Martin", "1850", "Gral Belgrano", pais6, "CZ1424"));
	}

	public Direccion conseguirDireccion(int idDireccion) {

		for (Direccion direccion : this.lDirecciones) {
			if (direccion.getIdDireccion() == idDireccion) {
				return direccion;
			}
		}
		return null;
	}

}
