package fr.drawurthings.graphics.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.drawurthings.bin.Paint;

public class TopMenuBar extends JPanel{
	
	Paint paint;
	JMenuItem file, edit, display, help;
	
	public TopMenuBar(Paint p){
		this.paint = p;
		build();
	}
	
	public void build(){
		JMenuBar panel = new JMenuBar();
		MenuListener ml = new MenuListener();
		file = new JMenuItem("Fichier");
		file.addActionListener(ml);
		edit = new JMenuItem("Edition");
		edit.addActionListener(ml);
		display = new JMenuItem("Affichage");
		display.addActionListener(ml);
		help = new JMenuItem("?");
		help.addActionListener(ml);
		setLayout(new BorderLayout(5, 0));
		panel.add(file);
		panel.add(edit);
		panel.add(display);
		panel.add(help);
		this.add(panel,BorderLayout.WEST);
		setPreferredSize(new Dimension(450,30));
	}
	
	class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Component source = (Component) e.getSource();
			if(e.getSource().equals(file)){
				new FilePopup(paint).show(source,source.getX(),getHeight());
			}else{
				new FilePopup(paint).show(source,source.getX(),getHeight());
			}
			
		}
		
	}

}
