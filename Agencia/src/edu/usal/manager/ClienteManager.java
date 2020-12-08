package edu.usal.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.usal.controller.ClienteController;
import edu.usal.negocio.dao.factory.ClienteFactory;
import edu.usal.negocio.dao.factory.DireccionFactory;
import edu.usal.negocio.dao.factory.PasajeroFrecuenteFactory;
import edu.usal.negocio.dao.factory.PasaporteFactory;
import edu.usal.negocio.dao.factory.TelefonoFactory;
import edu.usal.negocio.dao.implementaciones.sql.ClienteDAOImpleSQL;
import edu.usal.negocio.dao.implementaciones.sql.DireccionDAOImpleSQL;
import edu.usal.negocio.dao.implementaciones.sql.PasajeroFrecuenteDAOImpleSQL;
import edu.usal.negocio.dao.implementaciones.sql.PasaporteDAOImpleSQL;
import edu.usal.negocio.dao.implementaciones.sql.TelefonoDAOImpleSQL;
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

public class ClienteManager implements ActionListener {

	private ClienteController clienteController;

	private ClienteDAO clienteDAO;
	private PasaporteDAO pasaporteDAO;
	private TelefonoDAO telefonoDAO;
	private PasajeroFrecuenteDAO pasajeroFrecuenteDAO;
	private DireccionDAO direccionDAO;

