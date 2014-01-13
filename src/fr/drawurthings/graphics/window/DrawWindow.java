package fr.drawurthings.graphics.window;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.drawurthings.graphics.draw.DrawPanel;
import fr.drawurthings.graphics.menubar.TopMenuBar;
import fr.drawurthings.model.Paint;

@SuppressWarnings("serial")
public class DrawWindow extends JFrame implements Observer{
	
	private DrawPanel draw;
	private Paint paint;
	private JFrame toolFrame, figsFrame;
	private JMenuItem zoom;
	private JScrollPane jsp;
	
	public DrawWindow(Paint p){
		super("DrawUrThings : Draw (RC1)");
		this.setIconImage(new ImageIcon("ressources/icon/vectoricon.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(170, 50);
		this.paint = p;
		this.paint.addObserver(this);
		this.draw = new DrawPanel(this.paint);
		this.toolFrame = new Toolbox(this.paint.getToolbox());
		this.figsFrame = new FigureListWindows(p);
		this.buildContentPane();
	}
	
	public void buildContentPane(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(new TopMenuBar(this),BorderLayout.NORTH);
		jsp = new JScrollPane(draw);
		jsp.setPreferredSize(new Dimension(800, 600));
		c.add(jsp, BorderLayout.CENTER);
		JPanel bottom_bar = new JPanel();
		zoom = new JMenuItem("100%");
		bottom_bar.add(zoom);
		c.add(bottom_bar,BorderLayout.SOUTH);
		this.pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public Paint getPaint(){
		return this.paint;
	}
	
	public DrawPanel getDrawPanel(){
		return draw;
	}
	
	public JFrame getToolFrame(){
		return this.toolFrame;
	}
	
	public JFrame getFigsFrame(){
		return this.figsFrame;
	}

	@Override
	public void update(Observable o, Object arg) {
		jsp.setPreferredSize(new Dimension((int) (1920*paint.getMagnifyingLevel()), (int) (1080*paint.getMagnifyingLevel())));
		this.zoom.setText("" + (int) (paint.getMagnifyingLevel()*100)+"%");
		repaint();
	}
	

}
