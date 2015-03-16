//Recursively draws the Koch curve - making a flower shape
import gpdraw.*;

public class KochCurveDriver {
	public static void main(String [] args){
		KochCurve kc = new KochCurve();
		kc.drawCurve(300, 120);
	}
}

class KochCurve {
  private SketchPad pad;
  private DrawingTool pen;
  int length, angle;

  public KochCurve() {
    pad = new SketchPad(600,600);
    pen = new DrawingTool(pad);
    
  }

  public void drawCurve(int length, int a) {
	this.length = length;
	pen.up();
	pen.forward(length/2);
	pen.turnRight();
	pen.backward(length/2);
	pen.down();
    draw(6, length);
    angle = a;
    pen.turnRight(angle);
    draw(6, length);
    pen.turnRight(angle);
    draw(6, length);
  }

  private void draw(int level, double length) {
    if(level < 1)
       pen.forward(length);

    else {
      draw(level - 1, (length)/3);
      pen.turnLeft(60);
      draw(level - 1, (length)/3);
      pen.turnRight(120);
      draw(level - 1, (length)/3);
      pen.turnLeft(60);   
      draw(level - 1, (length)/3);
    }
  }
}