	public ClienteManager() {
		this.clienteController = new ClienteController();

	}

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public void altaClienteUnificado(String implementacion, String nombre, String apellido, String dni, String cuil,
			String nroPasaporte, String paisPasaporte, String provinciaPasaporte, String autoridadPasaporte,
			String fechaEmisionS, String vencimientoS, String telefpers, String telefcelul, String teleflabor,
			String fechNac, String nroPF, String nombreLA, String categoriaPF, String email, String calleDir,
			String calleAlt, String ciudadDir, String paisDir, String provDir, String cpDir) {

		int vecesCuil = 0;
		int vecesNroPasaporte = 0;
		int vecesNroPasajeroFrecuente = 0;

		vecesCuil = this.clienteController.vecesCuil(cuil, "SQL");
		vecesNroPasaporte = this.clienteController.getPasapContr().vecesPasaporte(nroPasaporte, implementacion);
		vecesNroPasajeroFrecuente = this.clienteController.getPasajFContr().vecesPasajeroFrecuente(nroPF,
				implementacion);

		Cliente cliente = null;

		if (vecesCuil == 0) {

			if (vecesNroPasaporte == 0) {

				if (vecesNroPasajeroFrecuente == 0) {

					cliente = this.clienteController.crearClienteGUI(implementacion, nombre, apellido, dni, cuil,
							nroPasaporte, paisPasaporte, provinciaPasaporte, autoridadPasaporte, fechaEmisionS,
							vencimientoS, telefpers, telefcelul, teleflabor, fechNac, nroPF, nombreLA, categoriaPF,
							email, calleDir, calleAlt, ciudadDir, paisDir, provDir, cpDir);

					Connection con = null;

					cargarImplementaciones(implementacion);

					int clienteAlterado = 0;
					int pasaporteAlterado = 0;
					int telefonoAlterado = 0;
					int pasajeroFrecuenteAlterado = 0;
					int direccionAlterado = 0;

					int cantidadRegistrosAlterados = 0;

					if (implementacion.matches("SQL")) {

						try {
							con = ConnectionDB.getConnection();
							con.setAutoCommit(false);

							clienteAlterado = ((ClienteDAOImpleSQL) this.clienteDAO).agregarCliente(cliente, con);

							pasaporteAlterado = ((PasaporteDAOImpleSQL) this.pasaporteDAO)
									.agregarPasaporte(cliente.getPasaporte(), cuil, con);

							telefonoAlterado = ((TelefonoDAOImpleSQL) this.telefonoDAO)
									.agregarTelefono(cliente.getTelefono(), cuil, con);

							pasajeroFrecuenteAlterado = ((PasajeroFrecuenteDAOImpleSQL) this.pasajeroFrecuenteDAO)
									.agregarPasajeroFrecuente(cliente.getPasajeroFrecuente(), cuil, con);

							direccionAlterado = ((DireccionDAOImpleSQL) this.direccionDAO)
									.agregarDireccion(cliente.getDireccion(), cuil, con);

							con.commit();

							cantidadRegistrosAlterados = +clienteAlterado + pasaporteAlterado + telefonoAlterado
									+ pasajeroFrecuenteAlterado + direccionAlterado;

							System.out.println("Total de registros alterados = " + cantidadRegistrosAlterados);

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
					}

					if (cantidadRegistrosAlterados == 5) {
						this.clienteController.getClCVista().mostrarmensaje("Cliente dado de alta exitosamente");
					} else {
						this.clienteController.getClCVista().mostrarmensaje("Hubo un error");
					}

				} else {
					this.clienteController.getClCVista()
							.mostrarmensaje("El pasajero frecuente ya pertenece a un cliente");
				}

			} else {
				this.clienteController.getClCVista().mostrarmensaje("El pasaporte ya pertenece a un cliente");
			}

		} else {
			this.clienteController.getClCVista().mostrarmensaje("El cuil ya pertenece a un cliente");
		}

	}

	public Cliente consultaClienteUnificado(String implementacion, String cuil, Object vista) {

		int vecesCuil = 0;
		Connection con = null;
		ArrayList<String> lDatos = new ArrayList<>();

		vecesCuil = this.clienteController.vecesCuil(cuil, "SQL");
		boolean bandera = false;

		Cliente cliente = null;

		if (vecesCuil == 1) {

			bandera = true;

			cargarImplementaciones(implementacion);

			Direccion direccion = null;
			Pasaporte pasaporte = null;
			Telefono telefono = null;
			PasajeroFrecuente pasajeroFrecuente = null;

			if (implementacion.matches("SQL")) {

				try {
					con = ConnectionDB.getConnection();
					con.setAutoCommit(false);

					cliente = ((ClienteDAOImpleSQL) this.clienteDAO).conseguirCliente(cuil, con);

					pasaporte = ((PasaporteDAOImpleSQL) this.pasaporteDAO).conseguirPasaporte(cliente.getIdCliente(),
							con, this.getClienteController().getPaisContr());

					telefono = ((TelefonoDAOImpleSQL) this.telefonoDAO).conseguirTelefono(cliente.getIdCliente(), con);

					pasajeroFrecuente = ((PasajeroFrecuenteDAOImpleSQL) this.pasajeroFrecuenteDAO)
							.conseguirPasajeroFrecuente(cliente.getIdCliente(), con,
									this.clienteController.getPasajFContr().getLinAerContr());

					direccion = ((DireccionDAOImpleSQL) this.direccionDAO).conseguirDireccion(cliente.getIdCliente(),
							con, this.getClienteController().getPaisContr());

					cliente.setPasaporte(pasaporte);
					cliente.setTelefono(telefono);
					cliente.setPasajeroFrecuente(pasajeroFrecuente);
					cliente.setDireccion(direccion);

					con.commit();

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
			}

			lDatos.add(cliente.getNombre());
			lDatos.add(cliente.getApellido());
			lDatos.add(cliente.getDni());
			lDatos.add(cliente.getCuil());
			lDatos.add(cliente.getPasaporte().getnroPasaporte());
			lDatos.add(cliente.getPasaporte().getPais().getDescripcion());
			lDatos.add(this.clienteController.getPasapContr().getPaisContr()
					.conseguirProvEstado_descripcion(cliente.getPasaporte().getPais()));
			lDatos.add(cliente.getPasaporte().getAutoridad());
			lDatos.add(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getPasaporte().getFechEmision()));
			lDatos.add(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getPasaporte().getVencimiento()));
			lDatos.add(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getFechNac()));
			lDatos.add(cliente.getEmail());
			lDatos.add(cliente.getDireccion().getCalle());
			lDatos.add(cliente.getDireccion().getAltura());
			lDatos.add(cliente.getDireccion().getCiudad());
			lDatos.add(cliente.getDireccion().getPais().getDescripcion());
			lDatos.add(this.clienteController.getPasapContr().getPaisContr()
					.conseguirProvEstado_descripcion(cliente.getDireccion().getPais()));
			lDatos.add(cliente.getDireccion().getCp());
			lDatos.add(cliente.getPasajeroFrecuente().getCategoria());
			lDatos.add(cliente.getPasajeroFrecuente().getNroPF());
			lDatos.add(cliente.getPasajeroFrecuente().getLineaAerea().getNombre());
			lDatos.add(cliente.getTelefono().getPersonal());
			lDatos.add(cliente.getTelefono().getCelular());
			lDatos.add(cliente.getTelefono().getLaboral());

		}

