package edu.usal.vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import edu.usal.manager.ClienteManager;
import edu.usal.util.ValidableTextField;

import java.awt.Font;

public class ClienteUVista2 extends JPanel implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;

	ClienteManager clienteManager;

	// Datos del cliente
	private JLabel lblDatosGenerales;
	private JLabel varDPnombre;
	private JTextField textField_nombre;
	private JLabel varDPapellido;
	private JTextField textField_apellido;
	private JLabel varDPDNI;
	private JTextField textField_DNI;
	private JLabel varDPCuil;
	private JTextField textField_cuil;
	private JLabel varDPfechaNacimiento;
	private JDateChooser dateChooser_fechaNac;
	private Date fechaNac;
	private JLabel varDPemail;
	private JTextField textField_email;

	// Pasaporte
	private JLabel lblPasaporte;
	private JLabel varPnroPasaporte;
	private JTextField textField_nroPasaporte;
	private JLabel varPfechaEmision;
	private JLabel varPfechaVecimiento;
	private JDateChooser dateChooser_fechEmision, dateChooser_vencimiento;
	private Date fechEmision, vencimiento;
	private JComboBox<String> comboPPaisesDescrip;
//	private JTextField comboPPaisesDescrip;
	JLabel varPautEmision;
	private JTextField textField_autEmision;
	// Pais = OTRO
	private JLabel varPpais;
	private JLabel varPOtroPais;
	private JTextField textField_POtroPais;
	// Pais = Cualquiera
	private JLabel varPProvinciaEstado;
	private JTextField textField_PProvinciaDescrip;
	// Pais = Argentina
	private JLabel varPProvincia;
	private JComboBox<String> comboPProvinciasDescrip;
//	private JTextField comboPProvinciasDescrip;

	private JLabel lblDatosDeContacto;
	// Telefono
	private JLabel varTpersonal;
	private JTextField textField_nroPersonal;
	private JLabel varTcelular;
	private JTextField textField_nroCelular;
	private JLabel varTlaboral;
	private JTextField textField_nroLaboral;

	// Pasajero frecuente
	private JLabel lblPasajeroFrecuente;
	private JLabel varPFnumero;
	private JTextField textField_numeroPasaFrec;
	private JLabel varPFcategoria;
	private JTextField textField_categoria;
	private JLabel varPFlineaAerea;
	private JComboBox<String> comboPFlineaAereaNombre;
//	private JTextField comboPFlineaAereaNombre;

	// Direccion
	private JLabel varDcalle;
	private JTextField textField_calle;
	private JLabel varDaltura;
	private JTextField textField_altura;
	private JLabel varDciudad;
	private JTextField textField_ciudad;
	private JLabel varDcp;
	private JTextField textField_CP;
	private JComboBox<String> comboDPaisesDescrip;
//	private JTextField comboDPaisesDescrip;
	private JLabel varDpais;
	// Pais = OTRO
	private JLabel varDDirOtroPais;
	private JTextField textField_dirOtroPais;
	// Pais = Cualquiera
	private JLabel varDDirProvinciaEstado;
	private JTextField textField_DirProvinciaDescrip;
	// Pais = Argentina
	private JLabel varDDirProvincia;
	private JComboBox<String> comboDirProvinciasDescrip;
//	private JTextField comboDirProvinciasDescrip;

	public JButton btnConsultar;
	public JButton btnLimpiar;
	public JButton btnModificar;

	public ClienteUVista2(ClienteManager clienteManager) {
		this.clienteManager = clienteManager;
		setLayout(null);

		JLabel lblClientes = new JLabel("Modificacion de Clientes");
		lblClientes.setBounds(10, 15, 146, 17);
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblClientes);

		lblDatosGenerales = new JLabel("Datos Generales");
		lblDatosGenerales.setBounds(10, 56, 93, 14);
		lblDatosGenerales.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		add(lblDatosGenerales);
		lblDatosGenerales.setVisible(false);

		varDPnombre = new JLabel("Nombre");
		varDPnombre.setBounds(30, 81, 44, 14);
		varDPnombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPnombre);
		varDPnombre.setVisible(false);

		textField_nombre = new ValidableTextField("^[a-zA-Z0-9'\\s]{1,50}$");
		textField_nombre.setBounds(88, 75, 86, 20);
		add(textField_nombre);
		textField_nombre.setColumns(10);
		textField_nombre.setVisible(false);
