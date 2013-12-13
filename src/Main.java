import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.panel.DrawPanel;
import fr.drawurthings.graphics.window.DrawWindow;


public class Main {
	
	public static void main(String[] args){
		Paint p = new Paint();
		DrawPanel draw = new DrawPanel(p);
		new DrawWindow(draw);
		p.addFigures(Drawable.RECTANGLE, 150, 150, 200, 200);
		p.addFigures(Drawable.RECTANGLE, 0, 0, 40, 50);
		p.addFigures(Drawable.LINE, 75, 300, 100, 400);
		
	}
}
