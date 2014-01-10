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
		Paint p = new Paint(tbm);
		new DrawWindow(p);
		/*ToolboxModel tbm = new ToolboxModel();
		new Toolbox(tbm);
		Paint p = new Paint(tbm);
		DrawPanel draw = new DrawPanel(p);
		new DrawWindow(p,draw);
		new FigureListWindows(p);*/
	}
}
