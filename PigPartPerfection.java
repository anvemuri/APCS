//My own, original game in which the player navigates a grid, trying to catch pigs. Once a pig is caught, the user is giver a
//question relating to the anatomy of a pig. 

import java.awt.*;			// for classes Image, Graphics, Color 
import java.awt.event.*;	// for classes KeyListener, MouseListener
import javax.swing.*;		// for class JFrame
import java.io.*; 			//file and ioexception
import java.util.Scanner;	// imports Scanner library
import javax.imageio.*; 	//ImageIO

public class PigPartPerfection implements MouseListener, KeyListener{		// class PigPartPerfection
 
	private JFrame frame;
	private DrawingArea canvas;		
	Image image, image2;
	JLabel start, instructions;
	private int xpos, ypos;
	int panelnumber;
	int openpanel;
	 Panel p; //JPanel for the backdrop
    Grid g; //JPanel for the Grid
    int move = 0; //number of moves (first move = placement randomly done)
    int xloc, yloc; //x and y locations  of the icon
    boolean gameover = false; //tells if game is over or not
    JLayeredPane l = new JLayeredPane(); //Each panel sits on top of each other (layered)  
    int[][] array = new int[14][16]; //stores values for Piece, monster
    int qtimer;
    int questionnumber = 0;
    boolean round1 = true;
    int questionmod = 0;	
    boolean answer = true;
    boolean bigprint = false;
	private int sizeX = 301, sizeY = 271; // Pig
	String imagename = "Pig.jpg";
	String imagename2 = "Game.jpg";

	public PigPartPerfection ( )   {
		xpos = ypos = 220;		// center the picture in the frame	
		panelnumber = 1;
		openpanel = 1;
	}
	
	public static void main (String[] args) {
		PigPartPerfection si = new PigPartPerfection();
		si.Start();
		si.Instructions();
		si.Game();
	}
 
