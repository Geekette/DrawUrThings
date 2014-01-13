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

	
	public AboutWindow(){
		super("A propos de DrawUrThings");
		this.setIconImage(new ImageIcon("vectoricon.png").getImage());
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
		
		JLabel logo = new JLabel(new ImageIcon("logo.png"));
		
		String resume = "\tDrawUrThings (Version 1.0 - RC1 (13012014) - JRE "+ System.getProperty("java.version")+")\n\tAlexandre Canny\n\tThéo Plockyn\n\tValentin Ramecourt";
		resume = resume.replaceAll("\n", "<br>");
		
		String credit = "Remerciements : \n\tNicolas Anquetil\n\tEric Lepretre\n\tJean Martinet\n\nRessources graphiques : \n\tFugue Icons (par Yusuke Kamiyamane) \n\nPolices :\n\tWaltograph (par Justin Callaghan)\n\n"
				+ "Remerciements spéciaux :";
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
