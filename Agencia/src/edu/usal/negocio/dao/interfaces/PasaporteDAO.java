package edu.usal.negocio.dao.interfaces;

import java.io.IOException;
import java.sql.Connection;

import edu.usal.negocio.dto.Pasaporte;

public interface PasaporteDAO {

	public int agregarPasaporte(Pasaporte pasaporte, String cuil, Connection con) throws IOException;

}
