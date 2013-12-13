package fr.drawurthings.figures;

import java.awt.Color;

public class Line extends Drawable{
	
	public Line(int originX, int originY,int width,int height,int layer){
		this.SHAPE_TYPE = Drawable.RECTANGLE;
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = width;
		this.layer = layer;
	}
	
	public Line(int originX, int originY, int width,int height,int layer, Color c){
		this(originX, originY,width, height, layer);
		this.border = c;
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
