package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.Empresa;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_id;
	private JTextField txt_name;
	private JTextField txt_address;
	private JTextField txt_phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCliente dialog = new RegCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegCliente() {
		setTitle("Cliente");
		setBounds(100, 100, 200, 233);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Registro Nuevo Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblId = new JLabel("ID: ");
			lblId.setBounds(10, 19, 46, 14);
			panel.add(lblId);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 52, 46, 14);
			panel.add(lblNombre);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(10, 85, 74, 14);
			panel.add(lblDireccion);
			
			JLabel lblTelefono = new JLabel("Telefono: ");
			lblTelefono.setBounds(10, 118, 74, 14);
			panel.add(lblTelefono);
			
			txt_id = new JTextField();
			txt_id.setEditable(false);
			txt_id.setBounds(65, 16, 86, 20);
			panel.add(txt_id);
			txt_id.setColumns(10);
			txt_id.setText(String.valueOf(Empresa.getInstance().getGen_user()));
			
			txt_name = new JTextField();
			txt_name.setBounds(65, 49, 86, 20);
			panel.add(txt_name);
			txt_name.setColumns(10);
			
			txt_address = new JTextField();
			txt_address.setBounds(65, 82, 86, 20);
			panel.add(txt_address);
			txt_address.setColumns(10);
			
			txt_phone = new JTextField();
			txt_phone.setBounds(65, 115, 86, 20);
			panel.add(txt_phone);
			txt_phone.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (txt_id.getText().isEmpty()  || txt_name.getText().isEmpty() || txt_phone.getText().isEmpty() || txt_address.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Fallo en la operacion, faltan datos por completar.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							Cliente clienteToAdd = new Cliente(txt_name.getText(),txt_address.getText(),txt_phone.getText(),txt_id.getText());
							Empresa.getInstance().addCliente(clienteToAdd);
							JOptionPane.showMessageDialog(null, "Cliente: "+txt_name.getText()+" agregado satisfactoriamente.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
							clear();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void clear() {
		txt_id.setText(String.valueOf(Empresa.getInstance().getGen_user()));;
		txt_name.setText("");
		txt_phone.setText("");
		txt_address.setText("");
		
	}
}
