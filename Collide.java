//Checks when the slider piece (moves side to side), diag piece (moves diagonally) and the jumper piece (random) collide
import java.util.Random;

public class Collide {
	boolean sAlive, dAlive, jAlive;
	Piece s1, d1, j1; 			///slider, diag, jumper
	int count; 			///counts number of turns taken
	int sRow, sCol;
	int dRow, dCol;
	int jRow, jCol;
	
	int SrowPosition, ScolPosition;
	int SoldRowPosition, SoldColPosition;
	
	int DrowPosition, DcolPosition;
	int DoldRowPosition, DoldColPosition;
	
	int JrowPosition, JcolPosition;
	int JoldRowPosition, JoldColPosition;
	int coincount;
	
	Random r = new Random();
	char []A = new char [5];  //Array A -- first row
	char []B = new char [5];  //Array B -- second row
	char []C = new char [5];  //Array C -- third row
	char []D = new char [5];  //Array D -- fourth row
	char []E = new char [5];  //Array E -- fifth row

	public Collide () {
		//s1 = new Slider();
		//d1 = new Diag();
		//j1 = new Jumper();
		count = 0;		//counts the number of turns
		sAlive = true;
		dAlive = true;
		jAlive = true;
		coincount = 3;
	}
	
	public static void main (String [] args) {
		Collide cg = new Collide();
		cg.playAll();
	}
	
	/*
	 * initialize the board
	 */
	public void initializeBoard() {			//This method Prints the Initial Board, where it is all zeros
		//System.out.println();
		for (int a = 0; a < 5; a++) { //print till there are 5 zeros in the array
			A[a] = '_';
		}		
		for (int b = 0; b <5; b++){
			B[b] = '_';
		}
		
		for (int c = 0; c <5; c++){
			C[c] = '_';
		}
		
		for (int d = 0; d <5; d++){
			D[d] = '_';
		}
		
		for (int e = 0; e <5; e++){
			E[e] = '_';
		}
		
	}
	/*
	 * printing board
	 */
	public void printBoard(){ //prints board with 1. the first values..and then 2. changing value every time
		for (int a = 0; a < 5; a++) {
			System.out.print(A[a] + "  ");
		}
		System.out.println();
		
		for (int b = 0; b <5; b++){
			System.out.print(B[b] + "  ");  
		}
		System.out.println();
		
		for (int c = 0; c <5; c++){
			System.out.print(C[c] + "  ");
		}
		System.out.println();
		
		for (int d = 0; d <5; d++){
			System.out.print(D[d] + "  ");
		}
		System.out.println();
		
		for (int e = 0; e <5; e++){
			System.out.print(E[e] + "  ");
		}
		System.out.println("\n");
	}
	
	/*
	 * Replacing old value with new value for S coordinate
	 */
	
	void setSPosition(int sRow, int sCol){
		//placing 'S'
		
		if ((sRow==JrowPosition) && (sCol==JcolPosition) && jAlive) {
			
			jAlive = false;
			coincount--;
			//System.out.println("JALIVE - " + coincount);
		}
		else if ((sRow==DrowPosition) && (sCol==DcolPosition) && dAlive) {
			
			dAlive = false;
			coincount--;
			//System.out.println("DALIVE - " + coincount);
		}
				switch (sRow) {
				
					case 0: 
						A[sCol] = 'S';
							break;
					case 1:
						B[sCol] = 'S';
							break;
					case 2:
						C[sCol] = 'S';
							break;
					case 3:
						D[sCol] = 'S';
							break;
					case 4:
						E[sCol] = 'S';
							break;
				}
	}
	/*
	 * Replacing old value with new value for D coordinate
	 */
	
	void setDPosition(int dRow, int dCol){
		//placing 'D'
		if ((dRow==JrowPosition) && (dCol==JcolPosition) && jAlive){
			jAlive = false;
			coincount--;
			//System.out.println("JALIVE - " + coincount);
		}
		else if ((dRow==SrowPosition) && (dCol==ScolPosition) && sAlive)  {
			
			sAlive = false;
			coincount--;
			//System.out.println("SALIVE - " + coincount);
		}
				switch (dRow) {
				
					case 0:
						A[dCol] = 'D';
							break;
					case 1:
						B[dCol] = 'D';
							break;
					case 2: 
						C[dCol] = 'D';
							break;
					case 3:
						D[dCol] = 'D';
							break;
					case 4:
						E[dCol] = 'D';
							break;
				}
	}
	
