package fr.drawurthings.graphics.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.drawurthings.bin.Paint;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements Observer {
	
	Paint p;
	
	public DrawPanel(Paint draw){
		this.p = draw;
		this.p.addObserver(this);
		this.setPreferredSize(new Dimension(800, 600));
		this.setVisible(true);
		repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		this.p.paintOnGraphics(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
