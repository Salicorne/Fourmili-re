import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *   Un simple afficheur pixel par pixel 
 */
public class Afficheur extends JFrame {

 /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
protected BufferedImage image;

 /** La tambouille Java interne pour afficher l'image */
 protected class ImagePanel extends JPanel {

  /**
	 * 
	 */
   private static final long serialVersionUID = 1L;

public ImagePanel() {
   setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
  }

  protected void paintComponent(Graphics g) {
   super.paintComponent(g);
   g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
  }
  
 }

////////////////////////////////////
// API Publique
////////////////////////////////////
  /***************************************************************
   * constructeur qui affiche une fen�tre graphique � l'�cran
   * @param width la largeur
   * @param height la hauteur
   * ***************************************************************/
 public Afficheur(int width, int height) {
  this(width, height, "Afficheur");
 }
 /***************************************************************
   * constructeur qui affiche une fen�tre graphique � l'�cran
   * @param width la largeur
   * @param height la hauteur
   * @param name le nom
   * ***************************************************************/
 public Afficheur(int width, int height, String name) {
  super(name);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
  getContentPane().add(new ImagePanel(), BorderLayout.CENTER);
  pack();
  setVisible(true);
 }

 /***************************************************************
   * m�thode qui affiche un pixel blanc
   * @param x l'abscisse
   * @param y l'ordonn�e
   * ***************************************************************/
 public void pixel(int x, int y) {
  pixel(x,y,Color.white);
 }
  /***************************************************************
   * m�thode qui affiche un pixel couleur selon les composantes  RVB
   * @param x l'abscisse
   * @param y l'ordonn�e
   * @param rouge la composante R
   * @param vert la composante V
   * @param bleu la composante B
   * ***************************************************************/
 public void pixel(int x, int y, int rouge, int vert, int bleu) {
  pixel(x,y,new Color(rouge,vert,bleu));
 }
 

  /***************************************************************
   * m�thode qui affiche un pixel couleur de type Color
   * @param x l'abscisse
   * @param y l'ordonn�e
   * @param c la couleur
   * ***************************************************************/
 public void pixel(int x, int y, Color c) {
  image.setRGB(x,y,c.getRGB());

  repaint();
 }
 
  /****************************************************************************
   * m�thode qui affiche un carr� o� tous les pixels sont d'une m�me couleur
   * @param x l'abscisse du centre du carr�
   * @param y l'ordonn�e du centre du carr�
   * @param cote le c�t� du carr�
   * @param c la couleur
   * ***************************************************************/
  public void carreCentre(int x, int y, int cote, Color c){
   int r = cote/2;
   for(int i = -r; i<=r-1;i++){
      for(int j = -r; j<=r-1;j++){
        pixel(x+i,y+j,c);
      }
    }
 }
}