package fr.drawurthings.graphics.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.graphics.panel.FiguresList;

@SuppressWarnings("serial")
public class FigureListWindows extends JFrame {

	public FigureListWindows(Paint p){
		this.setTitle("Liste de figures");
		Dimension resol = Toolkit.getDefaultToolkit().getScreenSize();
		this.setIconImage(new ImageIcon("vectoricon.png").getImage());
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.add(new FiguresList(p));
		this.setLocation((int)resol.getWidth()-250,(int) resol.getHeight()-450);
		this.pack();
	}
	
}
