package edu.usal.controller;


import edu.usal.negocio.dto.FormaDePago;
import edu.usal.negocio.dto.TarjetaCredito;


import java.util.List;
import java.util.ArrayList;



public class FormaDePagoController {
	

private List<FormaDePago> lFormaDePagos = null;

	
	
	public FormaDePagoController() {
		this.lFormaDePagos = new ArrayList<FormaDePago>();
	}

	

	public List<FormaDePago> getlFormaDePagos() {
		return lFormaDePagos;
	}



	public void setlFormaDePagos(List<FormaDePago> lFormaDePagos) {
		this.lFormaDePagos = lFormaDePagos;
	}



	public void cargarFormaDePagos() {
		
		this.lFormaDePagos = new ArrayList();		
		
		this.lFormaDePagos.add( new FormaDePago(0,"Efectivo"));
		this.lFormaDePagos.add( new TarjetaCredito(3,"Tarjeta de Credito",3));
		this.lFormaDePagos.add( new TarjetaCredito(6,"Tarjeta de Credito",6));		
		this.lFormaDePagos.add( new TarjetaCredito(12,"Tarjeta de Credito",12));
		this.lFormaDePagos.add( new TarjetaCredito(24,"Tarjeta de Credito",24));
	}
	
	
	public FormaDePago conseguirFormaDePago(int idFormaDePago) {

		for (FormaDePago f : this.lFormaDePagos) {
			if (f.getidPago() == idFormaDePago) {
				return f;
			}
		}
		return null;
	}
	
	public double FactorDeInteres(int idPago) {
		
		double factorDeInteres = -9;
		
				
		if(idPago > 6) {
			factorDeInteres = 1.1;
		}else {
			factorDeInteres = 1;
		}
		
		return factorDeInteres;
		
	}
	

}

