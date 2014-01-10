package fr.drawurthings.graphics.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.figures.Polygon;
import fr.drawurthings.graphics.listener.DrawPanelListener;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements Observer {
	
	Paint p;
	
	public DrawPanel(Paint draw){
		this.p = draw;
		this.p.addObserver(this);
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setVisible(true);
		DrawPanelListener dpl = new DrawPanelListener(this.p, this);
		this.addMouseListener(dpl);
		this.addMouseMotionListener(dpl);
		this.addMouseWheelListener(dpl);
		repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(p.getBgcolor());
		g.fillRect(0, 0, getWidth(), getHeight());
		for(Iterator<Drawable> it = p.getDrawables().iterator(); it.hasNext();){
			this.paintDrawable(it.next(), g);
		}
		if(p.getWorkingLayer() >= 0 && p.getCurrentTool() == -1){
			Drawable figure = p.getDrawables().get(p.getWorkingLayer());
			g.setColor(Color.BLUE);
			if(figure.getShapeType() != Drawable.LINE){ 
				g.drawRect(figure.getOriginX(), figure.getOriginY(), figure.getWidth(), figure.getHeight());
				g.drawRect(figure.getOriginX()-5, figure.getOriginY()-5, 10, 10);
				g.drawRect(figure.getOriginX()-5, figure.getOriginY() + figure.getHeight()-5, 10, 10);
				g.drawRect(figure.getOriginX()+figure.getWidth()-5, figure.getOriginY()+ figure.getHeight()-5, 10, 10);
				g.drawRect(figure.getOriginX() + figure.getWidth()-5, figure.getOriginY()-5, 10, 10);
			}else{
				g.drawRect(figure.getOriginX()-5, figure.getOriginY()-5, 10, 10);
				g.drawRect(figure.getOriginX()+figure.getWidth()-5, figure.getOriginY()+ figure.getHeight()-5, 10, 10);
			}
		}
	}
	
	public void paintDrawable(Drawable d,Graphics g){
		if(d.getShapeType() == Drawable.LINE){
			g.setColor(d.getBorderColor());
			g.drawLine(d.getOriginX(), d.getOriginY(), d.getWidth() + d.getOriginX(), d.getOriginY()+d.getHeight());
		}else if(d.getShapeType() == Drawable.RECTANGLE){
			g.setColor(d.getFillingColor());
			g.fillRect(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
			g.setColor(d.getBorderColor());
			g.drawRect(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
		}else if(d.getShapeType() == Drawable.SQUARE){
			g.setColor(d.getFillingColor());
			g.fillRect(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
			g.setColor(d.getBorderColor());
			g.drawRect(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
		}else if(d.getShapeType() == Drawable.OVAL){
			g.setColor(d.getFillingColor());
			g.fillOval(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
			g.setColor(d.getBorderColor());
			g.drawOval(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
		}else if(d.getShapeType() == Drawable.CIRCLE){
			g.setColor(d.getFillingColor());
			g.fillOval(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
			g.setColor(d.getBorderColor());
			g.drawOval(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
		}else if(d.getShapeType() > Drawable.CIRCLE){
			Polygon pol = (Polygon) d;
			g.setColor(d.getFillingColor());
			g.fillPolygon(pol.getXArray(), pol.getYArray(), pol.getNbPoint());
			g.setColor(d.getBorderColor());
			g.drawPolygon(pol.getXArray(), pol.getYArray(), pol.getNbPoint());
		}
	}
	
	public void exportAs(String filedir){
		BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB); 
		Graphics g = bi.createGraphics();
		this.paint(g);
		g.dispose();
		try{
			ImageIO.write(bi,"png",new File(filedir));
		}catch (Exception e) {
				
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
