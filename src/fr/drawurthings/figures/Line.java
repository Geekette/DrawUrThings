package fr.drawurthings.figures;

import java.awt.Color;

public class Line extends Drawable{
	
<<<<<<< HEAD
	public Line(int originX, int originY,int width,int height,int layer){
		this.SHAPE_TYPE = Drawable.RECTANGLE;
=======
	public Line(int originX, int originY, int width,int height,int layer){
>>>>>>> 49a891703ce56960b78189c86bf2547887fb334a
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = width;
		this.layer = layer;
	}
	
<<<<<<< HEAD
	public Line(int originX, int originY, int width,int height,int layer, Color c){
		this(originX, originY,width, height, layer);
		this.border = c;
	}

=======
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

>>>>>>> 49a891703ce56960b78189c86bf2547887fb334a
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