//		textField_nombre.setEditable(false);

		varDPapellido = new JLabel("Apellido");
		varDPapellido.setBounds(197, 81, 45, 14);
		varDPapellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPapellido);
		varDPapellido.setVisible(false);

		textField_apellido = new ValidableTextField("^[a-zA-Z0-9'\\s]{1,50}$");
		textField_apellido.setBounds(259, 75, 86, 20);
		textField_apellido.setColumns(10);
		add(textField_apellido);
		textField_apellido.setVisible(false);
//		textField_apellido.setEditable(false);

		varDPDNI = new JLabel("DNI");
		varDPDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		varDPDNI.setBounds(408, 81, 26, 14);
		add(varDPDNI);
		varDPDNI.setVisible(false);

		textField_DNI = new ValidableTextField("^[0-9]{1,8}$");
		textField_DNI.setBounds(453, 76, 86, 20);
		textField_DNI.setColumns(10);
		add(textField_DNI);
		textField_DNI.setVisible(false);
//		textField_DNI.setEditable(false);

		varDPCuil = new JLabel("por CUIL");
		varDPCuil.setBounds(196, 20, 72, 14);
		varDPCuil.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPCuil);
		varDPCuil.setVisible(true);

		textField_cuil = new ValidableTextField("^(20|23|27)([0-9]{9})$");
		textField_cuil.setBounds(255, 17, 86, 20);
		textField_cuil.setColumns(10);
		add(textField_cuil);
		textField_cuil.setVisible(true);

		varDPfechaNacimiento = new JLabel("Fec Nac");
		varDPfechaNacimiento.setBounds(197, 106, 65, 14);
		varDPfechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPfechaNacimiento);
		varDPfechaNacimiento.setVisible(false);

		dateChooser_fechaNac = new JDateChooser();
//		dateChooser_fechaNac = new JTextField();
		dateChooser_fechaNac.setBounds(259, 99, 87, 20);
		dateChooser_fechaNac.setDateFormatString("dd/MM/yyyy");
		add(dateChooser_fechaNac);
		dateChooser_fechaNac.setVisible(false);
//		dateChooser_fechaNac.setEditable(false);

		varDPemail = new JLabel("Email");
		varDPemail.setBounds(404, 103, 30, 14);
		varDPemail.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDPemail);
		varDPemail.setVisible(false);

		textField_email = new ValidableTextField("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,3})$");
		textField_email.setBounds(453, 99, 86, 20);
		textField_email.setColumns(10);
		add(textField_email);
		textField_email.setVisible(false);
//		textField_email.setEditable(false);

		lblPasaporte = new JLabel("Pasaporte");
		lblPasaporte.setBounds(10, 145, 58, 14);
		lblPasaporte.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		add(lblPasaporte);
		lblPasaporte.setVisible(false);

		varPnroPasaporte = new JLabel("Numero");
		varPnroPasaporte.setBounds(30, 170, 44, 14);
		varPnroPasaporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPnroPasaporte);
		varPnroPasaporte.setVisible(false);

		textField_nroPasaporte = new ValidableTextField("^[a-zA-Z0-9]{1,50}$");
		textField_nroPasaporte.setBounds(88, 167, 86, 20);
		textField_nroPasaporte.setColumns(10);
		add(textField_nroPasaporte);
		textField_nroPasaporte.setVisible(false);
		textField_nroPasaporte.setEditable(false);

		varPfechaEmision = new JLabel("Fec Emision");
		varPfechaEmision.setBounds(177, 170, 65, 14);
		varPfechaEmision.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPfechaEmision);
		varPfechaEmision.setVisible(false);

		dateChooser_fechEmision = new JDateChooser();
//		dateChooser_fechEmision = new JTextField();
		dateChooser_fechEmision.setBounds(259, 167, 87, 20);
		dateChooser_fechEmision.setDateFormatString("dd/MM/yyyy");
		add(dateChooser_fechEmision);
		dateChooser_fechEmision.setVisible(false);
//		dateChooser_fechEmision.setEditable(false);

		varPfechaVecimiento = new JLabel("Fec Vencimiento");
		varPfechaVecimiento.setBounds(351, 173, 92, 14);
		varPfechaVecimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPfechaVecimiento);
		varPfechaVecimiento.setVisible(false);

		dateChooser_vencimiento = new JDateChooser();
//		dateChooser_vencimiento = new JTextField();
		dateChooser_vencimiento.setBounds(453, 167, 87, 20);
		dateChooser_vencimiento.setDateFormatString("dd/MM/yyyy");
		add(dateChooser_vencimiento);
		dateChooser_vencimiento.setVisible(false);
