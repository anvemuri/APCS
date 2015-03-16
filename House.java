//Draws a house - with variables that change everytime you run it (ex. sky color, sun/moon, window shape, roof/door color, and flower color)

import java.awt.*;
import javax.swing.*;

public class DrawSquare{
	JFrame frame;
	MyPanel panel; 
			
	public static void main(String [] args){
		DrawSquare c = new DrawSquare();
		c.Run();
	}
	public void Run(){
		frame = new JFrame("My House");	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		//Put JPanel in frame
		panel = new MyPanel();												
		frame.getContentPane().add(panel);								
		
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
class MyPanel extends JPanel{
	int i = 1 + (int)(Math.random() * ((200 - 1) + 1));
	int f = 1 + (int)(Math.random() * ((300 - 1) + 1));
	public MyPanel(){}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color (0, i, 204));
		
		paintGrass(g);
		houseBase(g);
		houseRoof(g);
		paintDoor(g);
		paintWindows(g);
		drawWindows(g);
		drawFlowers(g);
	}
	
	public void paintGrass(Graphics g){
		g.setColor(new Color (102, 204, 0));
		g.fillRect(0, 350, 500, 150);
	}
	
	public void houseBase(Graphics g){
		g.setColor(new Color (255,178,102));
		g.fillRect(100, 200, 300, 300);
		g.setColor(new Color (102,0,0));
		g.drawRect(100, 200, 300, 300);		
	}
	public void houseRoof(Graphics g){
		 int xpoints[] = {125, 45, 125, 45, 125};
		 int ypoints[] = {25, 75, 145, 145, 25};
		 int npoints = 3;

		 g.fillPolygon(xpoints, ypoints, npoints);
	}
	public void paintDoor(Graphics g){
		g.setColor(new Color (102,0,0));
		g.fillRect(200, 350, 100, 120);
		g.setColor(new Color (255,178,102));
		g.fillOval(210, 400, 20, 20);
	}
	public void paintWindows(Graphics g){
		g.setColor(new Color(204, 255, 255));
		g.fillOval(140, 230, 65, 75);
		g.fillRoundRect(300, 230, 70, 70, 30, 30);
	}
	public void drawWindows(Graphics g){
		g.setColor(new Color (102,0,0));
		//oval window
		g.drawOval(140, 230, 65, 75);
		g.drawLine(172, 230, 172, 305);
		g.drawLine(140, 268, 205, 268);
		//rectangular window
		g.drawRoundRect(300, 230, 70, 70, 30, 30);
		g.drawLine(335, 230, 335, 300);
		g.drawLine(300, 268, 370, 268);
	}
	public void drawFlowers(Graphics g){
		//stem
		g.setColor(new Color(0, 153, 0));
		//255 102
		g.drawLine(48, 300, 48, 370);
		g.drawLine(49, 300, 49, 370);
		g.drawLine(50, 300, 50, 370);
		g.drawLine(51, 300, 51, 370);
		g.drawLine(52, 300, 52, 370);
		g.drawLine(438, 300, 438, 370);
		g.drawLine(439, 300, 439, 370);
		g.drawLine(440, 300, 440, 370);
		g.drawLine(441, 300, 441, 370);
		g.drawLine(442, 300, 442, 370);
		//petals
		g.setColor(new Color(255,f,51));
		//left flower
		g.fillOval(52, 303, 20, 20);
		g.fillOval(55, 289, 20, 20);
		g.fillOval(44, 283, 20, 20);
		g.fillOval(34, 290, 20, 20);
		g.fillOval(36, 302, 20, 20);
		//right flower
		g.fillOval(440, 303, 20, 20);
		g.fillOval(443, 289, 20, 20);
		g.fillOval(432, 283, 20, 20);
		g.fillOval(422, 290, 20, 20);
		g.fillOval(424, 302, 20, 20);
		
		//center
		g.setColor(Color.orange);
		g.fillOval(44, 294, 20, 20);
		g.fillOval(432, 295, 20, 20);
	}
	
}
