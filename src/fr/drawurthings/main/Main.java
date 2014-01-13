package fr.drawurthings.main;
import javax.swing.JFrame;

import fr.drawurthings.graphics.window.DrawWindow;
import fr.drawurthings.graphics.window.SplashScreen;
import fr.drawurthings.model.Paint;
import fr.drawurthings.model.ToolboxModel;


public class Main {
	
	public static void main(String[] args){
		JFrame splash = new SplashScreen();
		ToolboxModel tbm = new ToolboxModel();
		Paint p = new Paint(tbm);
		new DrawWindow(p);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		splash.dispose();
	}
}
