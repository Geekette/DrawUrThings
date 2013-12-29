package fr.drawurthings.toolbox;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class Toolbox extends JFrame implements Observer {
	
	ToolboxModel m;
	ColorsPanel colors;
	ShapesPanel tools;
	
	public Toolbox(ToolboxModel m){
		super("Boite à outils");
		this.m=m;
		this.m.addObserver(this);
		setSize(450, 350);
		setLocation(0, 50);
		this.setAlwaysOnTop(true);
		this.requestFocus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container boxes = getContentPane();
		boxes.setLayout(new GridLayout(2, 1));

		tools = new ShapesPanel(m);
		
		colors = new ColorsPanel(m);
		
		
		boxes.add(tools);
		boxes.add(colors);

		pack();
		setVisible(true);
	}
	
	public static void main(String args[]){
		Toolbox t = new Toolbox(new ToolboxModel());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		colors.setBordure(m.getBordure());
		colors.setInterieur(m.getInterieur());
	}
}



