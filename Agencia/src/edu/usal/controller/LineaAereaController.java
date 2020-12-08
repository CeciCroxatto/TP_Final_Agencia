package edu.usal.controller;

import edu.usal.negocio.dto.LineaAerea;
import edu.usal.vista.LineaAereaAltaVista;
import edu.usal.vista.LineaAereaBajaVista;
import edu.usal.vista.LineaAereaConsultaVista;
import edu.usal.vista.LineaAereaModificacionVista;
import edu.usal.negocio.dto.Alianza;
import edu.usal.negocio.dao.factory.LineaAereaFactory;
import edu.usal.negocio.dao.interfaces.LineaAereaDAO;
import edu.usal.negocio.dao.implementaciones.sql.LineaAereaDAOImpleSQL;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class LineaAereaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<LineaAerea> lLineaAereas = null;
	private String[] lLineasAereasNombre = null;
	private AlianzaController alianContr;
	private LineaAereaAltaVista laAltaVista = null;
	private LineaAereaBajaVista laBajaVista = null;
	private LineaAereaConsultaVista laConsultaVista = null;
	private LineaAereaModificacionVista laModificacionVista = null;

	public LineaAereaController() {
		this.lLineaAereas = new ArrayList<LineaAerea>();
		this.alianContr = new AlianzaController();
	}

	/*
	 * 
	 * Funciones que usan la GUI y Manager
	 *
	 */

	public LineaAerea conseguirLineaAerea_porNombre(String nombre) {

		cargarLineaAereas("SQL");
		LineaAerea lineaAerea = null;

		for (LineaAerea la : this.lLineaAereas) {
			if (la.getNombre().equals(nombre)) {
				lineaAerea = la;
			}
		}
		return lineaAerea;
	}

	public void cargarLineaAereas(String implementacion) {

		LineaAereaDAO lineaAereaDAO = LineaAereaFactory.getImplementacion(implementacion);
		this.alianContr = new AlianzaController();
		this.alianContr.cargarAlianzas();
		this.lLineaAereas = lineaAereaDAO.cargarLineaAereas(this.alianContr);

		int cantLineasAereas = this.lLineaAereas.size();
		this.lLineasAereasNombre = new String[cantLineasAereas];

		int i = 0;
		for (i = 0; i < cantLineasAereas; i++) {
			this.lLineasAereasNombre[i] = this.lLineaAereas.get(i).getNombre();
		}

	}

	/*
	 * 
	 * Funciones que usan la GUI y sin Manager
	 *
	 */

	public int crearLineaAerea(LineaAerea lineaAerea) {

		LineaAereaDAOImpleSQL lineaAereaDAOImpleSQL = new LineaAereaDAOImpleSQL();
		return lineaAereaDAOImpleSQL.crearLineaAerea(lineaAerea);

	}

	public int bajarLineaAerea(String idLAerea) {

		LineaAereaDAOImpleSQL lineaAereaDAOImpleSQL = new LineaAereaDAOImpleSQL();
		return lineaAereaDAOImpleSQL.bajarLineaAerea(idLAerea);

	}

	public String consultarLineaAerea(String idLAerea) {

		LineaAereaDAOImpleSQL lineaAereaDAOImpleSQL = new LineaAereaDAOImpleSQL();
		return lineaAereaDAOImpleSQL.consultarLineaAerea(idLAerea);

	}

	public int verificarID(String idLAerea) {

		LineaAereaDAOImpleSQL lineaAereaDAOImpleSQL = new LineaAereaDAOImpleSQL();
		return lineaAereaDAOImpleSQL.verificarID(idLAerea);

	}

	public int modificarLineaAerea(String idLAerea, String nombre, String idAlianza, int estadoBoolean) {

		LineaAereaDAOImpleSQL lineaAereaDAOImpleSQL = new LineaAereaDAOImpleSQL();
		return lineaAereaDAOImpleSQL.modificarLineaAerea(idLAerea, nombre, idAlianza, estadoBoolean);

	}

	/*
	 * 
	 * Funciones que usan la vista web
	 *
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			this.laAltaVista = new LineaAereaAltaVista();
			this.alianContr = new AlianzaController();
			this.alianContr.cargarAlianzas(); // para cargar el archivo de t exto
			laAltaVista.formularioAlta(request, response, alianContr);
			break;
		case "Baja":
			this.laBajaVista = new LineaAereaBajaVista();
			laBajaVista.formularioBaja_por_ID(request, response);
			break;
		case "Consulta":
			this.laConsultaVista = new LineaAereaConsultaVista();
			laConsultaVista.formulario_de_consulta(request, response);
			break;
		case "Modificacion":
			this.laModificacionVista = new LineaAereaModificacionVista();
			this.alianContr = new AlianzaController();
			this.alianContr.cargarAlianzas();
			laModificacionVista.formulario_de_modificacion(request, response, alianContr);
			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int registrosAlterados = -20;
		String valor = request.getParameter("boton"); // name
		response.setContentType("text/html");
		String idLAerea = null;
		String texto = null;
		String idAlianza = null;

		switch (valor) {

		case "Dar de alta": // value
			idLAerea = request.getParameter("idLAerea");
			String nombre = request.getParameter("nombre");

			idAlianza = request.getParameter("idAlianza");

			this.alianContr = new AlianzaController();
			this.alianContr.cargarAlianzas();
			Alianza alianza = this.alianContr.conseguirAlianza(idAlianza);

			LineaAerea LineaAereaNueva = new LineaAerea(idLAerea, nombre, alianza);
			registrosAlterados = -20;
			registrosAlterados = crearLineaAerea(LineaAereaNueva);
			if (registrosAlterados > 0) {
				response.getWriter().append("Linea Aerea guardada en la Base correctamente").println();
			} else {
				response.getWriter().append("No se pudo ejecutar la operación").println();
				response.getWriter().append(
						"Ya existe una Linea Aerea con ese Código. Si quiere actualizarla ir a la opcion de MODIFICAR")
						.println();
			}
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Dar de baja":
			idLAerea = request.getParameter("idLAerea");
//				cargarLineaAereas("SQL");
//				LineaAerea lineaAereaBajar = conseguirLineaAerea(idLAerea);
//				response.getWriter().append("Nombre Linea Aerea =");
//				response.getWriter().append(lineaAereaBajar.getNombre()).println();

			registrosAlterados = -20;
			registrosAlterados = bajarLineaAerea(idLAerea);
			if (registrosAlterados > 0) {
				response.getWriter().append("Linea Aerea dada de baja de la Base correctamente").println();
			} else {
				response.getWriter().append("No se pudo ejecutar la operación").println();
				response.getWriter().append("No existe una Linea Aerea con ese Código.").println();
			}
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Consultar":
			idLAerea = request.getParameter("idLAerea");
			texto = consultarLineaAerea(idLAerea);
			response.getWriter().append(texto).println();
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
			break;

		case "Modificar":
			idLAerea = request.getParameter("idLAerea");
			nombre = request.getParameter("nombre");
			idAlianza = request.getParameter("idAlianza");
			String estado = request.getParameter("estado");
			int estadoBoolean = -33;
			if (estado.matches("activo")) {
				estadoBoolean = 1;
			} else {
				if (estado.matches("inactivo"))
					estadoBoolean = 0;
			}

			registrosAlterados = -20;
			registrosAlterados = modificarLineaAerea(idLAerea, nombre, idAlianza, estadoBoolean);

			if (registrosAlterados > 0) {
				response.getWriter().append("Datos actualizados en la Base correctamente").println();
			} else {
				response.getWriter().append("No existe Linea Aerea con ese ID").println();
			}
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();

			break;

		}
	}

	/*
	 * 
	 * Funciones en desuso
	 *
	 */

//	public void guardarLineaAereas(String implementacion) {
//
//		LineaAereaDAO lineaAereaDAO = LineaAereaFactory.getImplementacion(implementacion);
//
//		if (lineaAereaDAO instanceof LineaAereaDAOImpleFile)
//			((LineaAereaDAOImpleFile) lineaAereaDAO).guardarLineaAereas(this.lLineaAereas);
//
//	}

	public String[] getlLineasAereasNombre() {
		return lLineasAereasNombre;
	}

	public void setlLineasAereasNombre(String[] lLineasAereasDescrip) {
		this.lLineasAereasNombre = lLineasAereasDescrip;
	}

	public List<LineaAerea> getlLineaAereas() {
		return lLineaAereas;
	}

	public void setlLineaAereas(List<LineaAerea> lLineaAereas) {
		this.lLineaAereas = lLineaAereas;
	}

}