//		dateChooser_vencimiento.setEditable(false);

		varPpais = new JLabel("Pais");
		varPpais.setBounds(33, 195, 23, 14);
		varPpais.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPpais);
		varPpais.setVisible(false);

		this.comboPPaisesDescrip = new JComboBox<String>(
				this.clienteManager.getClienteController().getPaisContr().getlPaisesDescrip());
//		this.comboPPaisesDescrip = new JTextField();
		this.comboPPaisesDescrip.setBounds(88, 193, 87, 20);
		add(this.comboPPaisesDescrip);
		this.comboPPaisesDescrip.addItemListener(this);
		comboPPaisesDescrip.setVisible(false);
//		comboPPaisesDescrip.setEditable(false);

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
//		this.textField_POtroPais.setEditable(false);

		this.varPProvincia = new JLabel("Provincia");
		this.varPProvincia.setBounds(186, 196, 52, 14);
		this.varPProvincia.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(this.varPProvincia);
		this.varPProvincia.setVisible(false);

		this.comboPProvinciasDescrip = new JComboBox<String>(
				this.clienteManager.getClienteController().getProvContr().getlProvinciasDescrip());
//		this.comboPProvinciasDescrip = new JTextField();
		this.comboPProvinciasDescrip.setBounds(259, 196, 87, 20);
		add(this.comboPProvinciasDescrip);
		this.comboPProvinciasDescrip.setVisible(false);
//		this.comboPProvinciasDescrip.setEditable(false);

		this.varPProvinciaEstado = new JLabel("Prov / Estado");
		this.varPProvinciaEstado.setBounds(177, 223, 76, 14);
		this.varPProvinciaEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(this.varPProvinciaEstado);
		this.varPProvinciaEstado.setVisible(false);

		this.textField_PProvinciaDescrip = new ValidableTextField("^[a-zA-Z0-9'\\s]{1,50}$");
		this.textField_PProvinciaDescrip.setBounds(259, 220, 86, 20);
		this.textField_PProvinciaDescrip.setColumns(10);
		add(this.textField_PProvinciaDescrip);
		this.textField_PProvinciaDescrip.setVisible(false);
//		this.textField_PProvinciaDescrip.setEditable(false);

		varPautEmision = new JLabel("Autoridad");
		varPautEmision.setBounds(380, 201, 56, 14);
		varPautEmision.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varPautEmision);
		varPautEmision.setVisible(false);

		textField_autEmision = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_autEmision.setBounds(453, 198, 86, 20);
		textField_autEmision.setColumns(10);
		add(textField_autEmision);
		textField_autEmision.setVisible(false);
//		textField_autEmision.setEditable(false);

		lblDatosDeContacto = new JLabel("Datos de Contacto");
		lblDatosDeContacto.setBounds(10, 264, 104, 14);
		lblDatosDeContacto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		add(lblDatosDeContacto);
		lblDatosDeContacto.setVisible(false);

		varTpersonal = new JLabel("Tel pers");
		varTpersonal.setBounds(29, 289, 45, 14);
		varTpersonal.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varTpersonal);
		varTpersonal.setVisible(false);

		textField_nroPersonal = new ValidableTextField("[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
		textField_nroPersonal.setBounds(88, 284, 86, 20);
		textField_nroPersonal.setColumns(10);
		add(textField_nroPersonal);
		textField_nroPersonal.setVisible(false);
//		textField_nroPersonal.setEditable(false);

		varTcelular = new JLabel("Celular");
		varTcelular.setBounds(209, 290, 39, 14);
		varTcelular.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varTcelular);
		varTcelular.setVisible(false);

		textField_nroCelular = new ValidableTextField("[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
		textField_nroCelular.setBounds(259, 284, 86, 20);
		textField_nroCelular.setColumns(10);
		add(textField_nroCelular);
		textField_nroCelular.setVisible(false);
//		textField_nroCelular.setEditable(false);

		varTlaboral = new JLabel("Tel laboral");
		varTlaboral.setBounds(384, 287, 59, 14);
		varTlaboral.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varTlaboral);
		varTlaboral.setVisible(false);

		textField_nroLaboral = new ValidableTextField("[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");
		textField_nroLaboral.setBounds(453, 284, 86, 20);
		textField_nroLaboral.setColumns(10);
		add(textField_nroLaboral);
		textField_nroLaboral.setVisible(false);
//		textField_nroLaboral.setEditable(false);

		varDcalle = new JLabel("Calle");
		varDcalle.setBounds(29, 314, 27, 14);
		varDcalle.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDcalle);
		varDcalle.setVisible(false);

		textField_calle = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_calle.setBounds(88, 310, 86, 20);
		textField_calle.setColumns(10);
		add(textField_calle);
		textField_calle.setVisible(false);
