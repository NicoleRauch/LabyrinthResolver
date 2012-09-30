/*
 * Created on 18.04.2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */

/**
 * @author rauch
 */
public class Field {
	int Row;
	int Col;
	
	Field(int r, int c){
		Row = r;
		Col = c;
	}
	
	Field(Field f){
		Row = f.Row;
		Col = f.Col;
	}
	
	int getRow(){
		return Row;
	}
	int getCol(){
		return Col;
	}
	void setRow(int r){
		Row = r;
	}
	void setCol(int c){
		Col = c;
	}

}
