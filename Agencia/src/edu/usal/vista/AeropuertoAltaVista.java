package edu.usal.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.usal.controller.AeropuertoController;

public class AeropuertoAltaVista extends JFrame {

	private static final long serialVersionUID = 1L;

	public JPanel contentPane;

	public JTextField txtIngresarId;

	public JTextField txtIngresarCiudad;

	public JComboBox<String> comboPaisesDescrip;

	public JLabel lblProvincia;
	public JComboBox<String> comboProvinciasDescrip;

	public JButton btnDarDeAlta;
	public JButton btnImprimir;

	public JTextField mostrarResultado;

	public AeropuertoAltaVista(AeropuertoController aeropContr) {
		setTitle("Alta de Aeropuerto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtIngresarId = new JTextField();
		txtIngresarId.setText("Ingresar ID");
		txtIngresarId.setBounds(181, 36, 86, 20);
		contentPane.add(txtIngresarId);
		txtIngresarId.setColumns(10);

		JLabel lblIdAeropuerto = new JLabel("ID Aeropuerto:");
		lblIdAeropuerto.setBounds(25, 39, 114, 14);
		contentPane.add(lblIdAeropuerto);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(25, 73, 46, 14);
		contentPane.add(lblCiudad);

		txtIngresarCiudad = new JTextField();
		txtIngresarCiudad.setText("Ingresar Ciudad");
		txtIngresarCiudad.setBounds(181, 67, 86, 20);
		contentPane.add(txtIngresarCiudad);
		txtIngresarCiudad.setColumns(10);

		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(25, 112, 46, 14);
		contentPane.add(lblPais);

		this.comboPaisesDescrip = new JComboBox<String>(aeropContr.getPaisController().getlPaisesDescrip());
		this.comboPaisesDescrip.setBounds(181, 109, 68, 20);
		contentPane.add(this.comboPaisesDescrip);
		this.comboPaisesDescrip.addItemListener(aeropContr);

		lblProvincia = new JLabel("Provincia:");
		lblProvincia.setBounds(25, 148, 68, 14);
		contentPane.add(lblProvincia);
		this.lblProvincia.setVisible(false);

		this.comboProvinciasDescrip = new JComboBox<String>(aeropContr.getProvController().getlProvinciasDescrip());
		this.comboProvinciasDescrip.setBounds(181, 140, 68, 20);
		contentPane.add(this.comboProvinciasDescrip);
		this.comboProvinciasDescrip.setVisible(false);

		this.btnDarDeAlta = new JButton("Dar de Alta");
		this.btnDarDeAlta.setBounds(118, 190, 89, 23);
		contentPane.add(this.btnDarDeAlta);
		this.btnDarDeAlta.addActionListener(aeropContr);

		this.btnImprimir = new JButton("Imprimir");
		this.btnImprimir.setBounds(217, 190, 89, 23);
		contentPane.add(this.btnImprimir);
		this.btnImprimir.addActionListener(aeropContr);

		mostrarResultado = new JTextField();
		mostrarResultado.setBounds(25, 230, 244, 20);
		contentPane.add(mostrarResultado);
		mostrarResultado.setColumns(10);

	}

}
