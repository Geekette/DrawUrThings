package fr.drawurthings.graphics.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import fr.drawurthings.graphics.window.AboutWindow;

@SuppressWarnings("serial")
public class HelpPopup extends JPopupMenu {
	
	private JMenuItem about;
	
	public HelpPopup(){
		ActionListener l = new HelpListener();
		about = new JMenuItem("A propos de...");
		about.addActionListener(l);
		this.add(about);
		
	}

	
	class HelpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(about)){
				new AboutWindow();
			}
			
		}
		
		
		
	}
}
