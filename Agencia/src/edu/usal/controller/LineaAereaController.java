package edu.usal.controller;

import edu.usal.negocio.dto.LineaAerea;
import edu.usal.negocio.dto.Alianza;
import edu.usal.negocio.dao.factory.LineaAereaFactory;
import edu.usal.negocio.dao.interfaces.LineaAereaDAO;
import edu.usal.negocio.dao.implementaciones.sql.LineaAereaDAOImpleSQL;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class LineaAereaController extends HttpServlet {

	private List<LineaAerea> lLineaAereas = null;
	private AlianzaController alianContr;

	public LineaAereaController() {
		this.lLineaAereas = new ArrayList<LineaAerea>();
		this.alianContr = new AlianzaController();
	}

	public LineaAereaController(AlianzaController alianContr) {
		this.lLineaAereas = new ArrayList<LineaAerea>();
		this.alianContr = alianContr;
	}

	public List<LineaAerea> getlLineaAereas() {
		return lLineaAereas;
	}

	public void setlLineaAereas(List<LineaAerea> lLineaAereas) {
		this.lLineaAereas = lLineaAereas;
	}

	public void cargarLineaAereas(String implementacion) {

		LineaAereaDAO lineaAereaDAO = LineaAereaFactory.getImplementacion(implementacion);
		this.alianContr = new AlianzaController();
		this.alianContr.cargarAlianzas("file");
		this.lLineaAereas = lineaAereaDAO.cargarLineaAereas(this.alianContr);

		// de tabla parametrica
	}

	public void guardarLineaAereas(String implementacion) {

		LineaAereaDAO lineaAereaDAO = LineaAereaFactory.getImplementacion(implementacion);

		lineaAereaDAO.guardarLineaAereas(this.lLineaAereas);

	}

	
	
	
	
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

	public LineaAerea conseguirLineaAerea(String idLAerea) {

		for (LineaAerea lineaAerea : this.lLineaAereas) {
			if (lineaAerea.getIdLAerea().equals(idLAerea)) {
				return lineaAerea;
			}
		}
		return null;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		formularioAlta(request, response);

		String action = request.getParameter("boton");
		switch (action) {
		case "Alta":
			formularioAlta(request, response);
			break;
		case "Baja":
			formularioBaja_por_ID(request, response);
			break;
		case "Consulta":
			formulario_de_consulta(request, response);
			break;
		case "Modificacion":
			formulario_de_modificacion(request, response);
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
			this.alianContr.cargarAlianzas("file");
			Alianza alianza = this.alianContr.conseguirAlianza(idAlianza);

			// public LineaAerea(String idLAerea, String nombre, Alianza alianza)
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
//			int idExiste = -4;
//			idExiste = verificarID(idLAerea);
//			if (idExiste == -4) {
//				formulario_de_modificacion2(request, response);
//			} else {
//				response.getWriter().append("No se pudo ejecutar la operación" + idExiste).println();
//				response.getWriter().append("No existe una Linea Aerea con ese Código.").println();
//				response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
//			}
			
			
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
			
			
//			
//		case "Guardar datos":
//			idLAerea = (String) request.getAttribute("idLAerea");
//			System.out.println("IDLAEREA = " + idLAerea);
////			idLAerea = request.getParameter("idLAerea");
//			nombre = request.getParameter("nombre");
//			idAlianza = request.getParameter("idAlianza");
//			String estado = request.getParameter("estado");
//			int estadoBoolean = -33;
//			if (estado.matches("activo")) {
//				estadoBoolean = 1;
//			} else {
//				if (estado.matches("inactivo"))
//					estadoBoolean = 0;
//			}
//
//			registrosAlterados = modificarLineaAerea(idLAerea, nombre, idAlianza, estadoBoolean);
//
//			if (registrosAlterados > 0) {
//				response.getWriter().append("Datos actualizados en la Base correctamente").println();
//			} else {
//				response.getWriter().append("No se pudo ejecutar la operación" + registrosAlterados).println();
//			}
//			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
//			break;

		}
	}

	public void formularioAlta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Alta de Linea Aerea</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Alta de Linea Aerea</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
//		out.append("<article class=\"FormularioAltaLineaAerea\">");
		out.append("<article>");
		out.append("<form action=\"LineaAereaServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Alta de Linea Aerea</b>");
		out.append("</th>");
		out.append("<tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Codigo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Codigo\"");
		out.append("name=\"idLAerea\" pattern=\"^[A-Z]{2}$\" title=\"Dos mayusculas\" required>");
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\"> Nombre: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Nombre\"");
		out.append("name=\"nombre\" pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">Alianza: </label>");
		out.append("</td>");
		out.append("<td>");
//out.append("<input type=\"text\" id=\"3\" placeholder=\"Ingresar idAlianza\"name=\"idAlianza\" required>");
		out.append("<select name=\"idAlianza\">");

		this.alianContr = new AlianzaController();
		this.alianContr.cargarAlianzas("file");

		for (Alianza alianza : this.alianContr.getlAlianzas()) {
			out.append("<option value=\"");
			out.append(alianza.getIdAlianza());
			out.append("\">");
			out.append(alianza.getIdAlianza());
		}

		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"boton\" value=\"Dar de alta\">");
		out.append("</th>");
		out.append("</tr>");
		out.append("</table>");
		out.append("</article>");
		out.append("</form>");
		out.append("<a href=Inicio.html>Volver al Inicio</a>");
		out.append("</article>");
		out.append("<hr>");
		out.append("</body>");
		out.append("</html>");
	}

	public void formularioBaja_por_ID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Baja de Linea Aerea</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Baja de Linea Aerea</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