		if (vista.getClass() == this.clienteController.getClRVista().getClass()) {
			if (bandera) {
				this.clienteController.getClRVista().llenarDatos(lDatos);
			} else {
				this.clienteController.getClRVista().mostrarmensaje("No existe Cliente con ese CUIL");
				this.clienteController.getClRVista().otraConsulta();
			}
		}

		if (vista.getClass() == this.clienteController.getClUVista().getClass()) {
			if (bandera) {
				this.clienteController.getClUVista().llenarDatos(lDatos);
				this.clienteController.getClUVista().getTextField_cuil().setEditable(false);
			} else {
				this.clienteController.getClUVista().mostrarmensaje("No existe Cliente con ese CUIL");
				this.clienteController.getClUVista().otraConsulta();
			}
		}

		if (vista.getClass() == this.clienteController.getClDVista().getClass()) {
			if (bandera) {
				this.clienteController.getClDVista().llenarDatos(lDatos);
				this.clienteController.getClDVista().getTextField_cuil().setEditable(false);
			} else {
				this.clienteController.getClDVista().mostrarmensaje("No existe Cliente con ese CUIL");
				this.clienteController.getClDVista().otraConsulta();
			}
		}

		return cliente;

	}

	public void borrarClienteUnificado(String implementacion, String cuil) {

		Connection con = null;

		cargarImplementaciones(implementacion);

		Cliente cliente = null;

		cliente = consultaClienteUnificado(implementacion, cuil, this.clienteController.getClDVista());

		int clienteAlterado = 0;
		int pasaporteAlterado = 0;
		int telefonoAlterado = 0;
		int pasajeroFrecuenteAlterado = 0;
		int direccionAlterado = 0;
		int cantidadRegistrosAlterados = 0;

		try {
			con = ConnectionDB.getConnection();
			con.setAutoCommit(false);

			pasaporteAlterado = ((PasaporteDAOImpleSQL) this.pasaporteDAO).bajarPasaporte(cliente.getPasaporte(), con);

			telefonoAlterado = ((TelefonoDAOImpleSQL) this.telefonoDAO).bajarTelefono(cliente.getTelefono(), con);

			pasajeroFrecuenteAlterado = ((PasajeroFrecuenteDAOImpleSQL) this.pasajeroFrecuenteDAO)
					.bajarPasajeroFrecuente(cliente.getPasajeroFrecuente(), con);

			direccionAlterado = ((DireccionDAOImpleSQL) this.direccionDAO).bajarDireccion(cliente.getDireccion(), con);

			clienteAlterado = ((ClienteDAOImpleSQL) this.clienteDAO).bajarCliente(cliente, con);

			con.commit();

			cantidadRegistrosAlterados = +clienteAlterado + pasaporteAlterado + telefonoAlterado
					+ pasajeroFrecuenteAlterado + direccionAlterado;

			System.out.println("Total de registros alterados = " + cantidadRegistrosAlterados);

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

		if (cantidadRegistrosAlterados == 5) {
			this.clienteController.getClDVista().mostrarmensaje("Cliente dado de baja exitosamente");
		} else {
			this.clienteController.getClDVista().mostrarmensaje("Hubo un error");
		}
		this.clienteController.getClDVista().otroBorrar();
		this.clienteController.getClDVista().getTextField_cuil().setEditable(true);

	}

	public void modificarClienteUnificado(String implementacion, String nombre, String apellido, String dni,
			String cuil, String nroPasaporte, String paisPasaporte, String provinciaPasaporte,
			String autoridadPasaporte, String fechaEmisionS, String vencimientoS, String telefpers, String telefcelul,
			String teleflabor, String fechNac, String nroPF, String nombreLA, String categoriaPF, String email,
			String calleDir, String calleAlt, String ciudadDir, String paisDir, String provDir, String cpDir) {

		Connection con = null;

		cargarImplementaciones(implementacion);

		Cliente clienteViejo = null;
		Cliente clienteNuevo = null;

		clienteViejo = consultaClienteUnificado(implementacion, cuil, this.clienteController.getClDVista());
		clienteNuevo = this.clienteController.crearClienteGUI(implementacion, nombre, apellido, dni, cuil, nroPasaporte,
				paisPasaporte, provinciaPasaporte, autoridadPasaporte, fechaEmisionS, vencimientoS, telefpers,
				telefcelul, teleflabor, fechNac, nroPF, nombreLA, categoriaPF, email, calleDir, calleAlt, ciudadDir,
				paisDir, provDir, cpDir);

		clienteNuevo.setIdCliente(clienteViejo.getIdCliente());
		clienteNuevo.getTelefono().setIdTelefono(clienteViejo.getTelefono().getIdTelefono());
		clienteNuevo.getDireccion().setIdDireccion(clienteViejo.getDireccion().getIdDireccion());

		int clienteAlterado = 0;
		int pasaporteAlterado = 0;
		int telefonoAlterado = 0;
		int pasajeroFrecuenteAlterado = 0;
		int direccionAlterado = 0;
		int cantidadRegistrosAlterados = 0;

		try {
			con = ConnectionDB.getConnection();
			con.setAutoCommit(false);

			clienteAlterado = ((ClienteDAOImpleSQL) this.clienteDAO).modificarCliente(clienteNuevo, con);

			pasaporteAlterado = ((PasaporteDAOImpleSQL) this.pasaporteDAO)
					.modificarPasaporte(clienteNuevo.getPasaporte(), con);

			telefonoAlterado = ((TelefonoDAOImpleSQL) this.telefonoDAO).modificarTelefono(clienteNuevo.getTelefono(),
					con);

			pasajeroFrecuenteAlterado = ((PasajeroFrecuenteDAOImpleSQL) this.pasajeroFrecuenteDAO)
					.modificarPasajeroFrecuente(clienteNuevo.getPasajeroFrecuente(), con);

			direccionAlterado = ((DireccionDAOImpleSQL) this.direccionDAO)
					.modificarDireccion(clienteNuevo.getDireccion(), con);

			con.commit();

			cantidadRegistrosAlterados = +clienteAlterado + pasaporteAlterado + telefonoAlterado
					+ pasajeroFrecuenteAlterado + direccionAlterado;

			System.out.println("Total de registros alterados = " + cantidadRegistrosAlterados);

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

		if (cantidadRegistrosAlterados == 5) {
			this.clienteController.getClUVista().mostrarmensaje("Cliente modificado exitosamente");
		} else {
			this.clienteController.getClUVista().mostrarmensaje("Hubo un error");
		}

		this.clienteController.getClUVista().otroModificar();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// 1 x btnBorrar

		if (e.getSource() == this.clienteController.getClDVista().btnBorrar) {
			borrarClienteUnificado("SQL", this.clienteController.getClDVista().getTextField_cuil().getText());

		}

	}

	public void cargarImplementaciones(String implementacion) {
		this.clienteDAO = ClienteFactory.getImplementacion(implementacion);
		this.pasaporteDAO = PasaporteFactory.getImplementacion(implementacion);
		this.telefonoDAO = TelefonoFactory.getImplementacion(implementacion);
		this.pasajeroFrecuenteDAO = PasajeroFrecuenteFactory.getImplementacion(implementacion);
		this.direccionDAO = DireccionFactory.getImplementacion(implementacion);
	}

	public ClienteController getClienteController() {
		return clienteController;
	}

	public void setClienteController(ClienteController clienteController) {
		this.clienteController = clienteController;
	}

}
