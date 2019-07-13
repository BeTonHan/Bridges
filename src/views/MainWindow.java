package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import model.Grid;

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
	private JLabel lblNotification;
	Grid grid = new Grid(2, 2, 1, 1, 1);

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

		panel = new Field();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JCheckBox chckbxShowMissingBridges = new JCheckBox("Anzahl fehlender Br\u00FCcken anzeigen");

		JButton btnAutoSolve = new JButton("Automatisch l\u00F6sen");

		JButton btnNchsteBrcke = new JButton("N\u00E4chste Br\u00FCcke");

		lblNotification = new JLabel("Willkommen bei Bridges!");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxShowMissingBridges)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAutoSolve)
							.addGap(33)
							.addComponent(btnNchsteBrcke))
						.addComponent(lblNotification)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
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
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 580, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 496, Short.MAX_VALUE));
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
		// Erstelle neues Spielfeld
		try {

			dialog.setVisible(true);
			dialog.okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					drawField();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	protected void drawField() {
		// TODO Auto-generated method stub
		grid = new Grid(dialog.getFieldWidth(), dialog.getFieldHeight(), panel.getWidth(), panel.getHeight(), dialog.getNumberOfIslands());
		panel.repaint();
	}


	
	private void confirmProgramExit() {
		// Sicherheitsabfrage vor Beenden des Spiels
		if (JOptionPane.showConfirmDialog(null,
				"Wollen Sie das Spiel wirklich beenden? Nicht gespeicherte Spielstände gehen verloren!",
				"Spiel beenden?", JOptionPane.YES_NO_OPTION) == 0) {
			System.exit(0);
		}
	}

	/**
	 * Zeichnet die Inseln auf das Spielfeld
	 */
	private class Field extends JPanel {
		
		// Override paintComponent to perform your own painting
		@Override
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			Graphics2D graphics2d = (Graphics2D) g;
			graphics2d.setColor(Color.BLUE); // set the drawing color
			
			for (int x : grid.getxCoordinates()) {
				for (int y : grid.getyCoordinates()) {
					System.out.println(x + "; " + y);
					graphics2d.drawOval(x, y, 10, 10);
				}
			}
			
			graphics2d.setColor(Color.RED); // set the drawing color
			for (int i = 0; i < grid.getIslands().size(); i++) {
				graphics2d.drawOval(grid.getIslands().get(i).getxPos(), grid.getIslands().get(i).getyPos(), 10, 10);
			}
		}
		
	}

}
