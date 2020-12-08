package edu.usal.controller;

import edu.usal.negocio.dto.Aeropuerto;
import edu.usal.negocio.dto.Pais;
import edu.usal.negocio.dto.PaisArgentina;
import edu.usal.negocio.dto.Provincia;
import edu.usal.util.IOGeneral;
import edu.usal.vista.AeropuertoAltaVista;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class AeropuertoController implements ActionListener, ItemListener {

	private List<Aeropuerto> lAeropuertos = null;
	private PaisController paisController = null;
	private ProvinciaController provController = null;
	private AeropuertoAltaVista aeAltaVista = null;

	public ProvinciaController getProvController() {
		return provController;
	}

	public Aeropuerto conseguirAeropuerto(String idAeropuerto) {

		for (Aeropuerto aeropuerto : this.lAeropuertos) {
			if (aeropuerto.getIdAeropuerto().equals(idAeropuerto)) {
				return aeropuerto;
			}
		}
		return null;
	}

	public void imprimirListaAeropuertos() {
		IOGeneral.println("\nId Aeropuerto\tCiudad\tPais\tProvincia");
		for (Aeropuerto aeropuerto : this.lAeropuertos) {

			if (aeropuerto.getPais().getDescripcion().matches("Argentina")) {
				System.out.println(aeropuerto.getIdAeropuerto() + "\t\t" + aeropuerto.getCiudad() + "\t"
						+ aeropuerto.getPais().getDescripcion() + "\t"
						+ ((PaisArgentina) (aeropuerto.getPais())).getProvincia().getDescripcion());
			} else {
				System.out.println(aeropuerto.getIdAeropuerto() + "\t\t" + aeropuerto.getCiudad() + "\t"
						+ aeropuerto.getPais().getDescripcion());
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent accion) {
		if (accion.getSource() == this.aeAltaVista.btnDarDeAlta) {
//			this.aeAltaVista.mostrarResultado.setText("HOLAAAA");

			// guardar nuevo aeropuerto

			Pais pais = null;
			String descripcionPais = (String) this.aeAltaVista.comboPaisesDescrip.getSelectedItem();
			pais = this.paisController.conseguirPais_porDescripcion(descripcionPais);

			if (((String) this.aeAltaVista.comboPaisesDescrip.getSelectedItem()).matches("Argentina")) {
				String descripcionProvincia = (String) this.aeAltaVista.comboProvinciasDescrip.getSelectedItem();
				Provincia provincia = this.provController.conseguirProvincia_porDescripcion(descripcionProvincia);
				((PaisArgentina) pais).setProvincia(provincia);
			}

			this.lAeropuertos.add(new Aeropuerto(this.aeAltaVista.txtIngresarId.getText(),
					this.aeAltaVista.txtIngresarCiudad.getText(), pais));

		}
		if (accion.getSource() == this.aeAltaVista.btnImprimir) {
			imprimirListaAeropuertos();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == this.aeAltaVista.comboPaisesDescrip) {

			if (((String) this.aeAltaVista.comboPaisesDescrip.getSelectedItem()).matches("Argentina")) {
				this.aeAltaVista.lblProvincia.setVisible(true);
				this.aeAltaVista.comboProvinciasDescrip.setVisible(true);
			} else {
				this.aeAltaVista.lblProvincia.setVisible(false);
				this.aeAltaVista.comboProvinciasDescrip.setVisible(false);
			}

		}
	}

	public void crearAeropuerto() {

		this.paisController = new PaisController();
		this.paisController.cargarPaises();

		this.provController = new ProvinciaController();
		this.provController.cargarProvincias();

		this.aeAltaVista = new AeropuertoAltaVista(this);
		this.aeAltaVista.setVisible(true);

	}

//	public void cargarAeropuertos() {
//
//		this.lAeropuertos = new ArrayList();
//
//		Provincia provincia1 = new Provincia("CHU", "Chubut");
//		Pais pais1 = new PaisArgentina(054, "Argentina", provincia1);
//		this.lAeropuertos.add(new Aeropuerto("PMY", "Puerto Madryn", pais1)); // Aeropuerto El Tehuelche
//
//		Provincia provincia2 = new Provincia("LP", "La Pampa");
//		Pais pais2 = new PaisArgentina(054, "Argentina", provincia2);
//		this.lAeropuertos.add(new Aeropuerto("RSA", "Santa Rosa", pais2)); // Aeropuerto de Santa Rosa
//
//		Pais pais3 = new PaisOtro(055, "Brasil");
//		this.lAeropuertos.add(new Aeropuerto("FLN", "Florianopolis", pais3));
//
//		Pais pais4 = new PaisOtro(598, "Uruguay");
//		this.lAeropuertos.add(new Aeropuerto("ATI", "Artigas", pais4));
//
//		Pais pais5 = new PaisOtro(598, "Uruguay");
//		this.lAeropuertos.add(new Aeropuerto("CYR", "Colonia del Sacramento", pais5));
//
//		Provincia provincia6 = new Provincia("COR", "Cordoba");
//		Pais pais6 = new PaisArgentina(054, "Argentina", provincia6);
//		this.lAeropuertos.add(new Aeropuerto("COR", "Cordoba", pais6));
//
//	}

	public void setProvController(ProvinciaController provController) {
		this.provController = provController;
	}

	public PaisController getPaisController() {
		return paisController;
	}

	public void setPaisController(PaisController paisController) {
		this.paisController = paisController;
	}

	public AeropuertoAltaVista getAeAltaVista() {
		return aeAltaVista;
	}

	public void setAeAltaVista(AeropuertoAltaVista aeAltaVista) {
		this.aeAltaVista = aeAltaVista;
	}

	public AeropuertoController() {
		this.lAeropuertos = new ArrayList<Aeropuerto>();

	}

	public AeropuertoController(AeropuertoAltaVista aeAltaVista) {
		this.lAeropuertos = new ArrayList<Aeropuerto>();
		this.aeAltaVista = aeAltaVista;
	}

	public List<Aeropuerto> getlAeropuertos() {
		return lAeropuertos;
	}

	public void setlAeropuertos(List<Aeropuerto> lAeropuertos) {
		this.lAeropuertos = lAeropuertos;
	}

}
