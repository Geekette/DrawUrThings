package fr.drawurthings.graphics.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import fr.drawurthings.model.Paint;

/**
 * Classe définissant un JPopupMenu contenant des options d'edition pour le logiciel DrawUrThings.
 */
@SuppressWarnings("serial")
public class EditPopup extends JPopupMenu {
	
	@SuppressWarnings("unused")
	private Paint p;
	private JMenuItem cancel, redo;
	
	/**
	 * Instancie le menu contextuel "Edition".
	 * @param p Le moodéle Paint.
	 */
	public EditPopup(Paint p){
		EditListener el = new EditListener();
		this.p = p;
		this.cancel = new JMenuItem("Annuler", new ImageIcon("ressources/icon/undo.png"));
		this.cancel.addActionListener(el);
		this.redo = new JMenuItem("Refaire", new ImageIcon("ressources/icon/redo.png"));
		this.redo.addActionListener(el);
		this.add(cancel);
		this.add(redo);
	}
	
	/**
	 * Listener du menu contextuel "Edition".
	 * /!\ A implémenter.
	 */
	class EditListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
