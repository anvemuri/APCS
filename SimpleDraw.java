import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.event.*;

public class SimpleDraw {

	public static void main(String[] args) {

		MyGUI gui = new MyGUI();
	}
}

class MyGUI implements ActionListener, MouseListener, MouseMotionListener {

	// Attributes
	Color color = Color.RED;
	MyDrawingPanel drawingPanel;
	Color[][] arr = new Color[20][20];

	MyGUI() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				arr[i][j] = Color.white;
			}
		}
		// Create Java Window
		JFrame window = new JFrame("SimpleDraw");
		window.setBounds(100, 100, 445, 600);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create GUI elements

		// JPanel to draw in
		drawingPanel = new MyDrawingPanel();
		drawingPanel.setBounds(20, 20, 400, 400);
		drawingPanel.setBorder(BorderFactory.createEtchedBorder());
		drawingPanel.addMouseListener(this);
		drawingPanel.addMouseMotionListener(this);

		// JButton
		JButton button = new JButton("Reset");
		button.setBounds(190, 510, 75, 20);
		button.addActionListener(this);

		// JRadioButtons
		JRadioButton radioButton1 = new JRadioButton("Red", true);
		JRadioButton radioButton2 = new JRadioButton("Green");
		JRadioButton radioButton3 = new JRadioButton("Blue");
		JRadioButton radioButton4 = new JRadioButton("Custom Color");

		radioButton1.setBounds(50, 75, 100, 20);
		radioButton2.setBounds(50, 100, 100, 20);
		radioButton3.setBounds(50, 125, 100, 20);
		radioButton4.setBounds(50, 150, 100, 20);
		radioButton1.addActionListener(this);
		radioButton2.addActionListener(this);
		radioButton3.addActionListener(this);
		radioButton4.addActionListener(this);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioButton1);
		radioGroup.add(radioButton2);
		radioGroup.add(radioButton3);
		radioGroup.add(radioButton4);

		/* MENU STUFF */
		// JMenuBar
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenuItem open = new JMenuItem("Open", 'o');
		JMenuItem save = new JMenuItem("Save", 's');
		JMenuItem clear = new JMenuItem("Clear", 'c');
		mb.add(file);
		mb.add(edit);
		file.add(open);
		file.add(save);
		edit.add(clear);
		open.addActionListener(this);
		save.addActionListener(this);
		clear.addActionListener(this);
		window.setJMenuBar(mb);

		// Add GUI elements to the Java window's ContentPane
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);

		JPanel colorPanel = new JPanel();
		colorPanel.setBorder(BorderFactory.createTitledBorder("Drawing Color"));

		colorPanel.setBounds(75, 425, 300, 70);
		colorPanel.add(radioButton1);
		colorPanel.add(radioButton2);
		colorPanel.add(radioButton3);
		colorPanel.add(radioButton4);

		mainPanel.add(drawingPanel);

		mainPanel.add(colorPanel);
		mainPanel.add(button);

		window.getContentPane().add(mainPanel);

		// Let there be light
		window.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != null) {

			if (e.getActionCommand().equals("Red"))
				color = Color.RED;
			else if (e.getActionCommand().equals("Green"))
				color = Color.GREEN;
			else if (e.getActionCommand().equals("Blue"))
				color = Color.BLUE;
			else if (e.getActionCommand().equals("Custom Color")) {
				color = JColorChooser.showDialog(null, "Choose Color", Color.white);
			}//custom

			if (e.getActionCommand().equals("Reset") || e.getActionCommand().equals("Clear")) {
				clearDraw();
			}//clear
			if (e.getActionCommand().equals("Save")) {
				JFileChooser fc = new JFileChooser();
				fc.showSaveDialog(null);
				File filesaved = fc.getSelectedFile();
				FileWriter filewriter = null;
				try{
					filewriter = new FileWriter(filesaved);
					filewriter.write("P3");
					filewriter.write("20 20\n");
					filewriter.write("255\n");
					for (int i = 0; i < arr.length; i++){
						for (int j = 0; j < arr[i].length; j++){
							filewriter.write(arr[i][j].getRed() + "\t" + arr[i][j].getGreen() + "\t" + arr[i][j].getBlue() + "\t");
						}
						filewriter.write("\n");
					}
					filewriter.close();
				}
				catch(Exception a){
					System.out.println("error = " + a.getMessage());
				}
			}//save
			if (e.getActionCommand().equals("Open")) {
				JFileChooser fc2 = new JFileChooser();
				fc2.showOpenDialog(null);
				File fileopened = fc2.getSelectedFile();
				Scanner kb = new Scanner(System.in);
				kb.next();
				if (kb.next() == "#"){}
				int xsize = kb.nextInt();
				int ysize = kb.nextInt();
				kb.nextInt();
				for (int i = 0; i < 20; i++){
					for (int j = 0; j < 20; j++){
						arr[i][j] = new Color(kb.nextInt(), kb.nextInt(), kb.nextInt());
					}
				}	
			}//open

		}

	}

	public void clearDraw() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				arr[i][j] = Color.white;
			}
		}
		drawingPanel.repaint();
		drawingPanel.paintComponent(drawingPanel.getGraphics());

	}

	private class MyDrawingPanel extends JPanel {

		// Not required, but gets rid of the serialVersionUID warning. Google
		// it, if desired.
		static final long serialVersionUID = 1234567890L;

		public void paintComponent(Graphics g) {

			g.setColor(Color.white);
			g.fillRect(2, 2, this.getWidth() - 2, this.getHeight() - 2);

			g.setColor(Color.lightGray);
			for (int x = 0; x < this.getWidth(); x += 20)
				g.drawLine(x, 0, x, this.getHeight());

			for (int y = 0; y < this.getHeight(); y += 20)
				g.drawLine(0, y, this.getWidth(), y);

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					g.setColor(arr[i][j]);
					g.fillRect(i * 20 + 1, j * 20 + 1, 19, 19);
				}
			}

		}
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
		// System.out.println("Dragging");
		Graphics g = drawingPanel.getGraphics();
		int x = 20 * (e.getX() / 20);
		int y = 20 * (e.getY() / 20);
		// System.out.println("(" + x + "," + y + ")");
		int mInfo = e.getModifiersEx();
		if (mInfo == InputEvent.BUTTON1_DOWN_MASK) {
			g.setColor(color);
			arr[x / 20][y / 20] = color;
		}
		if (mInfo == InputEvent.BUTTON3_DOWN_MASK) {
			g.setColor(Color.white);
			arr[x / 20][y / 20] = Color.white;
		}
		g.fillRect(x + 1, y + 1, 19, 19);
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseDragged(MouseEvent e) {
		// System.out.println("Dragging");
		Graphics g = drawingPanel.getGraphics();
		int x = 20 * (e.getX() / 20);
		int y = 20 * (e.getY() / 20);
		// System.out.println("(" + x + "," + y + ")");
		int mInfo = e.getModifiersEx();
		if (mInfo == InputEvent.BUTTON1_DOWN_MASK) {
			g.setColor(color);
			if (e.getX() >= 0 && e.getX() < 400 && e.getY() >= 0
					&& e.getY() < 400)
				arr[x / 20][y / 20] = color;
		}
		if (mInfo == InputEvent.BUTTON3_DOWN_MASK) {
			g.setColor(Color.white);
			if (x >= 0 && x <= 400 && y >= 0 && y <= 400)
				arr[x / 20][y / 20] = Color.white;
		}
		g.fillRect(x + 1, y + 1, 19, 19);
	}

	public void mouseMoved(MouseEvent arg0) {
	}
}