//		out.append("<article class=\"FormularioAltaLineaAerea\">");
		out.append("<article>");
		out.append("<form action=\"LineaAereaServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Baja de Linea Aerea</b>");
		out.append("</th>");
		out.append("<tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Codigo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Codigo\"");
		out.append("name=\"idLAerea\" pattern=\"^[A-Z]{2}$\" title=\"Dos mayusculas\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"boton\" value=\"Dar de baja\">");
		out.append("</th>");
		out.append("</tr>");
		out.append("</table>");
		out.append("</article>");
		out.append("</form>");
		out.append("<a href=Inicio.html>Volver al Inicio</a>");
		out.append("</article>");
		out.append("<hr>");
		out.append("</body>");
		out.append("</html>");

	}

	public void formulario_de_consulta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Consulta de Linea Aerea</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Consulta de Linea Aerea</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
//		out.append("<article class=\"FormularioAltaLineaAerea\">");
		out.append("<article>");
		out.append("<form action=\"LineaAereaServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Consulta de Linea Aerea</b>");
		out.append("</th>");
		out.append("<tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Codigo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Codigo\"");
		out.append("name=\"idLAerea\" pattern=\"^[A-Z]{2}$\" title=\"Dos mayusculas\" required>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"boton\" value=\"Consultar\">");
		out.append("</th>");
		out.append("</tr>");
		out.append("</table>");
		out.append("</article>");
		out.append("</form>");
		out.append("<a href=Inicio.html>Volver al Inicio</a>");
		out.append("</article>");
		out.append("<hr>");
		out.append("</body>");
		out.append("</html>");

	}

	public void formulario_de_modificacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.append("<!DOCTYPE html>");
		out.append("<head>");
		out.append("<meta charset= \"utf-8\">");
		out.append("<title>Modificacion de Linea Aerea</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<article class=\"Titulo\">");
		out.append("<h1>");
		out.append("<i>");
		out.append("<u>");
		out.append("<b>Modificacion de Linea Aerea</b>");
		out.append("</u>");
		out.append("</i>");
		out.append("</h1>");
		out.append("</article>");
		out.append("<hr>");
//		out.append("<article class=\"FormularioAltaLineaAerea\">");
		out.append("<article>");
		out.append("<form action=\"LineaAereaServlet\" method=\"post\">");
		out.append("<article class=\"Tabla\">");
		out.append("<table border=\"1\">");
		out.append("<tr>");
		out.append("<th colspan=\"2\">");
		out.append("<b>Modificacion de Linea Aerea</b>");
		out.append("</th>");
		out.append("<tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"1\">Codigo: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Codigo\"");
		out.append("name=\"idLAerea\" pattern=\"^[A-Z]{2}$\" title=\"Dos mayusculas\" required>");
		out.append("</td>");
		out.append("</tr>");

		
		
		
		out.append("<td>");
		out.append("<label for=\"2\"> Nombre: </label>");
		out.append("</td>");
		out.append("<td>");

		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Nombre\"");
		out.append("name=\"nombre\" pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
		
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">Alianza: </label>");
		out.append("</td>");
		out.append("<td>");
