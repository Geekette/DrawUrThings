package fr.drawurthings.bin;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import fr.drawurthings.graphics.panel.DrawPanel;

public class Export {
	
	private Paint paint;
	private DrawPanel panel;
	double old_magnifying;
	
	public Export(Paint paint){
		this.paint = paint;
		panel = new DrawPanel(paint);
		panel.setSize(1920, 1080);
		old_magnifying = paint.getMagnifyingLevel();
	}
	
	
	
	public void exportAs(String filedir){
		paint.setMagnifyingLevel(1);
		BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB); 
		Graphics g = bi.createGraphics();
		panel.paint(g);
		g.dispose();
		paint.setMagnifyingLevel(old_magnifying);
		try{
			ImageIO.write(bi,"png",new File(filedir));
		}catch (Exception e) {
		}
	}
}
