//This program uses GUIS to create a Frame which draws circles with the left click, and square with the right click on the 
//RightPanel. The Slider on the left side panel changes the radius/width


// import libraries
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
/*import javax.swing.border.*;*/

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClassworkGUIAVemuriPeriod7 implements MouseListener{
	int slidernum;
	JSlider slider = new JSlider(JSlider.VERTICAL, 0, 50, 25);
	RightPanel rp;

	public static void main(String[] args) {
		ClassworkGUIAVemuriPeriod7 gui = new ClassworkGUIAVemuriPeriod7();
		gui.createGUI();
	}

	public void createGUI() {
		// Create a basic Java window frame
		JFrame window = new JFrame("My Window Title");

		// Decide what to do when the user closes the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Get the screen dimensions
		int screenWidth = (int)(window.getToolkit().getScreenSize().getWidth());
		int screenHeight = (int)(window.getToolkit().getScreenSize().getHeight());
		// Our window size
		int frameWidth = 800;
		int frameHeight = 600;
		// Centers the JFrame, regardless of screen resolution
		window.setBounds(screenWidth/2 - frameWidth/2, screenHeight/2 - frameHeight/2, frameWidth, frameHeight);

		// Decide whether to allow users to resize the window
		window.setResizable(true);

		// Define the overall layout
		window.getContentPane().setLayout(new GridLayout(1, 3));
		// Create GUI components
		JPanel LeftPanel = new JPanel();
		window.add(LeftPanel);
		LeftPanel.setBackground(new Color(240, 150, 150));
		LeftPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		LeftPanel.setLayout(new BoxLayout(LeftPanel, BoxLayout.Y_AXIS));
		TopLeft tl = new TopLeft();
		tl.setPreferredSize(new Dimension(200, 150));
		LeftPanel.add(tl);
		JPanel bl = new JPanel();
		bl.setLayout(new GridLayout(1, 3));
		LeftPanel.add(bl);
		
		JPanel MiddlePanel = new JPanel();
		window.add(MiddlePanel);
		MiddlePanel.setBackground(new Color(189, 236, 182));
		MiddlePanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		JPanel Blackboard = new JPanel();
		Blackboard.setBackground(new Color(240, 240, 150));
		Blackboard.setPreferredSize(new Dimension(200, 300));
		Blackboard.setBorder(new TitledBorder("Blackboard"));
		MiddlePanel.add(Blackboard, BorderLayout.CENTER);
		
		rp = new RightPanel();
		window.add(rp);
		rp.setBorder(new BevelBorder(BevelBorder.LOWERED));
		rp.addMouseListener(this);
		
		// Add GUI components to JFrame (window)
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(2);
		LeftPanel.add(slider);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		JButton button1 = new JButton("one");
		JButton button2 = new JButton("two");
		JButton button3 = new JButton("three");
		bl.add(button1);
		bl.add(button2);
		bl.add(button3);
		window.setMinimumSize(new Dimension(100,100));
		// Make the window visible
		window.setVisible(true);
	}
	
	private class LeftPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(new Color(240, 150, 150));
		}
		
	}
	private class TopLeft extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(Color.white);
			setBorder(new LineBorder(Color.gray, 5, true));
		}
	}
	private class MiddlePanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(new Color(189, 236, 182));
		}
		
	}
	private class RightPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(new Color(150, 160, 200));
		}
	}
	private class Blackboard extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(new Color(240, 240, 150));
		}
		
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {
		Graphics g = rp.getGraphics();
		slidernum = slider.getValue();
		int mInfo = arg0.getModifiersEx();
		g.setColor(Color.black);
		if (mInfo == InputEvent.BUTTON1_DOWN_MASK){
			g.drawOval(arg0.getX(), arg0.getY(), slidernum, slidernum);
		}
		if (mInfo == InputEvent.BUTTON3_DOWN_MASK){
			g.drawRect(arg0.getX(), arg0.getY(), slidernum, slidernum);
		}
	}
	public void mouseReleased(MouseEvent arg0) {}

}
