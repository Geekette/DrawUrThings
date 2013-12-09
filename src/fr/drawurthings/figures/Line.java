package fr.drawurthings.figures;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Drawable{
	
	public Line(int originX, int originY, int width,int height,int layer){
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = width;
		this.layer = layer;
	}
	
	public Line(int originX, int originY,int width,int height,int layer, Color c){
		this(originX, originY,height, width, layer);
		this.border = c;
	}


	@Override
	public void drawOnGraphics(Graphics g) {
		g.setColor(this.border);
		g.drawLine(originX, originY, originX+width, originY + height);
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
		return "Line, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}

}
