import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Program extends JFrame implements KeyListener {
	// Variables
	private ImageIcon antIcon = new ImageIcon(
			new ImageIcon("../Assets/Ant.jpg").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
	private ImageIcon bugIcon = new ImageIcon(
			new ImageIcon("../Assets/Bug.jpg").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
	private ImageIcon empIcon = new ImageIcon(
			new ImageIcon("../Assets/Empty.jpg").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
	private JPanel panel;
	private JLabel[][] cell;
	private Font mt;
	private JLabel details;
	private JLabel message;
	private String end;
	private String org;

	private int width;
	private int height;
	private int antTotal;
	private int bugTotal;
	private World world;

	// Constructor
	public Program(String title, int width, int height, int antTotal, int bugTotal) {
		super("Ants and Bugs");

		setSize(750, 795);
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.width = width;
		this.height = height;
		this.antTotal = antTotal;
		this.bugTotal = bugTotal;
		cell = new JLabel[width][height];

		panel = new JPanel(new GridLayout(width, height));
		add(panel, BorderLayout.CENTER);

		addKeyListener(this);

		world = new World(width, height, antTotal, bugTotal);
		world.generate();

		updateIcon();
		mt = new Font("TimesRoman", Font.BOLD, 15);

		message = new JLabel("   Ant: " + world.getAntNo() + "  Bug: " + world.getBugNo());
		message.setFont(mt);
		add(message, BorderLayout.NORTH);

		details = new JLabel("  Time = " + world.getTime() + "    Press [ENTER] for next step.");
		details.setFont(mt);
		add(details, BorderLayout.SOUTH);

		setVisible(true);
	}

	// Overriden KeyListener functions
	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			world.incrementTime();
			world.nextStep();

			message.setText("   Ant: " + world.getAntNo() + "  Bug: " + world.getBugNo());
			details.setText("  Time = " + world.getTime() + "    Press [ENTER] for next step.");

			updateIcon();

			if (world.getAntNo() == 0 || world.getBugNo() == 0) {
				if (world.getAntNo() == 0)
					org = "Ants ";
				else
					org = "Bugs ";
				end = org + "are terminated.";

				int input = JOptionPane.showConfirmDialog(null, end, "Simulation finished", JOptionPane.DEFAULT_OPTION);
				System.out.println(input);

				System.exit(0);
			}
		}
	}

	public void keyTyped(KeyEvent evt) {
	}

	public void keyReleased(KeyEvent evt) {
	}

	// Methods
	public void updateIcon() // Update icons of each cell (Aiman Hans, Lim Ee Tien, Lim Kee Hian)
	{
		panel.removeAll();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cell[i][j] = new JLabel();

				if (world.getContent(i, j) instanceof Ant)
					cell[i][j].setIcon(antIcon);
				else if (world.getContent(i, j) instanceof Bug)
					cell[i][j].setIcon(bugIcon);
				else
					cell[i][j].setIcon(empIcon);

				panel.add(cell[i][j]);
			}
		}
		panel.revalidate();
	}

	// Main
	public static void main(String[] args) {
		int mapSize = 9, ants = 10, bugs = 2;

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("--") && (i + 1 != args.length)) {
				switch (args[i]) {
					case "--bugs": {
						if (isInt(args[i + 1]))
							bugs = Integer.parseInt(args[i + 1]);
						break;
					}
					case "--ants": {
						if (isInt(args[i + 1]))
							ants = Integer.parseInt(args[i + 1]);
						break;
					}
					case "--size": {
						if (isInt(args[i + 1]))
							mapSize = Integer.parseInt(args[i + 1]);
						break;
					}
				}
			}
		}

		new Program("Ants and Bugs", mapSize, mapSize, ants, bugs);
	}

	public static boolean isInt(String s) {
		for (int i = 0; i < s.length(); i++)
			if (!Character.isDigit(s.charAt(i)))
				return false;

		return true;
	}
}