package edu.usal.manager;

import java.sql.Connection;
import java.sql.SQLException;

import edu.usal.negocio.dao.factory.ClienteFactory;
import edu.usal.negocio.dao.factory.DireccionFactory;
import edu.usal.negocio.dao.factory.PasajeroFrecuenteFactory;
import edu.usal.negocio.dao.factory.PasaporteFactory;
import edu.usal.negocio.dao.factory.TelefonoFactory;
import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dao.interfaces.DireccionDAO;
import edu.usal.negocio.dao.interfaces.PasajeroFrecuenteDAO;
import edu.usal.negocio.dao.interfaces.PasaporteDAO;
import edu.usal.negocio.dao.interfaces.TelefonoDAO;
import edu.usal.negocio.dto.Cliente;
import edu.usal.negocio.dto.Direccion;
import edu.usal.negocio.dto.PasajeroFrecuente;
import edu.usal.negocio.dto.Pasaporte;
import edu.usal.negocio.dto.Telefono;
import edu.usal.util.ConnectionDB;

public class ClienteManager {

	private ClienteDAO clienteDAOImpleSQL;
	private PasaporteDAO pasaporteDAOImpleSQL;
	private TelefonoDAO telefonoDAOImpleSQL;
	private PasajeroFrecuenteDAO pasajeroFrecuenteDAOImpleSQL;
	private DireccionDAO direccionDAOImpleSQL;

	public ClienteManager() {

		this.clienteDAOImpleSQL = ClienteFactory.getImplementacion("SQL");
		this.pasaporteDAOImpleSQL = PasaporteFactory.getImplementacion("SQL");
		this.telefonoDAOImpleSQL = TelefonoFactory.getImplementacion("SQL");
		this.pasajeroFrecuenteDAOImpleSQL = PasajeroFrecuenteFactory.getImplementacion("SQL");
		this.direccionDAOImpleSQL = DireccionFactory.getImplementacion("SQL");

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public int altaClienteUnificado(Direccion direccion, Pasaporte pasaporte, Telefono telefono,
			PasajeroFrecuente pasajeroFrecuente, Cliente cliente) {

		Connection con = null;

		cliente.setPasaporte(pasaporte);
		cliente.setTelefono(telefono);
		cliente.setPasajeroFrecuente(pasajeroFrecuente);
		cliente.setDireccion(direccion);
		String cuil = cliente.getCuil();

		int clienteAlterado = 0;
		int pasaporteAlterado = 0;
		int telefonoAlterado = 0;
		int pasajeroFrecuenteAlterado = 0;
		int direccionAlterado = 0;

		try {
			con = ConnectionDB.getConnection();
			con.setAutoCommit(false);

			clienteAlterado = this.clienteDAOImpleSQL.agregarCliente(cliente, con);

			pasaporteAlterado = this.pasaporteDAOImpleSQL.agregarPasaporte(pasaporte, cuil, con);

			telefonoAlterado = this.telefonoDAOImpleSQL.agregarTelefono(telefono, cuil, con);

			pasajeroFrecuenteAlterado = this.pasajeroFrecuenteDAOImpleSQL.agregarPasajeroFrecuente(pasajeroFrecuente,
					cuil, con);

			direccionAlterado = this.direccionDAOImpleSQL.agregarDireccion(direccion, cuil, con);

			con.commit();

			System.out.println("Total de registros alterados = " + clienteAlterado + pasaporteAlterado
					+ telefonoAlterado + pasajeroFrecuenteAlterado + direccionAlterado);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("El Manager no pudo cargar los datos en la base");
			if (con != null) {
				try {
					ConnectionDB.RollBack(con);
				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("El Manager no pudo hacer rollback");
				}
			}

		} finally {

			try {
				con.close();

			} catch (SQLException e3) {
				e3.printStackTrace();
				System.out.println("El Manager no pudo cerrar la conexion");
			}

		}

		return (clienteAlterado + pasaporteAlterado + telefonoAlterado + pasajeroFrecuenteAlterado + direccionAlterado);

	}

}
