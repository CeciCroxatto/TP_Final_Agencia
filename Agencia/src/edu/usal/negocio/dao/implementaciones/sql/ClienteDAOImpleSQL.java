package edu.usal.negocio.dao.implementaciones.sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.usal.util.ConnectionDB;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import edu.usal.negocio.dto.Cliente;
import edu.usal.controller.DireccionController;
import edu.usal.controller.PasajeroFrecuenteController;
import edu.usal.controller.PasaporteController;
import edu.usal.controller.TelefonoController;
import edu.usal.negocio.dao.interfaces.ClienteDAO;

public class ClienteDAOImpleSQL implements ClienteDAO {

	final String INSERT = "INSERT INTO Cliente VALUES (?, ?, ?, ?, ?, ?)";
	final String SELECT = "SELECT * from Cliente where cuil = ?";
	final String DELETE = "DELETE Cliente where IDCLIENTE = ?";
	final String UPDATE = "UPDATE Cliente SET [NOMBRE] = ?, [APELLIDO] = ?, [DNI]= ?, [FECNAC]= ?,[MAIL] = ? where IDCLIENTE = ?";

	/*
	 * 
	 * Funciones que usan la GUI y el Manager
	 *
	 */

	public String vecesCuil(String cuil) {

		Connection con = null;
		ResultSet res = null;
		String cantidad = null;

		try {
			con = ConnectionDB.getConnection();
			Statement q = con.createStatement();
			res = q.executeQuery("Select count(*) as columna from cliente where CUIL = " + cuil);

			while (res.next()) {
				cantidad = res.getString("columna");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();

			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}

		return cantidad;

	}

	public int agregarCliente(Cliente cliente, Connection con) throws IOException {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(INSERT);
			// @nombre ,@apellido, @dni ,@cuil ,@fecNac ,@mail

			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellido());
			ps.setString(3, cliente.getDni());
			ps.setString(4, cliente.getCuil());
			ps.setString(5, new SimpleDateFormat("MM-dd-yyyy").format(cliente.getFechNac()));
			ps.setString(6, cliente.getEmail());
			registrosModificados = ps.executeUpdate();

			System.out.println("Cliente agregado");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				ps.close();

			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}

