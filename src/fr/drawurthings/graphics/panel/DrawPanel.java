package fr.drawurthings.graphics.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.figures.Drawable;
import fr.drawurthings.graphics.listener.DrawPanelListener;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements Observer {
	
	Paint p;
	
	public DrawPanel(Paint draw){
		this.p = draw;
		this.p.addObserver(this);
		this.setPreferredSize(new Dimension(800, 600));
		this.setVisible(true);
		DrawPanelListener dpl = new DrawPanelListener(this.p);
		this.addMouseListener(dpl);
		this.addMouseMotionListener(dpl);
		repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(p.getBgcolor());
		g.fillRect(0, 0, getWidth(), getHeight());

		for(Iterator<Drawable> it = p.getDrawables().iterator(); it.hasNext();){
			this.paintDrawable(it.next(), g);
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
			g.fillRect(d.getOriginX(), d.getOriginY(), d.getHeight(), d.getHeight());
			g.setColor(d.getBorderColor());
			g.drawRect(d.getOriginX(), d.getOriginY(), d.getHeight(), d.getHeight());
		}else if(d.getShapeType() == Drawable.OVAL){
			g.setColor(d.getFillingColor());
			g.fillOval(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
			g.setColor(d.getBorderColor());
			g.drawOval(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
		}else if(d.getShapeType() == Drawable.CIRCLE){
			g.setColor(d.getFillingColor());
			g.fillOval(d.getOriginX(), d.getOriginY(), d.getHeight(), d.getHeight());
			g.setColor(d.getBorderColor());
			g.drawOval(d.getOriginX(), d.getOriginY(), d.getHeight(), d.getHeight());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
