package fr.drawurthings.graphics.window;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.drawurthings.graphics.toolbox.ColorsPanel;
import fr.drawurthings.graphics.toolbox.ShapesPanel;
import fr.drawurthings.model.ToolboxModel;

@SuppressWarnings("serial")
public class Toolbox extends JFrame implements Observer {
	
	ToolboxModel m;
	ColorsPanel colors;
	ShapesPanel tools;
	
	public Toolbox(ToolboxModel m){
		super("Boite à outils");
		this.setIconImage(new ImageIcon("ressources/icon/vectoricon.png").getImage());
		this.m=m;
		this.m.addObserver(this);
		setSize(450, 350);
		setLocation(0, 180);
		this.setAlwaysOnTop(true);
		this.requestFocus();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Container boxes = getContentPane();
		boxes.setLayout(new GridLayout(2, 1));

		tools = new ShapesPanel(m);
		
		colors = new ColorsPanel(m);
		
		
		boxes.add(tools);
		boxes.add(colors);

		pack();
		setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		colors.setBordure(m.getBordure());
		colors.setInterieur(m.getInterieur());
	}
}



