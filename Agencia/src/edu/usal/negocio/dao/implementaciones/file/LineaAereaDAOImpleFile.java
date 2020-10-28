package edu.usal.negocio.dao.implementaciones.file;

import edu.usal.controller.AlianzaController;
import edu.usal.controller.PasaporteController;
import edu.usal.negocio.dao.interfaces.LineaAereaDAO;
import edu.usal.negocio.dto.Alianza;
import edu.usal.negocio.dto.LineaAerea;

import edu.usal.util.FileIO;
import java.util.ArrayList;
import java.util.List;




public class LineaAereaDAOImpleFile implements LineaAereaDAO {

	private List<LineaAerea> lLineaAereaDAO;

	
	
	public List<LineaAerea> getlLineaAereaDAO() {
		return lLineaAereaDAO;
	}

	public void setlLineaAereaDAO(List<LineaAerea> lLineaAereaDAO) {
		this.lLineaAereaDAO = lLineaAereaDAO;
	}
	
	
	
	@Override
	public List<LineaAerea> cargarLineaAereas(AlianzaController alianContr){
		
		this.lLineaAereaDAO = new ArrayList();
		FileIO IOfile = new FileIO();
		String[] splitted = null;
		String idLAerea = null;
		String nombre = null;
		String idAlianza = null;
		Alianza alianza = null;

		for (String s : IOfile.cargarLista("LineaAerea")) {

			splitted = s.split("[,]", 0);
			idLAerea = splitted[0];
			nombre = splitted[1];
			idAlianza = splitted[2];
			alianza = alianContr.conseguirAlianza(idAlianza);
			
			
			//public LineaAerea(String idLAerea, String nombre, Alianza alianza)
			lLineaAereaDAO.add(new LineaAerea(idLAerea, nombre, alianza));
		}
		
		return this.lLineaAereaDAO;
		
	}
	
	
	@Override
	public void guardarLineaAereas(List<LineaAerea> lLineaAereas) {
	
		FileIO IOfile = new FileIO();
		List<String> lString = new ArrayList();
		
		lString.add("# String idLAerea, String nombre, String idAlianza (primeras 3 letras)");
		lString.add("# idLAerea: https://www.iata.org/en/publications/directories/code-search/");
		lString.add("# idAlianza: https://www.infoviajera.com/2015/08/listado-de-alianzas-de-aerolineas-actualizado/");
		
		for (LineaAerea la : lLineaAereas) {
			
			lString.add(la.getIdLAerea() + "," + la.getNombre() 
			+ "," + la.getAlianza().getIdAlianza());
		}
		
		IOfile.guardarLista("LineaAerea", lString);
		
		
	}
	
	
	
}