import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.panel.DrawPanel;
import fr.drawurthings.graphics.window.DrawWindow;
import fr.drawurthings.toolbox.Toolbox;
import fr.drawurthings.toolbox.ToolboxModel;


public class Main {
	
	public static void main(String[] args){
		ToolboxModel tbm = new ToolboxModel();
		Toolbox tb = new Toolbox(tbm);
		Paint p = new Paint(tbm);
		DrawPanel draw = new DrawPanel(p);
		new DrawWindow(draw);
		/*p.addFigures(Drawable.OVAL, 150, 150, 190, 175);
		p.addFigures(Drawable.RECTANGLE, 0, 0, -175, 500);
		try{
			Thread.sleep(10000);
		}catch(Exception e){
			e.printStackTrace();
		}
		p.addFigures(Drawable.OVAL, 150, 150, 200, 200);
		p.addFigures(Drawable.OVAL, 150, 150, 200, 200);
		p.addFigures(Drawable.LINE, 75, 300, 100, 400);*/
		
	}
}
