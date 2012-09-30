import java.awt.Color;
import java.util.Vector;

/*
 * Created on 18.04.2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */

/**
 * @author rauch
 */
public class LabyrinthWalker extends Thread {

	Vector path;
	int index = 0;
	boolean Paint = true;
	// if set to false, the calculation will be interrupted
	LabyrinthBoard lb;
	LabyrinthCanvas lc;

	LabyrinthWalker(LabyrinthBoard lb, LabyrinthCanvas lc) {
		this.lb = lb;
		this.lc = lc;
		path = new Vector();

		path.add(lb.getStart());
	}

	void workCalc(int index) {
		boolean found_end = false;

		// test whether field index is allowed
		if (((Field) (path.elementAt(index))).getRow() < 1
			|| ((Field) (path.elementAt(index))).getRow() > lb.getSizeY()
			|| ((Field) (path.elementAt(index))).getCol() < 1
			|| ((Field) (path.elementAt(index))).getCol() > lb.getSizeX()
			|| lb.getBoard(
				((Field) (path.elementAt(index))).getRow(),
				((Field) (path.elementAt(index))).getCol())
				== 100
			|| lb.getBoard(
				((Field) (path.elementAt(index))).getRow(),
				((Field) (path.elementAt(index))).getCol())
				== 1)
			return;

		lb.setBoard(
			((Field) (path.elementAt(index))).getRow(),
			((Field) (path.elementAt(index))).getCol(),
			(char) 1);
		// mark field as visited
		lc.paintRect(
			((Field) (path.elementAt(index))).getCol(),
			((Field) (path.elementAt(index))).getRow(),
			Color.blue);
		try {
			sleep(200);
		} catch (InterruptedException e) {
		}
		if (((Field) (path.elementAt(index))).getRow()
			== lb.getFinish().getRow()
			&& ((Field) (path.elementAt(index))).getCol()
				== lb.getFinish().getCol()) {
			// finished!
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
			found_end = true;
		} else {
			Field n = new Field((Field) (path.elementAt(index)));
			n.setCol(n.getCol() + 1); // move one step to the right
			path.add(index + 1, n);
			workCalc(index + 1); // right
			n.setCol(n.getCol() - 2);
			workCalc(index + 1); // left
			n.setCol(n.getCol() + 1);
			n.setRow(n.getRow() + 1);
			workCalc(index + 1); // up
			n.setRow(n.getRow() - 2);
			workCalc(index + 1); // down
		}
		lb.setBoard(
			((Field) (path.elementAt(index))).getRow(),
			((Field) (path.elementAt(index))).getCol(),
			(char) 0);
		// mark field as unvisited
		// recalc the rect since the user may have changed the window in between
		lc.paintRect(
			((Field) (path.elementAt(index))).getCol(),
			((Field) (path.elementAt(index))).getRow(),
			(found_end ? Color.lightGray : Color.white));
		try {
			sleep(200);
		} catch (InterruptedException e) {
		}
		return;
	}

	public void run() {
		path.removeAllElements();
		index = 0;
		path.add(lb.getStart());
		workCalc(0);
	}

}
