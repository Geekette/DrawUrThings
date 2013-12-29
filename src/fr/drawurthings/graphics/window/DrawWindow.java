package fr.drawurthings.graphics.window;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class DrawWindow extends JFrame{
	
	JPanel draw;
	
	public DrawWindow(JPanel draw){
		super("DrawUrThings : Draw (Alpha)");
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
		JScrollPane jsp = new JScrollPane(draw);
		jsp.setPreferredSize(new Dimension(800, 600));
		c.add(jsp, BorderLayout.CENTER);
		/*JPanel bottom_bar = new JPanel();
		JMenu zoom = new JMenu("100%");
		bottom_bar.add(zoom);
		c.add(bottom_bar,BorderLayout.SOUTH);
		//c.add(draw);*/
		this.pack();
	}

}
