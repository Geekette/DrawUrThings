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
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Rectangle, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}

	@Override
	public boolean isVisibleAt(int posX, int posY) {
		if(posX > originX && posX < (originX + width))
			if(posY > originY && posY < (originY + height))
				return true;
		return false;
	}

}
