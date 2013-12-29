package fr.drawurthings.graphics.window;

import javax.swing.JFrame;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.graphics.panel.FiguresList;

@SuppressWarnings("serial")
public class FigureListWindows extends JFrame {

	public FigureListWindows(Paint p){
		this.setTitle("Liste de figures");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.add(new FiguresList(p));
		this.setLocation(970, 350);
		this.pack();
	}
	
}
