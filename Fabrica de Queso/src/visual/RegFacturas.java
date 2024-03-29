package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;


import logico.Cilindro;
import logico.CilindroHueco;
import logico.Cliente;
import logico.Empresa;
import logico.Esferico;
import logico.Factura;
import logico.Queso;
import server.Server;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class RegFacturas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_id;
	private JTextField txt_name;
	private JTextField txt_address;
	private JList list_main;
	private JList list_second;
	DefaultListModel<String> dbQuesos = new DefaultListModel<>();
	DefaultListModel<String> quesosEnCarrito = new DefaultListModel<String>();
	private JTextField total;
	private JLabel lblTotal;
	private Cliente users= null;
	private JFormattedTextField ftxtTel;
	private String totalito;
	private static Socket soc;
	private static DataOutputStream salida;

	
	private MaskFormatter mascaraTel() {
		MaskFormatter mask = new MaskFormatter();
		try {
			mask = new MaskFormatter("(###)-###-####");
			mask.setPlaceholderCharacter('_');
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mask;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegFacturas dialog = new RegFacturas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the dialog.
	 */
	public RegFacturas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegFacturas.class.getResource("/recursos/expediente.png")));
		setResizable(false);
		setTitle("Facturaci\u00F3n");
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(211, 211, 211));
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informacion de Factura", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblId = new JLabel("ID: ");
				lblId.setBounds(10, 30, 46, 14);
				panel.add(lblId);
			}
			{
				txt_id = new JTextField();
				txt_id.setBounds(69, 28, 133, 20);
				panel.add(txt_id);
				txt_id.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Nombre:");
				lblNewLabel.setBounds(10, 62, 68, 14);
				panel.add(lblNewLabel);
			}
			{
				txt_name = new JTextField();
				txt_name.setBounds(69, 58, 226, 20);
				panel.add(txt_name);
				txt_name.setColumns(10);
			}
			{
				JLabel lblDireccion = new JLabel("Direccion: ");
				lblDireccion.setBounds(10, 90, 68, 14);
				panel.add(lblDireccion);
			}
			{
				txt_address = new JTextField();
				txt_address.setBounds(69, 86, 226, 20);
				panel.add(txt_address);
				txt_address.setColumns(10);
			}
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (txt_id.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Se necesita un ID para buscar el cliente", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						for (Cliente user : Empresa.getInstance().getUser()) {
							if (txt_id.getText().equalsIgnoreCase(user.getId_cliente())) {
								JOptionPane.showMessageDialog(null, "Cliente existente", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
								txt_id.setEnabled(false);
								txt_name.setEnabled(false);
								txt_name.setText(user.getName());
								txt_address.setEnabled(false);
								txt_address.setText(user.getAddress());
								ftxtTel.setEnabled(false);
								ftxtTel.setText(user.getPhone());
								users = user;
							}
							if(!txt_id.getText().equalsIgnoreCase(user.getId_cliente()) && user==null){
								JOptionPane.showMessageDialog(null, "El cliente no existe. Registrelo", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
								RegCliente newUser = new RegCliente();
								newUser.setModal(true);
								newUser.setVisible(true);
							}

						}
					}
				}
			});
			btnBuscar.setBounds(206, 26, 89, 23);
			panel.add(btnBuscar);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(10, 121, 54, 14);
			panel.add(lblTelefono);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(211, 211, 211));
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Quesos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			panel_1.setBounds(10, 146, 604, 253);
			panel.add(panel_1);
			panel_1.setLayout(null);
			{
				list_main = new JList<>(dbQuesos);
				list_main.setBounds(29, 36, 173, 183);
				panel_1.add(list_main);
				updateQueso();
			
			}
			{
				list_second = new JList<>(quesosEnCarrito);
				list_second.setBounds(401, 36, 173, 183);
				list_second.setSelectedIndex(0);
				panel_1.add(list_second);
			}
			JLabel lblInventarioQuesos = new JLabel("Inventario Quesos:");
			lblInventarioQuesos.setBounds(63, 11, 122, 14);
			panel_1.add(lblInventarioQuesos);
			{
				JButton btn_add = new JButton("--->");
				btn_add.addActionListener(new ActionListener() {
					@SuppressWarnings("unchecked")
					public void actionPerformed(ActionEvent e) {
						DefaultListModel<String> db = ((DefaultListModel<String>)list_main.getModel());
						DefaultListModel<String> carrito = ((DefaultListModel<String>)list_second.getModel());
						for (int item: list_main.getSelectedIndices()) {
							carrito.addElement(db.getElementAt(item));
							db.removeElement(db.getElementAt(item));
							float aux = 0;
							for (int i = 0; i < carrito.getSize(); i++) {
								String s = (String) carrito.getElementAt(i);
								String[] separador = s.split(":", 2);
								String second_p = separador[1];
								aux =aux+ Float.parseFloat(second_p);
								total.setText(String.valueOf(aux));
								totalito = String.valueOf(aux);
							}
						}
					}
				});
				btn_add.setBounds(244, 64, 89, 36);
				panel_1.add(btn_add);
			}
			{
				JButton btn_delete = new JButton("<---");
				btn_delete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						@SuppressWarnings("unchecked")
						DefaultListModel<String> db = ((DefaultListModel<String>)list_main.getModel());
						@SuppressWarnings("unchecked")
						DefaultListModel<String> carrito = ((DefaultListModel<String>)list_second.getModel());
						
						for (int item: list_second.getSelectedIndices()) {
							db.addElement(carrito.getElementAt(item));
							carrito.removeElement(carrito.getElementAt(item));
							float aux = 0;
							for (int i = 0; i < carrito.getSize(); i++) {
								String s = (String) carrito.getElementAt(i);
								String[] separador = s.split(":", 2);
								String second_p = separador[1];
								aux = aux-Float.parseFloat(second_p);
								total.setText(String.valueOf(Math.abs(aux)));
								totalito = String.valueOf(Math.abs(aux));
														
							}
						}
						if (list_second.getModel().getSize()==0) {
							total.setText("");
						}
						
					}
				});
				btn_delete.setBounds(244, 129, 89, 36);
				panel_1.add(btn_delete);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Carrito:");
				lblNewLabel_1.setBounds(464, 11, 46, 14);
				panel_1.add(lblNewLabel_1);
			}
			
			lblTotal= new JLabel("Total: ");
			lblTotal.setBounds(401, 227, 46, 14);
			panel_1.add(lblTotal);
			
			total = new JTextField();
			total.setEditable(false);
			total.setBounds(461, 225, 113, 20);
			panel_1.add(total);
			total.setColumns(10);
			
			ftxtTel = new JFormattedTextField(mascaraTel());
			ftxtTel.setBounds(69, 119, 226, 19);
			panel.add(ftxtTel);
			{
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(RegFacturas.class.getResource("/recursos/queso128.png")));
				label.setBounds(481, 13, 133, 125);
				panel.add(label);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(211, 211, 211));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("unchecked")
					public void actionPerformed(ActionEvent e) {
						
						if (list_second.getModel().getSize()==0 && users==null) {
							JOptionPane.showMessageDialog(null, "No se puede imprimir una factura sin datos", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							DefaultListModel<String> carrito = ((DefaultListModel<String>)list_second.getModel());
							ArrayList<Queso> items = new ArrayList<Queso>();
							for (int i = 0; i < carrito.getSize(); i++) {
								String s = (String) carrito.getElementAt(i);
								String[] separador1 = s.split(",", 2);
								String codigo = separador1[0];
								items.add(Empresa.getInstance().quesoByCode(codigo));
								Empresa.getInstance().deleteQueso(codigo);
								updateQueso();
								
								
								
							}
							Factura factToAdd = new Factura(users, items, "1");
							Empresa.getInstance().addFactura(factToAdd);
							convertirFactura(factToAdd);
							try {
								enviarFact(factToAdd);
							} catch (Exception e2) {
								e2.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Compra realizada", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
							
						}
					}

					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}


	public void convertirFactura(Factura factToAdd) {
		File invoiceFile = new File (factToAdd.getCod_fact()+".txt");
		FileWriter escritor;
		BufferedWriter bufferWrite;
		ArrayList<String> message = new ArrayList<String>();
		try {
			escritor = new FileWriter(invoiceFile); 
			bufferWrite = new BufferedWriter(escritor); 
			String separador = System.getProperty("line.separator");
			message.add(separador+"*-------------------- Fabrica de Queso --------------------*");
			message.add(separador+"* Cliente: "+factToAdd.getP().getName()+"                               *");
			message.add(separador+"*---------------------------------------------------------*");
			message.add(separador+"*----------------------- Quesos-----------------------*");
			message.add(separador+"*---------------------------------------------------------*");
			message.add(separador+"*  Descripcion           Cant.               Precio       *\n");
			for (Queso cheese : factToAdd.getItem()) {
				message.add("*  " +cheese.getId()+ cheese.getTipo() + String.valueOf(cheese.costoTotal()) + "\n");	
			}
			message.add("*---------------------------------------------------------*\n");
			message.add("*TOTAL A PAGAR: RD$: "+ totalito +"\n");

			
			for (String text : message) {
				bufferWrite.write(text);		
			}

			bufferWrite.flush();
			bufferWrite.close();
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
public void enviarFact(Factura factToSend) throws IOException {
		File factura = new File (factToSend.getCod_fact()+".txt");
		salida.writeUTF(factToSend.getCod_fact());
		salida.flush();
		sendFile(factura);
		factura.delete();
	} 
	
public void sendFile(File file) throws IOException {
	FileInputStream fileIn = new FileInputStream(file);
	// convierte el fichero en bytes.
	byte[] buf = new byte[Short.MAX_VALUE];
	int bytesRead;        
	while( (bytesRead = fileIn.read(buf)) != -1 ) {
		salida.writeShort(bytesRead);
		salida.write(buf,0,bytesRead);

	}
	salida.writeShort(-1);
	fileIn.close();
}

	protected void updateQueso() {
		dbQuesos.clear();
		for (Queso  aux : Empresa.getInstance().getCheese()) {
			if (aux instanceof Esferico) {
				dbQuesos.addElement(aux.getId()+", Esferico :"+aux.costoTotal());
			}
			if (aux instanceof Cilindro && !(aux instanceof CilindroHueco)) {
				dbQuesos.addElement(aux.getId()+", Cilindrico :"+aux.costoTotal());
				
			}
			if (aux instanceof CilindroHueco) {
				dbQuesos.addElement(aux.getId()+", CilindricoHueeco :"+aux.costoTotal());
			}
		}
		
	}
}
