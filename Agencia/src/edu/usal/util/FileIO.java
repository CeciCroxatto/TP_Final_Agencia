
package edu.usal.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.io.FileWriter;



public class FileIO {

	// Atributos
//	private String name;

	

	// Constructores
	public FileIO() {
	}

//	public FileIO(String name) {
//		this.name = name;
//	}


	
	
	// Leer archivo de TEXTO y cargar lista de string
	public List<String> cargarLista(String clase) {
		
		PropertiesUtil prop = null;
		
		try {
			prop = new PropertiesUtil();

		} catch (IOException e) {
			IOGeneral.println("Error 1: " + e.toString());
		}
		
		List<String> lString = new ArrayList();
		String pathClase = prop.classToPath(clase);
//		System.out.println(pathClase);
	
		Scanner archivoEntrada = null;
	
		try {
			
			File fileToRead = new File(pathClase);
			archivoEntrada = new Scanner(fileToRead);
			
			while(archivoEntrada.hasNext()) {
				String lineaActual = archivoEntrada.nextLine();
				
				 if (!lineaActual.startsWith("#"))
					lString.add(lineaActual);
			}
			
		}catch (Exception e) {
			IOGeneral.println("Error 2: " + e.toString());
		}
		
		return lString;
	}
	
	
	// Guardar lista de string en archivo de TEXTO
	public boolean guardarLista(String claseString, List<String> lString){
		
		PropertiesUtil prop = null;
		
		try {
			prop = new PropertiesUtil();

		} catch (IOException e) {
			IOGeneral.println(e.toString());
			return false;
		}
		
		String pathClase = prop.classToPath(claseString);
//		System.out.println(pathClase);
		FileWriter archivo = null;
	
		try {
			
//			if (! new File(pathClase).exists()) {
//				archivo = new FileWriter(new File(pathClase), false);
//			}
//			else {
//				archivo = new FileWriter(new File(pathClase), true);					
//			}
			
			archivo = new FileWriter(new File(pathClase), false);
			
//			archivo.write("#int idCliente, String nombre, String apellido"
//					+ ", String dni, String nroPasaporte, String cuil"
//					+ ", Date fechNac, int idTelefono, String nroPF, String email"
//					+ ", int idDireccion" + "\n");
			
			for (String s : lString) {
				archivo.write(s+"\n");
			}			
			
			archivo.close();
				
			}catch (Exception e) {
				IOGeneral.println(e.toString());
				return false;
			}
		
		return true;
	}
	

	
	

}