	void setJPosition(int jRow, int jCol){
		//placing 'J'
		
		if ((jRow==SrowPosition) && (jCol==ScolPosition) && sAlive) {
			sAlive = false;
			coincount--;
			//System.out.println("SALIVE - " + coincount);
		}
		else if ((jRow==DrowPosition) && (jCol==DcolPosition) && dAlive) {
			dAlive = false;
			coincount--;
			//System.out.println("DALIVE - " + coincount);
		}
				switch (jRow) {
				
					case 0:
						A[jCol] = 'J';
							break;
					case 1:
						B[jCol] = 'J';
							break;
					case 2: 
						C[jCol] = 'J';
							break;
					case 3:
						D[jCol] = 'J';
							break;
					case 4:
						E[jCol] = 'J';
							break;
				}
	}
	/*
	 * Taking out the old Char, and placing zero there
	 */
	void eraseOldChar(int row, int col){
		//System.out.println("eraseOldChar() row = " + row + "col = " + col);
		switch (row) {
		
		case 0: A[col] = '_';
				break;
		case 1:B[col] = '_';
				break;
		case 2:C[col] = '_';
				break;
		case 3:D[col] = '_';
				break;
		case 4:E[col] = '_';
				break;
	}
	}
	
	/*
	 * Play All - Slider, Diag, Jump
	 */
	
	public void playAll(){
		System.out.println("Game Board   count = " + count + "   Turn = Initial" );
		initializeBoard();
		//printBoard();
		
		Random rs = new Random();
		SrowPosition = rs.nextInt(5);
		ScolPosition = rs.nextInt(5);
		setSPosition(SrowPosition, ScolPosition);
		
		Random rd = new Random();
		DrowPosition = rd.nextInt(5);
		DcolPosition = rd.nextInt(5);		
		setDPosition(DrowPosition, DcolPosition);
		
		Random rj = new Random();
		JrowPosition = rj.nextInt(5);
		JcolPosition = rj.nextInt(5);		
		//System.out.println("*** INITIAL JrowPosition = "+ JrowPosition + "JcolPosition = " + JcolPosition);
		setJPosition(JrowPosition, JcolPosition);
		
		

		printBoard(); // taking values and placing the letters S, D, and J randomly on the board.
		
		Piece ps = new Slider();
		Piece pd = new Diag();
		Piece pj = new Jumper();

		//int i = 0;
		
		do {		
			
			
			if (sAlive) 
			{
				count++;
			System.out.println("Game Board   count = " + count + "   Turn = S" );	
			//System.out.println("*** in  S Move");
			eraseOldChar(SrowPosition, ScolPosition);
			String newSPosition = ps.movePiece(SrowPosition, ScolPosition);
			SrowPosition = Integer.parseInt(newSPosition.substring(0,1));
			ScolPosition = Integer.parseInt(newSPosition.substring(1));
			setSPosition(SrowPosition, ScolPosition);
			//System.out.println("after S Move");
			//System.out.println("looping " + i + " sAlive = " + sAlive + " dAlive = " + dAlive + " jAlive = " + jAlive + "coincount = " + coincount);
			printBoard();
			}

			
			if (dAlive) 
			{
				count++;
			System.out.println("Game Board   count = " + count + "   Turn = D" );	
			//System.out.println("*** in  D Move");
			//DoldRowPosition = DrowPosition;
			//DoldColPosition = DcolPosition;
			eraseOldChar(DrowPosition, DcolPosition);
			//System.out.println(newDPosition);
			String newDPosition = pd.movePiece(DrowPosition, DcolPosition);
			DrowPosition = Integer.parseInt(newDPosition.substring(0,1));
			DcolPosition = Integer.parseInt(newDPosition.substring(1));
			setDPosition(DrowPosition, DcolPosition);
			//System.out.println("looping " + i + " sAlive = " + sAlive + " dAlive = " + dAlive + " jAlive = " + jAlive + "coincount = " + coincount);
			printBoard();
			}

			if (jAlive) 
			{
				count++;
			System.out.println("Game Board   count = " + count + "   Turn = J" );	
			//System.out.println("*** in  J Move");
		    //JoldRowPosition = JrowPosition;
		    //JoldColPosition = JcolPosition;
			eraseOldChar(JrowPosition, JcolPosition);
			String newJPosition = pj.movePiece(JrowPosition, JcolPosition);
			JrowPosition = Integer.parseInt(newJPosition.substring(0,1));
			JcolPosition = Integer.parseInt(newJPosition.substring(1));
			setJPosition(JrowPosition, JcolPosition);
			//eraseJOldPosition(JoldRowPosition, JoldColPosition);			
			//System.out.println("looping " + i + " sAlive = " + sAlive + " dAlive = " + dAlive + " jAlive = " + jAlive + " coincount = " + coincount);
			printBoard();
			}

		} 
		while (coincount > 1);
		//System.out.println(coincount);
		//System.out.println("Done Loooping " + i + " sAlive = " + sAlive + " dAlive = " + dAlive + " jAlive = " + jAlive);
		if (sAlive)
			System.out.println("Slider wins!");
		else if (dAlive)
			System.out.println("Diag wins!");
		else
			System.out.println("Jumper wins!");
	}
}

