package edu.usal.negocio.dao.interfaces;

import java.io.IOException;
import java.sql.Connection;

import edu.usal.negocio.dto.PasajeroFrecuente;

public interface PasajeroFrecuenteDAO {

	public int agregarPasajeroFrecuente(PasajeroFrecuente pasajeroFrecuente, String cuil, Connection con)
			throws IOException;

}
