package fr.drawurthings.graphics.window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AboutWindow extends JFrame {


	/**
	 * Instancie une fenêtre d'à propos. 
	 */
	public AboutWindow(){
		super("A propos de DrawUrThings");
		this.setIconImage(new ImageIcon("ressources/icon/vectoricon.png").getImage());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension resol = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(resol.width/2-230, resol.height/2-180);
		this.setVisible(true);
		this.buildContentPane();
	}

	public void buildContentPane(){
		Container c = this.getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel logo = new JLabel(new ImageIcon("ressources/image/logo.png"));

		String resume = "\tDrawUrThings (Version 1.0 \"Hugo\" - JRE "+ System.getProperty("java.version")+")\n\tAlexandre Canny\n\tThéo Plockyn\n\tValentin Ramecourt";
		resume = resume.replaceAll("\n", "<br>");

		String credit = "Remerciements : \n\tNicolas Anquetil\n\tEric Lepretre\n\tJean Martinet\n\nRessources graphiques : \n\tFugue Icons (par Yusuke Kamiyamane) \n\nPolices :\n\tWaltograph (par Justin Callaghan)\n\n"
				+ "Remerciements spéciaux :\n\tPatricia Everaere\n\tLes contributeurs de la documentation Java\n\nMessages des développeurs : \n\t\"Maman, on fait le Space Moutain!?\" (Geekette)\n\t\"60% of the time, it works everytime.\" (Théo)\n\t\"Battu par une petite fille!\" (PommePommeGirl)";
		JTextArea credits = new JTextArea(credit);
		credits.setEditable(false);
		JScrollPane paneCredit = new JScrollPane(credits);
		paneCredit.setPreferredSize(new Dimension(420,160));

		gbc.fill = GridBagConstraints.NORTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		c.add(logo,gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(5, 20, 5, 20);
		c.add(new JLabel("<html>" + resume),gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(5, 20, 5, 20);
		c.add(paneCredit,gbc);
		c.setPreferredSize(new Dimension(460,360));
		this.pack();
	}


}
