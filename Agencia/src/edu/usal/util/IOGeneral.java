package edu.usal.util;

import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;


@SuppressWarnings("resource")
public class IOGeneral {
	
	public static void println(String frase){
		System.out.println(frase);
	}
	
	public static int leerInt(String msjInicio, String msjError){
		IOGeneral.println(msjInicio);
		Scanner scan = new Scanner(System.in);
		while(!scan.hasNextInt()){
			IOGeneral.println(msjError);
			scan.next();
		}
		return scan.nextInt();
	}
	
	public static float leerFloat(String msjInicio, String msjError){
		IOGeneral.println(msjInicio);
		Scanner scan = new Scanner(System.in);
		while(!scan.hasNextFloat()){
			IOGeneral.println(msjError);
			scan.next();
		}
		return scan.nextFloat();
	}
	
	public static double leerDouble(String msjInicio, String msjError){
		IOGeneral.println(msjInicio);
		Scanner scan = new Scanner (System.in);
		while(!scan.hasNextDouble()){
			IOGeneral.println(msjError);
			scan.next();
		}
		return scan.nextDouble();
	}
	
	public static String leerLinea(String msjInicio){
		IOGeneral.println(msjInicio);
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	public static String leerFrase(String msjInicio){
		IOGeneral.println(msjInicio);
		Scanner scan = new Scanner (System.in);
		return scan.next();
	}
	
	
	public static Date convertirADate(String dd_mm_aaaa){
		
		Date date = Calendar.getInstance().getTime();
		String[] splitted = null;
		
		splitted = dd_mm_aaaa.split("[/]", 0);
		
		int dia = Integer.parseInt(splitted[0]);
		int mes = Integer.parseInt(splitted[1]);
		int anio = Integer.parseInt(splitted[2]);
		
		date.setDate(dia);
		date.setMonth(mes - 1);
		date.setYear(anio);
					
		return date;

	}
	
	

}

