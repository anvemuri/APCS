import java.awt.geom.*;     // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import gpdraw.*;            // for DrawingTool

public class IrregularPolygonDriver{
	public IrregularPolygonDriver(){}
	public static void main(String [] args){
		IrregularPolygon ir = new IrregularPolygon();
		Point2D.Double shape1 = new Point2D.Double(20, 10);	//point 1
		ir.add(shape1);	//adding the point to the arraylist
		Point2D.Double shape2 = new Point2D.Double(70, 20);	//point 2
		ir.add(shape2);	//adding the point to the arraylist
		Point2D.Double shape3 = new Point2D.Double(50, 50);	//point 3
		ir.add(shape3);	//adding the point to the arraylist
		Point2D.Double shape4 = new Point2D.Double(0, 40);	//point 4
		ir.add(shape4);	//adding the point to the arraylist
		ir.draw();	//drawing the shape with all the points
		System.out.printf("Perimeter = %.1f" , ir.perimeter());	//perimeter
		System.out.printf("\nArea = %.1f" , ir.area());	//area
	}
}

class IrregularPolygon{
   private ArrayList <Point2D.Double> myPolygon;	//values in this arraylist are 'Point2D.Double's
   double x, y, perimeter, area;
   DrawingTool pen;
   SketchPad pad;

   // constructors
   public IrregularPolygon() {
	   myPolygon = new ArrayList <Point2D.Double>();
	   x = y = 1;
	   perimeter = area = 0.0;
	   pad = new SketchPad(500,500);
	   pen = new DrawingTool(pad);
   }

   // public methods
   public void add(Point2D.Double aPoint) {	//parameter - the coordinate
	   myPolygon.add(aPoint);	//adding to the arrayList
   }

   public void draw() {
	   x = myPolygon.get(0).getX();	//finding starting 'x' value
	   y = myPolygon.get(0).getY();	//finding starting 'y' value
	   Point2D.Double point = new Point2D.Double(x, y);
	   myPolygon.add(point);	//adding the first point to the end (so the first point can be referenced easily)
	   pen.up();
	   pen.move(myPolygon.get(0).getX(), myPolygon.get(0).getY());	//moving 
	   pen.down();
	   for(int i = 1; i < myPolygon.size(); i++){
		   pen.move(myPolygon.get(i).getX(), myPolygon.get(i).getY());
	   }
   }

   public double perimeter() { 
	   for(int i = 0; i < myPolygon.size() - 1; i++){
		   perimeter += ((Point2D.Double)myPolygon.get(i)).distance((Point2D.Double)myPolygon.get(i + 1)); //distance from point a to b to c to ---until the end of the list.
		   }
	   return perimeter;
	}

   public double area() {
	   for(int i = 0; i < myPolygon.size()-1; i++){
		   double x0 = (myPolygon.get(i).getX());
		   double y1 = (myPolygon.get(i + 1).getY());
		   area += (x0 * y1);	//all the values that should be added together
		}
	   for(int i = 0; i < myPolygon.size()-1; i++){
		   double y0 = (myPolygon.get(i).getY());
		   double x1 = (myPolygon.get(i + 1).getX());
		   area -= (y0 * x1);	//all the values that should be subtracted
		}
	   
	   area = Math.abs(area);
	   area = 0.5 * area;
	   	
		return area;
	}
}
