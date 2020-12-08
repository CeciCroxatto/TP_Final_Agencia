package edu.usal.vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import edu.usal.controller.ClienteController;
import edu.usal.util.ValidableTextField;
import java.awt.Font;

public class ClienteCVista2 extends JPanel implements ItemListener, ActionListener {

	private static final long serialVersionUID = 1L;

	ClienteController clientContr;

	// Datos del cliente
	private JTextField textField_nombre;
	private JTextField textField_apellido;
	private JTextField textField_DNI;
	private JTextField textField_cuil;
	private JDateChooser dateChooser_fechaNac;
	private Date fechaNac;
	private JTextField textField_email;

	// Pasaporte
	private JTextField textField_nroPasaporte;
	private JDateChooser dateChooser_fechEmision, dateChooser_vencimiento;
	private Date fechEmision, vencimiento;
	private JComboBox<String> comboPPaisesDescrip;
	private JTextField textField_autEmision;
	// Pais = OTRO
	private JLabel varPOtroPais;
	private JTextField textField_POtroPais;
	// Pais = Cualquiera
	private JLabel varPProvinciaEstado;
	private JTextField textField_PProvinciaDescrip;
	// Pais = Argentina
	private JLabel varPProvincia;
	private JComboBox<String> comboPProvinciasDescrip;

	// Telefono
	private JTextField textField_nroPersonal;
	private JTextField textField_nroCelular;
	private JTextField textField_nroLaboral;

	// Pasajero frecuente
	private JTextField textField_numeroPasaFrec;
	private JTextField textField_categoria;
	private JComboBox<String> comboPFlineaAereaNombre;

	// Direccion
	private JTextField textField_calle;
	private JTextField textField_altura;
	private JTextField textField_ciudad;
	private JTextField textField_CP;
	private JComboBox<String> comboDPaisesDescrip;
	// Pais = OTRO
	private JLabel varDDirOtroPais;
	private JTextField textField_dirOtroPais;
	// Pais = Cualquiera
	private JLabel varDDirProvinciaEstado;
	private JTextField textField_DirProvinciaDescrip;
	// Pais = Argentina
	private JLabel varDDirProvincia;
	private JComboBox<String> comboDirProvinciasDescrip;

	public JButton btnDarDeAlta;
	public JButton btnLimpiar;

	public ClienteCVista2(ClienteController clientContr) {

		this.clientContr = clientContr;
		setLayout(null);

		JLabel lblClientes = new JLabel("Alta de Clientes");
		lblClientes.setBounds(10, 10, 146, 17);
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblClientes);

		JLabel lblDatosGenerales = new JLabel("Datos Generales");
		lblDatosGenerales.setBounds(10, 43, 93, 14);
		lblDatosGenerales.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		add(lblDatosGenerales);

