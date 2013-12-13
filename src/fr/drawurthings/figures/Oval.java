package fr.drawurthings.figures;

import java.awt.Color;

public class Oval extends Drawable{
	
	
	public Oval(int originX, int originY, int width, int height, int layer){
		this.SHAPE_TYPE = Drawable.OVAL;
		this.originX = originX;
		this.originY = originY;
		this.height = height;
		this.width = width;
		this.layer = layer;
	}
	
	public Oval(int originX, int originY, int width, int height, int layer, Color border){
		this(originX, originY, width, height, layer);
		this.border = border;
	}
	
	public Oval(int originX, int originY, int width, int height, int layer, Color border,Color fill){
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
		return "Oval, Origin = (" + this.originX + "," + this.originY +") , Vector = (" + this.width + "," +this.height + "). Color : " + this.border + ".";
	}

	@Override
	public boolean isVisibleAt(int posX, int posY) {
		if(posX > originX && posX < (originX + height))
			if(posY > originY && posY < (originY + width))
				return true;
		return false;
	}

}
