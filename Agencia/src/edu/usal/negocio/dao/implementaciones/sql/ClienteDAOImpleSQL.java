package edu.usal.negocio.dao.implementaciones.sql;

import java.util.ArrayList;
import java.util.List;

import edu.usal.util.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;

import edu.usal.negocio.dto.Cliente;
import edu.usal.negocio.dto.Pasaporte;
import edu.usal.negocio.dto.Telefono;
import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.negocio.dto.Direccion;
import edu.usal.controller.DireccionController;
import edu.usal.controller.PasajeroFrecuenteController;
import edu.usal.controller.PasaporteController;
import edu.usal.controller.TelefonoController;
import edu.usal.negocio.dao.interfaces.ClienteDAO;

public class ClienteDAOImpleSQL implements ClienteDAO {

	@Override
	public List<Cliente> cargarClientes(PasaporteController pasapContr, TelefonoController telefContr,
			PasajeroFrecuenteController pasajFContr, DireccionController direcContr) {

		ArrayList<Cliente> listaClientes = new ArrayList<>();
		ResultSet res;
		Pasaporte pasaporte = null;
		Telefono telefono = null;
		PasajeroFrecuente pasajeroFrecuente = null;
		Direccion direccion = null;

		try (Connection db = ConnectionDB.getConnection();
				Statement q = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
			res = q.executeQuery("Select * from Cliente");
			while (res.next()) {

				pasaporte = pasapContr.conseguirPasaporte(res.getString("PASAPORTE_ID"));
				telefono = telefContr.conseguirTelefono(res.getInt("TELEFONO_ID"));
				pasajeroFrecuente = pasajFContr.conseguirPasajeroFrecuente(res.getString("NRO_PASFREC"));
				direccion = direcContr.conseguirDireccion(res.getInt("DIRECCION_ID"));

//				public Cliente(int idCliente, String nombre, String apellido, String dni, 
//						Pasaporte pasaporte,
//						String cuil, Date fechNac, String email,
//						Telefono telefono, PasajeroFrecuente pasajeroFrecuente, Direccion direccion)
				listaClientes.add(new Cliente(res.getInt("IDCLIENTE"), res.getString("NOMBRE"),
						res.getString("APELLIDO"), res.getString("DNI"), pasaporte, res.getString("CUIL"),
						res.getDate("FECNAC"), res.getString("MAIL"), telefono, pasajeroFrecuente, direccion));
			}
			res.close();

		} catch (Exception e) {
			e.printStackTrace();
			return listaClientes;
		}

		return listaClientes;

	}

	public String nroPasaporte_por_CUIL(String cuil) {

		// con el cuil en la base consigo el numero de pasaporte
		ResultSet res = null;
		String pasaporte_id = null;

		try (Connection con = ConnectionDB.getConnection();
				Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

			con.setAutoCommit(false);

			res = stm.executeQuery("Select * from Cliente where CUIL = '" + cuil + "'");

			while (res.next()) {

				if (res != null) {

					pasaporte_id = res.getString("PASAPORTE_ID");
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

		return pasaporte_id;

	}

	public int crearCliente(String nombre, String apellido, String dni, String cuil, String nroPasaporte,
			String fechNac, String email, int idTelefono, String nroPF, int idDireccion) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		int registrosAlterados = -302;

		try {
			con.setAutoCommit(false);
			cst = con.prepareCall("EXEC sp_CrearCliente ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
			cst.setString(1, nombre);
			cst.setString(2, apellido);
			cst.setString(3, dni);
			cst.setString(4, cuil);
			cst.setString(5, nroPasaporte);
			String fechaS = fechNac;
			cst.setString(6, fechaS);
			cst.setInt(7, idTelefono);
			cst.setString(8, nroPF);
			cst.setString(9, email);
			cst.setInt(10, idDireccion);

			try {
				registrosAlterados = cst.executeUpdate();
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

		return registrosAlterados;

	}

	public int bajarCliente(String cuil) {
		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		int registrosAlterados = -22;

		try {
			con.setAutoCommit(false);
			cst = con.prepareCall("EXEC sp_BajaLogicaCliente ?");

			cst.setString(1, cuil);

			try {
				registrosAlterados = cst.executeUpdate();
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

		return registrosAlterados;

	}

	public String consultarCliente(String cuil) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		ResultSet res = null;
		String texto = "No se encontro un Cliente con ese CUIL";

		try {

			con.setAutoCommit(false);
			cst = con.prepareCall("EXEC sp_ConsultarCliente ?");
			cst.setString(1, cuil);

			res = cst.executeQuery();

			while (res.next()) {

				if (res != null) {

					texto = "Nombre: " + res.getString("NOMBRE") + "<br>" + " Apellido: " + res.getString("APELLIDO")
							+ "<br>" + "DNI: " + res.getString("DNI") + "<br>" + "CUIL: " + res.getString("CUIL")
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

	public int modificarCliente(String nombre, String apellido, String dni, String cuil, String nroPasaporte,
			String fechaS, String email, int idTelefono, String nroPF, int idDireccion) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		int registrosAlterados = -13;

		try {
			con.setAutoCommit(false);
			cst = con.prepareCall("EXEC sp_ModificarCliente ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?");
			cst.setString(1, nombre);
			cst.setString(2, apellido);
			cst.setString(3, dni);
			cst.setString(4, cuil);
			cst.setString(5, nroPasaporte);
			cst.setString(6, fechaS);
			cst.setInt(7, idTelefono);
			cst.setString(8, nroPF);
			cst.setString(9, email);
			cst.setInt(10, idDireccion);

			try {
				registrosAlterados = cst.executeUpdate();
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

		return registrosAlterados;

	}

}