//		textField_calle.setEditable(false);

		varDaltura = new JLabel("Altura");
		varDaltura.setBounds(219, 316, 35, 14);
		varDaltura.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDaltura);
		varDaltura.setVisible(false);

		textField_altura = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_altura.setBounds(259, 310, 86, 20);
		textField_altura.setColumns(10);
		add(textField_altura);
		textField_altura.setVisible(false);
//		textField_altura.setEditable(false);

		varDciudad = new JLabel("Ciudad");
		varDciudad.setBounds(405, 315, 38, 14);
		varDciudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDciudad);
		varDciudad.setVisible(false);

		textField_ciudad = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_ciudad.setBounds(453, 310, 86, 20);
		textField_ciudad.setColumns(10);
		add(textField_ciudad);
		textField_ciudad.setVisible(false);
//		textField_ciudad.setEditable(false);

		varDpais = new JLabel("Pais");
		varDpais.setBounds(29, 340, 23, 14);
		varDpais.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDpais);
		varDpais.setVisible(false);

		this.comboDPaisesDescrip = new JComboBox<String>(
				this.clienteManager.getClienteController().getPaisContr().getlPaisesDescrip());
//		this.comboDPaisesDescrip = new JTextField();
		this.comboDPaisesDescrip.setBounds(88, 336, 87, 20);
		add(this.comboDPaisesDescrip);
		this.comboDPaisesDescrip.addItemListener(this);
		this.comboDPaisesDescrip.setVisible(false);
//		this.comboDPaisesDescrip.setEditable(false);

		this.varDDirOtroPais = new JLabel("Ingrese Pais");
		this.varDDirOtroPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.varDDirOtroPais.setBounds(10, 364, 76, 14);
		add(this.varDDirOtroPais);
		this.varDDirOtroPais.setVisible(false);

		this.textField_dirOtroPais = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		this.textField_dirOtroPais.setColumns(10);
		this.textField_dirOtroPais.setBounds(88, 361, 86, 20);
		add(this.textField_dirOtroPais);
		this.textField_dirOtroPais.setVisible(false);
//		this.textField_dirOtroPais.setEditable(false);

		this.varDDirProvincia = new JLabel("Provincia");
		this.varDDirProvincia.setBounds(202, 342, 52, 14);
		this.varDDirProvincia.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(this.varDDirProvincia);
		this.varDDirProvincia.setVisible(false);

		this.comboDirProvinciasDescrip = new JComboBox<String>(
				this.clienteManager.getClienteController().getProvContr().getlProvinciasDescrip());
//		this.comboDirProvinciasDescrip = new JTextField();
		this.comboDirProvinciasDescrip.setBounds(259, 337, 86, 20);
		add(this.comboDirProvinciasDescrip);
		this.comboDirProvinciasDescrip.setVisible(false);
//		this.comboDirProvinciasDescrip.setEditable(false);

		this.varDDirProvinciaEstado = new JLabel("Prov / Estado");
		this.varDDirProvinciaEstado.setBounds(177, 367, 76, 14);
		this.varDDirProvinciaEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(this.varDDirProvinciaEstado);
		this.varDDirProvinciaEstado.setVisible(false);

		this.textField_DirProvinciaDescrip = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		this.textField_DirProvinciaDescrip.setBounds(259, 363, 86, 20);
		this.textField_DirProvinciaDescrip.setColumns(10);
		add(this.textField_DirProvinciaDescrip);
		this.textField_DirProvinciaDescrip.setVisible(false);
//		this.textField_DirProvinciaDescrip.setEditable(false);

		varDcp = new JLabel("CP");
		varDcp.setBounds(413, 339, 27, 14);
		varDcp.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(varDcp);
		varDcp.setVisible(false);

		textField_CP = new ValidableTextField("^[a-zA-Z0-9]{1,10}$");
		textField_CP.setBounds(453, 336, 86, 20);
		textField_CP.setColumns(10);
		add(textField_CP);
		textField_CP.setVisible(false);
