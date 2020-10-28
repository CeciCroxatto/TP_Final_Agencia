package edu.usal.controller;

import edu.usal.negocio.dto.Pasaporte;
import edu.usal.negocio.dto.Pais;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.PaisOtro;
import edu.usal.negocio.dto.Provincia;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class PasaporteController {

	private List<Pasaporte> lPasaportes = null;

	public PasaporteController() {
		this.lPasaportes = new ArrayList<Pasaporte>();
	}

	public List<Pasaporte> getlPasaportes() {
		return lPasaportes;
	}

	public void setlPasaportes(List<Pasaporte> lPasaportes) {
		this.lPasaportes = lPasaportes;
	}

	
	public void cargarPasaportes() {
		this.lPasaportes = new ArrayList();
		
		
		Date date1 = Calendar.getInstance().getTime();
		date1.setDate(02); //dia
		date1.setMonth(3-1);//mes
		date1.setYear(2010);
		Date date11 = Calendar.getInstance().getTime();
		date11.setDate(date1.getDate());
		date11.setMonth(date1.getMonth());
		date11.setYear(date1.getYear()+10);
		Provincia provincia1 = new Provincia ("CHU","Chubut");
		Pais pais1 = new PaisArgentina(054,"Argentina",provincia1);
		this.lPasaportes.add( new Pasaporte("ABC123456", pais1, "Gob Argentino", date1, date11));


		Date date2 = Calendar.getInstance().getTime();
		date2.setDate(13); //dia
		date2.setMonth(2-1);//mes
		date2.setYear(2012);
		Date date21 = Calendar.getInstance().getTime();
		date21.setDate(date2.getDate());
		date21.setMonth(date2.getMonth());
		date21.setYear(date2.getYear()+10);
		Provincia provincia2 = new Provincia ("LP","La Pampa");
		Pais pais2 = new PaisArgentina(054,"Argentina",provincia2);
		this.lPasaportes.add( new Pasaporte("ABC123457", pais2, "Gob Argentino", date2, date21));
		
		
		Date date3 = Calendar.getInstance().getTime();
		date3.setDate(20); //dia
		date3.setMonth(8-1);//mes
		date3.setYear(2013);
		Date date31 = Calendar.getInstance().getTime();
		date31.setDate(date3.getDate());
		date31.setMonth(date3.getMonth());
		date31.setYear(date3.getYear()+10);
		Pais pais3 = new PaisOtro(055,"Brasil","Camboriu");
		this.lPasaportes.add( new Pasaporte("BBC123455", pais3, "Gob Brasil", date3, date31));
		
		Date date4 = Calendar.getInstance().getTime();
		date4.setDate(20); //dia
		date4.setMonth(8-1);//mes
		date4.setYear(2014);
		Date date41 = Calendar.getInstance().getTime();
		date41.setDate(date4.getDate());
		date41.setMonth(date4.getMonth());
		date41.setYear(date4.getYear()+10);
		Pais pais4 = new PaisOtro(598,"Uruguay","Montevideo");
		this.lPasaportes.add( new Pasaporte("UBC123458", pais4, "Gob Uruguayo", date4, date41));
		
		Date date5 = Calendar.getInstance().getTime();
		date5.setDate(05); //dia
		date5.setMonth(8-1);//mes
		date5.setYear(2015);
		Date date51 = Calendar.getInstance().getTime();
		date51.setDate(date5.getDate());
		date51.setMonth(date5.getMonth());
		date51.setYear(date5.getYear()+10);
		Pais pais5 = new PaisOtro(598,"Uruguay","Colonia");
		this.lPasaportes.add( new Pasaporte("UBC123459", pais5, "Gob Uruguayo", date5, date51));
		
		Date date6 = Calendar.getInstance().getTime();
		date6.setDate(23); //dia
		date6.setMonth(2-1);//mes
		date6.setYear(2016);
		Date date61 = Calendar.getInstance().getTime();
		date61.setDate(date6.getDate());
		date61.setMonth(date6.getMonth());
		date61.setYear(date6.getYear()+10);
		Provincia provincia6 = new Provincia ("COR","Cordoba");
		Pais pais6 = new PaisArgentina(054,"Argentina",provincia6);
		this.lPasaportes.add( new Pasaporte("ABC123457", pais6, "Gob Argentino", date6, date61));
	
	}
	
	
	public Pasaporte conseguirPasaporte(String nroPasaporte) {

		for (Pasaporte pasaporte : this.lPasaportes) {
			if (pasaporte.getnroPasaporte().equals(nroPasaporte)) {
				return pasaporte;
			}
		}
		return null;
	}

	
	
}
