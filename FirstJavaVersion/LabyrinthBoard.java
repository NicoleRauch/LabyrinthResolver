import java.util.Random;

/*
 * Created on 18.04.2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */

/**
 * @author rauch
 */
public class LabyrinthBoard {

	char[][] Board; // 0 = free, 1 = visited, 10 = start/finish, 100 = blocked
	int SizeX;
	int SizeY;
	Field start, finish;

	char getBoard(int x, int y) {
		return Board[x][y];
	}

	void setBoard(int x, int y, char v) {
		Board[x][y] = v;
	}

	int getSizeX() {
		return SizeX;
	}

	int getSizeY() {
		return SizeY;
	}

	/** dangerous!
	 * 
	 * @return Field
	 */
	Field getStart() {
		return start;
	}

	/** dangerous!
	 * 
	 * @return Field
	 */
	Field getFinish() {
		return finish;
	}

	void initBoard() {
		// mark fields in the board as blocked

		Random rand = new Random();

		for (int i = 1; i <= SizeY; i++)
			for (int j = 1; j <= SizeX; j++){
				// distance to either wall
				// = 0 for elements on the wall, 
				// larger for elems in the middle
				int idist = Math.min(i-1,SizeY-i);
				int jdist = Math.min(j-1,SizeX-j);
				if (rand.nextInt(SizeX/2 + SizeY/2) > idist+jdist)
					Board[i][j] = 100; // block the field
				else
					Board[i][j] = 0;  // unblock the field
			}

		/* standard configuration:
		Board[1][1] = 100;
		Board[1][2] = 100;
		Board[1][3] = 100;
		Board[1][4] = 100;
		Board[1][6] = 100;
		Board[1][7] = 100;
		Board[1][8] = 100;
		Board[1][9] = 100;
		Board[1][11] = 100;
		Board[1][12] = 100;
		Board[2][1] = 100;
		Board[2][7] = 100;
		Board[2][8] = 100;
		Board[2][9] = 100;
		Board[2][12] = 100;
		Board[3][3] = 100;
		Board[3][6] = 100;
		Board[3][11] = 100;
		Board[3][12] = 100;
		Board[4][1] = 100;
		Board[4][2] = 100;
		Board[4][3] = 100;
		Board[4][9] = 100;
		Board[4][12] = 100;
		Board[5][1] = 100;
		Board[5][5] = 100;
		Board[5][7] = 100;
		Board[5][8] = 100;
		Board[5][9] = 100;
		Board[5][10] = 100;
		Board[6][1] = 100;
		Board[6][3] = 100;
		Board[6][4] = 100;
		Board[6][5] = 100;
		Board[6][7] = 100;
		Board[6][12] = 100;
		Board[7][1] = 100;
		Board[7][7] = 100;
		Board[7][9] = 100;
		Board[7][10] = 100;
		Board[8][1] = 100;
		Board[8][2] = 100;
		Board[8][3] = 100;
		Board[8][4] = 100;
		Board[8][5] = 100;
		Board[8][6] = 100;
		Board[8][7] = 100;
		Board[8][8] = 100;
		Board[8][9] = 100;
		Board[8][10] = 100;
		Board[8][11] = 100;
		Board[8][12] = 100;
		*/

		// other startup variables
		start = new Field(3, 1);
		Board[start.Row][start.Col] = 10;
		finish = new Field(7, 12);
		Board[finish.Row][finish.Col] = 10;

	}

	LabyrinthBoard() {

		Board = new char[26][];
		for (int i = 0; i < 26; i++) {
			Board[i] = new char[26];
		}
		SizeX = 12;
		SizeY = 8;

		initBoard();
	}

}
