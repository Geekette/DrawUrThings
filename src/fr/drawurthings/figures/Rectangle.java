package fr.drawurthings.figures;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Drawable{
	
	public Rectangle(int originX, int originY, int width, int height, int layer){
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = width;
		this.layer = layer;
	}
	
	public Rectangle(int originX, int originY, int width, int height, int layer, Color border){
		this(originX, originY, width, height, layer);
		this.border = border;
	}
	
	public Rectangle(int originX, int originY, int width, int height, int layer, Color border,Color fill){
		this(originX, originY, width, height, layer,border);
		this.fill = fill;
	}

	@Override
	public void drawOnGraphics(Graphics g) {
		g.setColor(fill);
		g.fillRect(originX, originY, width, height);
		g.setColor(border);
		g.drawRect(originX, originY, width, height);
	}

	@Override
	public void magnifyOnGraphics(Graphics g, double factor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int heigth) {
		this.width = width;
		this.height = heigth;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
