package fr.drawurthings.figures;

import java.awt.Color;

public class Rectangle extends Drawable{
	
	
	public Rectangle(int originX, int originY, int width, int height, int layer){
		this.SHAPE_TYPE = Drawable.RECTANGLE;
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
	public void resize(int width, int heigth) {
		this.width = width;
		this.height = heigth;
	}

	@Override
	public String toString() {
		return "Rectangle, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}

}
