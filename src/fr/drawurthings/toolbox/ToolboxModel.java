package fr.drawurthings.toolbox;

import java.awt.Color;
import java.util.Observable;

public class ToolboxModel extends Observable {
	
	Color colorInterieur, colorBordure;
	int shapeSelected;
	
	public ToolboxModel(){
		colorInterieur = Color.WHITE;
		colorBordure = Color.BLACK;
		shapeSelected = -1;
	}
	
	public void setInterieur(Color c){
		colorInterieur = c;
		setChanged();notifyObservers();
	}
	public void setBordure(Color c){
		colorBordure = c;
		setChanged();notifyObservers();
	}
	public void setShape(int s){
		shapeSelected = s;
		setChanged();notifyObservers();
	}
	public Color getInterieur(){
		return colorInterieur;
	}
	public Color getBordure(){
		return colorBordure;
	}
	public int getShape(){
		return shapeSelected;
	}
}
