package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Empresa;
import server.Server;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Principal extends JFrame {

//	private static Server s;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
//					s = new Server("Ejecutando el Backend");
//					s.start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param emp 
	 */
	public Principal() {
		setTitle("Fabrica de Quesos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(211, 211, 211));
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		mnCliente.setIcon(new ImageIcon(Principal.class.getResource("/recursos/clientes.png")));
		menuBar.add(mnCliente);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.setIcon(new ImageIcon(Principal.class.getResource("/recursos/archivo32.png")));
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegCliente reg = new RegCliente();
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnCliente.add(mntmNuevo);
		
		JMenu mnFacturas = new JMenu("Facturas");
		mnFacturas.setIcon(new ImageIcon(Principal.class.getResource("/recursos/fact32.png")));
		menuBar.add(mnFacturas);
		
		JMenuItem mntmNuevo_1 = new JMenuItem("Nuevo");
		mntmNuevo_1.setIcon(new ImageIcon(Principal.class.getResource("/recursos/archivo32.png")));
		mntmNuevo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegFacturas factura = new RegFacturas();
				factura.setModal(true);
				factura.setVisible(true);
			}
		});
		mnFacturas.add(mntmNuevo_1);
		
		JMenu mnQuesos = new JMenu("Quesos");
		mnQuesos.setIcon(new ImageIcon(Principal.class.getResource("/recursos/queso32.png")));
		menuBar.add(mnQuesos);
		
		JMenuItem mntmNuevo_2 = new JMenuItem("Nuevo");
		mntmNuevo_2.setIcon(new ImageIcon(Principal.class.getResource("/recursos/archivo32.png")));
		mntmNuevo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegQueso factura = new RegQueso();
				factura.setModal(true);
				factura.setVisible(true);
			}
		});
		mnQuesos.add(mntmNuevo_2);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
