package tests;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import ihm.panneau.PanneauImage;

public class TestGraphique extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	JLayeredPane jLayeredPane;

	PanneauImage luteceOuest;
	PanneauImage luteceEst;
	PanneauImage alerte;

	public TestGraphique() {

		super("Test carte");

		this.setLayout(new GridLayout());

		File file1 = new File("images/ambulance rouge.jpg");
		File file2 = new File("images/ambulance verte.jpg");

		try {
			luteceOuest = new PanneauImage(file1);
			add(luteceOuest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			luteceEst = new PanneauImage(file2);
			add(luteceEst);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * try { alerte = new ImageComponent(new File(
		 * "C:/Users/MF/Desktop/Cercles alertes.png")); } catch (IOException e)
		 * { e.printStackTrace(); }
		 */
		luteceOuest.addMouseListener(this);
		luteceEst.addMouseListener(this);
				
	}

	/*
	 * public void paint(Graphics g) { super.paint(g); g.drawImage(alerte.image,
	 * 90, 60, alerte.image.getWidth()/10, alerte.image.getHeight()/10, this); }
	 */
	
	public static void main(String[] args) {
		TestGraphique fenetre = new TestGraphique();
		int W = 480, H = 272;
		W = 1000; H = 720;
		fenetre.setSize(new Dimension(W, H));
		fenetre.setLocation(180, 0);
		fenetre.setVisible(true);
		fenetre.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
			}
			@Override
			public void mouseDragged(MouseEvent arg0) {
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("hello");
		remove(luteceOuest);
		add(luteceOuest);
		revalidate();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println(e.getX() + " / " + e.getY());
	}
}
