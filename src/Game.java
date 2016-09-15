import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Game extends JFrame {

	private static final long serialVersionUID = 4030205823742055195L;

	public enum SelectionMask {
		Standard, Plus1, Plus2, Plus3, Plus4
	}

	public static SelectionMask selection = SelectionMask.Plus2;

	public enum Orientation {
		North, East, South, West
	}

	private static Orientation orientation = Orientation.North;
	public JLabel[][] board = new JLabel[13][13];
	public JLabel[][] board2 = new JLabel[13][13];

	public Player player1 = new Player();

	private String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L" };

	public Game(String name) {
		super(name);
		setResizable(true);
	}

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		Game frame = new Game("Battleships");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		// Display the window.
		frame.getContentPane().setPreferredSize(new Dimension(1280, 720));

		frame.pack();
		frame.setVisible(true);
	}

	public void addComponentsToPane(final Container pane) {
		JPanel gameWindow = new JPanel(new BorderLayout());
		gameWindow.setBackground(Color.RED);
		gameWindow.setLayout(new GridLayout(12, 12));

		// Left Board

		for (int y = 0; y < 12; y++) {
			for (int x = 0; x < 12; x++) {
				final int x2 = x;
				final int y2 = y;
				board[x2][y2] = new JLabel("(" + letters[x] + y + ")");
				board[x2][y2].setForeground(Color.WHITE);
				;
				board[x2][y2].setHorizontalAlignment(AbstractButton.CENTER);
				board[x2][y2].setPreferredSize(new Dimension(50, 50));
				board[x2][y2].setOpaque(true);
				board[x2][y2].setBackground(Color.BLUE);
				board[x2][y2].setBorder(BorderFactory
						.createLineBorder(Color.BLACK));

				board[x2][y2].addMouseListener(new MouseAdapter() {
					Color prevColor;
					Boolean clicked = false;

					public void mouseEntered(MouseEvent e) {
						if (!clicked) {
							prevColor = board[x2][y2].getBackground();
							MouseClick(x2, y2, Color.DARK_GRAY);
						}
					}

					public void mouseExited(MouseEvent e) {
						if (!clicked) {
							MouseClick(x2, y2, prevColor);
						}
					}

					public void mouseClicked(MouseEvent e) {
						clicked = true;
						MouseClick(x2, y2, Color.WHITE);
					}
				});

				gameWindow.add(board[x2][y2]);
			}
		}

		pane.add(gameWindow, BorderLayout.WEST);

		// Adding right window
		JPanel gameWindow2 = new JPanel(new BorderLayout());
		gameWindow2.setBackground(Color.GREEN);
		gameWindow2.setLayout(new GridLayout(12, 12));

		// Right Board

		for (int y = 0; y < 12; y++) {
			for (int x = 0; x < 12; x++) {
				final int x2 = x;
				final int y2 = y;
				board2[x2][y2] = new JLabel("(" + letters[x] + y + ")");
				board2[x2][y2].setForeground(Color.WHITE);
				;
				board2[x2][y2].setHorizontalAlignment(AbstractButton.CENTER);
				board2[x2][y2].setPreferredSize(new Dimension(50, 50));
				board2[x2][y2].setOpaque(true);
				board2[x2][y2].setBackground(Color.BLUE);
				board2[x2][y2].setBorder(BorderFactory
						.createLineBorder(Color.BLACK));

				board2[x2][y2].addMouseListener(new MouseAdapter() {
					Color prevColor;
					Boolean clicked = false;

					public void mouseEntered(MouseEvent e) {
						if (!clicked) {
							// prevColor = board2[x2][y2].getBackground();
							// MouseClick(x2, y2, Color.DARK_GRAY);
						}
					}

					public void mouseExited(MouseEvent e) {
						if (!clicked) {
							// MouseClick(x2, y2, prevColor);
						}
					}

					public void mouseClicked(MouseEvent e) {
						// clicked = true;
						// MouseClick(x2, y2, Color.WHITE);
					}
				});

				gameWindow2.add(board2[x2][y2]);
			}
		}

		pane.add(gameWindow2, BorderLayout.EAST);

		// Adding GUI window
		JPanel guiWindow = new JPanel(new BorderLayout());
		guiWindow.setBackground(Color.DARK_GRAY);
		guiWindow.setLayout(new GridLayout(1, 20));

		final JButton rot = new JButton("Rotate (" + orientation.toString()
				+ ")");
		rot.setVerticalTextPosition(AbstractButton.CENTER);
		rot.setHorizontalTextPosition(AbstractButton.CENTER);
		rot.setActionCommand("enable");
		rot.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (orientation) {
				case North:
					orientation = Orientation.East;
					break;
				case East:
					orientation = Orientation.South;
					break;
				case South:
					orientation = Orientation.West;
					break;
				case West:
					orientation = Orientation.North;
					break;
				}
				rot.invalidate();
				rot.validate();
			}
		});
		guiWindow.add(rot);

		JButton pButton = new JButton("Add Patrol Boat (" + player1.patrolCount
				+ " remaining)\n(1, 2)");
		pButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selection = SelectionMask.Plus1;
			}
		});
		guiWindow.add(pButton);
		JButton bButton = new JButton("Add Battleship ("
				+ player1.battleshipCount + " remaining)\n(1, 3)");
		bButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selection = SelectionMask.Plus2;
			}
		});
		guiWindow.add(bButton);
		JButton sButton = new JButton("Add Submarine ("
				+ player1.submarineCount + " remaining)\n(1, 3)");
		sButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selection = SelectionMask.Plus2;
			}
		});
		guiWindow.add(sButton);
		JButton dButton = new JButton("Add Destroyer ("
				+ player1.destroyerCount + " remaining)\n(1, 4)");
		dButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selection = SelectionMask.Plus3;
			}
		});
		guiWindow.add(dButton);
		JButton cButton = new JButton("Add Carrier (" + player1.carrierCount
				+ " remaining)\n(1, 5)");
		cButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selection = SelectionMask.Plus4;
			}
		});
		guiWindow.add(cButton);

		pane.add(guiWindow, BorderLayout.SOUTH);

	}

	public int xDirection() {
		switch (orientation) {
		case West:
			return -1;
		case East:
			return +1;
		default:
			return 0;
		}
	}

	public int yDirection() {
		switch (orientation) {
		case North:
			return -1;
		case South:
			return +1;
		default:
			return 0;
		}
	}

	public void MouseClick(int x, int y, Color c) {
		switch (selection) {
		case Standard:
			if (board[x][y] != null) {
				board[x][y].setBackground(c);
			}
			break;

		case Plus1:
			int xDir = xDirection();
			int yDir = yDirection();
			if (board[x][y] != null) {
				board[x][y].setBackground(c);
			}
			if (board[x + xDir][y + yDir] != null) {
				board[x + xDir][y + yDir].setBackground(c);
			}
			break;

		case Plus2:
			xDir = xDirection();
			yDir = yDirection();
			if (board[x][y] != null) {
				board[x][y].setBackground(c);
			}
			if (board[x + xDir][y + yDir] != null) {
				board[x + xDir][y + yDir].setBackground(c);
			}
			if (board[x + (xDir * 2)][y + (yDir * 2)] != null) {
				board[x + (xDir * 2)][y + (yDir * 2)].setBackground(c);
			}
			break;

		case Plus3:
			xDir = xDirection();
			yDir = yDirection();
			if (board[x][y] != null) {
				board[x][y].setBackground(c);
			}
			if (board[x + xDir][y + yDir] != null) {
				board[x + xDir][y + yDir].setBackground(c);
			}
			if (board[x + (xDir * 2)][y + (yDir * 2)] != null) {
				board[x + (xDir * 2)][y + (yDir * 2)].setBackground(c);
			}
			if (board[x + (xDir * 3)][y + (yDir * 3)] != null) {
				board[x + (xDir * 3)][y + (yDir * 3)].setBackground(c);
			}
			break;

		case Plus4:
			xDir = xDirection();
			yDir = yDirection();
			if (board[x][y] != null) {
				board[x][y].setBackground(c);
			}
			if (board[x + xDir][y + yDir] != null) {
				board[x + xDir][y + yDir].setBackground(c);
			}
			if (board[x + (xDir * 2)][y + (yDir * 2)] != null) {
				board[x + (xDir * 2)][y + (yDir * 2)].setBackground(c);
			}
			if (board[x + (xDir * 3)][y + (yDir * 3)] != null) {
				board[x + (xDir * 3)][y + (yDir * 3)].setBackground(c);
			}
			if (board[x + (xDir * 4)][y + (yDir * 4)] != null) {
				board[x + (xDir * 4)][y + (yDir * 4)].setBackground(c);
			}
			break;
		}

	}

}
