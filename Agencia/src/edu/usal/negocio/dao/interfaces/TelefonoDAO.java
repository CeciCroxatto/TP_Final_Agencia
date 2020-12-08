package edu.usal.negocio.dao.interfaces;

import java.io.IOException;
import java.sql.Connection;

import edu.usal.negocio.dto.Telefono;

public interface TelefonoDAO {

	public int agregarTelefono(Telefono telefono, String cuil, Connection con) throws IOException;

}