		return registrosModificados;
	}

	public Cliente conseguirCliente(String cuil, Connection con) throws IOException {

		ArrayList<String> lDatos = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet res = null;

		try {
			ps = con.prepareStatement(SELECT);

			ps.setString(1, cuil);

			res = ps.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			int cantColumnas = rsmd.getColumnCount();
			boolean bandera = true;

			if (res.next()) {
				while (bandera) {

					if (res != null) {
						for (int i = 1; i <= cantColumnas; i++) {
							lDatos.add(res.getString(i));
						}
						bandera = res.next();

					}
				}
			} else {
				lDatos.add("No se encontro un Cliente con ese Numero de CUIL");
			}

//			con.close();
//		if (con.isClosed())
//			System.out.println("Conexion cerrada");

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		Date fechNac = null;

		try {
			fechNac = new SimpleDateFormat("yyyy-MM-dd").parse(lDatos.get(5));
		} catch (ParseException e) {
			e.printStackTrace();
		}

//		public Cliente(int idCliente, String nombre, String apellido, String dni, String cuil, Date fechNac, String email) 
		return new Cliente(Integer.parseInt((lDatos.get(0))), lDatos.get(1), lDatos.get(2), lDatos.get(3),
				lDatos.get(4), fechNac, lDatos.get(6));

	}

	public int bajarCliente(Cliente cliente, Connection con) throws IOException {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, cliente.getIdCliente());
			registrosModificados = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				ps.close();

			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}

		return registrosModificados;
	}

	public int modificarCliente(Cliente cliente, Connection con) throws IOException {

		PreparedStatement ps = null;
		int registrosModificados = 0;

		try {
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellido());
			ps.setString(3, cliente.getDni());
			ps.setString(4, new SimpleDateFormat("MM-dd-yyyy").format(cliente.getFechNac()));
			ps.setString(5, cliente.getEmail());
			ps.setInt(6, cliente.getIdCliente());
			registrosModificados = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				ps.close();

			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}

		return registrosModificados;
	}

	/*
	 * 
	 * Funciones que usan la GUI y sin Manager
	 *
	 */

	@Override
	public String crearClienteGUI(String nombre, String apellido, String dni, String cuil, String nroPasaporte,
			String paisPasaporte, String provinciaPasaporte, String autoridadPasaporte, String fechaEmisionS,
			String vencimientoS, String telefpers, String telefcelul, String teleflabor, String fechNac, String nroPF,
			String nombreLA, String categoriaPF, String email, String calleDir, String calleAlt, String ciudadDir,
			String paisDir, String provDir, String cpDir) {
//
//		Connection con = ConnectionDB.getConnection();
//		CallableStatement cst = null;
//		boolean isResultSet = false;
		String mensaje = "No se pudo realizar la operacion";
//
//		try {
//			cst = con.prepareCall(
//					"EXEC sp_CrearCliente2 ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
//			cst.setString(1, nombre);
//			cst.setString(2, apellido);
//			cst.setString(3, dni);
//			cst.setString(4, cuil);
//			cst.setString(5, nroPasaporte);
//			cst.setString(6, paisPasaporte);
//			cst.setString(7, provinciaPasaporte);
//			cst.setString(8, autoridadPasaporte);
//			cst.setString(9, fechaEmisionS);
//			cst.setString(10, vencimientoS);
//			cst.setString(11, telefpers);
//			cst.setString(12, telefcelul);
//			cst.setString(13, teleflabor);
//			cst.setString(14, fechNac);
//			cst.setString(15, nroPF);
//			cst.setString(16, nombreLA);
//			cst.setString(17, categoriaPF);
//			cst.setString(18, email);
//			cst.setString(19, calleDir);
//			cst.setString(20, calleAlt);
//			cst.setString(21, ciudadDir);
//			cst.setString(22, paisDir);
//			cst.setString(23, provDir);
//			cst.setString(24, cpDir);
//
//			try {
//				isResultSet = cst.execute();
//				mensaje = "";
//				while (true) {
//					if (isResultSet) {
//
//						try (ResultSet res = cst.getResultSet();) {
//							while (res.next()) {
//
//								if (res != null) {
//
//									mensaje = mensaje + res.getString("msg") + "\n";
//								}
//							}
//						}
//
//					} else {
//						int updateCount = cst.getUpdateCount();
//						if (updateCount == -1) {
//							break;
//						}
//					}
//					isResultSet = cst.getMoreResults();
//
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				try {
//					cst.close();
//					ConnectionDB.RollBack(con);
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//
//			con.commit();
//			con.close();
////			if (con.isClosed())
////				System.out.println("Conexion cerrada");
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

		return mensaje;

	}

	@Override
	public ArrayList<String> consultarCliente_porCUILGUI(String cuil) {

		ArrayList<String> lDatos = new ArrayList<>();
//		Connection con = ConnectionDB.getConnection();
//		PreparedStatement ps = null;
//		ResultSet res = null;
//
//		try {
//			ps = con.prepareStatement(
//					"select	c.NOMBRE as Nombre_Cliente, c.APELLIDO as Apellido_Cliente, c.DNI as DNI_Cliente, c.CUIL as CUIL_Cliente, pa.IDPASAPORTE as Nro_Pasaporte, p2.NOMBRE_PAIS as Pais_Pasaporte, pa.PROVINCIA as Provincia_Pasaporte, pa.AUTORIDAD as Autoridad_Pasaporte, CONVERT(varchar,pa.FECEMISION,103) as Emision_Pasaporte, CONVERT(varchar,pa.VTO,103) as Vencimiento_Pasaporte, CONVERT(varchar,c.FECNAC,103) as Fecha_de_Nacimiento, c.MAIL as Mail_Cliente, d.CALLE as Calle_Direccion, d.ALTURA as Altura_Direccion, d.CIUDAD as Ciudad_Direccion, p.NOMBRE_PAIS as Pais_Direccion, d.PROVINCIA as Provincia_Direccion, d.CP as Cod_Postal_Direccion, pf.CATEGORIA as Cat_PF, pf.NRO_PF as Nro_FP, l.NOMBRE as Aerolinea_PF, t.[TPERSONAL] as Tel_Personal, t.TCELULAR as Tel_Celular, t.TLABORAL as Tel_Laboral from Cliente c inner join Pasaporte pa on c.[IDCLIENTE] = pa.[CLIENTE] inner join Telefono t on c.[IDCLIENTE] = t.[CLIENTEID] inner join PasajeroFrecuente pf on c.[IDCLIENTE] = pf.[CLIENTEID] inner join Direccion d on c.[IDCLIENTE] = d.[CLIENTEID] inner join Pais p on d.PAISID = p.IDPAIS  inner join pais p2 on pa.PAISID = p2.IDPAIS inner join LineaAerea l on l.IDLAEREA = pf.LINAERID where cuil = ?");
//
//			ps.setString(1, cuil);
//
//			res = ps.executeQuery();
//			ResultSetMetaData rsmd = res.getMetaData();
//			int cantColumnas = rsmd.getColumnCount();
//			boolean bandera = true;
//
//			if (res.next()) {
//				while (bandera) {
//
//					if (res != null) {
//						for (int i = 1; i <= cantColumnas; i++) {
//							String nombreColumna = rsmd.getColumnName(i);
//							lDatos.add(res.getString(nombreColumna));
//						}
//						bandera = res.next();
//
//					}
//				}
//			} else {
//				lDatos.add("No se encontro un Cliente con ese Numero de CUIL");
//			}
//
//			con.close();
////			if (con.isClosed())
////				System.out.println("Conexion cerrada");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			try {
//				ConnectionDB.RollBack(con);
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//
//		}

		return lDatos;
	}

	@Override
	public String borrarCliente_porCUILGUI(String cuil) {

//		Connection con = ConnectionDB.getConnection();
//		CallableStatement cst = null;
//		boolean isResultSet = false;
		String mensaje = "No se pudo realizar la operacion";
//
//		try {
//			cst = con.prepareCall("EXEC sp_EliminarCliente2 ?");
//			cst.setString(1, cuil);
//
//			try {
//				isResultSet = cst.execute();
//				mensaje = "";
//				while (true) {
//					if (isResultSet) {
//
//						try (ResultSet res = cst.getResultSet();) {
//							while (res.next()) {
//
//								if (res != null) {
//
//									mensaje = mensaje + res.getString("msg") + "\n";
//								}
//							}
//						}
//
//					} else {
//						int updateCount = cst.getUpdateCount();
//						if (updateCount == -1) {
//							break;
//						}
//					}
//					isResultSet = cst.getMoreResults();
//
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//				try {
//					cst.close();
//					ConnectionDB.RollBack(con);
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//
//			con.commit();
//			con.close();
////			if (con.isClosed())
////				System.out.println("Conexion cerrada");
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

		return mensaje;

	}

	@Override
	public String modificarCliente_porCUILGUI(String nombre, String apellido, String dni, String cuil,
			String nroPasaporte, String paisPasaporte, String provinciaPasaporte, String autoridadPasaporte,
			String fechaEmisionS, String vencimientoS, String telefpers, String telefcelul, String teleflabor,
			String fechNac, String nroPF, String nombreLA, String categoriaPF, String email, String calleDir,
			String calleAlt, String ciudadDir, String paisDir, String provDir, String cpDir) {

		Connection con = ConnectionDB.getConnection();
		CallableStatement cst = null;
		boolean isResultSet = false;
		String mensaje = "No se pudo realizar la operacion";

		try {
			cst = con.prepareCall(
					"EXEC sp_ModificarCliente2 ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
			cst.setString(1, nombre);
			cst.setString(2, apellido);
			cst.setString(3, dni);
			cst.setString(4, cuil);
			cst.setString(5, nroPasaporte);
			cst.setString(6, paisPasaporte);
			cst.setString(7, provinciaPasaporte);
			cst.setString(8, autoridadPasaporte);
			cst.setString(9, fechaEmisionS);
			cst.setString(10, vencimientoS);
			cst.setString(11, telefpers);
			cst.setString(12, telefcelul);
			cst.setString(13, teleflabor);
			cst.setString(14, fechNac);
			cst.setString(15, nroPF);
			cst.setString(16, nombreLA);
			cst.setString(17, categoriaPF);
			cst.setString(18, email);
			cst.setString(19, calleDir);
			cst.setString(20, calleAlt);
			cst.setString(21, ciudadDir);
			cst.setString(22, paisDir);
			cst.setString(23, provDir);
			cst.setString(24, cpDir);

			try {
				isResultSet = cst.execute();
				mensaje = "";
				while (true) {
					if (isResultSet) {

						try (ResultSet res = cst.getResultSet();) {
							while (res.next()) {

								if (res != null) {

									mensaje = mensaje + res.getString("msg") + "\n";
								}
							}
						}

					} else {
						int updateCount = cst.getUpdateCount();
						if (updateCount == -1) {
							break;
						}
					}
					isResultSet = cst.getMoreResults();

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
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				ConnectionDB.RollBack(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return mensaje;

	}

	/*
	 * 
	 * Funciones que usan la vista web
	 *
	 */

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
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

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
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

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
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

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
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

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
//			if (con.isClosed())
//				System.out.println("Conexion cerrada");

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

	/*
	 * 
	 * Funciones en desuso
	 *
	 */

	@Override
	public List<Cliente> cargarClientes(PasaporteController pasapContr, TelefonoController telefContr,
			PasajeroFrecuenteController pasajFContr, DireccionController direcContr) {

		ArrayList<Cliente> listaClientes = new ArrayList<>();
//		ResultSet res;
//		Pasaporte pasaporte = null;
//		Telefono telefono = null;
//		PasajeroFrecuente pasajeroFrecuente = null;
//		Direccion direccion = null;
//
//		try (Connection db = ConnectionDB.getConnection();
//				Statement q = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
//			res = q.executeQuery("Select * from Cliente");
//
//			while (res.next()) {
//
//				pasaporte = pasapContr.conseguirPasaporte(res.getString("PASAPORTE_ID"));
//				telefono = telefContr.conseguirTelefono(res.getInt("TELEFONO_ID"));
//				pasajeroFrecuente = pasajFContr.conseguirPasajeroFrecuente(res.getString("NRO_PASFREC"));
//				direccion = direcContr.conseguirDireccion(res.getInt("DIRECCION_ID"));
//
////				public Cliente(int idCliente, String nombre, String apellido, String dni, 
////						Pasaporte pasaporte,
////						String cuil, Date fechNac, String email,
////						Telefono telefono, PasajeroFrecuente pasajeroFrecuente, Direccion direccion)
//				listaClientes.add(new Cliente(res.getInt("IDCLIENTE"), res.getString("NOMBRE"),
//						res.getString("APELLIDO"), res.getString("DNI"), pasaporte, res.getString("CUIL"),
//						res.getDate("FECNAC"), res.getString("MAIL"), telefono, pasajeroFrecuente, direccion));
//			}
//			res.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return listaClientes;

	}

}
