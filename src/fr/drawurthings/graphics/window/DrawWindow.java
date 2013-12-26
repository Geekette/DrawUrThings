package fr.drawurthings.graphics.window;

import java.awt.Container;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawWindow extends JFrame{
	
	JPanel draw;
	
	public DrawWindow(JPanel draw){
		super("DrawUrThings : Draw (Alpha)");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.draw = draw;
		this.setLocation(150, 50);
		this.setIconImage(new ImageIcon("vectoricon.png").getImage());
		this.buildContentPane();
	}
	
	public void buildContentPane(){
		Container c = this.getContentPane();
		c.add(draw);
		this.pack();
	}

}
