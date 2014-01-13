import fr.drawurthings.bin.Paint;
import fr.drawurthings.graphics.window.DrawWindow;
import fr.drawurthings.toolbox.ToolboxModel;


public class Main {
	
	public static void main(String[] args){
		ToolboxModel tbm = new ToolboxModel();
		Paint p = new Paint(tbm);
		new DrawWindow(p);
	}
}