//		textField_CP.setEditable(false);

		lblPasajeroFrecuente = new JLabel("Pasajero Frecuente");
		lblPasajeroFrecuente.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPasajeroFrecuente.setBounds(10, 401, 128, 14);
		add(lblPasajeroFrecuente);
		lblPasajeroFrecuente.setVisible(false);

		varPFnumero = new JLabel("Numero");
		varPFnumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		varPFnumero.setBounds(30, 431, 45, 14);
		add(varPFnumero);
		varPFnumero.setVisible(false);

		textField_numeroPasaFrec = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_numeroPasaFrec.setColumns(10);
		textField_numeroPasaFrec.setBounds(82, 426, 86, 20);
		add(textField_numeroPasaFrec);
		textField_numeroPasaFrec.setVisible(false);
		textField_numeroPasaFrec.setEditable(false);

		varPFlineaAerea = new JLabel("Linea Aerea");
		varPFlineaAerea.setFont(new Font("Tahoma", Font.BOLD, 11));
		varPFlineaAerea.setBounds(177, 429, 72, 14);
		add(varPFlineaAerea);
		varPFlineaAerea.setVisible(false);

		comboPFlineaAereaNombre = new JComboBox<String>(
				this.clienteManager.getClienteController().getLaContr().getlLineasAereasNombre());
//		comboPFlineaAereaNombre = new JTextField();
		comboPFlineaAereaNombre.setBounds(253, 426, 87, 20);
		add(comboPFlineaAereaNombre);
		comboPFlineaAereaNombre.setVisible(false);
//		comboPFlineaAereaNombre.setEditable(false);

		varPFcategoria = new JLabel("Categoria");
		varPFcategoria.setFont(new Font("Tahoma", Font.BOLD, 11));
		varPFcategoria.setBounds(385, 429, 59, 14);
		add(varPFcategoria);
		varPFcategoria.setVisible(false);

		textField_categoria = new ValidableTextField("^[a-zA-Z0-9\\s]{1,50}$");
		textField_categoria.setColumns(10);
		textField_categoria.setBounds(453, 426, 86, 20);
		add(textField_categoria);
		textField_categoria.setVisible(false);
