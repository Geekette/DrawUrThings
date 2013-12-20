package fr.drawurthings.bin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import fr.drawurthings.figures.*;
import fr.drawurthings.toolbox.ToolboxModel;

public class Paint extends Observable{

	
	ArrayList<Drawable> figures;
	private Color bgcolor;
	private ToolboxModel toolbox;
	
	public Paint(ToolboxModel t){
		this.figures = new ArrayList<Drawable>();
		this.bgcolor = Color.WHITE;
		this.toolbox = t;
	}

	public Paint(Color bgColor){
		this.figures = new ArrayList<Drawable>();
		this.bgcolor = bgColor;
	}
	
	public Color getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(Color bgcolor) {
		this.bgcolor = bgcolor;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addFigures(int type,int originX,int originY, int width, int height){
		if(type == Drawable.LINE){
			figures.add(new Line(originX, originY, width, height, figures.size(),toolbox.getBordure()));
		}else if(type == Drawable.RECTANGLE){
			figures.add(new Rectangle(originX, originY, width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}else if(type == Drawable.SQUARE){
			figures.add(new Square(originX, originY, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}else if(type == Drawable.OVAL){
			figures.add(new Oval(originX, originY, width, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}else if(type == Drawable.CIRCLE){
			figures.add(new Circle(originX, originY, height, figures.size(),toolbox.getBordure(),toolbox.getInterieur()));
		}
		setChanged();
		notifyObservers();
	}
	
	public void setFigureFillingColor(int layer,Color c){
		this.figures.get(layer).setFillingColor(c);
		setChanged();
		notifyObservers();
	}
	
	public void setFigureBorderColor(int layer,Color c){
		this.figures.get(layer).setBorderColor(c);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Drawable> getDrawables(){
		Collections.sort(this.figures);
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

	public int getActiveLayerAt(int posX, int posY){
		for(int i = figures.size()-1;i>=0;i--){
			if(figures.get(i).isVisibleAt(posX,posY)){
				return i;
			}
		}
		return -1;
	}
	
	public int getCurrentTool(){
		return this.toolbox.getShape();
	}

}
