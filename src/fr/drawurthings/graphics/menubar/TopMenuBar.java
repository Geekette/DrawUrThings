package fr.drawurthings.graphics.menubar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.drawurthings.graphics.window.DrawWindow;
import fr.drawurthings.model.Paint;

@SuppressWarnings("serial")
public class TopMenuBar extends JPanel{
	
	private Paint paint;
	private DrawWindow dw;
	private JMenuItem file, edit, display, help;
	
	public TopMenuBar(DrawWindow dw){
		this.dw = dw;
		this.paint = dw.getPaint();
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
				new FilePopup(dw).show(source,0,getHeight());
			}else if(e.getSource().equals(edit)){
				new EditPopup(paint).show(source,0,getHeight());
			}else if(e.getSource().equals(display)){
				new DisplayPopup(dw).show(source,0,getHeight());
			}else if(e.getSource().equals(help)){
				new HelpPopup().show(source,0,getHeight());
			}
			
		}
		
	}

}
