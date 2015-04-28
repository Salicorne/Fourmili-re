/**
 * Cette classe permet de gérer la position dans un plan : 
 * coordonnées d'un point en 2D
 */
public class Pos {
  private int  x; //coordonnées dans la zone
  private int  y;
  /*************************************************
    * constructeur
    * @param a : abscisse du point
    * @param b : ordonnée
    * ***********************************************/
  public Pos(int a,int b){
    this.x=a;
    this.y=b;
  }
    /*************************************************
    * transforme en String
    * @return l'objet Pos transformé en String
    * ***********************************************/
  public String toString(){
    return "Pos : "+this.x+","+this.y;
  }  
    /***********************************************************************
    * teste l'égalité de la position en référence avec une autre position
    * @param p : la position à comparer avec la position en référence
    * @return vrai si même position et faux sinon
    * ********************************************************************/
  public boolean equals(Pos p){
    return p.cx()==this.cx() && p.cy()==this.cy() ;
  }
    /*************************************************
    * accesseur qui rend l'abscisse
    * @return l'abscisse
    * ***********************************************/
  public int cx(){
    return this.x;
  }
    /*************************************************
    * accesseur qui rend l'ordonnée
    * @return l'ordonnée 
    * ***********************************************/
  //accès
  public int cy(){
    return this.y;
  }
  
  /****************************************************************
    * Constructeur qui fabrique une position aléatoire dans la zone
    * @param dim borne max de la zone
    * ******************************************************/
   public Pos(int dim){
    x = (int) (Math.random()*(dim-2))+2;
    y = (int) (Math.random()*(dim-2))+2;
  }
   
   /***********************************************************************
     * méthode qui rend une position voisine aléatoire de la position courante
     * @return une position voisine
     * ************************************************************************/
   public Pos posVoisine(){
     int dx = (int) (Math.random() *3)-1;
     int dy = (int) (Math.random() *3)-1;
     return new Pos(this.x+dx,this.y+dy);
   }
   
  
}