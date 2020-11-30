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

	@Override
	public void guardarVentas(List<Venta> lVentas) {

	}

	public String crearVenta(String cuil, int idVuelo, int idPago, double importe_vuelo, double importe_total) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		ResultSet res = null;
		String texto = "VACIO";

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

			texto = res.getString("msg");

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
