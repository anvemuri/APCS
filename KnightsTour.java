// This program starts at (1,1) in a 8x8 square and moves the knight from any position on the board using its standard 
L-shaped moves (two over in one direction, then one in a perpendicular direction)

import java.util.ArrayList;
import java.util.Random;

public class KnightsTour1Driver {
	public static void main(String[] args) {
		KnightsTour1 k = new KnightsTour1();
		k.initializeArray();
		k.movePiece(1, 1);
		System.out.println(k.printTable() + " squares were visited.");
	}
}
class KnightsTour1{
	int[][] array = new int[9][9];
	int num;
	public KnightsTour1(){}
	public void initializeArray() {
		for (int row = 1; row < array.length; row++) {
			for (int col = 1; col < array[row].length; col++) {
				array[row][col] = 0;
			}
		}
		for (int row = 0; row < array.length; row++) {
			array[row][0] = row;
		}
		for (int col = 0; col < array[0].length; col++) {
			array[0][col] = col;
		}
	}

	public void movePiece(int row, int col) {
		Random r = new Random();
		boolean atPos;
		num = 1;
		int rowPos = row, colPos = col;
		int[] horizontal = { 1, 2, 2, 1, -1, -2, -2, -1 };
		int[] vertical = { -2, -1, 1, 2, 2, 1, -1, -2 };
		ArrayList<setAndGet> possible = new ArrayList<setAndGet>();
		ArrayList<setAndGet> done = new ArrayList<setAndGet>();

		while (num <= 64) {
			possible.clear();
			array[rowPos][colPos] = num;
			for (int i = 0; i < horizontal.length; i++) {
				atPos = false;
				setAndGet p = new setAndGet(rowPos + vertical[i], colPos + horizontal[i]);
				if (rowPos + vertical[i] > 0 && rowPos + vertical[i] < 9 && colPos + horizontal[i] > 0 && colPos + horizontal[i] < 9) {
					for (int j = 0; j < done.size(); j++) {
						if ((done.get(j)).getRow() == p.getRow() && (done.get(j)).getCol() == p.getCol())
							atPos = true;
					}
					if (!atPos)
						possible.add(p);
				}
			}
			if (possible.isEmpty()){
				break;
			}
			setAndGet move = possible.get(r.nextInt(possible.size()));
			rowPos = move.myRow;
			colPos = move.myCol;
			done.add(new setAndGet(rowPos, colPos));
			num++;
		}
	}

	public int printTable() {

		for (int row = 0; row < array.length; row++) {
			for (int col = 0; col < array[row].length; col++) {
				System.out.printf(" %4d ", array[row][col]);
			}
			System.out.println();
		}
		System.out.println();
		return num;
	}
}

class setAndGet {
	int myRow, myCol;
	public setAndGet(int row, int col) {
		myRow = row;
		myCol = col;
	}
	public void setRow(int row) {
		myRow = row;
	}
	public void setCol(int col) {
		myCol = col;
	}
	public int getRow() {
		return myRow;
	}
	public int getCol() {
		return myCol;
	}
	public int compareTo(setAndGet p) {
		if (myRow == p.myRow && myCol == p.myCol){
			return 0;
		}
		
		else{
			return -1;
		}
	}
}
