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

		this.lLineaAereas = lineaAereaDAO.cargarLineaAereas(this.alianContr);

		// de tabla parametrica
	}

	public void guardarLineaAereas(String implementacion) {

		LineaAereaDAO lineaAereaDAO = LineaAereaFactory.getImplementacion(implementacion);

		lineaAereaDAO.guardarLineaAereas(this.lLineaAereas);

	}

	public void guardarNuevaLineaAerea(LineaAerea lineaAerea) {

		LineaAereaDAOImpleSQL lineaAereaDAOImpleSQL = new LineaAereaDAOImpleSQL();
		lineaAereaDAOImpleSQL.guardarNuevaLineaAerea(lineaAerea);

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

		alta(request, response);
		
		
//		String action = request.getParameter("action");
//		switch(action) {
//		case "alta":
//			alta(request, response);
//			break;
//		case "baja":
//			baja(request, response);
//			break;
//		case "modificacion":
//			modificacion(request, response);
//			break;
//		case "consulta":
//			consulta(request, response);
//			break;	
//		}


	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String valor = request.getParameter("enviar"); // name

		if (valor.equals("Enviar")) { // value

			response.setContentType("text/html");

			String idLAerea = request.getParameter("idLAerea");
			String nombre = request.getParameter("nombre");
			// FALTA validar IDALIANZA
			String idAlianza = request.getParameter("idAlianza");

			this.alianContr = new AlianzaController();
			this.alianContr.cargarAlianzas("file");
			Alianza alianza = this.alianContr.conseguirAlianza(idAlianza);

//			public LineaAerea(String idLAerea, String nombre, Alianza alianza)
			LineaAerea nuevaLineaAerea = new LineaAerea(idLAerea, nombre, alianza);
			guardarNuevaLineaAerea(nuevaLineaAerea);
			response.getWriter().append("Linea Aerea guardada en la Base correctamente").println();
			response.getWriter().append("<a href=Inicio.html>Volver al Inicio</a>").println();
		}
	}
	
	
	public void alta (HttpServletRequest request, HttpServletResponse response)
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
		out.append("<article class=\"FormularioAltaLineaAerea\">");
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
		out.append("<label for=\"1\">IdLAerea: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"1\" placeholder=\"Ingresar ID\"name=\"idLAerea\" required>");
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"2\"> Nombre: </label>");
		out.append("</td>");
		out.append("<td>");
		out.append("<input type=\"text\" id=\"2\" placeholder=\"Ingresar Nombre\"name=\"nombre\" required>");
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<td>");
		out.append("<label for=\"3\">IdAlianza: </label>");
		out.append("</td>");
		out.append("<td>");
//out.append("<input type=\"text\" id=\"3\" placeholder=\"Ingresar idAlianza\"name=\"idAlianza\" required>");
		out.append("<select name=\"idAlianza\">");

		this.alianContr = new AlianzaController();
		this.alianContr.cargarAlianzas("file");
		
		for(Alianza alianza : this.alianContr.getlAlianzas()) {
			out.append("<option value=\"");out.append(alianza.getIdAlianza());
			out.append("\">");out.append(alianza.getIdAlianza());
		}

		out.append("</select>");
		out.append("</td>");
		out.append("</tr>");
		out.append("<tr>");
		out.append("<th colspan=\"2\" align=\"CENTER\">");
		out.append("<input type=\"submit\"name=\"enviar\" value=\"Enviar\">");
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

	public void baja (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Devolver formulario html
	}
	
	public void modificacion (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Devolver formulario html
	}
	
	public void consulta (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Devolver formulario html
	}

}
