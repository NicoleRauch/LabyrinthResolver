import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


/*
 * Created on 18.04.2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */

/**
 * @author rauch
 */
public class LabyrinthCanvas extends Canvas {

	Point LowerLeft;
	int delta;
	
	LabyrinthBoard l;
	
	LabyrinthCanvas( LabyrinthBoard l ){
		this.l = l;
		LowerLeft = new Point();
		setBackground(Color.white);
		setForeground(Color.black);
	}

	public void paint( Graphics g ) {
		// calculates the size of the fields and draws the black outlines
		int k, j;
		Dimension d = getSize();  // size of the window
		
		k = (int) d.getWidth() / ( l.getSizeX() + 1 );
		j = (int) d.getHeight() / ( l.getSizeY() + 1 );
		delta = ( k < j ? k : j );

		LowerLeft.y = LowerLeft.x = delta / 2;

		Rectangle r = g.getClipBounds();
		if( r == null ){
			g.drawString( "Clipping rectangle is empty!", 
						  50, 50 );
			return;
		}

		g.clearRect( r.x, r.y, r.width, r.height );

		Rectangle rect = new Rectangle();
		rect.x = LowerLeft.x;
		rect.y = LowerLeft.y;
		rect.width = delta;
		rect.height = delta;
		for(k=1; k <= l.getSizeY(); k++ )
		{
				rect.x = LowerLeft.x;
				for (j=1; j <= l.getSizeX(); j++)
				{
					switch( (int)l.getBoard(k,j) ){
						case 0:   // clear
						g.setColor(Color.white);
						break;
						case 1:   // visited
						g.setColor(Color.blue);
						break;
						case 10:  // start/finish field
						g.setColor(Color.lightGray);
						break;
						case 100: // blocked
							g.setColor(Color.red);
							break;
						default:  // this is an error
						g.setColor(Color.black);
						break;

					}
					g.fillRect(rect.x, rect.y, rect.width, rect.height);
					rect.x += delta;
				}
				rect.y += delta;
		}
	}
	
	void paintRect( int xnum, int ynum, Color c ){
		Graphics g = getGraphics();
		if( g == null ) return;  // canvas currently not displayable

		g.setColor(c);
		g.fillRect( LowerLeft.x + (xnum - 1) * delta,  // x-coord
		           LowerLeft.y + (ynum - 1) * delta,  // y-coord
				   delta, delta );  // width, height
		
	}

}
