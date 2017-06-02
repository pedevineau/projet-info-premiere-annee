package ihm.menu;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MenuEditeur extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JLabel texteExplicatif;
	public JPanel nomPanel;
	public JLabel nomLabel;
	public JTextField nomTextField;
	public JPanel urlPanel;
	public JLabel urlLabel;
	public JTextField urlTextField;
	public JButton envoyer;
	public JButton retour;
	public String url;

	public MenuEditeur(){
		
		setLayout(new GridLayout(5,1));
		
		texteExplicatif = new JLabel("<html><div style='text-align: center;'>"
				+ "Indiquez dans ce champ le chemin du fichier YAML"
				+ " contenant les informations relatives à votre nouveau monde.<br>"
				+ " Pour un exemple, consultez le dossier fichiersYAML/modeles"
				+ "</div></html>");
		texteExplicatif.setSize(700, 200);
		
		nomPanel = new JPanel();		
		nomLabel = new JLabel("Nom de votre monde");
		nomTextField = new JTextField(20);
		nomPanel.add(nomLabel);
		nomPanel.add(nomTextField);
		
		urlPanel = new JPanel();
		urlLabel = new JLabel("Chemin vers votre fichier YAML");
		urlTextField = new JTextField(60);
		urlPanel.add(urlLabel);
		urlPanel.add(urlTextField);
		
		envoyer = new JButton("Envoyer");
		retour = new JButton("Retour");
		
		Border bordureVideAvecMarge = BorderFactory.createEmptyBorder(10, 15, 10, 15);
		nomTextField.setBorder(bordureVideAvecMarge);
		urlTextField.setBorder(bordureVideAvecMarge);
		
		add(texteExplicatif);
		add(nomPanel);
		add(urlPanel);
		add(envoyer);
		add(retour);

	}
	
	public static void main (String[] args){
		MenuEditeur me = new MenuEditeur();
		me.setSize(700, 400);
		me.setVisible(true);
	}
}
