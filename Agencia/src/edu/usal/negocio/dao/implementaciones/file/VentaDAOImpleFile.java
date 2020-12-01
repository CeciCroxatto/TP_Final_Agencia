package edu.usal.negocio.dao.implementaciones.file;

import edu.usal.controller.ClienteController;
import edu.usal.controller.FormaDePagoController;
import edu.usal.controller.VueloController;
import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dto.Venta;
import edu.usal.negocio.dto.Cliente;
import edu.usal.negocio.dto.Vuelo;
import edu.usal.negocio.dto.FormaDePago;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.usal.util.FileIO;
import edu.usal.util.IOGeneral;

public class VentaDAOImpleFile implements VentaDAO {

	private List<Venta> lVentaDAO;

	public List<Venta> getlVentaDAO() {
		return lVentaDAO;
	}

	public void setlVentaDAO(List<Venta> lVentaDAO) {
		this.lVentaDAO = lVentaDAO;
	}

	@Override
	public List<Venta> cargarVentas(ClienteController clientContr, VueloController vueloContr,
			FormaDePagoController formaDPContr) {

		this.lVentaDAO = new ArrayList();
		FileIO IOfile = new FileIO();
		String[] splitted = null;

		int idVenta = 0;
		int idCliente = 0;
		int idVuelo = 0;
		Date fecha = null;
		int idPago = 0;
		double importe_vuelo = 0;
		double importe_total = 0;

		Cliente cliente = null;
		Vuelo vuelo = null;
		FormaDePago formaDePago = null;

		for (String s : IOfile.cargarLista("Venta")) {

			// # int idVenta, int idCliente, int idVuelo, Date fecha, int idPago
			splitted = s.split("[,]", 0);

			idVenta = Integer.parseInt(splitted[0]);
			idCliente = Integer.parseInt(splitted[1]);
			idVuelo = Integer.parseInt(splitted[2]);
			try {
				fecha = new SimpleDateFormat("dd/mm/yyyy").parse(splitted[3]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			idPago = Integer.parseInt(splitted[4]);

			importe_vuelo = Double.parseDouble(splitted[5]);
			importe_total = Double.parseDouble(splitted[6]);

			cliente = clientContr.conseguirCliente(idCliente);
			vuelo = vueloContr.conseguirVuelo(idVuelo);
			formaDePago = formaDPContr.conseguirFormaDePago(idPago);

			lVentaDAO.add(new Venta(idVenta, cliente, vuelo, fecha, formaDePago, importe_vuelo, importe_total));
		}
		return this.lVentaDAO;
	}

	public void guardarVentas(List<Venta> lVentas) {

		FileIO IOfile = new FileIO();
		List<String> lString = new ArrayList();

		lString.add(
				"# int idVenta, int idCliente, int idVuelo, Date fecha, int idPago, double importe_vuelo, double importe_total");

		for (Venta v : lVentas) {

			lString.add(v.getIdVenta() + "," + v.getCliente().getIdCliente() + "," + v.getVuelo().getIdVuelo() + ","
					+ v.getFecha().getDate() + "/" + (v.getFecha().getMonth() + 1) + "/" + v.getFecha().getYear() + ","
					+ v.getFormaDePago().getidPago() + "," + v.getImporte_vuelo() + "," + v.getImporte_total());
		}

		IOfile.guardarLista("Venta", lString);

	}

}
