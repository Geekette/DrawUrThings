package fr.drawurthings.graphics.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.graphics.window.DrawWindow;

@SuppressWarnings("serial")
public class FilePopup extends JPopupMenu {

	private Paint paint;
	private DrawWindow dw;

	private JMenuItem newbutton, open, saveas, export, exit;

	public FilePopup(DrawWindow dw) {
		this.paint = dw.getPaint();
		this.dw = dw;
		this.build();
	}

	public void build(){
		FileListener fl = new FileListener();
		newbutton = new JMenuItem("Nouveau", new ImageIcon("new.png"));
		newbutton.addActionListener(fl);
		open = new JMenuItem("Ouvrir",new ImageIcon("open.png"));
		open.addActionListener(fl);
		saveas = new JMenuItem("Enregistrer sous...", new ImageIcon("save.png"));	
		saveas.addActionListener(fl);
		export = new JMenuItem("Exporter au format PNG");
		export.addActionListener(fl);
		exit = new JMenuItem("Quitter", new ImageIcon("remove.png"));
		exit.addActionListener(fl);
		this.add(newbutton);
		this.add(open);
		this.add(new Separator());
		this.add(saveas);
		this.add(new Separator());
		this.add(export);
		this.add(new Separator());
		this.add(exit);
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
				String name;
				JFileChooser fc = new JFileChooser();
				fc.showSaveDialog(null);
				File fichier = fc.getSelectedFile();
				name = fichier.getAbsolutePath();
				if(name.length()>=5){
					if(!name.substring(name.length()-4).equals(".dut")){
						name+=".dut";
					}
				}else{
					name+=".dut";
				}
				paint.saveAs(name);
			}else if(e.getSource().equals(newbutton)){
				int reponse = JOptionPane.showConfirmDialog(null, "Toutes les modifications non enregistrées seront perdus. Continuer?", "Créer un nouveau document?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(reponse == JOptionPane.YES_OPTION){
					paint.removeAll();
				}
			}else if(e.getSource().equals(exit)){
				int reponse = JOptionPane.showConfirmDialog(null, "Toutes les modifications non enregistrées seront perdus. Continuer?", "Quitter?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(reponse == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}else if(e.getSource().equals(export)){
				String name;
				JFileChooser fc = new JFileChooser();
				fc.showSaveDialog(null);
				File fichier = fc.getSelectedFile();
				name = fichier.getAbsolutePath();
				if(name.length()>=5){
					if(!name.substring(name.length()-4).equals(".png")){
						name+=".png";
					}
				}else{
					name+=".png";
				}
				dw.getDrawPanel().exportAs(name);
			}

		}

	}

}