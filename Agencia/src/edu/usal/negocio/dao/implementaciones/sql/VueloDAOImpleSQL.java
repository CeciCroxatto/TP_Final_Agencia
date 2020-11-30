package edu.usal.negocio.dao.implementaciones.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.usal.controller.AeropuertoController;
import edu.usal.controller.LineaAereaController;
import edu.usal.negocio.dao.interfaces.VueloDAO;
import edu.usal.negocio.dto.Vuelo;
import edu.usal.util.ConnectionDB;

public class VueloDAOImpleSQL implements VueloDAO {

	@Override
	public List<Vuelo> cargarVuelos(AeropuertoController aeropContr, LineaAereaController lineaAContr) {

		ArrayList<Vuelo> listaVuelos = new ArrayList<>();
		
		
		return listaVuelos;
	}

	@Override
	public void guardarVuelos(List<Vuelo> lVuelos) {

	}
	
	
	public Date conseguirFechaDeSalida_por_ID(int idVuelo) {

		ResultSet res = null;
		String fechaSalidaS = null;
		Date fechaSalida = null;

		try (Connection con = ConnectionDB.getConnection();
				Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			con.setAutoCommit(false);

			res = stm.executeQuery("Select * from Vuelo where IDVUELO = '" + idVuelo + "'");

			while (res.next()) {

				if (res != null) {

					fechaSalidaS = res.getString("FEC_SALIDA");
					
					try {
						fechaSalida = new SimpleDateFormat("yyyy-MM-dd").parse(fechaSalidaS);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					
					
				}
			}

			res.close();
			con.close();
			if (con.isClosed())
				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			System.out.println("No se pudo conectar a la base ");
			e.printStackTrace();
			try {
//				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return fechaSalida;

	}
	
	
	public Date conseguirFechaDeLlegada_por_ID(int idVuelo) {

		ResultSet res = null;
		String fechaLlegadaS =  null;
		Date fechaLlegada = null;

		try (Connection con = ConnectionDB.getConnection();
				Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			con.setAutoCommit(false);

			res = stm.executeQuery("Select * from Vuelo where IDVUELO = '" + idVuelo + "'");

			while (res.next()) {

				if (res != null) {

					fechaLlegadaS = res.getString("FEC_LLEGADA");
					
					try {
						fechaLlegada = new SimpleDateFormat("yyyy-MM-dd").parse(fechaLlegadaS);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
			}

			res.close();
			con.close();
			if (con.isClosed())
				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			System.out.println("No se pudo conectar a la base ");
			e.printStackTrace();
			try {
//				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return fechaLlegada;

	}
	
	
	

	public int crearVuelo(String numeroVuelo, int asientosTotales, int asientosDisponibles, String idAeropuertoSalida,
			String idAeropuertoLlegada, String fechSalidaS, String fechLlegadaS, String horasVuelo, String idLAerea) {

		Connection con = ConnectionDB.getConnection();
		PreparedStatement ps = null;
		int registrosAlterados = -302;

		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement("INSERT INTO Vuelo VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, numeroVuelo);
			ps.setInt(2, asientosDisponibles);
			ps.setInt(3, asientosTotales);
			ps.setString(4, idAeropuertoSalida);
			ps.setString(5, idAeropuertoLlegada);
			ps.setString(6, fechSalidaS);
			ps.setString(7, fechLlegadaS);
			ps.setString(8, horasVuelo);
			ps.setString(9, idLAerea);
			ps.setInt(10, 1);

			try {
				registrosAlterados = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					ps.close();
					ConnectionDB.RollBack(con);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			con.commit();
			con.close();
			if (con.isClosed())
				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return registrosAlterados;

	}

	
	public int bajarVuelo(String numeroVuelo, String fechSalidaS) {
		Connection con = ConnectionDB.getConnection();
		PreparedStatement ps = null;
		int registrosAlterados = -22;
		
		System.out.println(numeroVuelo + fechSalidaS);

		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement("update Vuelo set ESTADO = 0 where NROVUELO = ? AND FEC_SALIDA = ?");
			
			ps.setString(1, numeroVuelo);
			ps.setString(2, fechSalidaS);

			try {
				registrosAlterados = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					ps.close();
					ConnectionDB.RollBack(con);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			con.commit();
			con.close();
			if (con.isClosed())
				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return registrosAlterados;

	}
	

	public String consultarVuelo(String numeroVuelo, String fechSalidaS) {
		
		Connection con = ConnectionDB.getConnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		String texto = "No se encontro un Vuelo con ese Numero de Vuelo para esa Fecha de Salida";

		try {

			con.setAutoCommit(false);
			ps = con.prepareStatement("Select * from Vuelo where NROVUELO = ? AND FEC_SALIDA = ?");
			ps.setString(1, numeroVuelo);
			ps.setString(2, fechSalidaS);
			
			res = ps.executeQuery();
			
			texto = "";
			
			while (res.next()) {

				if (res != null) {

					texto = texto + "IDVuelo: " + res.getString("IDVUELO") + "<br>" + " NroVuelo: " + res.getString("NROVUELO") + "<br>"
							+ "Asientos Disponibles: " + res.getString("ASIENTOS_DISPONIBLES") + "<br>" 
							+ "Fecha de Salida: " + res.getString("FEC_SALIDA") + "<br>"
							+ "Horas de Vuelo: " + res.getString("HORAS_VUELO") + "<br>"
							+ "<br>";
				} 
			}

			res.close();
			con.close();
			if (con.isClosed())
				System.out.println("Conexion cerrada");

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} 

		
		
		return texto;
	}
	
	

	public int modificarVuelo(int idVuelo, String numeroVuelo, int asientosTotales, int asientosDisponibles, 
			String idAeropuertoSalida, String idAeropuertoLlegada, 
			String fechSalidaS, String fechLlegadaS, String horasVuelo, String idLAerea) {
		
		Connection con = ConnectionDB.getConnection();
		PreparedStatement ps = null;
		int registrosAlterados = -13;
		
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement("UPDATE Vuelo SET NROVUELO = ?, ASIENTOS_DISPONIBLES = ?, "
					+ "ASIENTOS_TOTALES = ?, AEROPUERTO_SALIDA_ID = ?, AEROPUERTO_LLEGADA_ID = ?,"
					+ "FEC_SALIDA = ?, FEC_LLEGADA = ?, HORAS_VUELO = ?, AEROLINEA_ID = ? "
					+ "where IDVUELO = ?");
			
			ps.setString(1, numeroVuelo);
			ps.setInt(2, asientosDisponibles);
			ps.setInt(3, asientosTotales);
			ps.setString(4, idAeropuertoSalida);
			ps.setString(5, idAeropuertoLlegada);
			ps.setString(6, fechSalidaS);
			ps.setString(7, fechLlegadaS);
			ps.setString(8, horasVuelo);
			ps.setString(9, idLAerea);
			ps.setInt(10, idVuelo);

			try {
				registrosAlterados = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					ps.close();
					ConnectionDB.RollBack(con);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			con.commit();
			con.close();
			if (con.isClosed())
				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return registrosAlterados;

	}
	
	
	
}
