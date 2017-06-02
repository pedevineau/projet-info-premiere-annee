package ihm.panneau;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PanneauImage extends JComponent {

	private static final long serialVersionUID = 1L;
	
	final BufferedImage image;
	int x;
	int y;

	public PanneauImage(File file, int x, int y) throws IOException {
		image = ImageIO.read(file);
		this.x = x;
		this.y = y;
		setSize(new Dimension(image.getWidth(), image.getHeight()));
	}
	public PanneauImage(File file) throws IOException{
		this(file, 0, 0);
	}

	public PanneauImage(File file, Dimension dimension) throws IOException {
		image = ImageIO.read(file);
		setSize(dimension);
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, x, y, image.getWidth(), image.getHeight(), this);
		
	}
	
	

	/*	
    public static void main(String[] args) {
		JFrame frame = new JFrame("test");
		try {
			File file = new File("C:/Users/MF/Desktop/luteceest.jpg");
			PanneauImage img = new PanneauImage(file);
			frame.add(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	*/
	
}
