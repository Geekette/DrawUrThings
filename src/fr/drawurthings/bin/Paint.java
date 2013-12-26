package fr.drawurthings.bin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;

import fr.drawurthings.figures.*;
import fr.drawurthings.toolbox.ToolboxModel;

public class Paint extends Observable{

	public static int BACKGROUD_LAYER = -10;
	public static int UPPER_LAYER = -20;
	public static int DOWN_LAYER = -30;
	public static int FOREGROUND_LAYER = -40;
	
	ArrayList<Drawable> figures;
	private Color bgcolor;
	private ToolboxModel toolbox;
	private double magnifyingLevel = 1;
	
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
		if(this.magnifyingLevel !=1){
			figures.get(figures.size()-1).modifyScale(magnifyingLevel);
			figures.get(figures.size()-1).setZoom(magnifyingLevel);
		}
		setChanged();
		notifyObservers();
	}
	
	public void duplicateFigure(int layer){
		Drawable tmp = figures.get(layer);
		this.addFigures(tmp.getShapeType(), tmp.getOriginX()+15, tmp.getOriginY()+15, tmp.getWidth(), tmp.getHeight());
		figures.get(figures.size()-1).setBorderColor(tmp.getBorderColor());
		figures.get(figures.size()-1).setFillingColor(tmp.getFillingColor());
		setChanged();
		notifyObservers();
	}
	
	public void removeFigure(int layer){
		figures.remove(layer);
		Collections.sort(figures);
		for(int i = 0;i<figures.size();i++){
			figures.get(i).setLayer(i);
		}
		setChanged();
		notifyObservers();
	}
	
	public void removeAll(){
		this.bgcolor = Color.WHITE;
		figures.clear();
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
	
	public void resizeFigureUsingCorner(int layer, int corner, int posX, int posY){
		this.figures.get(layer).resizeMovingACorner(corner, posX, posY);
		setChanged();
		notifyObservers();
	}
	
	public double getMagnifyingLevel() {
		return magnifyingLevel;
	}

	public void setMagnifyingLevel(double magnifyingLevel) {
		this.magnifyingLevel = magnifyingLevel;
		for(Iterator<Drawable> it = figures.iterator();it.hasNext();){
			it.next().setZoom(this.magnifyingLevel);
		}
		setChanged();
		notifyObservers();
	}

	public void arrangeLayout(int source_layer, int action) throws IllegalArgumentException{
		Drawable tmp = figures.get(source_layer);
		if(action == UPPER_LAYER){
			if(source_layer == figures.size()-1) throw new IllegalArgumentException("La figure est au premier plan et ne peut être avancée.");
			figures.get(source_layer).setLayer(source_layer+1);
			figures.get(source_layer+1).setLayer(source_layer);
		}else if(action == DOWN_LAYER){
			if(source_layer == 0) throw new IllegalArgumentException("La figure est en arrière plan et ne peut être reculée.");
			figures.get(source_layer).setLayer(source_layer-1);
			figures.get(source_layer-1).setLayer(source_layer);
		}else if(action == BACKGROUD_LAYER){
			if(source_layer == 0) throw new IllegalArgumentException("La figure est en arrière plan et ne peut être reculée.");
			figures.remove(tmp);
			for(int i = 0; i<source_layer;i++){
				figures.get(i).setLayer(i+1);
			}
			tmp.setLayer(0);
			figures.add(tmp);
		}else if(action == FOREGROUND_LAYER){
			if(source_layer == figures.size()-1) throw new IllegalArgumentException("La figure est au premier plan et ne peut être avancée.");
			figures.remove(tmp);
			figures.add(tmp);
			for(int i = 0; i<figures.size();i++){
				figures.get(i).setLayer(i);
			}
		}
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
