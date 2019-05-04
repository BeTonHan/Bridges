package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class NeuesRaetselDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JTextField txtIsles;
	private JRadioButton rdbtnAutoSize;
	private JRadioButton rdbtnCustomSize;
	private JCheckBox chckbxCustomNumber;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton okButton;
	private JButton cancelButton;

	public int getBreite() {
		return Integer.parseInt(txtWidth.getText());
	}

	public void setBreite(int breite) {
		this.txtWidth.setText("" + breite);
	}

	/**
	 * Create the dialog.
	 */
	public NeuesRaetselDialog() {
		initElements();
		handleEvents();

	}

	private void handleEvents() {
		// Automatische Feldgenerierung gewählt
		rdbtnAutoSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshSettings();
			}
		});

		// Manuelle Feldgenerierung gewählt
		rdbtnCustomSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshSettings();
			}
		});

		// Manuelle Eingabe der Inselzahlen gewählt
		chckbxCustomNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxCustomNumber.isSelected())
					txtIsles.setEditable(true);
				else
					txtIsles.setEditable(false);

			}
		});
		
		// Maus bewegt sich über Feld für Inselanzahl
		txtIsles.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				try { txtIsles.setToolTipText("Erlaubte Anzahl der Inseln bei der derzeitigen Feldgröße: 2 - " + (Integer.parseInt(txtWidth.getText()) * Integer.parseInt(txtHeight.getText())) / 5);
				}
				catch (IllegalArgumentException ex) {
					txtIsles.setToolTipText("Erlaubte Anzahl der Inseln kann erst nach Eingabe einer zulässigen Feldgröße ermittelt werden...");
				}
			}
		});
		

		// OK-Button gedrückt
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkFieldValues())
					dispose();
			}
		});

	}

	private void initElements() {
		// TODO Auto-generated method stub
		setBounds(100, 100, 293, 298);
		setTitle("Neues Rätsel");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		rdbtnAutoSize = new JRadioButton("Automatische Gr\u00F6\u00DFe und Inselzahl");
		buttonGroup.add(rdbtnAutoSize);

		rdbtnCustomSize = new JRadioButton("Gr\u00F6\u00DFe und Inselanzahl selbst festlegen");

		buttonGroup.add(rdbtnCustomSize);
		rdbtnCustomSize.setSelected(true);

		JLabel lblWidth = new JLabel("Breite:");

		JLabel lblHeight = new JLabel("H\u00F6he:");

		txtWidth = new JTextField();
		txtWidth.setText("4");
		txtWidth.setColumns(10);

		txtHeight = new JTextField();
		txtHeight.setText("4");
		txtHeight.setColumns(10);

		chckbxCustomNumber = new JCheckBox("Inselanzahl festlegen");

		JLabel lblIsles = new JLabel("Inseln:");

		txtIsles = new JTextField();
		txtIsles.setText("3");
		txtIsles.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtnAutoSize).addComponent(rdbtnCustomSize)))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(32).addGroup(gl_contentPanel
										.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblHeight)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblWidth)
												.addGap(18).addComponent(txtWidth, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(chckbxCustomNumber)
										.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblIsles)
												.addGap(18).addComponent(txtIsles, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(110, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(17).addComponent(rdbtnAutoSize)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rdbtnCustomSize)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblWidth).addComponent(
						txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(12)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblHeight).addComponent(
						txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxCustomNumber)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblIsles).addComponent(
						txtIsles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(35, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				cancelButton = new JButton("Abbrechen");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				okButton = new JButton("OK");

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

	}

	private void refreshSettings() {
		// Je nach Auswahl der Optionsfelder werden die Textfelder initialisiert oder
		// zur Bearbeitung freigegeben.
		// TODO Zufallswertgenerierung auf einzelne Methoden übertragen

		if (rdbtnAutoSize.isSelected()) {
			int randWidth = ThreadLocalRandom.current().nextInt(4, 25 + 1);
			int randHeight = ThreadLocalRandom.current().nextInt(4, 25 + 1);
			long randIsles = ThreadLocalRandom.current().nextInt(2, (int) ((randWidth * randHeight) / 5) + 1);
			txtWidth.setText(String.valueOf(randWidth));
			txtHeight.setText(String.valueOf(randHeight));
			txtIsles.setText(String.valueOf(randIsles));
			txtWidth.setEditable(false);
			txtHeight.setEditable(false);
			txtIsles.setEditable(false);
			chckbxCustomNumber.setEnabled(false);
		} else {
			txtWidth.setEditable(true);
			txtHeight.setEditable(true);
			chckbxCustomNumber.setEnabled(true);
			chckbxCustomNumber.setSelected(false);			
		}

	}

	private boolean checkFieldValues() {
		// Prüft, ob gewählte Eingabedaten zulässig sind

		try {
			int width = Integer.parseInt(txtWidth.getText());
			int height = Integer.parseInt(txtHeight.getText());
			int isles = Integer.parseInt(txtIsles.getText());
			int maxIslesAllowed = (int) ((width * height) / 5);
			if ((width < 4) || (width > 25) || (height < 4) || (height > 25)) {
				JOptionPane.showMessageDialog(null,
						"Höhe und Breite des Spielfelds müssen sich zwischen den Werten 4 und 25 befinden!");
				return false;
			} else if ((isles > maxIslesAllowed) || (isles < 2)) {
				JOptionPane.showMessageDialog(null, "Auf einem Spielfeld dieser Größe können sich 2 - "
						+ maxIslesAllowed + " Inseln befinden!");
				return false;
			} else
				return true;
		} 
		catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null,
					"Bitte geben Sie in den Feldern nur ganze Zahlen innerhalb der erlaubten Spannbreite ein!");
			return false;
		}
	}

}