		JLabel varDPnombre = new JLabel("Nombre");
		varDPnombre.setBounds(30, 81, 44, 14);
		varDPnombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPnombre);

		textField_nombre = new ValidableTextField("^[a-zA-Z0-9'\\s]{1,50}$");
		textField_nombre.setBounds(88, 75, 86, 20);
		add(textField_nombre);
		textField_nombre.setColumns(10);

		JLabel varDPapellido = new JLabel("Apellido");
		varDPapellido.setBounds(197, 81, 45, 14);
		varDPapellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPapellido);

		textField_apellido = new ValidableTextField("^[a-zA-Z0-9'\\s]{1,50}$");
		textField_apellido.setBounds(259, 75, 86, 20);
		textField_apellido.setColumns(10);
		add(textField_apellido);

		JLabel varDPDNI = new JLabel("DNI");
		varDPDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		varDPDNI.setBounds(408, 81, 26, 14);
		add(varDPDNI);

		textField_DNI = new ValidableTextField("^[0-9]{1,8}$");
		textField_DNI.setBounds(453, 76, 86, 20);
		textField_DNI.setColumns(10);
		add(textField_DNI);

		JLabel varDPCuil = new JLabel("CUIL");
		varDPCuil.setBounds(30, 105, 26, 14);
		varDPCuil.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPCuil);

		textField_cuil = new ValidableTextField("^(20|23|27)([0-9]{9})$");
		textField_cuil.setBounds(88, 99, 86, 20);
		textField_cuil.setColumns(10);
		add(textField_cuil);

		JLabel varDPfechaNacimiento = new JLabel("Fec Nac");
		varDPfechaNacimiento.setBounds(197, 106, 65, 14);
		varDPfechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPfechaNacimiento);

		dateChooser_fechaNac = new JDateChooser();
		dateChooser_fechaNac.setBounds(259, 99, 87, 20);
		dateChooser_fechaNac.setDateFormatString("dd/MM/yyyy");
		add(dateChooser_fechaNac);

		JLabel varDPemail = new JLabel("Email");
		varDPemail.setBounds(404, 103, 30, 14);
		varDPemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPemail);

		textField_email = new ValidableTextField("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,3})$");
		textField_email.setBounds(453, 99, 86, 20);
		textField_email.setColumns(10);
		add(textField_email);

		JLabel lblPasaporte = new JLabel("Pasaporte");
		lblPasaporte.setBounds(10, 145, 58, 14);
		lblPasaporte.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		add(lblPasaporte);

		JLabel varPnroPasaporte = new JLabel("Numero");
		varPnroPasaporte.setBounds(30, 170, 44, 14);
		varPnroPasaporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPnroPasaporte);

		textField_nroPasaporte = new ValidableTextField("^[a-zA-Z0-9]{1,50}$");
		textField_nroPasaporte.setBounds(88, 167, 86, 20);
		textField_nroPasaporte.setColumns(10);
		add(textField_nroPasaporte);

		JLabel varPfechaEmision = new JLabel("Fec Emision");
		varPfechaEmision.setBounds(177, 170, 65, 14);
		varPfechaEmision.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPfechaEmision);

		dateChooser_fechEmision = new JDateChooser();
		dateChooser_fechEmision.setBounds(259, 167, 87, 20);
		dateChooser_fechEmision.setDateFormatString("dd/MM/yyyy");
		add(dateChooser_fechEmision);

		JLabel varPfechaVecimiento = new JLabel("Fec Vencimiento");
		varPfechaVecimiento.setBounds(351, 173, 92, 14);
		varPfechaVecimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPfechaVecimiento);

		dateChooser_vencimiento = new JDateChooser();
		dateChooser_vencimiento.setBounds(453, 167, 87, 20);
		dateChooser_vencimiento.setDateFormatString("dd/MM/yyyy");
		add(dateChooser_vencimiento);

		JLabel varPpais = new JLabel("Pais");
		varPpais.setBounds(33, 195, 23, 14);
		varPpais.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPpais);

		this.comboPPaisesDescrip = new JComboBox<String>(clientContr.getPaisContr().getlPaisesDescrip());
		this.comboPPaisesDescrip.setBounds(88, 193, 87, 20);
		add(this.comboPPaisesDescrip);
		this.comboPPaisesDescrip.addItemListener(this);

		this.varPOtroPais = new JLabel("Ingrese Pais");
		this.varPOtroPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.varPOtroPais.setBounds(10, 223, 76, 14);
		add(this.varPOtroPais);
		this.varPOtroPais.setVisible(false);

		this.textField_POtroPais = new ValidableTextField("^[a-zA-Z0-9'\\s]{1,50}$");
		this.textField_POtroPais.setColumns(10);
		this.textField_POtroPais.setBounds(88, 220, 86, 20);
		add(this.textField_POtroPais);
		this.textField_POtroPais.setVisible(false);

		this.varPProvincia = new JLabel("Provincia");
		this.varPProvincia.setBounds(186, 196, 52, 14);
		this.varPProvincia.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(this.varPProvincia);
		this.varPProvincia.setVisible(false);

		this.comboPProvinciasDescrip = new JComboBox<String>(clientContr.getProvContr().getlProvinciasDescrip());
		this.comboPProvinciasDescrip.setBounds(259, 196, 87, 20);
		add(this.comboPProvinciasDescrip);
		this.comboPProvinciasDescrip.setVisible(false);

		this.varPProvinciaEstado = new JLabel("Prov / Estado");
		this.varPProvinciaEstado.setBounds(363, 199, 76, 14);
		this.varPProvinciaEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(this.varPProvinciaEstado);
		this.varPProvinciaEstado.setVisible(true);

		this.textField_PProvinciaDescrip = new ValidableTextField("^[a-zA-Z0-9'\\s]{1,50}$");
		this.textField_PProvinciaDescrip.setBounds(453, 193, 86, 20);
		this.textField_PProvinciaDescrip.setColumns(10);
		add(this.textField_PProvinciaDescrip);
		this.textField_PProvinciaDescrip.setVisible(true);

		JLabel varPautEmision = new JLabel("Autoridad");
		varPautEmision.setBounds(186, 225, 56, 14);
		varPautEmision.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPautEmision);

		textField_autEmision = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_autEmision.setBounds(259, 222, 86, 20);
		textField_autEmision.setColumns(10);
		add(textField_autEmision);

		JLabel lblDatosDeContacto = new JLabel("Datos de Contacto");
		lblDatosDeContacto.setBounds(10, 264, 104, 14);
		lblDatosDeContacto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		add(lblDatosDeContacto);

		JLabel varTpersonal = new JLabel("Tel pers");
		varTpersonal.setBounds(29, 289, 45, 14);
		varTpersonal.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varTpersonal);

		textField_nroPersonal = new ValidableTextField("[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
		textField_nroPersonal.setBounds(88, 284, 86, 20);
		textField_nroPersonal.setColumns(10);
		add(textField_nroPersonal);

		JLabel varTcelular = new JLabel("Celular");
		varTcelular.setBounds(209, 290, 39, 14);
		varTcelular.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varTcelular);

		textField_nroCelular = new ValidableTextField("[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
		textField_nroCelular.setBounds(259, 284, 86, 20);
		textField_nroCelular.setColumns(10);
		add(textField_nroCelular);

		JLabel varTlaboral = new JLabel("Tel laboral");
		varTlaboral.setBounds(384, 287, 59, 14);
		varTlaboral.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varTlaboral);

		textField_nroLaboral = new ValidableTextField("[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
		textField_nroLaboral.setBounds(453, 284, 86, 20);
		textField_nroLaboral.setColumns(10);
		add(textField_nroLaboral);

		JLabel varDcalle = new JLabel("Calle");
		varDcalle.setBounds(29, 314, 27, 14);
		varDcalle.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDcalle);

		textField_calle = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_calle.setBounds(88, 310, 86, 20);
		textField_calle.setColumns(10);
		add(textField_calle);

		JLabel varDaltura = new JLabel("Altura");
		varDaltura.setBounds(219, 316, 35, 14);
		varDaltura.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDaltura);

		textField_altura = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_altura.setBounds(259, 310, 86, 20);
		textField_altura.setColumns(10);
		add(textField_altura);

		JLabel varDciudad = new JLabel("Ciudad");
		varDciudad.setBounds(405, 315, 38, 14);
		varDciudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDciudad);

		textField_ciudad = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_ciudad.setBounds(453, 310, 86, 20);
		textField_ciudad.setColumns(10);
		add(textField_ciudad);

		JLabel varDpais = new JLabel("Pais");
		varDpais.setBounds(29, 340, 23, 14);
		varDpais.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDpais);

		this.comboDPaisesDescrip = new JComboBox<String>(clientContr.getPaisContr().getlPaisesDescrip());
		this.comboDPaisesDescrip.setBounds(88, 336, 87, 20);
		add(this.comboDPaisesDescrip);
		this.comboDPaisesDescrip.addItemListener(this);

		this.varDDirOtroPais = new JLabel("Ingrese Pais");
		this.varDDirOtroPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.varDDirOtroPais.setBounds(10, 363, 76, 14);
		add(this.varDDirOtroPais);
		this.varDDirOtroPais.setVisible(false);

		this.textField_dirOtroPais = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		this.textField_dirOtroPais.setColumns(10);
		this.textField_dirOtroPais.setBounds(88, 360, 86, 20);
		add(this.textField_dirOtroPais);
		this.textField_dirOtroPais.setVisible(false);

		this.varDDirProvincia = new JLabel("Provincia");
		this.varDDirProvincia.setBounds(202, 342, 52, 14);
		this.varDDirProvincia.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(this.varDDirProvincia);
		this.varDDirProvincia.setVisible(false);

		this.comboDirProvinciasDescrip = new JComboBox<String>(clientContr.getProvContr().getlProvinciasDescrip());
		this.comboDirProvinciasDescrip.setBounds(259, 337, 86, 20);
		add(this.comboDirProvinciasDescrip);
		this.comboDirProvinciasDescrip.setVisible(false);

		this.varDDirProvinciaEstado = new JLabel("Prov / Estado");
		this.varDDirProvinciaEstado.setBounds(367, 336, 76, 14);
		this.varDDirProvinciaEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(this.varDDirProvinciaEstado);
		this.varDDirProvinciaEstado.setVisible(true);

		this.textField_DirProvinciaDescrip = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		this.textField_DirProvinciaDescrip.setBounds(453, 336, 86, 20);
		this.textField_DirProvinciaDescrip.setColumns(10);
		add(this.textField_DirProvinciaDescrip);
		this.textField_DirProvinciaDescrip.setVisible(true);

		JLabel varDcp = new JLabel("CP");
		varDcp.setBounds(219, 366, 27, 14);
		varDcp.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDcp);

		textField_CP = new ValidableTextField("^[a-zA-Z0-9]{1,10}$");
		textField_CP.setBounds(259, 363, 86, 20);
		textField_CP.setColumns(10);
		add(textField_CP);

		JLabel lblPasajeroFrecuente = new JLabel("Pasajero Frecuente");
		lblPasajeroFrecuente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPasajeroFrecuente.setBounds(10, 401, 128, 14);
		add(lblPasajeroFrecuente);

		JLabel varPFnumero = new JLabel("Numero");
		varPFnumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		varPFnumero.setBounds(30, 431, 45, 14);
		add(varPFnumero);

		textField_numeroPasaFrec = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_numeroPasaFrec.setColumns(10);
		textField_numeroPasaFrec.setBounds(82, 426, 86, 20);
		add(textField_numeroPasaFrec);

		JLabel varPFlineaAerea = new JLabel("Linea Aerea");
		varPFlineaAerea.setFont(new Font("Tahoma", Font.BOLD, 11));
		varPFlineaAerea.setBounds(177, 429, 72, 14);
		add(varPFlineaAerea);

		comboPFlineaAereaNombre = new JComboBox<String>(clientContr.getLaContr().getlLineasAereasNombre());
		comboPFlineaAereaNombre.setBounds(253, 426, 87, 20);
		add(comboPFlineaAereaNombre);

		JLabel varPFcategoria = new JLabel("Categoria");
		varPFcategoria.setFont(new Font("Tahoma", Font.BOLD, 11));
		varPFcategoria.setBounds(385, 429, 59, 14);
		add(varPFcategoria);

		textField_categoria = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_categoria.setColumns(10);
		textField_categoria.setBounds(453, 426, 86, 20);
		add(textField_categoria);

		btnDarDeAlta = new JButton("Dar de Alta");
		btnDarDeAlta.setBounds(104, 485, 134, 23);
		add(btnDarDeAlta);
		btnDarDeAlta.addActionListener(this);

		btnLimpiar = new JButton("Cancelar");
		btnLimpiar.setBounds(284, 485, 120, 23);
		add(btnLimpiar);
		btnLimpiar.addActionListener(clientContr);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (e.getSource() == this.comboDPaisesDescrip) {

			// direccion
			if (((String) this.comboDPaisesDescrip.getSelectedItem()).matches("Otro")) {
				// Pais = OTRO
				this.varDDirOtroPais.setVisible(true);
				this.textField_dirOtroPais.setVisible(true);
				// Pais = Argentina
				this.varDDirProvincia.setVisible(false);
				this.comboDirProvinciasDescrip.setVisible(false);
				// Pais = Cualquier OTRO
				this.varDDirProvinciaEstado.setVisible(true);
				this.textField_DirProvinciaDescrip.setVisible(true);

			} else {

				if (((String) this.comboDPaisesDescrip.getSelectedItem()).matches("Argentina")) {
					// Pais = Argentina
					this.varDDirProvincia.setVisible(true);
					this.comboDirProvinciasDescrip.setVisible(true);
					// Pais = Cualquier OTRO
					this.varDDirProvinciaEstado.setVisible(false);
					this.textField_DirProvinciaDescrip.setVisible(false);
					// Pais = OTRO
					this.varDDirOtroPais.setVisible(false);
					this.textField_dirOtroPais.setVisible(false);

				} else {
					// Pais = Argentina
					this.varDDirProvincia.setVisible(false);
					this.comboDirProvinciasDescrip.setVisible(false);
					// Pais = Cualquier OTRO
					this.varDDirProvinciaEstado.setVisible(true);
					this.textField_DirProvinciaDescrip.setVisible(true);
					// Pais = OTRO
					this.varDDirOtroPais.setVisible(false);
					this.textField_dirOtroPais.setVisible(false);
				}

			}
		}

		// PASAPORTE
		if (e.getSource() == this.comboPPaisesDescrip) {

			if (((String) this.comboPPaisesDescrip.getSelectedItem()).matches("Otro")) {
				// Pais = OTRO
				this.varPOtroPais.setVisible(true);
				this.textField_POtroPais.setVisible(true);
				// Pais = Argentina
				this.varPProvincia.setVisible(false);
				this.comboPProvinciasDescrip.setVisible(false);
				// Pais = Cualquiera
				this.varPProvinciaEstado.setVisible(true);
				this.textField_PProvinciaDescrip.setVisible(true);

			} else {

				if (((String) this.comboPPaisesDescrip.getSelectedItem()).matches("Argentina")) {
					// Pais = Argentina
					this.varPProvincia.setVisible(true);
					this.comboPProvinciasDescrip.setVisible(true);
					// Pais = Cualquiera
					this.varPProvinciaEstado.setVisible(false);
					this.textField_PProvinciaDescrip.setVisible(false);
					// Pais = OTRO
					this.varPOtroPais.setVisible(false);
					this.textField_POtroPais.setVisible(false);

				} else {
					// Pais = Argentina
					this.varPProvincia.setVisible(false);
					this.comboPProvinciasDescrip.setVisible(false);
					// Pais = Cualquiera
					this.varPProvinciaEstado.setVisible(true);
					this.textField_PProvinciaDescrip.setVisible(true);
					// Pais = OTRO
					this.varPOtroPais.setVisible(false);
					this.textField_POtroPais.setVisible(false);
				}

			}

		}

	}

	public void otraAlta() {
		int rta = !(true) ? 0
				: (JOptionPane.showConfirmDialog(null, "Quiere hacer otra alta?", "Confirmacion",
						JOptionPane.YES_NO_OPTION));

		if (rta == JOptionPane.YES_OPTION) {

			limpiar();
		}
	}

	public void limpiarConConfirmacion() {

		int rta = !(true) ? 0
				: (JOptionPane.showConfirmDialog(null, "El formulario sera vaciado. Confirma?", "Confirmacion",
						JOptionPane.YES_NO_OPTION));

		if (rta == JOptionPane.YES_OPTION) {

			limpiar();
		}

	}

	public void limpiar() {

		// Datos del cliente
		textField_nombre.setText("");
		textField_apellido.setText("");
		textField_DNI.setText("");
		textField_cuil.setText("");
		dateChooser_fechaNac.setCalendar(null);
		textField_email.setText("");

		// Pasaporte
		textField_nroPasaporte.setText("");
		dateChooser_fechEmision.setCalendar(null);
		dateChooser_vencimiento.setCalendar(null);
		comboPPaisesDescrip.setSelectedIndex(0);
		textField_autEmision.setText("");
		textField_POtroPais.setText("");
		textField_PProvinciaDescrip.setText("");
		comboPProvinciasDescrip.setSelectedIndex(0);

		// Telefono
		textField_nroPersonal.setText("");
		textField_nroCelular.setText("");
		textField_nroLaboral.setText("");

		// Pasajero frecuente
		textField_numeroPasaFrec.setText("");
		textField_categoria.setText("");
		comboPFlineaAereaNombre.setSelectedIndex(0);

		// Direccion
		textField_calle.setText("");
		textField_altura.setText("");
		textField_ciudad.setText("");
		textField_CP.setText("");
		comboDPaisesDescrip.setSelectedIndex(0);
		textField_dirOtroPais.setText("");
		textField_DirProvinciaDescrip.setText("");
		comboDirProvinciasDescrip.setSelectedIndex(0);

		mostrarmensaje("Datos limpiados");

	}

	public void mostrarmensaje(String mensaje) {
		JOptionPane.showConfirmDialog(null, mensaje, "Informacion", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);

	}

	@Override
	public void actionPerformed(ActionEvent action) {

		if (action.getSource() == btnDarDeAlta) {

			int bandera = 0;

			Date today = Calendar.getInstance().getTime();

			String paisDir = null, provDir = null;
			String paisPasaporte = null, provinciaPasaporte = null;

			if (textField_nombre.getText().length() > 0) {
				if (((LineBorder) (textField_nombre.getBorder())).getLineColor() == Color.BLACK) {
					if (textField_apellido.getText().length() > 0) {
						if (((LineBorder) (textField_apellido.getBorder())).getLineColor() == Color.BLACK) {
							if (textField_DNI.getText().length() > 0) {
								if (((LineBorder) (textField_DNI.getBorder())).getLineColor() == Color.BLACK) {
									if (textField_cuil.getText().length() > 0) {
										if (((LineBorder) (textField_cuil.getBorder())).getLineColor() == Color.BLACK) {

											// fecha de nacimiento
											if (dateChooser_fechaNac.getDate() != null) {
												if (today.compareTo(dateChooser_fechaNac.getDate()) > 0) {
													fechaNac = dateChooser_fechaNac.getDate();
													String fechaNacS = new SimpleDateFormat("MM-dd-yyyy")
															.format(fechaNac);

													if (textField_email.getText().length() > 0) {
														if (((LineBorder) (textField_email.getBorder()))
																.getLineColor() == Color.BLACK) {

															if (textField_nroPasaporte.getText().length() > 0) {
																if (((LineBorder) (textField_nroPasaporte.getBorder()))
																		.getLineColor() == Color.BLACK) {

																	// fecha de emision
																	if (dateChooser_fechEmision.getDate() != null) {
																		if (today.compareTo(dateChooser_fechEmision
																				.getDate()) > 0) {
																			fechEmision = dateChooser_fechEmision
																					.getDate();
																			String fechEmisionS = new SimpleDateFormat(
																					"MM-dd-yyyy").format(fechEmision);
																			if (dateChooser_vencimiento
																					.getDate() != null) {
																				if (dateChooser_fechEmision.getDate()
																						.compareTo(
																								dateChooser_vencimiento
																										.getDate()) < 0) {
																					vencimiento = dateChooser_vencimiento
																							.getDate();
																					String vencimientoS = new SimpleDateFormat(
																							"MM-dd-yyyy").format(
																									vencimiento);

																					// pais pasaporte
																					boolean pasaporteB = true;
																					do {

																						paisPasaporte = (String) this.comboPPaisesDescrip
																								.getSelectedItem();

																						if (paisPasaporte
																								.matches("Otro")) {

																							if (textField_POtroPais
																									.getText()
																									.length() > 0) {
																								if (((LineBorder) (textField_POtroPais
																										.getBorder()))
																												.getLineColor() == Color.BLACK) {
																									if (textField_PProvinciaDescrip
																											.getText()
																											.length() > 0) {
																										if (((LineBorder) (textField_PProvinciaDescrip
																												.getBorder()))
																														.getLineColor() == Color.BLACK) {
																											provinciaPasaporte = textField_POtroPais
																													.getText()
																													+ "-"
																													+ textField_PProvinciaDescrip
																															.getText();
																											bandera++;
																										} else {
																											cartelErrorIngresoDatos(
																													"Pasaporte: Prov / Estado: Hasta 50 caracteres alfanumericos");
																										}
																									} else {
																										cartelErrorIngresoDatos(
																												"Pasaporte: Prov / Estado: Campo obligatorio");
																									}
																								} else {
																									cartelErrorIngresoDatos(
																											"Pasaporte: Ingrese Pais: Hasta 50 caracteres alfanumericos");
																								}
																							} else {
																								cartelErrorIngresoDatos(
																										"Pasaporte: Ingrese Pais: Campo obligatorio");
																							}

																						} else {
																							if (paisPasaporte.matches(
																									"Argentina")) {
																								provinciaPasaporte = (String) comboPProvinciasDescrip
																										.getSelectedItem();
																								bandera++;
																							} else {
																								if (textField_PProvinciaDescrip
																										.getText()
																										.length() > 0) {
																									if (((LineBorder) (textField_PProvinciaDescrip
																											.getBorder()))
																													.getLineColor() == Color.BLACK) {
																										provinciaPasaporte = textField_PProvinciaDescrip
																												.getText();
																										bandera++;
																									} else {
																										cartelErrorIngresoDatos(
																												"Pasaporte: Prov / Estado: Hasta 50 caracteres alfanumericos");
																									}
																								} else {
																									cartelErrorIngresoDatos(
																											"Pasaporte: Prov / Estado: Campo obligatorio");
																								}

																							}
																						}

																						pasaporteB = false;
																					} while (pasaporteB);

																					if (bandera == 1) {
																						if (textField_autEmision
																								.getText()
																								.length() > 0) {
																							if (((LineBorder) (textField_autEmision
																									.getBorder()))
																											.getLineColor() == Color.BLACK) {
																								if (textField_nroPersonal
																										.getText()
																										.length() > 0) {
																									if (((LineBorder) (textField_nroPersonal
																											.getBorder()))
																													.getLineColor() == Color.BLACK) {
																										if (textField_nroCelular
																												.getText()
																												.length() > 0) {
																											if (((LineBorder) (textField_nroCelular
																													.getBorder()))
																															.getLineColor() == Color.BLACK) {
																												if (textField_nroLaboral
																														.getText()
																														.length() > 0) {
																													if (((LineBorder) (textField_nroLaboral
																															.getBorder()))
																																	.getLineColor() == Color.BLACK) {
																														if (textField_calle
																																.getText()
																																.length() > 0) {
																															if (((LineBorder) (textField_calle
																																	.getBorder()))
																																			.getLineColor() == Color.BLACK) {
																																if (textField_altura
																																		.getText()
																																		.length() > 0) {
																																	if (((LineBorder) (textField_altura
																																			.getBorder()))
																																					.getLineColor() == Color.BLACK) {
																																		if (textField_ciudad
																																				.getText()
																																				.length() > 0) {
																																			if (((LineBorder) (textField_ciudad
																																					.getBorder()))
																																							.getLineColor() == Color.BLACK) {

																																				// pais
																																				// direccion
																																				boolean direccionB = true;

																																				do {

																																					paisDir = (String) this.comboDPaisesDescrip
																																							.getSelectedItem();

																																					if (paisDir
																																							.matches(
																																									"Otro")) {

																																						if (textField_dirOtroPais
																																								.getText()
																																								.length() > 0) {
																																							if (((LineBorder) (textField_dirOtroPais
																																									.getBorder()))
																																											.getLineColor() == Color.BLACK) {
																																								if (textField_DirProvinciaDescrip
																																										.getText()
																																										.length() > 0) {
																																									if (((LineBorder) (textField_DirProvinciaDescrip
																																											.getBorder()))
																																													.getLineColor() == Color.BLACK) {
																																										provDir = textField_dirOtroPais
																																												.getText()
																																												+ "-"
																																												+ textField_DirProvinciaDescrip
																																														.getText();
																																										bandera++;
																																									} else {
																																										cartelErrorIngresoDatos(
																																												"Direccion: Prov / Estado: Hasta 50 caracteres alfanumericos");
																																									}
																																								} else {
																																									cartelErrorIngresoDatos(
																																											"Direccion: Prov / Estado: Campo obligatorio");
																																								}
																																							} else {
																																								cartelErrorIngresoDatos(
																																										"Direccion: Ingrese Pais: Hasta 50 caracteres alfanumericos");
																																							}
																																						} else {
																																							cartelErrorIngresoDatos(
																																									"Direccion: Ingrese Pais: Campo obligatorio");
																																						}

																																					} else {
																																						if (paisDir
																																								.matches(
																																										"Argentina")) {
																																							provDir = (String) comboDirProvinciasDescrip
																																									.getSelectedItem();
																																							bandera++;
																																						} else {
																																							if (textField_DirProvinciaDescrip
																																									.getText()
																																									.length() > 0) {
																																								if (((LineBorder) (textField_DirProvinciaDescrip
																																										.getBorder()))
																																												.getLineColor() == Color.BLACK) {
																																									provDir = textField_DirProvinciaDescrip
																																											.getText();
																																									bandera++;
																																								} else {
																																									cartelErrorIngresoDatos(
																																											"Direccion: Prov / Estado: Hasta 50 caracteres alfanumericos");
																																								}
																																							} else {
																																								cartelErrorIngresoDatos(
																																										"Direccion: Prov / Estado: Campo obligatorio");
																																							}

																																						}
																																					}

																																					direccionB = false;
																																				} while (direccionB);

																																				if (bandera == 2) {
																																					if (textField_CP
																																							.getText()
																																							.length() > 0) {
																																						if (((LineBorder) (textField_CP
																																								.getBorder()))
																																										.getLineColor() == Color.BLACK) {

																																							if (textField_numeroPasaFrec
																																									.getText()
																																									.length() > 0) {
																																								if (((LineBorder) (textField_numeroPasaFrec
																																										.getBorder()))
																																												.getLineColor() == Color.BLACK) {
																																									if (textField_categoria
																																											.getText()
																																											.length() > 0) {
																																										if (((LineBorder) (textField_categoria
																																												.getBorder()))
																																														.getLineColor() == Color.BLACK) {

																																											clientContr
																																													.crearClienteGUI(
																																															"SQL",
																																															textField_nombre
																																																	.getText(),
																																															textField_apellido
																																																	.getText(),
																																															textField_DNI
																																																	.getText(),
																																															textField_cuil
																																																	.getText(),
																																															textField_nroPasaporte
																																																	.getText(),
																																															paisPasaporte,
																																															provinciaPasaporte,
																																															textField_autEmision
																																																	.getText(),
																																															fechEmisionS,
																																															vencimientoS,
																																															textField_nroPersonal
																																																	.getText(),
																																															textField_nroCelular
																																																	.getText(),
																																															textField_nroLaboral
																																																	.getText(),
																																															fechaNacS,
																																															textField_numeroPasaFrec
																																																	.getText(),
																																															(String) comboPFlineaAereaNombre
																																																	.getSelectedItem(),
																																															textField_categoria
																																																	.getText(),
																																															textField_email
																																																	.getText(),
																																															textField_calle
																																																	.getText(),
																																															textField_altura
																																																	.getText(),
																																															textField_ciudad
																																																	.getText(),
																																															paisDir,
																																															provDir,
																																															textField_CP
																																																	.getText());

																																										} else {
																																											cartelErrorIngresoDatos(
																																													"Categoria: Hasta 50 caracteres alfanumericos");
																																										}
																																									} else {
																																										cartelErrorIngresoDatos(
																																												"Categoria: Campo obligatorio");
																																									}
																																								} else {
																																									cartelErrorIngresoDatos(
																																											"Numero de Pasajero Frecuente: Hasta 50 caracteres alfanumericos");
																																								}
																																							} else {
																																								cartelErrorIngresoDatos(
																																										"Numero de Pasajero Frecuente: Campo obligatorio");
																																							}

																																						} else {
																																							cartelErrorIngresoDatos(
																																									"CP: Hasta 50 caracteres alfanumericos");
																																						}
																																					} else {
																																						cartelErrorIngresoDatos(
																																								"CP: Campo obligatorio");
																																					}
																																				}

																																			} else {
																																				cartelErrorIngresoDatos(
																																						"Ciudad: Hasta 50 caracteres alfanumericos");
																																			}
																																		} else {
																																			cartelErrorIngresoDatos(
																																					"Ciudad: Campo obligatorio");
																																		}
																																	} else {
																																		cartelErrorIngresoDatos(
																																				"Altura: Hasta 50 caracteres alfanumericos");
																																	}
																																} else {
																																	cartelErrorIngresoDatos(
																																			"Altura: Campo obligatorio");
																																}
																															} else {
																																cartelErrorIngresoDatos(
																																		"Calle: Hasta 50 caracteres alfanumericos");
																															}
																														} else {
																															cartelErrorIngresoDatos(
																																	"Calle: Campo obligatorio");
																														}
																													} else {
																														cartelErrorIngresoDatos(
																																"Telefono laboral: Digitos, +, -, espacio. Si no posee ingrese 0.");
																													}
																												} else {
																													cartelErrorIngresoDatos(
																															"Telefono laboral: Campo obligatorio");
																												}
																											} else {
																												cartelErrorIngresoDatos(
																														"Celular: Digitos, +, -, espacio. Si no posee ingrese 0.");
																											}
																										} else {
																											cartelErrorIngresoDatos(
																													"Celular: Campo obligatorio");
																										}
																									} else {
																										cartelErrorIngresoDatos(
																												"Nro. Personal: Digitos, +, -, espacio. Si no posee ingrese 0.");
																									}
																								} else {
																									cartelErrorIngresoDatos(
																											"Nro. Personal: Campo obligatorio");
																								}
																							} else {
																								cartelErrorIngresoDatos(
																										"Autoridad: Hasta 50 caracteres alfanumericos");
																							}
																						} else {
																							cartelErrorIngresoDatos(
																									"Autoridad: Campo obligatorio");
																						}
																					}

																					// pais pasaporte

																					// fecha de vencimiento
																				} else {
																					cartelErrorIngresoDatos(
																							"Fecha de Vencimiento: Posterior a fecha de emision");
																				}
																			} else {
																				cartelErrorIngresoDatos(
																						"Fecha de Vencimiento: Campo obligatorio");
																			}

																			// fecha de emision
																		} else {
																			cartelErrorIngresoDatos(
																					"Fecha de Emision: Anterior a hoy");
																		}
																	} else {
																		cartelErrorIngresoDatos(
																				"Fecha de Emision: Campo obligatorio");
																	}

																} else {
																	cartelErrorIngresoDatos(
																			"Numero de Pasaporte: Hasta 50 caracteres alfanumericos");
																}
															} else {
																cartelErrorIngresoDatos(
																		"Numero de Pasaporte: Campo obligatorio");
															}
														} else {
															cartelErrorIngresoDatos("Email: Formato email");
														}
													} else {
														cartelErrorIngresoDatos("Email: Campo obligatorio");
													}

													// fecha de nacimiento
												} else {
													cartelErrorIngresoDatos("Fecha de nacimiento: Anterior a hoy");
												}
											} else {
												cartelErrorIngresoDatos("Fecha de Nacimiento: Campo obligatorio");
											}

										} else {
											cartelErrorIngresoDatos("CUIL: 11 digitos, que empiece con 20/23/27");
										}
									} else {
										cartelErrorIngresoDatos("CUIL: Campo obligatorio");
									}
								} else {
									cartelErrorIngresoDatos("DNI: Hasta 8 digitos");
								}
							} else {
								cartelErrorIngresoDatos("DNI: Campo obligatorio");
							}
						} else {
							cartelErrorIngresoDatos("Apellido: Hasta 50 caracteres alfanumericos");
						}
					} else {
						cartelErrorIngresoDatos("Apellido: Campo obligatorio");
					}
				} else {
					cartelErrorIngresoDatos("Nombre: Hasta 50 caracteres alfanumericos");
				}
			} else {
				cartelErrorIngresoDatos("Nombre: Campo obligatorio");
			}
		}

	}

	public void cartelErrorIngresoDatos(String msg) {
		JOptionPane.showConfirmDialog(null, msg, "Error en el Ingreso de Datos", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}

}
