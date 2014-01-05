package fr.drawurthings.figures;

public abstract class Polygon extends Drawable{
	
	public int[] xPoints, yPoints;

	public abstract int[] getXArray();
	public abstract int[] getYArray();
	public abstract int getNbPoint();
	public abstract void recalculate();

	@Override
	public void moveOrigin(int x, int y){
		setOriginX(x);
		setOriginY(y);
		recalculate();
	}
	
	@Override
	public boolean isVisibleAt(int posX, int posY) {
		if(posX > originX && posX < (originX + height))
			if(posY > originY && posY < (originY + width))
				return true;
		return false;
	}

}
