package fr.drawurthings.graphics.window;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.drawurthings.bin.Paint;
import fr.drawurthings.graphics.panel.TopMenuBar;

@SuppressWarnings("serial")
public class DrawWindow extends JFrame{
	
	JPanel draw;
	Paint paint;
	
	public DrawWindow(Paint p, JPanel draw){
		super("DrawUrThings : Draw (Alpha)");
		this.paint = p;
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.draw = draw;
		this.setLocation(170, 50);
		this.setIconImage(new ImageIcon("vectoricon.png").getImage());
		this.buildContentPane();
	}
	
	public void buildContentPane(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(new TopMenuBar(paint),BorderLayout.NORTH);
		
		JScrollPane jsp = new JScrollPane(draw);
		jsp.setPreferredSize(new Dimension(800, 600));
		c.add(jsp, BorderLayout.CENTER);
		JPanel bottom_bar = new JPanel();
		JMenu zoom = new JMenu("100%");
		bottom_bar.add(zoom);
		c.add(bottom_bar,BorderLayout.SOUTH);
		//c.add(draw);*/
		this.pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