//		textField_categoria.setEditable(false);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(360, 15, 134, 23);
		add(btnConsultar);
		btnConsultar.addActionListener(this);

		btnLimpiar = new JButton("Cancelar");
		btnLimpiar.setBounds(272, 484, 120, 23);
		add(btnLimpiar);
		btnLimpiar.addActionListener(this.clienteManager.getClienteController());
		btnLimpiar.setVisible(false);

		btnModificar = new JButton("Actualizar");
		btnModificar.setBounds(104, 484, 134, 23);
		add(btnModificar);
		btnModificar.addActionListener(this);
		btnModificar.setVisible(false);

	}

	public void otroModificar() {
		int rta = !(true) ? 0
				: (JOptionPane.showConfirmDialog(null, "Quiere modificar otro Cliente?", "Confirmacion",
						JOptionPane.YES_NO_OPTION));

		if (rta == JOptionPane.YES_OPTION) {

			limpiar();
			visibilidad(false);

		} else {
			textField_cuil.setEditable(false);
		}
	}

	public void limpiarConConfirmacion() {

		int rta = !(true) ? 0
				: (JOptionPane.showConfirmDialog(null, "El formulario sera vaciado. Confirma?", "Confirmacion",
						JOptionPane.YES_NO_OPTION));

		if (rta == JOptionPane.YES_OPTION) {

			limpiar();
			visibilidad(false);

		}

	}

	public void limpiar() {

		// Datos del cliente
		textField_nombre.setText("");
		textField_apellido.setText("");
		textField_DNI.setText("");
		textField_cuil.setText("");
		dateChooser_fechaNac.setCalendar(null);
//		dateChooser_fechaNac.setText("");
		textField_email.setText("");

		// Pasaporte
		textField_nroPasaporte.setText("");
		dateChooser_fechEmision.setCalendar(null);
		dateChooser_vencimiento.setCalendar(null);
//		dateChooser_fechEmision.setText("");
//		dateChooser_vencimiento.setText("");
		comboPPaisesDescrip.setSelectedIndex(0);
//		comboPPaisesDescrip.setText("");
		textField_autEmision.setText("");
		textField_POtroPais.setText("");
		this.textField_PProvinciaDescrip.setText("");
		comboPProvinciasDescrip.setSelectedIndex(0);
//		comboPProvinciasDescrip.setText("");

		// Telefono
		textField_nroPersonal.setText("");
		textField_nroCelular.setText("");
		textField_nroLaboral.setText("");

		// Pasajero frecuente
		textField_numeroPasaFrec.setText("");
		textField_categoria.setText("");
		comboPFlineaAereaNombre.setSelectedIndex(0);
//		comboPFlineaAereaNombre.setText("");

		// Direccion
		textField_calle.setText("");
		textField_altura.setText("");
		textField_ciudad.setText("");
		textField_CP.setText("");
		comboDPaisesDescrip.setSelectedIndex(0);
//		comboDPaisesDescrip.setText("");
		textField_dirOtroPais.setText("");
		textField_DirProvinciaDescrip.setText("");
		comboDirProvinciasDescrip.setSelectedIndex(0);
//		comboDirProvinciasDescrip.setText("");
		textField_DirProvinciaDescrip.setText("");

		textField_cuil.setEditable(true);

		mostrarmensaje("Datos limpiados");

	}

	public void mostrarmensaje(String mensaje) {
		JOptionPane.showConfirmDialog(null, mensaje, "Informacion", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);

	}

	public void visibilidad(boolean visibilidad) {
		lblDatosGenerales.setVisible(visibilidad);
		varDPnombre.setVisible(visibilidad);
		textField_nombre.setVisible(visibilidad);
		varDPapellido.setVisible(visibilidad);
		textField_apellido.setVisible(visibilidad);
		varDPDNI.setVisible(visibilidad);
		textField_DNI.setVisible(visibilidad);
		varDPfechaNacimiento.setVisible(visibilidad);
		dateChooser_fechaNac.setVisible(visibilidad);
		varDPemail.setVisible(visibilidad);
		textField_email.setVisible(visibilidad);
		lblPasaporte.setVisible(visibilidad);
		varPnroPasaporte.setVisible(visibilidad);
		textField_nroPasaporte.setVisible(visibilidad);
		varPfechaEmision.setVisible(visibilidad);
		dateChooser_fechEmision.setVisible(visibilidad);
		varPfechaVecimiento.setVisible(visibilidad);
		dateChooser_vencimiento.setVisible(visibilidad);
		varPpais.setVisible(visibilidad);
		comboPPaisesDescrip.setVisible(visibilidad);
		this.varPOtroPais.setVisible(visibilidad);
		this.textField_POtroPais.setVisible(visibilidad);
		this.varPProvincia.setVisible(visibilidad);
		this.comboPProvinciasDescrip.setVisible(visibilidad);
		this.varPProvinciaEstado.setVisible(visibilidad);
		this.textField_PProvinciaDescrip.setVisible(visibilidad);
		varPautEmision.setVisible(visibilidad);
		textField_autEmision.setVisible(visibilidad);
		lblDatosDeContacto.setVisible(visibilidad);
		varTpersonal.setVisible(visibilidad);
		textField_nroPersonal.setVisible(visibilidad);
		varTcelular.setVisible(visibilidad);
		textField_nroCelular.setVisible(visibilidad);
		varTlaboral.setVisible(visibilidad);
		textField_nroLaboral.setVisible(visibilidad);
		varDcalle.setVisible(visibilidad);
		textField_calle.setVisible(visibilidad);
		varDaltura.setVisible(visibilidad);
		textField_altura.setVisible(visibilidad);
		varDciudad.setVisible(visibilidad);
		textField_ciudad.setVisible(visibilidad);
		varDpais.setVisible(visibilidad);
		this.comboDPaisesDescrip.setVisible(visibilidad);
		this.varDDirOtroPais.setVisible(visibilidad);
		this.textField_dirOtroPais.setVisible(visibilidad);
		this.varDDirProvincia.setVisible(visibilidad);
		this.comboDirProvinciasDescrip.setVisible(visibilidad);
		this.varDDirProvinciaEstado.setVisible(visibilidad);
		this.textField_DirProvinciaDescrip.setVisible(visibilidad);
		varDcp.setVisible(visibilidad);
		textField_CP.setVisible(visibilidad);
		lblPasajeroFrecuente.setVisible(visibilidad);
		varPFnumero.setVisible(visibilidad);
		textField_numeroPasaFrec.setVisible(visibilidad);
		varPFlineaAerea.setVisible(visibilidad);
		comboPFlineaAereaNombre.setVisible(visibilidad);
		varPFcategoria.setVisible(visibilidad);
		textField_categoria.setVisible(visibilidad);
		btnLimpiar.setVisible(visibilidad);
		btnModificar.setVisible(visibilidad);

	}

	public void bordenegro() {

		Border defaultBorder = BorderFactory.createLineBorder(Color.BLACK);

		textField_cuil.setBorder(defaultBorder);
		textField_numeroPasaFrec.setBorder(defaultBorder);
		textField_nroPasaporte.setBorder(defaultBorder);

		textField_nombre.setBorder(defaultBorder);
		textField_apellido.setBorder(defaultBorder);
		textField_DNI.setBorder(defaultBorder);
		dateChooser_fechaNac.setBorder(defaultBorder);
		textField_email.setBorder(defaultBorder);
		dateChooser_fechEmision.setBorder(defaultBorder);
		dateChooser_vencimiento.setBorder(defaultBorder);
		this.textField_POtroPais.setBorder(defaultBorder);
		this.textField_PProvinciaDescrip.setBorder(defaultBorder);
		textField_autEmision.setBorder(defaultBorder);
		textField_nroPersonal.setBorder(defaultBorder);
		textField_nroCelular.setBorder(defaultBorder);
		textField_nroLaboral.setBorder(defaultBorder);
		textField_calle.setBorder(defaultBorder);
		textField_altura.setBorder(defaultBorder);
		textField_ciudad.setBorder(defaultBorder);
		this.textField_dirOtroPais.setBorder(defaultBorder);
		this.textField_DirProvinciaDescrip.setBorder(defaultBorder);
		textField_CP.setBorder(defaultBorder);
		textField_categoria.setBorder(defaultBorder);

	}

	public void llenarDatos(ArrayList<String> lDatos) {
//		System.out.println(lDatos);

		visibilidad(true);
		bordenegro();
		textField_cuil.setEditable(false);

//		textField_nroPasaporte.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		textField_numeroPasaFrec.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		textField_nombre.setText(lDatos.get(0));
		textField_apellido.setText(lDatos.get(1));
		textField_DNI.setText(lDatos.get(2));
		textField_nroPasaporte.setText(lDatos.get(4));
//		comboPPaisesDescrip.setText(lDatos.get(5));
		comboPPaisesDescrip.setSelectedItem(lDatos.get(5));

		if (lDatos.get(5).matches("Argentina")) {
			comboPProvinciasDescrip.setSelectedItem(lDatos.get(6));
//			this.varPProvincia.setVisible(true);
//			this.comboPProvinciasDescrip.setVisible(true);
			this.varPProvinciaEstado.setVisible(false);
			this.textField_PProvinciaDescrip.setVisible(false);
			this.varPOtroPais.setVisible(false);
			this.textField_POtroPais.setVisible(false);
		} else {
			if (lDatos.get(5).matches("Otro")) {
				String[] splittedP = lDatos.get(6).split("[-]", 0);
				textField_POtroPais.setText(splittedP[0]);
				this.textField_PProvinciaDescrip.setText(splittedP[1]);
				System.out.println(splittedP[0] + "-" + splittedP[1]);
//				this.varPOtroPais.setVisible(true);
//				this.textField_POtroPais.setVisible(true);
//				this.varPProvinciaEstado.setVisible(true);
//				this.textField_PProvinciaDescrip.setVisible(true);
				this.varPProvincia.setVisible(false);
				this.comboPProvinciasDescrip.setVisible(false);
			} else {
				this.textField_PProvinciaDescrip.setText(lDatos.get(6));
				this.varPProvincia.setVisible(false);
				this.comboPProvinciasDescrip.setVisible(false);
				this.varPOtroPais.setVisible(false);
				this.textField_POtroPais.setVisible(false);
//				this.varPProvinciaEstado.setVisible(true);
//				this.textField_PProvinciaDescrip.setVisible(true);
			}
		}

		textField_autEmision.setText(lDatos.get(7));

		try {
			fechEmision = new SimpleDateFormat("dd/MM/yyyy").parse(lDatos.get(8));
			vencimiento = new SimpleDateFormat("dd/MM/yyyy").parse(lDatos.get(9));
			fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(lDatos.get(10));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateChooser_fechEmision.setDate(fechEmision);
		dateChooser_vencimiento.setDate(vencimiento);
		dateChooser_fechaNac.setDate(fechaNac);

		textField_email.setText(lDatos.get(11));
		textField_calle.setText(lDatos.get(12));
		textField_altura.setText(lDatos.get(13));
		textField_ciudad.setText(lDatos.get(14));
//		comboDPaisesDescrip.setText(lDatos.get(15));		
		comboDPaisesDescrip.setSelectedItem(lDatos.get(15));

		if (lDatos.get(15).matches("Argentina")) {
			comboDirProvinciasDescrip.setSelectedItem(lDatos.get(16));
			this.varDDirProvinciaEstado.setVisible(false);
			this.textField_DirProvinciaDescrip.setVisible(false);
			this.varDDirOtroPais.setVisible(false);
			this.textField_dirOtroPais.setVisible(false);
//			this.varDDirProvincia.setVisible(true);
//			this.comboDirProvinciasDescrip.setVisible(true);
		} else {
			if (lDatos.get(15).matches("Otro")) {
				String[] splittedD = lDatos.get(16).split("[-]", 0);
				textField_dirOtroPais.setText(splittedD[0]);
				textField_DirProvinciaDescrip.setText(splittedD[1]);
				this.varDDirProvincia.setVisible(false);
				this.comboDirProvinciasDescrip.setVisible(false);
//				this.varDDirOtroPais.setVisible(true);
//				this.textField_dirOtroPais.setVisible(true);
//				this.varDDirProvinciaEstado.setVisible(true);
//				this.textField_DirProvinciaDescrip.setVisible(true);
			} else {
				textField_DirProvinciaDescrip.setText(lDatos.get(16));
				this.varDDirProvincia.setVisible(false);
				this.comboDirProvinciasDescrip.setVisible(false);
				this.varDDirOtroPais.setVisible(false);
				this.textField_dirOtroPais.setVisible(false);
//				this.varDDirProvinciaEstado.setVisible(true);
//				this.textField_DirProvinciaDescrip.setVisible(true);
			}
		}

		textField_CP.setText(lDatos.get(17));
		textField_categoria.setText(lDatos.get(18));
		textField_numeroPasaFrec.setText(lDatos.get(19));
//		comboPFlineaAereaNombre.setText(lDatos.get(20));
		comboPFlineaAereaNombre.setSelectedItem(lDatos.get(20));
		textField_nroPersonal.setText(lDatos.get(21));
		textField_nroCelular.setText(lDatos.get(22));
		textField_nroLaboral.setText(lDatos.get(23));

	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (e.getSource() == this.comboDPaisesDescrip) {

			if (((String) this.comboDPaisesDescrip.getSelectedItem()).matches("Otro")) {
				// Pais = OTRO
				this.varDDirOtroPais.setVisible(true);
				this.textField_dirOtroPais.setVisible(true);
				// Pais = Argentina
				this.varDDirProvincia.setVisible(false);
				this.comboDirProvinciasDescrip.setVisible(false);
				// Pais = Cualquiera
				this.varDDirProvinciaEstado.setVisible(true);
				this.textField_DirProvinciaDescrip.setVisible(true);

			} else {

				if (((String) this.comboDPaisesDescrip.getSelectedItem()).matches("Argentina")) {
					// Pais = Argentina
					this.varDDirProvincia.setVisible(true);
					this.comboDirProvinciasDescrip.setVisible(true);
					// Pais = Cualquiera
					this.varDDirProvinciaEstado.setVisible(false);
					this.textField_DirProvinciaDescrip.setVisible(false);
					// Pais = OTRO
					this.varDDirOtroPais.setVisible(false);
					this.textField_dirOtroPais.setVisible(false);

				} else {
					// Pais = Argentina
					this.varDDirProvincia.setVisible(false);
					this.comboDirProvinciasDescrip.setVisible(false);
					// Pais = Cualquiera
					this.varDDirProvinciaEstado.setVisible(true);
					this.textField_DirProvinciaDescrip.setVisible(true);
					// Pais = OTRO
					this.varDDirOtroPais.setVisible(false);
					this.textField_dirOtroPais.setVisible(false);
				}

			}
		}

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

	@Override
	public void actionPerformed(ActionEvent action) {

		// 1 x btnConsultar

		if (action.getSource() == btnConsultar) {

			if (this.getTextField_cuil().getText().length() > 0) {
				if (((LineBorder) (textField_cuil.getBorder())).getLineColor() == Color.BLACK) {
					clienteManager.consultaClienteUnificado("SQL", getTextField_cuil().getText(), this);

				} else {
					cartelErrorIngresoDatos("CUIL: 11 digitos, que empiece con 20/23/27");
				}
			} else {
				cartelErrorIngresoDatos("CUIL: Campo obligatorio");
			}

		}

		if (action.getSource() == btnModificar) {

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

																																											this.clienteManager
																																													.modificarClienteUnificado(
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
											cartelErrorIngresoDatos("CUIL: 11 digitos");
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

	public void otraConsulta() {
		int rta = !(true) ? 0
				: (JOptionPane.showConfirmDialog(null, "Quiere hacer otra consulta?", "Confirmacion",
						JOptionPane.YES_NO_OPTION));

		if (rta == JOptionPane.YES_OPTION) {

			limpiar();
			visibilidad(false);
		}
	}

	public JTextField getTextField_cuil() {
		return textField_cuil;
	}

	public void setTextField_cuil(JTextField textField_cuil) {
		this.textField_cuil = textField_cuil;
	}

}
