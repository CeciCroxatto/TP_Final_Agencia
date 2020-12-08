package edu.usal.vista;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import edu.usal.manager.ClienteManager;

public class MenuVista implements ActionListener {

	private JFrame frame;
	private JMenuBar menubar;
	private JMenu menuCliente;
	private JMenuItem menuItemCCliente;
	private JMenuItem menuItemDCliente;
	private JMenuItem menuItemRCliente;
	private JMenuItem menuItemUCliente;

	static JPanel panelPivot;
	private JPanel altaCliente;
	private JPanel bajaCliente;
	private JPanel consultaCliente;
	private JPanel modificacionCliente;

	ClienteManager clienteManager;

	public MenuVista() {

		frame = new JFrame();
		frame.setTitle("Agencia");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		menuCliente = new JMenu("Cliente");
		menubar.add(menuCliente);

		menuItemCCliente = new JMenuItem("Alta Clientes");
		menuCliente.add(menuItemCCliente);
		menuItemCCliente.addActionListener(this);

		menuItemDCliente = new JMenuItem("Baja Clientes");
		menuCliente.add(menuItemDCliente);
		menuItemDCliente.addActionListener(this);

		menuItemRCliente = new JMenuItem("Consulta Clientes");
		menuCliente.add(menuItemRCliente);
		menuItemRCliente.addActionListener(this);

		menuItemUCliente = new JMenuItem("Modificacion Clientes");
		menuCliente.add(menuItemUCliente);
		menuItemUCliente.addActionListener(this);

		panelPivot = new JPanel();
		panelPivot.setBounds(0, 0, 900, 550);
		frame.getContentPane().add(panelPivot);
		panelPivot.setLayout(new CardLayout(0, 0));

		this.clienteManager = new ClienteManager();
		clienteManager.getClienteController().cargarPaises();
		clienteManager.getClienteController().cargarProvincias();
		clienteManager.getClienteController().cargarLineasAereas("SQL");
		clienteManager.getClienteController().crearVistas(clienteManager);

		altaCliente = clienteManager.getClienteController().getClCVista();
		panelPivot.add(altaCliente);
		panelPivot.setVisible(true);
		panelPivot.validate();

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menuItemCCliente) {
			clienteManager.getClienteController().crearVistaC(clienteManager);
			altaCliente = clienteManager.getClienteController().getClCVista();
			panelPivot.removeAll();
			panelPivot.add(altaCliente);
			panelPivot.setVisible(true);
			panelPivot.validate();
		}

		if (e.getSource() == menuItemRCliente) {
			clienteManager.getClienteController().crearVistaR(clienteManager);
			consultaCliente = clienteManager.getClienteController().getClRVista();
			panelPivot.removeAll();
			panelPivot.add(consultaCliente);
			panelPivot.setVisible(true);
			panelPivot.validate();
		}

		if (e.getSource() == menuItemUCliente) {
			clienteManager.getClienteController().crearVistaU(clienteManager);
			modificacionCliente = clienteManager.getClienteController().getClUVista();
			panelPivot.removeAll();
			panelPivot.add(modificacionCliente);
			panelPivot.setVisible(true);
			panelPivot.validate();
		}

		if (e.getSource() == menuItemDCliente) {
			clienteManager.getClienteController().crearVistaD(clienteManager);
			bajaCliente = clienteManager.getClienteController().getClDVista();
			panelPivot.removeAll();
			panelPivot.add(bajaCliente);
			panelPivot.setVisible(true);
			panelPivot.validate();
		}

	}

}
