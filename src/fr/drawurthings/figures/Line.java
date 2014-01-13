package fr.drawurthings.figures;

import java.awt.Color;

@SuppressWarnings("serial")
public class Line extends Drawable{
	
	public Line(int originX, int originY,int width,int height,int layer){
		this.SHAPE_TYPE = Drawable.LINE;
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = width;
		this.layer = layer;
	}
	

	public Line(int originX, int originY,int width,int height,int layer, Color c){
		this(originX, originY,width, height, layer);
		this.border = c;
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Line, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}


	@Override
	public boolean isVisibleAt(int posX, int posY) {
		if((posX/zoom) > originX && (posX/zoom) < (originX + width))
			if((posY/zoom) > originY && (posY/zoom) < (originY + height))
				return true;
		return false;
	}

}