/*
 * includes the logic for moving all three pieces
 * inheritance-- Slider, Diag, and Jumper
 */

class Piece {
	//public int sx, sy; 			///location on the board
	public boolean alive; 			///is the piece still alive?
	String coordinate;
	public String movePiece(int row, int column) {
		return coordinate;} 			///method to move the piece	

}



class Slider extends Piece {
	
	public String movePiece(int sr, int sc) {
		boolean retry = false;
		Random r = new Random();
		//int d = r.nextInt(4);
		do {
			int d = r.nextInt(4);
			switch (d){
				case 0: if (sc < 4) 		{//can go right
							sc = sc + 1; 
							retry = false;
				}
						else retry = true;
						break;
				case 1: if (sc > 0) 	{	//can go left
							sc = sc - 1;
							retry = false;
				}
						else retry = true;
						break;
				case 2: if (sr < 4)			{//can go down
							sr = sr + 1;
							retry = false;
				}
						else retry = true;
						break;
				case 3: if (sr > 0)			{//can go up
							sr = sr - 1;
							retry = false;
				}
						else retry = true;
						break;
					
			}
		}
			while (retry == true);
			//System.out.println("Rowvalue = " + sr);
			//System.out.println("Columnvalue = " + sc);
			String coordinate = "" + sr + sc;
			//System.out.println(coordinate);
			return coordinate;
	}
	
}

class Diag extends Piece {
	public String movePiece(int dr, int dc) {
		boolean retry = false;
		Random r = new Random();
		
		do
		{
		int d = r.nextInt(4);
		
		switch (d){
		
			case 0: if ((dr > 0) && (dc < 4)) { //right top
								dc = dc + 1;
								dr = dr - 1;
								retry = false;
					}
					else retry = true;
					break;
			case 1: if ((dr < 4) && (dc < 4)){ //right bottom
							dc = dc + 1;
							dr = dr + 1;
							retry = false;
					}
					else retry = true;
					break;
			case 2:if ((dc > 0) && (dr > 0)) { //left top
							dc = dc - 1;
							dr = dr - 1;
							retry = false;
					}
					else retry = true;
					break;
			case 3: if ((dr < 4) && (dc > 0)) { //left bottom
							dc = dc - 1;
							dr = dr + 1;
							retry = false;
					}
					else retry = true;
					break;
		}
		}
		while (retry == true); 
		String coordinate = "" + dr + dc;
		return coordinate;
	
}
}

class Jumper extends Piece {
	public String movePiece(int jr, int jc) {
		Random r = new Random();
		int newjr, newjc;
		
		do
		{
		newjr = r.nextInt(4);
		newjc = r.nextInt(4);
		}
		while ((newjr != jr) && (newjc != jc));
		
		String coordinate = "" + newjr + newjc;
		return coordinate;
		}
}

