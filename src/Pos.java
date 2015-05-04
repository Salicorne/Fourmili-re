/**
 * Cette classe permet de g�rer la position dans un plan : 
 * coordonn�es d'un point en 2D
 */
public class Pos {
  private int  x; //coordonn�es dans la zone
  private int  y;
  /*************************************************
    * constructeur
    * @param a : abscisse du point
    * @param b : ordonn�e
    * ***********************************************/
  public Pos(int a,int b){
    this.x=a;
    this.y=b;
  }
    /*************************************************
    * transforme en String
    * @return l'objet Pos transform� en String
    * ***********************************************/
  public String toString(){
    return "Pos : "+this.x+","+this.y;
  }  
    /***********************************************************************
    * teste l'�galit� de la position en r�f�rence avec une autre position
    * @param p : la position � comparer avec la position en r�f�rence
    * @return vrai si m�me position et faux sinon
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
    * accesseur qui rend l'ordonn�e
    * @return l'ordonn�e 
    * ***********************************************/
  //acc�s
  public int cy(){
    return this.y;
  }
  
  /****************************************************************
    * Constructeur qui fabrique une position al�atoire dans la zone
    * @param dim borne max de la zone
    * ******************************************************/
   public Pos(int dim){
    x = (int) (Math.random()*(dim-2))+2;
    y = (int) (Math.random()*(dim-2))+2;
  }
   
   /***********************************************************************
     * m�thode qui rend une position voisine al�atoire de la position courante
     * @return une position voisine
     * ************************************************************************/
   public Pos posVoisine(){
     int dx = (int) (Math.random() *3)-1;
     int dy = (int) (Math.random() *3)-1;
     return new Pos(this.x+dx,this.y+dy);
   }
   
  
}