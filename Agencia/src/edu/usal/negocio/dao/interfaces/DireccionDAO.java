package edu.usal.negocio.dao.interfaces;

import java.io.IOException;
import java.sql.Connection;

import edu.usal.negocio.dto.Direccion;

public interface DireccionDAO {

	public int agregarDireccion(Direccion direccion, String cuil, Connection con) throws IOException;

}
