
package edu.usal.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import java.util.Properties;



public class PropertiesUtil {
	
	private static Properties properties = new Properties();
//	private static PropertiesUtil objeto = null;
	
	
	
	public PropertiesUtil() throws FileNotFoundException, IOException 
	{
		properties.load(new FileReader("config.properties"));
//		System.out.println(properties);
	}
	
	
	
	
	public String classToPath(String clase) {

		boolean bVocal = false;
		List<String> lVocales = new ArrayList<String>();
		lVocales.add("a");
		lVocales.add("e");
		lVocales.add("i");
		lVocales.add("o");
		lVocales.add("u");

		String ultimaLetra = String.valueOf(clase.charAt(clase.length() - 1));
		for (String sVocal : lVocales) {
			if (ultimaLetra.contains(sVocal)) {
				bVocal = true;
				break;
			}
		}

		List<String> lclases = new ArrayList<String>();
		lclases.add("path");
		lclases.add(clase);

		if (bVocal)
			lclases.add("s");
		else
			lclases.add("es");

		String sClase = String.join("", lclases);

		String pathClase = PropertiesUtil.getProperties().getProperty(sClase);

		return pathClase;

	}
	
	
	
	
	
//	public static PropertiesUtil getInstance() throws FileNotFoundException, IOException 
//	{
//		if(objeto == null)
//			objeto = new PropertiesUtil();
//		return objeto;
//	}
	
	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		PropertiesUtil.properties = properties;
	}
		
}


