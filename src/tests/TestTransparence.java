package tests;


import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
	
public class TestTransparence {
	 
	public class Transparence3 extends JFrame implements ChangeListener {
	 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JSlider slider;
		private MonContainer container;
	 
		public Transparence3 () {
	 
			super("Transparence");
	 
			slider = new JSlider(JSlider.HORIZONTAL,0,100,100);
			slider.setMajorTickSpacing(10);
			slider.setMinorTickSpacing(5);
			slider.setPaintTicks(true);
	        slider.addChangeListener(this);
	        container = new MonContainer(this.getLayeredPane());
	        this.setContentPane(container);
	 
			this.pack();
			this.setResizable(false);
	 
		}
	 
		public void stateChanged(ChangeEvent e)
		{
	 
		}
	 

	 
	}
	 
	class Dessin extends JPanel {
	 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private float transparency = 1.0f;
		private int x1 = 0;
		private int x2 = 0;
		private int y1 = 0;
		private int y2 = 0;
	 
		public Dessin (int X1, int X2, int Y1, int Y2)
		{
			super();
			this.setBounds(0,0,400,400);
			this.setOpaque(false);
			x1 = X1;
			x2 = X2;
			y1 = Y1;
			y2 = Y2;
		}
	 
		public void setTransparency(float aTransparency)
		{
			transparency = aTransparency;
		}
	 
	 
		public void paint(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,Math.min(transparency,1.0f)));
		  	g2.draw(new Rectangle(x1,y1,x2,y2));
		}
	 
	}
	 
	class MonContainer extends JPanel {
	 
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLayeredPane layeredPane;
		private Dessin panel1;
		private Dessin panel2;
	 
		public MonContainer (JLayeredPane aLayeredPane)
		{
			super();
			this.setPreferredSize(new Dimension(400,400));
			this.panel1 = new Dessin(50,50,30,30);
			this.panel2 = new Dessin(60,60,20,20);
			this.layeredPane = aLayeredPane;
			this.layeredPane.add(panel1,new Integer(-3000));
			this.layeredPane.add(panel2,new Integer(0));
	    }
	}
	public static void main (String args[]) {
		 
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    }
		catch (Exception e) {}
	//	new Transparence3();
	//TODO TestTransparence bugué
	}
}
