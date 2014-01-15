package fr.drawurthings.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import fr.drawurthings.graphics.draw.DrawPanel;

/**
 * Classe d'export en PNG de l'image dessinée.
 * 
 * @author Théo Plockyn, Valentin Ramecourt, Alexandre Canny
 *
 */
public class Export {

	private final Paint paint;
	private final DrawPanel panel;
	double old_magnifying;

	public Export(Paint paint){
		this.paint = paint;
		panel = new DrawPanel(paint);
		panel.setSize(1920, 1080);
		old_magnifying = paint.getMagnifyingLevel();
	}



	/**
	 * Methode d'export en PNG.
	 * 
	 * @param filedir - L'arborescence du fichier à enregistrer
	 */
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
