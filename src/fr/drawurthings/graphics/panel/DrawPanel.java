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

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements Observer {
	
	Paint p;
	
	public DrawPanel(Paint draw){
		this.p = draw;
		this.p.addObserver(this);
		this.setPreferredSize(new Dimension(800, 600));
		this.setVisible(true);
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				p.moveFiguresOnLayer(0, e.getX(), e.getY());
			}
		});
		repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
<<<<<<< HEAD
		for(Iterator<Drawable> it = p.getDrawables().iterator(); it.hasNext();){
			this.paintDrawable(it.next(), g);
		}
	}
	
	public void paintDrawable(Drawable d,Graphics g){
		if(d.getShapeType() == Drawable.LINE){
			g.setColor(d.getBorderColor());
			g.drawLine(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
		}else if(d.getShapeType() == Drawable.RECTANGLE){
			g.setColor(d.getFillingColor());
			g.fillRect(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
			g.setColor(d.getBorderColor());
			g.drawRect(d.getOriginX(), d.getOriginY(), d.getWidth(), d.getHeight());
		}
=======
		this.p.paintOnGraphics(g);
>>>>>>> 49a891703ce56960b78189c86bf2547887fb334a
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
