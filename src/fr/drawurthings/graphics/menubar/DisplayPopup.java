package fr.drawurthings.graphics.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import fr.drawurthings.graphics.window.DrawWindow;
import fr.drawurthings.model.Paint;

@SuppressWarnings("serial")
public class DisplayPopup extends JPopupMenu{
	
	private JMenuItem toolbox, figlist,percent25,percent50,percent,percent150,percent250;
	private JFrame tb, fl;
	private Paint p;
	
	public DisplayPopup(DrawWindow dw){
		this.tb = dw.getToolFrame();
		this.fl = dw.getFigsFrame();
		this.p = dw.getPaint();
		this.build();
	}
	
	public void build(){
		DisplayPopupListener dpl = new DisplayPopupListener();
		JMenu zoom = new JMenu("Zoom");
		percent25 = new JMenuItem("25%");
		percent25.addActionListener(dpl);
		percent50 = new JMenuItem("50%");
		percent50.addActionListener(dpl);
		percent = new JMenuItem("100%");
		percent.addActionListener(dpl);
		percent150 = new JMenuItem("150%");
		percent150.addActionListener(dpl);
		percent250 = new JMenuItem("250%");
		percent250.addActionListener(dpl);
		zoom.add(percent25);
		zoom.add(percent50);
		zoom.add(percent);
		zoom.add(percent150);
		zoom.add(percent250);
		this.add(zoom);
		this.add(new Separator());
		this.toolbox = new JCheckBoxMenuItem("Boite à outils", tb.isShowing());
		this.toolbox.addActionListener(dpl);
		this.figlist = new JCheckBoxMenuItem("Liste de figures", fl.isShowing());
		this.figlist.addActionListener(dpl);
		this.add(toolbox);
		this.add(figlist);
		
	}

	class DisplayPopupListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem) e.getSource();
			if(source.equals(toolbox)){
				tb.setVisible(!tb.isShowing());
			}else if(source.equals(figlist)){
				fl.setVisible(!fl.isShowing());
			}else if(source.equals(percent25) || source.equals(percent50)){
				p.setMagnifyingLevel(Double.parseDouble(source.getText().substring(0, 2))/100);
			}else if(source.equals(percent) || source.equals(percent150) || source.equals(percent250)){
				p.setMagnifyingLevel(Double.parseDouble(source.getText().substring(0, 3))/100);
			}
		}
		
		
	}
}
