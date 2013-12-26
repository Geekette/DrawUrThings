package fr.drawurthings.figures;

import java.awt.Color;

public class Circle extends Drawable{
	
	
	public Circle(int originX, int originY, int height, int layer){
		this.SHAPE_TYPE = Drawable.CIRCLE;
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = height;
		this.layer = layer;
		if(width<0 || height<0){
			adaptNegative();
		}
	}
	
	public Circle(int originX, int originY, int height, int layer, Color border){
		this(originX, originY, height, layer);
		this.border = border;
	}
	
	public Circle(int originX, int originY, int height, int layer, Color border,Color fill){
		this(originX, originY, height, layer,border);
		this.fill = fill;
	}


	@Override
	public void resize(int width, int height) {
		this.width = height;
		this.height = height;
		if(width<0 || height<0){
			adaptNegative();
		}
	}

	@Override
	public String toString() {
		return "Circle, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}

	@Override
	public boolean isVisibleAt(int posX, int posY) {
		if((posX/zoom) > originX && (posX/zoom) < (originX + width))
			if((posY/zoom) > originY && (posY/zoom) < (originY + height))
				return true;
		return false;
	}

}
