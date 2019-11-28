package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cilindro;
import logico.CilindroHueco;
import logico.Empresa;
import logico.Esferico;
import logico.Queso;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class RegQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_id;
	private JTextField txt_p_base;
	private JTextField txt_p_unitario;
	private JRadioButton rdbtnEsferico;
	private JRadioButton rdbtnCilindrico;
	private JRadioButton rdbtnCilindroHueco;
	private JPanel panel_esferico;
	private JPanel panel_cilindrico;
	private JPanel panel_cilindricoHueco;
	private JSpinner spn_radio_esferico;
	private JSpinner spn_radio_cilindrico;
	private JSpinner spn_largo_cilindrico;
	private JSpinner spn_largo_cilindricoHueco;
	private JSpinner spn_radioext_cilindricoHueco;
	private JSpinner spn_radioint_cilindricoHueco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegQueso dialog = new RegQueso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegQueso() {
		setResizable(false);
		setTitle("Registro de Queso");
		setBounds(100, 100, 553, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Queso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 517, 70);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tipo de Queso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 92, 517, 65);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		
		rdbtnEsferico = new JRadioButton("Esferico");
		rdbtnEsferico.setSelected(true);
		rdbtnEsferico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnEsferico.setSelected(true);
				rdbtnCilindrico.setSelected(false);
				rdbtnCilindroHueco.setSelected(false);
				panel_cilindrico.setVisible(false);
				panel_cilindricoHueco.setVisible(false);
				panel_esferico.setVisible(true);
				
			}
		});
		rdbtnEsferico.setSelected(true);
		rdbtnEsferico.setBounds(47, 23, 109, 23);
		panel_1.add(rdbtnEsferico);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 28, 46, 14);
		panel.add(lblId);
		
		JLabel lblNewLabel = new JLabel("Precio Base: ");
		lblNewLabel.setBounds(157, 28, 86, 14);
		panel.add(lblNewLabel);
		
		JLabel lblPrecioUnitario = new JLabel("Precio Unitario:");
		lblPrecioUnitario.setBounds(334, 28, 136, 14);
		panel.add(lblPrecioUnitario);
		
		txt_id = new JTextField();
		txt_id.setEditable(false);
		txt_id.setBounds(35, 25, 86, 20);
		panel.add(txt_id);
		txt_id.setColumns(10);
		txt_id.setText(String.valueOf(Empresa.getInstance().getGen_cheese()));
		
		txt_p_base = new JTextField();
		txt_p_base.setBounds(238, 25, 86, 20);
		panel.add(txt_p_base);
		txt_p_base.setColumns(10);
		
		txt_p_unitario = new JTextField();
		txt_p_unitario.setBounds(421, 25, 86, 20);
		panel.add(txt_p_unitario);
		txt_p_unitario.setColumns(10);
		
		
		
		
		rdbtnCilindrico = new JRadioButton("Cilindrico");
		rdbtnCilindrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCilindrico.setSelected(true);
				rdbtnEsferico.setSelected(false);
				rdbtnCilindroHueco.setSelected(false);
				panel_esferico.setVisible(false);
				panel_cilindrico.setVisible(true);
				panel_cilindricoHueco.setVisible(false);
			}
		});
		rdbtnCilindrico.setBounds(203, 23, 109, 23);
		panel_1.add(rdbtnCilindrico);
		
		rdbtnCilindroHueco = new JRadioButton("CilindricoHueco");
		rdbtnCilindroHueco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCilindroHueco.setSelected(true);
				rdbtnCilindrico.setSelected(false);
				rdbtnEsferico.setSelected(false);
				panel_esferico.setVisible(false);
				panel_cilindrico.setVisible(false);
				panel_cilindricoHueco.setVisible(true);
			}
		});
		rdbtnCilindroHueco.setBounds(359, 23, 109, 23);
		panel_1.add(rdbtnCilindroHueco);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEsferico);
		group.add(rdbtnCilindrico);
		group.add(rdbtnCilindroHueco);
		
		panel_esferico = new JPanel();
		panel_esferico.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion Especifica - Esferico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_esferico.setBounds(10, 162, 517, 66);
		contentPanel.add(panel_esferico);
		
		panel_cilindrico = new JPanel();
		panel_cilindrico.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion Especifica - Cilindrico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_cilindrico.setBounds(10, 162, 517, 66);
		contentPanel.add(panel_cilindrico);
		panel_cilindrico.setLayout(null);
		panel_cilindrico.setVisible(false);
		
		panel_cilindricoHueco = new JPanel();
		panel_cilindricoHueco.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion Especifica - CilindricoHueco", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_cilindricoHueco.setBounds(10, 162, 517, 66);
		contentPanel.add(panel_cilindricoHueco);
		panel_cilindricoHueco.setLayout(null);
		panel_cilindricoHueco.setVisible(false);
		
		JLabel lblRadio_1 = new JLabel("Radio Interno: ");
		lblRadio_1.setBounds(10, 28, 105, 14);
		panel_cilindricoHueco.add(lblRadio_1);
		
		spn_radioint_cilindricoHueco = new JSpinner();
		spn_radioint_cilindricoHueco.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spn_radioint_cilindricoHueco.setBounds(96, 25, 71, 20);
		panel_cilindricoHueco.add(spn_radioint_cilindricoHueco);
		
		JLabel lblRadioexterno = new JLabel("Radio externo:");
		lblRadioexterno.setBounds(198, 28, 105, 14);
		panel_cilindricoHueco.add(lblRadioexterno);
		
		spn_radioext_cilindricoHueco = new JSpinner();
		spn_radioext_cilindricoHueco.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spn_radioext_cilindricoHueco.setBounds(284, 25, 71, 20);
		panel_cilindricoHueco.add(spn_radioext_cilindricoHueco);
		
		JLabel lblLargo_1 = new JLabel("Largo: ");
		lblLargo_1.setBounds(377, 28, 46, 14);
		panel_cilindricoHueco.add(lblLargo_1);
		
		spn_largo_cilindricoHueco = new JSpinner();
		spn_largo_cilindricoHueco.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spn_largo_cilindricoHueco.setBounds(414, 25, 71, 20);
		panel_cilindricoHueco.add(spn_largo_cilindricoHueco);
		
		JLabel lblNewLabel_1 = new JLabel("Radio: ");
		lblNewLabel_1.setBounds(10, 28, 46, 14);
		panel_cilindrico.add(lblNewLabel_1);
		
		spn_radio_cilindrico = new JSpinner();
		spn_radio_cilindrico.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spn_radio_cilindrico.setBounds(47, 25, 73, 20);
		panel_cilindrico.add(spn_radio_cilindrico);
		
		JLabel lblLargo = new JLabel("Largo: ");
		lblLargo.setBounds(165, 28, 46, 14);
		panel_cilindrico.add(lblLargo);
		
		spn_largo_cilindrico = new JSpinner();
		spn_largo_cilindrico.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spn_largo_cilindrico.setBounds(204, 25, 73, 20);
		panel_cilindrico.add(spn_largo_cilindrico);
		panel_esferico.setLayout(null);
		
		spn_radio_esferico = new JSpinner();
		spn_radio_esferico.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spn_radio_esferico.setBounds(48, 22, 75, 20);
		panel_esferico.add(spn_radio_esferico);
		
		JLabel lblRadio = new JLabel("Radio:");
		lblRadio.setBounds(8, 25, 46, 14);
		panel_esferico.add(lblRadio);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						float p_base = 0, p_unitario = 0, radio_interno = 0, radio_externo = 0, largo = 0, radio_cilindrico = 0, largo_cilindrico = 0, radio_esferico = 0;
						txt_id.setText(String.valueOf(Empresa.getInstance().getGen_user()));
						p_base = Float.parseFloat(txt_p_base.getText());
						p_unitario = Float.parseFloat(txt_p_unitario.getText());
						radio_cilindrico = Float.parseFloat(spn_radio_cilindrico.getValue().toString());
						largo_cilindrico = Float.parseFloat(spn_largo_cilindrico.getValue().toString());
						radio_interno = Float.parseFloat(spn_radioext_cilindricoHueco.getValue().toString());
						radio_externo = Float.parseFloat(spn_radioint_cilindricoHueco.getValue().toString());
						largo = Float.parseFloat(spn_largo_cilindricoHueco.getValue().toString());
						radio_esferico = Float.parseFloat(spn_radio_esferico.getValue().toString());
						if (rdbtnEsferico.isSelected()) {
							if (txt_id.getText().isEmpty() || txt_p_base.getText().isEmpty() || txt_p_unitario.getText().isEmpty() || spn_radio_esferico.getValue() == "0") {
								JOptionPane.showMessageDialog(null, "Los campos requeridos no fueron llenados correctamente", "Notificacion", JOptionPane.INFORMATION_MESSAGE);

							}
							else
							{
								Queso item = new Esferico(txt_id.getText(), p_base, p_unitario, radio_esferico, "Cilindrico");
								Empresa.getInstance().addQueso(item);
								JOptionPane.showMessageDialog(null, "Queso Registrado exitosamente.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
								clean();
							}
						}
						if (rdbtnCilindrico.isSelected()) {
							if (txt_id.getText().isEmpty() || txt_p_base.getText().isEmpty() || txt_p_unitario.getText().isEmpty() || spn_radio_cilindrico.getValue() == "0" || spn_largo_cilindrico.getValue() == "0") {
								JOptionPane.showMessageDialog(null, "Los campos requeridos no fueron llenados correctamente", "Notificacion", JOptionPane.INFORMATION_MESSAGE);

							}
							else
							{
								Queso item = new Cilindro(txt_id.getText(), p_base, p_unitario, radio_cilindrico, largo_cilindrico, "cilindrico");
								Empresa.getInstance().addQueso(item);
								JOptionPane.showMessageDialog(null, "Queso Registrado exitosamente.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
								clean();
							}
						}
						if (rdbtnCilindroHueco.isSelected()) {
							if (txt_id.getText().isEmpty() || txt_p_base.getText().isEmpty() || txt_p_unitario.getText().isEmpty() || spn_radioext_cilindricoHueco.getValue() == "0" || spn_radioint_cilindricoHueco.getValue() == "0" || spn_largo_cilindricoHueco.getValue() == "0") {
								JOptionPane.showMessageDialog(null, "Los campos requeridos no fueron llenados correctamente", "Notificacion", JOptionPane.INFORMATION_MESSAGE);

							}
							else
							{
								
								if (radio_externo < radio_interno) {
									JOptionPane.showMessageDialog(null, "El radio Externo no puede ser menor que el interno.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
								}
								else
								{
									Queso item = new CilindroHueco(txt_id.getText(), p_base, p_unitario, largo,radio_externo, radio_interno, "cilindricoHueco");
									Empresa.getInstance().addQueso(item);
									JOptionPane.showMessageDialog(null, "Queso Registrado exitosamente.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
									clean();
								}
								
							}
						}
					}

					private void clean() {
						txt_id.setText(String.valueOf(Empresa.getInstance().getGen_cheese()));;
						txt_p_base.setText("");
						txt_p_unitario.setText("");
						spn_largo_cilindrico.setValue(0);
						spn_largo_cilindricoHueco.setValue(0);
						spn_radio_cilindrico.setValue(0);
						spn_radio_esferico.setValue(0);
						spn_radioext_cilindricoHueco.setValue(0);
						spn_radioint_cilindricoHueco.setValue(0);
						rdbtnEsferico.setSelected(true);
						
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
}
