package fr.drawurthings.graphics.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import fr.drawurthings.bin.Paint;

@SuppressWarnings("serial")
public class EditPopup extends JPopupMenu {
	
	@SuppressWarnings("unused")
	private Paint p;
	private JMenuItem cancel, redo;
	
	public EditPopup(Paint p){
		EditListener el = new EditListener();
		this.p = p;
		this.cancel = new JMenuItem("Annuler", new ImageIcon("undo.png"));
		this.cancel.addActionListener(el);
		this.redo = new JMenuItem("Refaire", new ImageIcon("redo.png"));
		this.redo.addActionListener(el);
		this.add(cancel);
		this.add(redo);
	}
	
	class EditListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
