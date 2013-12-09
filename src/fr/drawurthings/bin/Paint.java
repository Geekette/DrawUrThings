package fr.drawurthings.bin;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import fr.drawurthings.figures.*;

public class Paint extends Observable{
	
	public final static int LINE = 0;
	public final static int RECTANGLE = 1;
	
	ArrayList<Drawable> figures;
	
	public Paint(){
		this.figures = new ArrayList<Drawable>();
	}
	
	public void addFigures(int type,int originX,int originY, int heigth, int width){
		if(type == LINE){
			figures.add(new Line(originX, originY, heigth, width, figures.size()));
		}else if(type == RECTANGLE){
			figures.add(new Rectangle(originX, originY, heigth, width, figures.size()));
		}
		setChanged();
		notifyObservers();
	}
	
	public void paintOnGraphics(Graphics g){
		for(Iterator<Drawable> it = figures.iterator();it.hasNext();){
			it.next().drawOnGraphics(g);
		}
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

}
