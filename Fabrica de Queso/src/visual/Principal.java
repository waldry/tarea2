package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Empresa;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegCliente reg = new RegCliente();
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnCliente.add(mntmNuevo);
		
		JMenu mnFacturas = new JMenu("Facturas");
		mnFacturas.setIcon(new ImageIcon(Principal.class.getResource("/visual/fact.png")));
		menuBar.add(mnFacturas);
		
		JMenuItem mntmNuevo_1 = new JMenuItem("Nuevo");
		mntmNuevo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegFacturas factura = new RegFacturas();
				factura.setModal(true);
				factura.setVisible(true);
			}
		});
		mnFacturas.add(mntmNuevo_1);
		
		JMenu mnQuesos = new JMenu("Quesos");
		mnQuesos.setIcon(new ImageIcon(Principal.class.getResource("/visual/queso.png")));
		menuBar.add(mnQuesos);
		
		JMenuItem mntmNuevo_2 = new JMenuItem("Nuevo");
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
