import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.panel.DrawPanel;
import fr.drawurthings.graphics.window.DrawWindow;


public class Main {
	
	public static void main(String[] args){
		Paint p = new Paint();
		DrawPanel draw = new DrawPanel(p);
		new DrawWindow(draw);
		p.addFigures(Drawable.RECTANGLE, 50, 50, 100, 200);
		p.addFigures(Drawable.LINE, 400, 400, 0, 300);
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
	}
}
