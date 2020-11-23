package edu.usal.negocio.dao.implementaciones.sql;

import java.util.ArrayList;
import java.util.List;

import edu.usal.util.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dto.Alianza;
import edu.usal.controller.AlianzaController;
import edu.usal.negocio.dao.interfaces.LineaAereaDAO;

public class LineaAereaDAOImpleSQL implements LineaAereaDAO {

	@Override
	public List<LineaAerea> cargarLineaAereas(AlianzaController alianContr) {

		ArrayList<LineaAerea> listaLineaAereas = new ArrayList<>();
		ResultSet res;
		Alianza alianza = null;

		try (Connection db = ConnectionDB.getConnection();
				Statement q = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
			res = q.executeQuery("Select * from LineaAerea");
			while (res.next()) {

				alianza = alianContr.conseguirAlianza(res.getString("ALIANZA"));

				listaLineaAereas.add(new LineaAerea(res.getString("IDLAEREA"), res.getString("NOMBRE"), alianza));
			}
			res.close();

		} catch (Exception e) {
			e.printStackTrace();
			return listaLineaAereas;
		}

		return listaLineaAereas;

	}

	@Override
	public void guardarLineaAereas(List<LineaAerea> lLineaAereas) {

		Connection con = ConnectionDB.getConnection();
		Statement stm = null;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();
			// para que borre la base
			stm.executeUpdate("DELETE FROM LineaAerea");

			for (LineaAerea lineaAerea : lLineaAereas) {
				insert(lineaAerea, con, stm);
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

	public void guardarNuevaLineaAerea(LineaAerea lineaAerea) {

		Connection con = ConnectionDB.getConnection();
		Statement stm = null;

		try {
			con.setAutoCommit(false);
			stm = con.createStatement();
			insert(lineaAerea, con, stm);
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

	public void insert(LineaAerea lineaAerea, Connection con, Statement stm) {

		try {
			stm.executeUpdate("INSERT INTO LineaAerea VALUES('" + lineaAerea.getIdLAerea() + "','"
					+ lineaAerea.getNombre() + "','" + lineaAerea.getAlianza().getIdAlianza() + "','" + "1" + "')");
			;

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
