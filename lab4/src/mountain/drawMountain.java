package mountain;

import java.util.ArrayList;
import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class drawMountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	double dev;
	public HashMap<Side, Point> map;
	
	public drawMountain(double dev){
		super();
		p1 = new Point(100, 400);
		p2 = new Point(250, 100);
		p3 = new Point(400, 280);
		this.dev = dev;
		map = new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {
		return "Mountainrange";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, p1, p2, p3, dev);
	
	}

	/* 
	 * Reursive method: Draws a recursive line of the triangle. 
	 */
	private void fractalLine(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3, double dev) {
		if(order == 0){
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
			
			
			
		}else{		
			Point ab = pM(p1, p2, dev);
			Point bc = pM(p2, p3, dev); 
			Point ca = pM(p3, p1, dev);
			fractalLine(turtle, order-1, p1, ab, ca, dev/2);
			fractalLine(turtle, order-1, ab, p2, bc, dev/2);
			fractalLine(turtle, order-1, ca, bc, p3, dev/2);
			fractalLine(turtle, order-1, ab, bc, ca, dev/2);
		}
	}
	
	private Point pM(Point p1, Point p2, double dev){
		Side s = new Side(p1,p2);
			if(map.containsKey(s)){
				Point p = map.get(s);	
				map.remove(s);
				return p;
			}
		
		Point p = new Point(nextCoord(p1.getX(), p2.getX()), (int) (nextCoord(p1.getY(), p2.getY())  + RandomUtilities.randFunc(dev)));
		map.put(s, p);
		return p;
		
	}
	
	private int nextCoord(int z1, int z2){
		int cord = (((z1+z2)/2));
		return cord;
	}
	

}
