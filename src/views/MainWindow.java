package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmBeenden;
	private JMenuItem mntmRtselSpeichernUnter;
	private JMenuItem mntmRtselSpeichern;
	private JMenuItem mntmRtselLaden;
	private JMenuItem mntmRtselNeuStarten;
	private JMenuItem mntmNeuesRaetsel;
	private JPanel panel;
	NeuesRaetselDialog dialog = new NeuesRaetselDialog();
	private int intNumberOfBridges;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		initElements();
		handleEvents();
	}

	private void initElements() {
		// Initialisieren der Bildschirmelemente
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 700);

		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		mntmNeuesRaetsel = new JMenuItem("Neues R\u00E4tsel");
		
		mnDatei.add(mntmNeuesRaetsel);

		mntmRtselNeuStarten = new JMenuItem("R\u00E4tsel neu starten");
		mnDatei.add(mntmRtselNeuStarten);

		mntmRtselLaden = new JMenuItem("R\u00E4tsel laden");
		mnDatei.add(mntmRtselLaden);

		mntmRtselSpeichern = new JMenuItem("R\u00E4tsel speichern");
		mnDatei.add(mntmRtselSpeichern);

		mntmRtselSpeichernUnter = new JMenuItem("R\u00E4tsel speichern unter...");
		mnDatei.add(mntmRtselSpeichernUnter);

		mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JCheckBox chckbxShowMissingBridges = new JCheckBox("Anzahl fehlender Br\u00FCcken anzeigen");
		
		JButton btnAutoSolve = new JButton("Automatisch l\u00F6sen");
		
		JButton btnNchsteBrcke = new JButton("N\u00E4chste Br\u00FCcke");
		
		JLabel lblNotification = new JLabel("Willkommen bei Bridges!");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(chckbxShowMissingBridges)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnAutoSolve)
					.addGap(33)
					.addComponent(btnNchsteBrcke)
					.addGap(343))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNotification)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxShowMissingBridges)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAutoSolve)
						.addComponent(btnNchsteBrcke))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(lblNotification)
					.addGap(23))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 580, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 496, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	private void handleEvents() {
		// Behandlung der Bildschirmereignisse

		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmProgramExit();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmProgramExit();
			}
		});
		
		mntmNeuesRaetsel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewTask();
			}

			
		});
		
	}

	private void createNewTask() {
		// TODO Auto-generated method stub
		try {
			
			dialog.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void confirmProgramExit() {
		// Sicherheitsabfrage vor Beenden des Spiels
		if (JOptionPane.showConfirmDialog(null,
				"Wollen Sie das Spiel wirklich beenden? Nicht gespeicherte Spielstände gehen verloren!",
				"Spiel beenden?", JOptionPane.YES_NO_OPTION) == 0) {
			System.exit(0);
		}
	}
}
