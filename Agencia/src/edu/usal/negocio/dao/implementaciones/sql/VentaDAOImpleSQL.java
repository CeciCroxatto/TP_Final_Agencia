package edu.usal.negocio.dao.implementaciones.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.usal.controller.ClienteController;
import edu.usal.controller.FormaDePagoController;
import edu.usal.controller.VueloController;
import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dto.Venta;
import edu.usal.util.ConnectionDB;

public class VentaDAOImpleSQL implements VentaDAO {

	@Override
	public List<Venta> cargarVentas(ClienteController clientContr, VueloController vueloContr,
			FormaDePagoController formaDPContr) {

		ArrayList<Venta> listaVentas = new ArrayList<>();

		return listaVentas;
	}

	public String crearVenta(String cuil, int idVuelo, int idPago, double importe_vuelo, double importe_total) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		ResultSet res = null;
		String texto = "AAA";

		try {
			con.setAutoCommit(false);
			cst = con.prepareCall("EXEC sp_CrearVenta ?, ?, ?, ?, ?");
			cst.setString(1, cuil);
			cst.setInt(2, idVuelo);
			cst.setInt(3, idPago);
			cst.setDouble(4, importe_vuelo);
			cst.setDouble(5, importe_total);

			try {
				res = cst.executeQuery();
				while (res.next()) {
					if (res != null) {
						texto = res.getString("msg");
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
				try {
					cst.close();
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

		return texto;

	}

	public String bajarVenta(String cuil, String numeroVuelo) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		ResultSet res = null;
		String texto = "BBB";

		try {
			con.setAutoCommit(false);
			cst = con.prepareCall("EXEC sp_BajaLogicaVenta ?, ?");
			cst.setString(1, cuil);
			cst.setString(2, numeroVuelo);

			try {
				res = cst.executeQuery();
				while (res.next()) {
					if (res != null) {
						texto = res.getString("msg");
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
				try {
					cst.close();
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

		return texto;

	}

	public String consultarVenta(String cuil, String numeroVuelo) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		ResultSet res = null;
		String texto = "No se encontro una Venta para ese CUIL y Numero de Vuelo";

		try {

			con.setAutoCommit(false);
			cst = con.prepareCall("EXEC sp_ConsultarVenta ?, ?");
			cst.setString(1, cuil);
			cst.setString(2, numeroVuelo);

			res = cst.executeQuery();
			texto = "";

			while (res.next()) {

				if (res != null) {

					texto = texto + "Nombre: " + res.getString("NOMBRE") + "<br>" + " Apellido: "
							+ res.getString("APELLIDO") + "<br>" + "CUIL: " + res.getString("CUIL") + "<br>"
							+ "Numero de Vuelo: " + res.getString("NROVUELO") + "<br>" + "Aeropuerto de Salida: "
							+ res.getString("AEROPUERTO_SALIDA_ID") + "<br>" + "Aeropuerto de Llegada: "
							+ res.getString("AEROPUERTO_LLEGADA_ID") + "<br>" + "Nombre de LineaAerea: "
							+ res.getString("nombreLA") + "<br>" + "Importe total: " + res.getString("importe_total")
							+ "<br>" + "<br>";
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

	public String modificarVenta(String cuil, String numeroVuelo, String fechVenta, int idPago, double importe_vuelo,
			double importe_total) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		ResultSet res = null;
		String texto = "DDD";

		try {
			con.setAutoCommit(false);
			cst = con.prepareCall("EXEC sp_ModificarVenta ?, ?, ?, ?, ?, ?");
			cst.setString(1, cuil);
			cst.setString(2, numeroVuelo);
			cst.setString(3, fechVenta);
			cst.setInt(4, idPago);
			cst.setDouble(5, importe_vuelo);
			cst.setDouble(6, importe_total);

			try {
				res = cst.executeQuery();
				while (res.next()) {
					if (res != null) {
						texto = res.getString("msg");
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
				try {
					cst.close();
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

		return texto;

	}

}