//out.append("<input type=\"text\" id=\"3\" placeholder=\"Ingresar idAlianza\"name=\"idAlianza\" required>");
		out.append("<select name=\"idAlianza\">");

		this.alianContr = new AlianzaController();
		this.alianContr.cargarAlianzas("file");

		for (Alianza alianzaM : this.alianContr.getlAlianzas()) {
			out.append("<option value=\"");
			out.append(alianzaM.getIdAlianza());
			out.append("\">");
			out.append(alianzaM.getIdAlianza());
		}

		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");

		out.append("<td>");
		out.append("<label for=\"4\">Estado: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=radio name=\"estado\" value=\"activo\" required> Activo");
		out.append("<input type=radio name=\"estado\" value=\"inactivo\"> Inactivo");
		out.append("</td>");
		
		
		
		
		
		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"boton\" value=\"Modificar\">");
		out.append("</th>");
		out.append("</tr>");
		out.append("</table>");
		out.append("</article>");
		out.append("</form>");
		out.append("<a href=Inicio.html>Volver al Inicio</a>");
		out.append("</article>");
		out.append("<hr>");
		out.append("</body>");
		out.append("</html>");

	}
//
//	public void formulario_de_modificacion2(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		PrintWriter out = response.getWriter();
//		out.append("<!DOCTYPE html>");
//		out.append("<head>");
//		out.append("<meta charset= \"utf-8\">");
//		out.append("<title>Modificacion de Linea Aerea</title>");
//		out.append("</head>");
//		out.append("<body>");
//		out.append("<article class=\"Titulo\">");
//		out.append("<h1>");
//		out.append("<i>");
//		out.append("<u>");
//		out.append("<b>Modificacion de Linea Aerea</b>");
//		out.append("</u>");
//		out.append("</i>");
//		out.append("</h1>");
//		out.append("</article>");
//		out.append("<hr>");
////		out.append("<article class=\"FormularioAltaLineaAerea\">");
//		out.append("<article>");
//		out.append("<form action=\"LineaAereaServlet\" method=\"post\">");
//		out.append("<article class=\"Tabla\">");
//		out.append("<table border=\"1\">");
//		out.append("<tr>");
//		out.append("<th colspan=\"2\">");
//		out.append("<b>Modificacion de Linea Aerea</b>");
//		out.append("</th>");
//		out.append("<tr>");
//		out.append("<tr>");
//
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<td>");
//		out.append("<label for=\"1\"> Nombre: </label>");
//		out.append("</td>");
//		out.append("<td>");
//
//		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar Nombre\"");
//		out.append("name=\"nombre\" pattern=\"^[a-zA-Z0-9\\s]{1,50}$\" title=\"Hasta 50 caracteres alfanumericos\" required>");
//		
//		out.append("</td>");
//		out.append("</tr>");
//		out.append("<tr>");
//		out.append("<td>");
//		out.append("<label for=\"2\">Alianza: </label>");
//		out.append("</td>");
//		out.append("<td>");
////out.append("<input type=\"text\" id=\"3\" placeholder=\"Ingresar idAlianza\"name=\"idAlianza\" required>");
//		out.append("<select name=\"idAlianza\">");
//
//		this.alianContr = new AlianzaController();
//		this.alianContr.cargarAlianzas("file");
//
//		for (Alianza alianzaM : this.alianContr.getlAlianzas()) {
//			out.append("<option value=\"");
//			out.append(alianzaM.getIdAlianza());
//			out.append("\">");
//			out.append(alianzaM.getIdAlianza());
//		}
//
//		out.append("</select>");
//		out.append("</td>");
//		out.append("</tr>");
//
//		out.append("<td>");
//		out.append("<label for=\"3\">Estado: </label>");
//		out.append("</td>");
//		out.append("<td>");
//		out.append("<input type=radio name=\"estado\" value=\"activo\" required> Activo");
//		out.append("<input type=radio name=\"estado\" value=\"inactivo\"> Inactivo");
//		out.append("</td>");
//
//		out.append("<tr>");
//		out.append("<th colspan=\"2\" align=\"CENTER\">");
//		out.append("<input type=\"submit\"name=\"boton\" value=\"Guardar datos\">");
//		out.append("</th>");
//		out.append("</tr>");
//		out.append("</table>");
//		out.append("</article>");
//		out.append("</form>");
//		out.append("<a href=Inicio.html>Volver al Inicio</a>");
//		out.append("</article>");
//		out.append("<hr>");
//		out.append("</body>");
//		out.append("</html>");
//	}

	public void modificacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Devolver formulario html
	}

	public void consulta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Devolver formulario html
	}

}
