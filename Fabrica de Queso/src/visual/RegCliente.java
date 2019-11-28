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
import javax.swing.text.MaskFormatter;

import logico.Cliente;
import logico.Empresa;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_id;
	private JTextField txt_name;
	private JTextField txt_address;
	private JFormattedTextField ftxtTel;

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
		setBackground(new Color(211, 211, 211));
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegCliente.class.getResource("/recursos/clientes.png")));
		setResizable(false);
		setTitle("Registro de Cliente");
		setBounds(100, 100, 323, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(211, 211, 211));
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registro Nuevo Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblId = new JLabel("ID: ");
			lblId.setBounds(10, 19, 46, 14);
			panel.add(lblId);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 52, 58, 14);
			panel.add(lblNombre);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(10, 85, 74, 14);
			panel.add(lblDireccion);
			
			JLabel lblTelefono = new JLabel("Telefono: ");
			lblTelefono.setBounds(10, 118, 58, 14);
			panel.add(lblTelefono);
			
			txt_id = new JTextField();
			txt_id.setEditable(false);
			txt_id.setBounds(75, 17, 86, 20);
			panel.add(txt_id);
			txt_id.setColumns(10);
			txt_id.setText(String.valueOf(Empresa.getInstance().getGen_user()));
			
			txt_name = new JTextField();
			txt_name.setBounds(75, 50, 214, 20);
			panel.add(txt_name);
			txt_name.setColumns(10);
			
			txt_address = new JTextField();
			txt_address.setBounds(75, 83, 214, 20);
			panel.add(txt_address);
			txt_address.setColumns(10);
			
			ftxtTel = new JFormattedTextField(mascaraTel());
			ftxtTel.setBounds(75, 116, 136, 19);
			panel.add(ftxtTel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(211, 211, 211));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (txt_id.getText().isEmpty()  || txt_name.getText().isEmpty() || ftxtTel.getText().equals("(___)-___-____") || txt_address.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Fallo en la operacion, faltan datos por completar.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							Cliente clienteToAdd = new Cliente(txt_name.getText(),txt_address.getText(),ftxtTel.getText(),txt_id.getText());
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
				JButton cancelButton = new JButton("Cancelar");
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
		ftxtTel.setText("");
		txt_address.setText("");
		
	}
}