	public void Start()  {
		if (panelnumber == 1){
			System.out.println("Start");
			// Create the JFrame 
			frame = new JFrame("PigPartPerfection");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null); //no layout manager
			canvas = new DrawingArea ( );
			canvas.setBackground(new Color(75, 0, 130));
			
			// Get the Image from a file
			GetMyImage();
			GetMyImage2();
	
			frame.getContentPane().add( canvas );
			frame.addKeyListener(canvas);
			canvas.setBounds(0, 75, 800, 700); //0 x, 100 y, 800 wide, 700 tall
	
			start = new JLabel("          Pig Parts Perfection");
			start.setFont(new Font ("Berlin Sans FB Demi", Font.BOLD, 60));
			start.setForeground(new Color(255, 133, 133));
			frame.getContentPane().add(start);
			start.setBounds(0, 0, 800, 80);
			start.setOpaque(true);
			start.setBackground(new Color(75, 0, 130));
			
	
			frame.setBackground(new Color(255, 133, 133));
			frame.setSize(800, 700);
			frame.setLocation(300, 0);
			frame.setResizable(false);
			frame.setVisible(true);
		}
	}
	public void GetMyImage() {
		try {
			image = ImageIO.read(new File(imagename));
		}
		catch (IOException e) {
			System.err.println("ERROR: Cannot read image file");
			System.exit(1);
		}
	}
	public void GetMyImage2() {
		try {
			image2 = ImageIO.read(new File(imagename2));
		}
		catch (IOException e) {
			System.err.println("ERROR: Cannot read image file");
			System.exit(1);
		}
	}
	public void Instructions(){
		if (panelnumber == 2){
			System.out.println("I");
			frame = new JFrame("InstructionsPanel");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null); //no layout manager
			canvas = new DrawingArea ( );
			canvas.setBackground(new Color(75, 0, 130));

			frame.getContentPane().add( canvas );
			frame.addKeyListener(canvas);
			canvas.setBounds(0, 75, 800, 700); //0 x, 100 y, 800 wide, 700 tall

			instructions = new JLabel("Instructions");
			instructions.setFont(new Font ("Goudy Stout", Font.BOLD, 55));
			instructions.setForeground(new Color(200, 255, 0));
			frame.getContentPane().add(instructions); 
			instructions.setBounds(0, 0, 800, 80);
			instructions.setOpaque(true);
			instructions.setBackground(new Color(75, 0, 130));

			frame.setBackground(new Color(255, 133, 133));
			frame.setSize(800, 700);
			frame.setLocation(300, 0);
			frame.setResizable(false);
			frame.setVisible(true);

		}
	}
	public void Game(){
		openpanel++;
		if (panelnumber == 3){
			System.out.println("Game");
			frame = new JFrame("Game");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        g = new Grid();
	        p = new Panel();
	
	        frame.setSize(new Dimension(800, 700));
	        frame.setBackground(Color.black);
	        frame.add(l, BorderLayout.CENTER);
	        l.setBounds(0, 0, 780, 660); //Boundaries of the panel
	        g.setBounds(0, 0, 780, 660); //Boundaries of the panel
	        g.setOpaque(true); 
	        p.setOpaque(false);
	        p.setBounds(0, 0, 800, 700);
	        l.add(g, new Integer(0), 0); //add grid to the layeredpane
	        l.add(p, new Integer(1), 0); //add panel (backdrop) to the layeredpane
	        frame.setVisible(true); //frame- set visible
	        g.setBorder(BorderFactory.createLineBorder(new Color(25, 25, 112), 15)); //creates blue border around program
		}
	}
    class Grid extends JPanel { 
        public Grid() {
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //Draw the gray lines onto the canvas
            g.setColor(new Color(0, 50, 190));
            for (int col = 0; col < 10; col++) {f
                for (int row = 0; row < 10; row++) {
                    g.drawRect(row * 48 + 10, col * 48 + 10, 48, 48);
                }
            }
        }
    }
    class Panel extends JPanel implements KeyListener, MouseListener {

    	private Scanner inFile;
    	private PrintWriter outFile;
    	private String number;
    	
    	public Panel() {
            frame.addKeyListener(this);
            Mover mover = new Mover(); //Timer for the movement of the monster
            Timer timer = new Timer(1000, mover); //how often the monsters move
            PMover PMover = new PMover(); //Timer for the animation of Piece
            Timer timer2 = new Timer(400, PMover); //the speed of Piece's changing colors
            timer.start();  //Starting of the timers
            timer2.start(); //Starting of the timers
            if (gameover == true){
            	timer.stop();
            	timer2.stop();
            }
        } //end public Panel

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //Directions at the bottom of the page
            g.setColor(Color.red);
            g.fillRect(15, 500, 500, 60);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Directions:   j = left, l = right,i = up", 15, 520);
            g.drawString("k = down, r = new game", 160, 540);
            g.fillRect(540, 10, 200, 50);
            g.setColor(Color.cyan);
            g.drawString("END", 600, 40);

            if (move == 0) { //if first move - Piece, monster
                xloc = 0 + (int)(Math.random() * ((9 - 0) + 1));
                yloc = 0 + (int)(Math.random() * ((9 - 0) + 1));
                array[xloc][yloc] = 4;

                for (int i = 0; i < 6; i++) {
                    int xcheese = 0 + (int)(Math.random() * ((9 - 0) + 1));
                    int ycheese = 0 + (int)(Math.random() * ((9 - 0) + 1));
                    
                    while ((xcheese == xloc) && (ycheese == yloc)) { //checking for duplicates of cheese
                        xcheese = 0 + (int)(Math.random() * ((9 - 0) + 1));
                        ycheese = 0 + (int)(Math.random() * ((9 - 0) + 1));
                    }

                    while ((xcheese == xloc) && (ycheese == yloc)) { //checking for duplicates of Piece
                        xcheese = 0 + (int)(Math.random() * ((9 - 0) + 1));
                        ycheese = 0 + (int)(Math.random() * ((9 - 0) + 1));
                    }

                    while (array[xcheese][ycheese] == 3) { //saving random location for cheese
                        xcheese = 0 + (int)(Math.random() * ((9 - 0) + 1));
                        ycheese = 0 + (int)(Math.random() * ((9 - 0) + 1));
                    }
                    //cheesearray[xcheese][ycheese] = 2;
                } //end cheese

                for (int i = 0; i < 6; i++) {
                    int xmonster = 0 + (int)(Math.random() * ((9 - 0) + 1));
                    int ymonster = 0 + (int)(Math.random() * ((9 - 0) + 1));

                    while ((xmonster == xloc) && (ymonster == yloc)) { //checking for duplicates of Piece
                        xmonster = 0 + (int)(Math.random() * ((9 - 0) + 1));
                        ymonster = 0 + (int)(Math.random() * ((9 - 0) + 1));
                    }

                while (array[xmonster][ymonster] == 3) { //checking for duplicates of monster
                        xmonster = 0 + (int)(Math.random() * ((9 - 0) + 1));
                        ymonster = 0 + (int)(Math.random() * ((9 - 0) + 1));

                        while ((xmonster == xloc) && (ymonster == yloc)) {
                            xmonster = 0 + (int)(Math.random() * ((9 - 0) + 1));
                            ymonster = 0 + (int)(Math.random() * ((9 - 0) + 1));
                        }
                    }
                    array[xmonster][ymonster] = 3;
                }
                move++; //incrementing number of moves
            }
            //Printing of the playing field
            for (int i = 0; i < 14; i++) {
                for (int j = 0; j < 16; j++) {
                    int a = array[i][j];

                    if (a == 3) { //monster
                    	g.setColor(new Color(255, 133, 133));
                    	g.fillRect(((i + 1) * 48) - 35, ((j + 1) * 48) - 35, 40, 40);
                    	//g.fillOval(((i + 1) * 48) - 35, ((j + 1) * 48) - 35, 40, 40);
                    	g.setColor(Color.black);
                    	g.fillRect(((i + 1) * 48) - 29, ((j+1) * 48) - 30, 10, 13);
                    	//g.fillOval(((i + 1) * 48) - 27, ((j+1) * 48) - 30, 10, 17);
                    	g.fillRect(((i + 1) * 48) - 11, ((j+1) * 48) - 30, 10, 13);
                    	//g.fillOval(((i + 1) * 48) - 13, ((j+1) * 48) - 30, 10, 17);
                    	g.fillArc(((i + 1) * 48) - 30, ((j + 1) * 48) - 30, 30, 35, 180, 180);
                    }
                    
                    if (a == 4) { //Piece purple
                    	qtimer++;
                        g.setColor(new Color(102, 0, 102));
                        g.fillArc(((i + 1) * 48) - 35, ((j + 1) * 48) - 35, 40, 40, 0, 360);
                    }

                    if (a == 5) { //Piece green
                        g.setColor(new Color(0, 204, 0));
                        g.fillArc(((i + 1) * 48) - 35, ((j + 1) * 48) - 35, 40, 40, 0, 360);
                    }
                }
            }
        /*    String one = "Apex";
  		   String two = "Septum";
  		   String three = "Mesentary";
  		   String four = "Auricle";
  		   String five = "Aorta";
  		  JRadioButton apex = new JRadioButton(one);
        apex.setActionCommand(one);
        apex.setSelected(true);
 
        JRadioButton septum = new JRadioButton(two);
        septum.setActionCommand(two);
 
        JRadioButton mesentary = new JRadioButton(three);
        mesentary.setActionCommand(three);
 
        JRadioButton auricle = new JRadioButton(four);
        auricle.setActionCommand(four);
 
        JRadioButton aorta = new JRadioButton(five);
        aorta.setActionCommand(five);
        
        ButtonGroup group = new ButtonGroup();
        group.add(apex);
        group.add(septum);
        group.add(mesentary);
        group.add(auricle);
        group.add(aorta);
        
        p.add(apex);
        p.add(septum);
        p.add(mesentary);
        p.add(auricle);
        p.add(aorta);*/
        
            while (gameover == true) { //if the game is over
               	g.setColor(Color.black);
            	g.fillRoundRect(550, 500, 170, 120, 10, 10);
            	g.setColor(new Color(40, 250, 200));
            	g.setFont(new Font("Arial", Font.BOLD, 32)); 
            	g.drawString("When done", 550, 560);
            	g.drawString("Press P", 550, 580);
      		  
            	//repaint();
        		//questionnumber++;
                g.setFont(new Font("Arial", Font.BOLD, 50)); 
                g.setColor(new Color(40, 250, 200));
                g.drawString("QUESTION TIME!", 50, 300); 
                g.setFont(new Font("Arial", Font.BOLD, 24)); 
                g.setColor(new Color(102, 0, 102));
                if (round1 == true){
                	g.drawString("a. Apex", 560, 100);
                	g.drawString("b. Mesentary",560 , 150);
                	g.drawString("c. Auricle", 560, 200);
                	g.drawString("d. Septum", 560, 250);
                	g.drawString("e. Aorta", 560, 300);
                	g.drawString("f. L.E.Sphincter", 560, 350);
                }
                else{
                	g.drawString("a. Coronary Artery", 560, 100);
                	g.drawString("b. Tricuspid Valve",560 , 150);
                	g.drawString("c. Thymus Gland", 560, 200);
                	g.drawString("d. Pericardium", 560, 250);
                	g.drawString("e. Bicuspid Valve", 560, 300);
                	g.drawString("f. Pulmonary Artery", 560, 350);
                	
                }
                switch (questionnumber){
                case 1: 
                questionmod++;
                g.drawString("Question #1", 15, 580);
                questionnumber = 1;
                System.out.println("GAMEOVERLOOP");
            	System.out.println("switch");
            	g.setColor(Color.black);
            	g.fillRoundRect(550, 500, 170, 120, 10, 10);
            	g.setColor(new Color(40, 250, 200));
            	g.setFont(new Font("Arial", Font.BOLD, 32)); 
            	g.drawString("When done", 550, 560);
            	g.drawString("Press P", 550, 580);
            	g.setColor(Color.black);
            	//System.out.println("1");
            	if (inFile.hasNext()) {
            		g.setFont(new Font("Arial", Font.ITALIC, 24));
            		if (questionmod == 1){
            			System.out.println("questionmod");
            			number = inFile.nextLine();
            		}
        			System.out.println("Question1: " + number);
        			if (round1 == true)
        				g.drawString(number, 15, 610); 
        				//apex
        			else{
        				//The artery that travels across the outside of the heart, delivering oxygenated blood to the cells and tissue of the heart. - Coronary Artery
        				g.drawString("Artery- travels across the outside of the heart", 15, 600);
        				g.drawString("delivers oxy. blood to the cells and tissue of the heart.", 15, 620);
        			}
        			  /*if (answer == false){
                      	g.drawString("Sorry, that answer", 560, 420);
                      	g.drawString("is incorrect.", 560, 440);
                      	
                      }
        			  if (answer == true){
                        	g.drawString("Choose the Right Answer", 560, 420);  	
                        }*/
        		}
            	break;
            	case 2: 
            		 questionmod++;
                     g.drawString("Question #2", 15, 580);
                     questionnumber = 2;
                     System.out.println("GAMEOVERLOOP");
                 	System.out.println("switch");
                 	g.setColor(Color.black);
                 	g.fillRoundRect(550, 500, 170, 120, 10, 10);
                 	g.setColor(new Color(40, 250, 200));
                 	g.setFont(new Font("Arial", Font.BOLD, 32)); 
                 	g.drawString("When done", 550, 560);
                	g.drawString("Press P", 550, 580);
                 	//System.out.println("2");
                 	g.setColor(Color.black);
             		g.setFont(new Font("Arial", Font.ITALIC, 24));
             		if (round1 == true){
	         			g.drawString("The circular muscle located between the esophagus", 15, 600); 
	         			g.drawString("and the stomach ", 15, 620);
	         			//L.E.S
             		}
             		else{
             			g.drawString("Artery-delivers deoxy. blood from the right ventricle", 15, 600);
             			g.drawString("to the lungs (will be oxygenated)", 15, 620);
             			//pulmonary artery           
             		}
     			  /*if (answer == false){
                  	g.drawString("Sorry, that answer", 560, 420);
                  	g.drawString("is incorrect.", 560, 440);
                  }
     			 if (answer == true){
                 	g.drawString("Good Job! :)", 560, 420);  	
                 }*/
                	break;
                case 3: 
                	questionmod++;
                    g.drawString("Question #3", 15, 580);
                    questionnumber = 3;
                    System.out.println("GAMEOVERLOOP");
                	System.out.println("switch");
                	g.setColor(Color.black);
                	g.fillRoundRect(550, 500, 170, 120, 10, 10);
                	g.setColor(new Color(40, 250, 200));
                	g.setFont(new Font("Arial", Font.BOLD, 32)); 
                	g.drawString("When done", 550, 560);
                	g.drawString("Press P", 550, 580);
                	//System.out.println("3");
                	g.setColor(Color.black);
             		g.setFont(new Font("Arial", Font.ITALIC, 24));
             		if (round1 == true){
	         			g.drawString("Tube-connects the left ventricle to the.", 15, 600); 
	         			g.drawString(" descending aorta", 15, 620);
	         			//aorta
             		}
             		else     			
             			g.drawString("prevents backflow of oxygenated blood.", 15, 610);
             			//bicuspid
         		  /*if (answer == false){
                  	g.drawString("Sorry, that answer", 560, 420);
                  	g.drawString("is incorrect.", 560, 440);
                  	
                  }
         		 if (answer == true){
                 	g.drawString("Good Job! :)", 560, 420);  	
                 }*/
                	break;
                case 4: 
                	questionmod++;
                    g.drawString("Question #4", 15, 580);
                    questionnumber = 4;
                    System.out.println("GAMEOVERLOOP");
                	System.out.println("switch");
                	g.setColor(Color.black);
                	g.fillRoundRect(550, 500, 170, 120, 10, 10);
                	g.setColor(new Color(40, 250, 200));
                	g.setFont(new Font("Arial", Font.BOLD, 32)); 
                	g.drawString("When done", 550, 560);
                	g.drawString("Press P", 550, 580);
                	//System.out.println("4");
                	g.setColor(Color.black);
             		g.setFont(new Font("Arial", Font.ITALIC, 24));
             		if (round1 == true){
	         			g.drawString("Tissue- contains the capillaries. The nutrients in ", 15, 600); 
	         			g.drawString("the food that enter the small intestine are absorbed villi.", 15, 620);
	         			//Mesentary
             		}
             		else
             			g.drawString("prevents backflow of deoxygenated blood.", 15, 610);
             			//tricuspid
         		  /*if (answer == false){
                  	g.drawString("Sorry, that answer", 560, 420);
                  	g.drawString("is incorrect.", 560, 440);
                  	
                  }
         		 if (answer == true){
                 	g.drawString("Good Job! :)", 560, 420);  	
                 }*/
                	break;
                case 5: 
                	questionmod++;
                    g.drawString("Question #5", 15, 580);
                    questionnumber = 5;
                    System.out.println("GAMEOVERLOOP");
                	System.out.println("switch");
                	g.setColor(Color.black);
                	g.fillRoundRect(550, 500, 170, 120, 10, 10);
                	g.setColor(new Color(40, 250, 200));
                	g.setFont(new Font("Arial", Font.BOLD, 32)); 
                	g.drawString("When done", 550, 560);
                	g.drawString("Press P", 550, 580);
                	//System.out.println("5");
                	g.setColor(Color.black);
             		g.setFont(new Font("Arial", Font.ITALIC, 24));
             		if (round1 == true){
	         			g.drawString("Helps expand the volume of the atira, allowing for", 15, 600); 
	         			g.drawString("larger capacity of blood. ", 15, 620);
	         			//Auricle
             		}
             		else
             			//thymus gland
             			g.drawString("Releases thymosins which stimulates T-Cell development", 15, 610);
         		  /*if (answer == false){
                  	g.drawString("Sorry, that answer", 560, 420);
                  	g.drawString("is incorrect.", 560, 440);
                  	
                  }
         		 if (answer == true){
                 	g.drawString("Good Job! :)", 560, 420);  	
                 }*/
                	break;
                case 6: 
                	questionmod++;
                   g.drawString("Question #6", 15, 580);
                    questionnumber = 6;
                    System.out.println("GAMEOVERLOOP");
                	System.out.println("switch");
                	g.setColor(Color.black);
                	g.fillRoundRect(550, 500, 170, 120, 10, 10);
                	g.setColor(new Color(40, 250, 200));
                	g.setFont(new Font("Arial", Font.BOLD, 32)); 
                	g.drawString("When done", 550, 560);
                	g.drawString("Press P", 550, 580);
                	//System.out.println("6");
                	g.setColor(Color.black);
             		g.setFont(new Font("Arial", Font.ITALIC, 24));
             		if (round1 == true){
             			g.drawString("Barrier in the middle of the heart-separates the left", 15, 600); 
             			g.drawString("and right so the oxy. and deoxy. blood don't mix. ", 15, 620);
             			//Septum
             		}
             		else
             			//Pericardium
             			g.drawString("Membrane-protects the heart and keeps it in place.", 15, 610);
         		  /*if (answer == false){
                  	g.drawString("Sorry, that answer", 560, 420);
                  	g.drawString("is incorrect.", 560, 440);
                  	
                  }
         		 if (answer == true){
                 	g.drawString("Good Job! :)", 560, 420);  	
                 }*/
                	break;
                }
                System.out.println("directions");
                System.out.println(answer + " atop");
                if (answer == false){
                	outFile.printf("questionnumber = %d\n", questionnumber);
                  	g.drawString("Sorry, that answer", 560, 420);
                  	g.drawString("is incorrect.", 560, 440);
                  	
                  }
         		 if (answer == true){
                 	g.drawString("Select the", 560, 410); 
                 	g.drawString("right answer!", 560, 450);
                 }
         		 if (bigprint == true){
         			g.setFont(new Font("Arial", Font.BOLD, 32)); 
         			g.drawString("CORRECT!", 200, 450);
         		 }
               //gameover = false;
                return;
            }

            String fileName = "GameQuestions.txt";
    		try {
    			inFile = new Scanner(new File(fileName)); 
    		} catch (FileNotFoundException e) {
    			System.err.printf("ERROR: Cannot open %s\n", fileName);
    			System.exit(1);
    		}
    		try {
    		  outFile = new PrintWriter(new File("GameAnswers.txt"));
    		} catch (IOException e) {
    		  e.printStackTrace();
    		  System.exit(1);
    		}
    		//inFile.close();
    		

        }
        
        public void actionPerformed(ActionEvent e) {
            System.out.println("actionPerformed");
        }

        private class PMover implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 14; i++) {
                    for (int j = 0; j < 16; j++) {
                        int a = array[i][j];
                        if (gameover == false){
    						if (a == 4) { //if the mouth is purple
    							array[i][j] = 5; //make it green
    							repaint();
    						}
    						if (a == 5) { //if the mouth is green 
    							array[i][j] = 4; //make it purple
    							repaint();
    						}
    					}
                    }
                }
            }
        }
        private class Mover implements ActionListener {
            public void actionPerformed(ActionEvent e) {
            	for (int i = 0; i < 14; i++) {
                    for (int j = 0; j < 16; j++) {
                        int a = array[i][j];

                        if (a == 3) { //monster
                            int random = 1 + (int)(Math.random() * ((4 - 1) + 1));
                            switch (random) {
                                case 1:
                                	if (gameover == false) {
                                        if (i == 0) //Checking boundaries
                                        	break;
                                        if (array[i - 1][j] == 3) //If he encounters another monster
                                        	break;
                                        array[i][j] = 1;
                                        array[i - 1][j] = 3;
                                        if (array[i - 1][j] == 4){ //If he runs into Piece, game over
                                        	gameover = true;
                                        	questionnumber++;
                                        	answer = true;
                                        	bigprint = false;
                                        }
                                        break;
                                	}

                                case 2:
                                	if (gameover == false){
                                        if (i == 9) //Checking boundaries
                                        	break;
                                        if (array[i + 1][j] == 3) //checking for monsters
                                        	break;
                                        if (array[i + 1][j] == 4){ //checking for Piece
                                        	gameover = true;
                                        	questionnumber++;
                                        	answer = true;
                                        	bigprint = false;
                                        }
                                        array[i][j] = 1;
                                        array[i + 1][j] = 3;
                                        break;
                                	}

                                case 3:
                                	if (gameover == false){
                                        if (j == 0) //checking boundaries
                                        	break;
                                        if (array[i][j - 1] == 3) //checking for other monsters
                                        	break;
                                        if (array[i][j - 1] == 4){ //checking for Piece
                                        	gameover = true;
                                        	questionnumber++;
                                        	answer = true;
                                        	bigprint = false;
                                        }
                                        array[i][j] = 1;
                                        array[i][j - 1] = 3;
                                        break;
                                	}

                                case 4:
                                	if (gameover == false){
                                        if (j == 9) //checking for out of bounds
                                        	break;
                                        if (array[i][j + 1] == 3) //checking for other monsters
                                        	break;
                                        if (array[i][j + 1] == 4){ //checking for Piece
                                        	gameover = true;
                                        	questionnumber++;
                                        	answer = true;
                                        	bigprint = false;
                                        }
                                        array[i][j] = 1;
                                        array[i][j + 1] = 3;
                                        break;
                                	}
              
                            }
                            repaint();
                        }
                    }
                }
            }
        }
    	    public void mouseClicked(MouseEvent e) {
    			System.out.println("MouseEvent");
    			int xloc, yloc;
    			xloc = yloc = 0;
    			xloc = e.getX();
    			yloc = e.getY();
    			if (gameover == true){
    				if (xloc > 500 && yloc > 500){
    					gameover = false;
    				}
    			}
    		}
    		public void mouseEntered(MouseEvent e) {
    			System.out.println("entered");
    		}
    		public void mouseExited(MouseEvent e) {}
    		public void mousePressed(MouseEvent e) {}
    		public void mouseReleased(MouseEvent e) {}
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        	public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                outFile.print(c);
                switch (c) { //Switch- direction for Piece- (user types key)
                    case 'j': 
                    	if (gameover == false){
                        if (xloc == 0) { //If going out of bounds
                            array[9][yloc] = 4; //Piece
                            if (array[9][yloc] == 3) { //if he encounters a monster
                            	array[9-1][yloc] = 1;
                                gameover = true; //game is over
                                questionnumber++;
                                answer = true;
                                bigprint = false;
                            }
                            array[xloc][yloc] = 1;
                            xloc = 9;
                            repaint();
                            break;
                        }
                        if (array[xloc - 1][yloc] == 3) {
                            gameover = true;
                            questionnumber++;
                            answer = true;
                            bigprint = false;
                        }
                        array[xloc - 1][yloc] = 4;
                        array[xloc][yloc] = 1;
                        xloc = xloc - 1;
                        repaint();
                        break;
                    	}
                    	else {
                    		break;
                    	}
                    case 'l':
                    	if (gameover == false){
                        if (xloc == 9) {
                            array[0][yloc] = 4;
                            if (array[0][yloc] == 3) { //If Piece runs into a monster
                                gameover = true; 
                                questionnumber++;
                                answer = true;
                                bigprint = false;
                            }
                            array[xloc][yloc] = 1;
                            xloc = 0;
                            repaint();
                            break;
                        }

                        if (array[xloc + 1][yloc] == 3) { //runs into monster- game over
                            gameover = true;
                            questionnumber++;
                            answer = true;
                            bigprint = false;
                        }
                        array[xloc + 1][yloc] = 4;
                        array[xloc][yloc] = 1;
                        xloc = xloc + 1;
                        repaint();
                        break;
                    	}
                    	else {
                    		System.out.println("Game Over");
                    		break;
                    	}
                    case 'i':
                    	if (gameover == false){
                        if (yloc == 0) {
                            array[xloc][9] = 4;
                            if (array[xloc][9] == 3) { //checks if game is over
                                gameover = true;
                                questionnumber++;
                                answer = true;
                                bigprint = false;
                            }
                            array[xloc][yloc] = 1; //moving Piece
                            yloc = 9;
                            repaint();
                            break;
                        }

                        if (array[xloc][yloc - 1] == 3) {
                            gameover = true;
                            questionnumber++;
                            answer = true;
                            bigprint = false;
                        }
                        array[xloc][yloc - 1] = 4;
                        array[xloc][yloc] = 1;
                        yloc = yloc - 1;
                        repaint();
                        break;
                    	}
                    	else {
                    		System.out.println("Game Over");
                    		break;
                    	}
                    case 'k':
                    	if (gameover == false){
                        if (yloc == 9) {
                            array[xloc][0] = 4;

                            if (array[xloc][0] == 3) { //If encounters a monster
                                gameover = true;
                                questionnumber++;
                                answer = true;
                                bigprint = false;
                            }
                            array[xloc][yloc] = 1;
                            yloc = 0;

                            repaint();
                            break;
                        }		
                        if (array[xloc][yloc + 1] == 3) { //if monster encountered
                            gameover = true;
                            questionnumber++;
                            answer = true;
                            bigprint = false;
                        }
                        array[xloc][yloc + 1] = 4;
                        array[xloc][yloc] = 1;
                        yloc = yloc + 1;
                        repaint();
                        break;
                    	}
                    	else {
                    		System.out.println("Game Over");
                    		break;
                    	}
            	case 'r': //resetting
            		answer = true;
                    for (int i = 0; i < 14; i++) { //clearing the board
                        for (int j = 0; j < 16; j++) {
                            array[i][j] = 1;
                            //cheesearray[i][j] = 1;
                        }
                    }
                    questionnumber = 0;
                    move = 0; //reseting number of moves back to zero
                    gameover = false; //playing game - so game not over
                    if (round1 == true)
                    	round1 = false;
                    else
                    	round1 = true;
                    break;
            	/*case 'e':
            		gameover = true;
            		panelnumber = 1;
            		Start();*/
            	case 'a':
            		if (questionnumber == 1){
            			answer = true;
            			bigprint = true;
            		}
            		else {
            			answer = false;
            			repaint();
            		}
            		break;
            	case 'b':
            		if (questionnumber == 4){
            			answer = true;
            			bigprint = true;
            		}
            		else 
            			answer = false;
            		break;
            	case 'c':
            		if (questionnumber == 5){
            			answer = true;
            			bigprint = true;
            		}
            		else 
            			answer = false;
            		break;
            	case 'd':
            		if (questionnumber == 6){
            			answer = true;
            			bigprint = true;
            		}
            		else {
            			System.out.println("d");
            			answer = false;
            			System.out.println(answer);
            		}
            		break;
            	case 'e':
            		if (questionnumber == 3){
            			answer = true;
            			bigprint = true;
            		}
            		else 
            			answer = false;
            		break;
            	case 'f':
            		if (questionnumber == 2){
            			answer = true;
            			bigprint = true;
            		}
            		else
            			answer = false;
            		break;
            	case 'n':
            		Start();
            	case 'p':
            		gameover = false;
            		repaint();
               default: 
            	   System.out.println("N/A");
            	   break;
        	}
                repaint();
        }
    }
	// canvas 
	class DrawingArea extends JPanel implements MouseListener, KeyListener {
		public DrawingArea ( )   {
			addMouseListener (this);
			addKeyListener (this);
		}
		public void paintComponent ( Graphics g ) {
			super.paintComponent ( g );	
			if (panelnumber == 1){
				System.out.println("1");
				g.drawImage(image, xpos, ypos, sizeX, sizeY, this);
			}
				g.fillRoundRect(550, 500, 200, 120, 10, 10);
	        	g.setColor(new Color(40, 250, 200));
	        	g.setFont(new Font("Arial", Font.BOLD, 32)); 
	        	g.drawString("Instructions", 560, 560);
			if (panelnumber == 2){
				System.out.println("2");
				g.drawImage(image, xpos, ypos, sizeX, sizeY, this);
				g.setColor(Color.green);
				g.fillRect(0, 0, 800, 800);
				g.setColor(Color.blue);
				g.setFont(new Font("Arial", Font.BOLD, 26));
	            g.drawString("Welcome to Pig Parts Pefection! In this game, you will ", 15, 52);
	            g.drawString("learn the parts of a pig, and be able to master them! ", 15, 82);
	            g.drawString("You will be playing the game on a grid, trying to catch the ", 15, 112);
	            g.drawString("moving pink pigs. Your playing piece will be flashing purple and ", 15, 142);
	            g.drawString("green. Once you catch one, a question will pop up, and you  ", 15, 172);
	            g.drawString("will have to select one of the terms at the top of the page. ", 15, 202);
	            g.drawString("Answer by typing the letter for the answer choice", 15, 232);
	            g.drawString("Press 'p' after you answer the question to continue playing. ", 15, 262);
	            g.drawString("If a pig passes by you and you don't catch it, you will become", 15, 292);
	            g.drawString("invisible till you move again. There are 6 pigs - equal to 6", 15, 322);
	            g.drawString("pig related questions, and when you press 'r', they reset.", 15, 352);
	            g.setFont(new Font("Arial", Font.BOLD, 48));
	            g.drawString("                 GOOD LUCK! :)", 15, 450);
	            g.setColor(Color.darkGray);
	            g.fillRoundRect(550, 500, 200, 120, 10, 10);
	        	g.setColor(new Color(40, 250, 200));
	        	g.setFont(new Font("Arial", Font.BOLD, 32)); 
	        	g.drawString("Information", 562, 560);
			}
		   if (panelnumber == 3){
			   if (openpanel == 1)
				   Game();
			   System.out.println("3");
	            g.setColor(Color.pink);
	            g.drawString("WELCOME TO PIG PARTS PERFECTION", 100, 85);
	            g.setFont(new Font("Arial", Font.ITALIC, 15));
	            g.drawString("Apex: the tip of the heart", 20, 140);
	            g.drawString("Mesentary: Tissue- contains the capillaries. The nutrients in the food that enter the small intestine are absorbed villi.", 20, 170);
	            g.drawString("Auricle: Helps expand the volume of the atira, allowing for larger capacity of blood.", 20, 200);
	            g.drawString("Septum: Barrier in the middle of the heart-separates the left and right so the oxy. and deoxy. blood don't mix.", 20, 230);
	            g.drawString("Aorta: Tube-connects the left ventricle to the descending aorta", 20, 260);
	            g.drawString("L.E.Sphincter: The circular muscle located between the esophagus and the stomach.", 20, 290);
	            g.drawString("Coronary Artery: Artery -travels across the outside of the heart. Delivers oxy. blood to the cells and tissue of the heart. ", 20, 320);
	            g.drawString("Tricuspid Valve: Prevents backflow of deoxygenated blood.", 20, 350);
	            g.drawString("Thymus Gland: Releases thymosins which stimulates T-Cell development", 20, 380);
	            g.drawString("Pericardium: Membrane-protects the heart and keeps it in place.", 20, 410);
	            g.drawString("Bicuspid Valve: Prevents backflow of oxygenated blood", 20, 440);
	            g.drawString("Pulmonary Valve: Artery-delivers deoxy. blood from the right ventricle to the lungs (will be oxygenated)", 20, 470);
	            g.setColor(Color.darkGray);
	            g.fillRoundRect(550, 500, 200, 120, 10, 10);
	        	g.setColor(new Color(40, 250, 200));
	        	g.setFont(new Font("Arial", Font.BOLD, 32)); 
	        	g.drawString("Click to Start", 550, 560);
	        	/*JComboBox list = new JComboBox();
	        	list.addItem("Purple");
	        	list.addItem("Blue");
	        	list.addItem("Green");
	        	list.setSelectedIndex(0);
	        	list.addActionListener((ActionListener) this);*/
		   }
		}
		/*public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			String name = (String)cb.getSelectedItem();
			if (name == "Purple"){}
			else if (name == "Blue"){}
			else if (name == "Green"){}
			System.out.println("Your color choice is " + (String)cb.getSelectedItem());
		}*/
		// Mouse methods
		public void mousePressed ( MouseEvent e )   {
			int xloc, yloc;
			xloc = yloc = 0;
			xloc = e.getX();
			yloc = e.getY();
			if (panelnumber ==3){
				if (xloc > 500 && yloc > 500){
					Game();
					panelnumber = 1;
				}
			}
			if (panelnumber ==2){
				if (xloc > 500 && yloc > 500){
					Game();
					panelnumber = 3;
				}
			}
			if (panelnumber == 1){
				if (xloc > 500 && yloc > 500){
					Instructions();
					panelnumber = 2;
				}	
			}
	
		}
		public void mouseClicked ( MouseEvent e )    {
			repaint();
		}
		public void mouseReleased ( MouseEvent e )    {}
		public void mouseEntered ( MouseEvent e )    {}
		public void mouseExited ( MouseEvent e )    {}
		// Key methods
		public void keyPressed (KeyEvent e) {}
		public void keyTyped (KeyEvent e) {}
		public void keyReleased (KeyEvent e) {}
	}
	public void mousePressed ( MouseEvent e )   {
		int xloc, yloc;
		xloc = yloc = 0;
		xloc = e.getX();
		yloc = e.getY();
		if (xloc > 500 && yloc > 500){
			panelnumber = 2;
			Instructions();
		}
		if (xloc > 500 && yloc > 500){
			panelnumber = 3;
			Game();
		}
		if (xloc > 500 && yloc > 500){
			panelnumber = 1;
			Start();
		}
	}
	public void mouseClicked ( MouseEvent e )    {}
	public void mouseReleased ( MouseEvent e )    {}
	public void mouseEntered ( MouseEvent e )    {}
	public void mouseExited ( MouseEvent e )    {}
	// Key methods
	public void keyPressed (KeyEvent e) {}
	public void keyTyped (KeyEvent e) {}
	public void keyReleased (KeyEvent e) {}
	
}
