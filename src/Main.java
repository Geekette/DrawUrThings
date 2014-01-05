import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.panel.DrawPanel;
import fr.drawurthings.graphics.window.DrawWindow;
import fr.drawurthings.graphics.window.FigureListWindows;
import fr.drawurthings.toolbox.Toolbox;
import fr.drawurthings.toolbox.ToolboxModel;


public class Main {
	
	public static void main(String[] args){
		ToolboxModel tbm = new ToolboxModel();
		new Toolbox(tbm);
		Paint p = new Paint(tbm);
		DrawPanel draw = new DrawPanel(p);
		new DrawWindow(p,draw);
		new FigureListWindows(p);
		//p.open("draw1.dut");
		/*p.addFigures(Drawable.RECTANGLE, 0, 0, 100, 100);
		p.setMagnifyingLevel(1.5);
		p.addFigures(Drawable.OVAL, 150, 150, 190, 175);
		p.addFigures(Drawable.RECTANGLE, 0, 0, -175, 500);
		p.addFigures(Drawable.OVAL, 150, 150, 200, 200);
		p.addFigures(Drawable.OVAL, 150, 150, 200, 200);
		p.addFigures(Drawable.LINE, 75, 300, 100, 400);
		p.saveAs();*/
	}
}
