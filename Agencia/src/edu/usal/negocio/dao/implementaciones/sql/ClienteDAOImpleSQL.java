package edu.usal.negocio.dao.implementaciones.sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.usal.util.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public List<Cliente> cargarClientes(PasaporteController pasapContr
			, TelefonoController telefContr, PasajeroFrecuenteController pasajFContr
			, DireccionController direcContr) {

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
				listaClientes.add(new Cliente(res.getInt("IDCLIENTE"), res.getString("NOMBRE"), res.getString("APELLIDO"), res.getString("DNI"),
											pasaporte,
											res.getString("CUIL"), res.getDate("FECNAC"), res.getString("MAIL"),
											telefono, pasajeroFrecuente, direccion));
			}
			res.close();

		} catch (Exception e) {
			e.printStackTrace();
			return listaClientes;
		}

		return listaClientes;

	}

	
	
	@Override
	public void guardarClientes(List<Cliente> lClientes) {

		Connection con = ConnectionDB.getConnection();
		Statement stm = null;

		try {
			con.setAutoCommit(false);

			stm = con.createStatement();
			// para que borre la base
			stm.executeUpdate("DELETE FROM Cliente");

			for (Cliente cliente : lClientes) {
				stm.executeUpdate("INSERT INTO Cliente VALUES('" 
						+ cliente.getIdLAerea() + "','"
						+ cliente.getNombre() + "','" 
						+ cliente.getAlianza().getNombre() + "')");
			}
			con.commit();

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

}
