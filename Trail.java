/*This program is a 'Trail' made my pressing 'a' to go left, 'd' to go right', 'x' to go down, 'w' to go up, and 'q', 'e', 'z',
and 'c' to go diagonal. After reaching a certain length, the color starts to slowly fade. Pressing any other key than
specified will result in the 'trail' to stay in the same spot, but the color to slowly fade away.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Trail {

	JFrame frame;			// JFrame and JPanel must be global object instances
	PPanel np;			// KeyListener, FocusListener panel with text field
	int[][] array = new int[100][100];

	int move = 0;
	int x = 49;
	int y =49;

	public Trail () {}

	public static void main(String[] args) {
		Trail Trail = new Trail();
		Trail.Run();
	} //end public void


	public void Run() {
		frame = new JFrame("Trail");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create PPanel and add to JFrame
		np = new PPanel();
		frame.getContentPane().add(np, BorderLayout.CENTER);

		frame.setSize(500, 500);		// explicitly set size in pixels
		frame.setLocation(500,300);	// set location on my screen
		frame.setVisible(true);		// set to true to make visible
		for(int i=0; i < 100; i++)
		{
			for(int j=0; j < 100; j++)
			{
				array[i][j] = 255;
			}
		}

	} //end Run()

	class PPanel extends JPanel implements KeyListener {

		public PPanel() {
			frame.addKeyListener(this); //hi
		} //end public PPanel


		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(move == 0) //if first move, draw square in the center
			{
				g.fillRect(49*5,49*5,5,5); 
				//center square
			}

			for(int i=0; i < 100; i++)
			{
				for(int j=0; j < 100; j++)
				{
					if(array[i][j] < 255)
					{
						Color myColor = new Color(array[i][j], array[i][j], array[i][j]); //sets color
						g.setColor(myColor);
						g.fillRect((i)*5, (j)*5, 5, 5); //fill the rectangle, 5x5
					}
				}
			}
		} //end paintComponent



		public void keyPressed (KeyEvent e) {
			//nothing to do here
		} //end keyPressed

		public void keyReleased (KeyEvent e) {
			//nothing to do here
		} //end keyReleased 

		public void keyTyped (KeyEvent e) {
			char c = e.getKeyChar();
			switch (c) { //Switch case for getting what direction the user wants to go
				case 'a': 

				if (x==0) { //checks if going outside of grid
					repaint();
					break;
				}

				x--;
				array[x][y] = 0; //make it black
				move++; //increment move
				break;

				case 'd':

				if (x==99) { //checks if going outside of grid

					break;
				}

				x++;
				array[x][y] = 0;
				move++;
				break;

				case 'w':
				if (y==0) { //check if going outside of grid

					break;
				}
				y--;
				array[x][y] = 0; //make it black
				move++; //increment move
				break;

				case 'x':

				if (y==99) { //checks if going outside of grid

					break;
				}
				y++;
				array[x][y] = 0; //make it black
				move++; //increment move
				break;

				case 'q': 
				if(x>0&&y>0) //if inside, move. If outside of grid, don't. 
				{	
					x--;
					y--;
				}
				array[x][y] = 0; //make it black
				move++; //increment move
				break;

				case 'e':
				if(x<99&&y>0) //if inside, move. If outside of grid, don't. 
				{
					x++;
					y--;
				}

				array[x][y] = 0; //make it black
				move++; //increment move
				break;

				case 'z':
				if(x>0&&y<100) //if inside, move. If outside of grid, don't. 
				{	
					x--;
					y++;
				}
				array[x][y] = 0; //make it black
				move++; //increment move
				break;

				case 'c':
				if(x<99&&y<99) //if inside, move. If outside of grid, don't. 
				{
					x++;
					y++;
				}
				array[x][y] = 0; //make it black
				move++; //increment move
				break;

			}

			for(int i=0; i < 100; i++) //fading effect for squares
			{
				for(int j=0; j < 100; j++)
				{
					if(array[i][j] < 255) //if not white
					{
						array[i][j]+=5; //add 5 rgb to it
					}
				}
			}
			repaint();	//repaint after movement

		} //end keyTyped

	} //end PPanel

} //end Trail
