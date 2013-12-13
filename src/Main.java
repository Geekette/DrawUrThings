import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.panel.DrawPanel;
import fr.drawurthings.graphics.window.DrawWindow;


public class Main {
	
	public static void main(String[] args){
		Paint p = new Paint();
		DrawPanel draw = new DrawPanel(p);
		new DrawWindow(draw);
<<<<<<< HEAD
		p.addFigures(Drawable.RECTANGLE, 50, 50, 100, 200);
		p.addFigures(Drawable.LINE, 400, 400, 0, 300);
=======
		p.addFigures(Paint.RECTANGLE, 50, 50, 100, 200);
		p.addFigures(Paint.LINE, 600, 400, 0, 50);
>>>>>>> 49a891703ce56960b78189c86bf2547887fb334a
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.resizeFiguresOnLayer(0, 90, 60);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.moveFiguresOnLayer(0, 400, 400);
<<<<<<< HEAD
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.resizeFiguresOnLayer(1, 0, 150);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.moveFiguresOnLayer(1, 150, 150);
=======
		p.resizeFiguresOnLayer(1, 150, 0);
>>>>>>> 49a891703ce56960b78189c86bf2547887fb334a
	}
}
