package fr.drawurthings.graphics.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import fr.drawurthings.bin.Paint;

public class FilePopup extends JPopupMenu {

	Paint paint;

	JMenuItem newbutton, open, saveas, exit;

	public FilePopup(Paint p) {
		this.paint = p;
		this.build();
	}

	public void build(){
		FileListener fl = new FileListener();
		newbutton = new JMenuItem("Nouveau", new ImageIcon("new.png"));
		open = new JMenuItem("Ouvrir",new ImageIcon("open.png"));
		open.addActionListener(fl);
		saveas = new JMenuItem("Enregistrer sous...", new ImageIcon("save.png"));	
		saveas.addActionListener(fl);
		this.add(newbutton);
		this.add(open);
		this.add(saveas);
	}

	class FileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(open)) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				File fichier = fc.getSelectedFile();
				paint.open(fichier.getAbsolutePath());
			}else if(e.getSource().equals(saveas)){
				paint.saveAs("home.dut");
			}

		}

	}

}
