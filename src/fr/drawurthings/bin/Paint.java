package fr.drawurthings.bin;

import java.util.ArrayList;
import java.util.Observable;

import fr.drawurthings.figures.*;

public class Paint extends Observable{

	
	ArrayList<Drawable> figures;
	
	public Paint(){
		this.figures = new ArrayList<Drawable>();
	}
	
	public void addFigures(int type,int originX,int originY, int width, int height){
		if(type == Drawable.LINE){
			figures.add(new Line(originX, originY, width, height, figures.size()));
		}else if(type == Drawable.RECTANGLE){
			figures.add(new Rectangle(originX, originY, width, height, figures.size()));
		}
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Drawable> getDrawables(){
		return this.figures;
	}
		
	public void resizeFiguresOnLayer(int layer, int width, int heigth){
		figures.get(layer).resize(width, heigth);
		setChanged();
		notifyObservers();
	}
	
	public void moveFiguresOnLayer(int layer, int coordX, int coordY){
		figures.get(layer).moveOrigin(coordX, coordY);
		setChanged();
		notifyObservers();
	}

	public int getActiveLaterAt(int posX, int posY){
		return 0;
	}
}