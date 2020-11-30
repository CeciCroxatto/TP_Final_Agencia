package edu.usal.negocio.dao.implementaciones.sql;

import java.util.ArrayList;
import java.util.List;

import edu.usal.util.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dto.Alianza;
import edu.usal.controller.AlianzaController;
import edu.usal.negocio.dao.interfaces.LineaAereaDAO;

public class LineaAereaDAOImpleSQL implements LineaAereaDAO {

	@Override
	public List<LineaAerea> cargarLineaAereas(AlianzaController alianContr) {

		ArrayList<LineaAerea> listaLineaAereas = new ArrayList<>();
		ResultSet res;
		Alianza alianza = null;

		try (Connection con = ConnectionDB.getConnection();
				Statement q = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
			res = q.executeQuery("Select * from LineaAerea");
			while (res.next()) {

				alianza = alianContr.conseguirAlianza(res.getString("ALIANZA"));

				listaLineaAereas.add(new LineaAerea(res.getString("IDLAEREA"), res.getString("NOMBRE"), alianza));
			}
			res.close();
			con.close();
			if (con.isClosed())
				System.out.println("Conexion cerrada");

		} catch (Exception e) {
			e.printStackTrace();
			return listaLineaAereas;
		}
		System.out.println("Base de datos le�da");

		return listaLineaAereas;

	}

	@Override
	public void guardarLineaAereas(List<LineaAerea> lLineaAereas) {

//		Connection con = ConnectionDB.getConnection();
//		Statement stm = null;
//
//		try {
//			con.setAutoCommit(false);
//			stm = con.createStatement();
//			// para que borre la base
//			stm.executeUpdate("DELETE FROM LineaAerea");
//
//			for (LineaAerea lineaAerea : lLineaAereas) {
//				insert(lineaAerea, con, stm);
//			}
//			con.commit();
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//			try {
//				ConnectionDB.RollBack(con);
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//
//		}

	}

	public int crearLineaAerea(LineaAerea lineaAerea) {

		Connection con = ConnectionDB.getConnection();
		Statement stm = null;
		int registrosAlterados = -302;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();

			try {
				registrosAlterados = stm.executeUpdate(
						"INSERT INTO LineaAerea VALUES('" + lineaAerea.getIdLAerea() + "','" + lineaAerea.getNombre()
								+ "','" + lineaAerea.getAlianza().getIdAlianza() + "','" + "1" + "')");
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					stm.close();
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

	public int bajarLineaAerea(String idLAerea) {
		Connection con = ConnectionDB.getConnection();
		Statement stm = null;
		int registrosAlterados = -22;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();

			registrosAlterados = stm
					.executeUpdate("update LineaAerea set ESTADO = 0 where IDLAEREA = '" + idLAerea + "'");

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

	public String consultarLineaAerea(String idLAerea) {

		ResultSet res = null;
		String texto = "No se encontro una Linea Aerea con ese ID";
//		Connection con = null;

		try (Connection con = ConnectionDB.getConnection();
				Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			con.setAutoCommit(false);

			res = stm.executeQuery("Select * from LineaAerea where IDLAEREA = '" + idLAerea + "'");

			while (res.next()) {

				if (res != null) {

					String estado = null;
					if (res.getInt("ESTADO") == 0) {
						estado = "La Linea Aerea se encuentra dada de baja";
					} else {
						estado = "La Linea Aerea se encuentra dada de alta";
					}

					texto = "ID: " + res.getString("IDLAEREA") + "<br>" + " Nombre: " + res.getString("NOMBRE") + "<br>"
							+ "Alianza:" + res.getString("ALIANZA") + "<br>" + "Estado: " + estado + "<br>";
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
//			finally {
//			res.close();
//			con.close();
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");
//		}
		
		
		return texto;
	}

	public int verificarID(String idLAerea) {
		Connection con = ConnectionDB.getConnection();
		Statement stm = null;
		ResultSet res;
		int existe = -4;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();

			res = stm.executeQuery("select * from LineaAerea where IDLAEREA = '" + idLAerea + "'");

			
			while (res.next()) {
				
				existe = 1;

//				if ( idLAerea == res.getString("IDLAEREA") ) {
//
//					existe = 1;
//
//				} else {
//					existe = 0;
//				}
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
		return existe;
	}

	public int modificarLineaAerea(String idLAerea, String nombre, String idAlianza, int estadoBoolean) {
		Connection con = ConnectionDB.getConnection();
		Statement stm = null;
		int registrosAlterados = -13;
		
//		System.out.println(nombre + idAlianza + estadoBoolean + idLAerea);

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();

			registrosAlterados = stm.executeUpdate("update LineaAerea set NOMBRE = '" + nombre + "', " + "ALIANZA = '"
					+ idAlianza + "', " + "ESTADO = " + estadoBoolean + " where IDLAEREA = '" + idLAerea + "'");

//			registrosAlterados = stm.executeUpdate("update LineaAerea set NOMBRE = 'aaaaaa', ALIANZA = 'Sky', ESTADO = 1 where IDLAEREA = 'AA'");
	
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
