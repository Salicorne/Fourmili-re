
/**
 * Cette classe mod�lise le territoire sur lequel se d�place les fourmis :
 *  les tas de nourriture,les ph�romones, les obstacles ...
 * */
 
public class Zone {
  private static final int TASMAX = 10; // nombre maximum de tas
  private static final int QUANTITE = 10; // quantit� de nourriture d�pos�e
  private static int NUANCE = 10; //coefficient multiplicateur pour l'affichage du tas
  private static final int COTETAS = 10; 
  private int [][] t; // le territoire des fourmis
  private int dim;      // la dimension du territoire
  private Pos[] lesTas = new Pos[TASMAX]; // les tas de nourriture
  private int nbTas = 0; // le nombre de tas
  
  /*************************************************
   * constructeur de la Zone 
   * @param dim  la dimension du terrain
   * <ul>une zone est modelisee par ces informations :
   * <li>   le tableau t representant le territoire des fourmis 
   * <li>    - t[i][j] = Integer.MAX_VALUE => obstacle
   * <li>    - t[i][j] > 0 => quantit� de nourriture
   * <li>    - t[i][j] = 0 => libre
   * <li>    - t[i][j] < 0 => ph�romone (pour la suite)
   * <li>  la dimension de la Zone(le cote du carre)
   * <li>  le nombre maximum de tas
   * <li>  le nombre de tas de nourriture
   * <li>  la taille des tas 
   * <li>  la position des tas de nourriture
   * <ul>
   * ***********************************************/
  public Zone (int dim ){
    this.dim = dim;
    this.t = new int[dim][dim];
  }
    /*************************************************
    * accesseur qui rend la dimension
    * @return la dimension
    * ***********************************************/
  public int getDim() {
    return dim;
  }
   /***********************************************************************
    * teste si une position est valide ( situ�e sur la Zone)
    * @param p  la position � tester
    * @return vrai si OK
    * ********************************************************************/
  public boolean posValide(Pos p){
    return (p.cx()>0 && p.cx()<t.length && p.cy()>0 && p.cy()<t.length) ;
   }

   /********************************************************
    *  installe un tas de nourriture carr� 
    * @param  p  la position centrale du tas
    * *******************************************************/
  public void metTas(Pos p){
    int R = COTETAS/2;
    for(int i=p.cx()-R;i<=p.cx()+R-1;i++){
      for(int j=p.cy()-R;j<=p.cy()+R-1;j++){
        t[i][j]=QUANTITE;
      }//for j
    }//for i
    lesTas[nbTas]=p;
    nbTas++;
  }
 
   /***********************************************************************
    * fonction d'acc�s � la quantit� de nourriture � une position donn�e
    * @param p  la position 
    * @return la quantit� 
    * ********************************************************************/
  // rend la valeur situee � la position p
   public int getQuantite(Pos p){
      return t[p.cx()][p.cy()];
    }

   /***********************************************************************
    * diminue la quantit� de nourriture d'une unit� � la position p
    * @param p  la position 
    * ********************************************************************/
    public void diminue(Pos p){
      t[p.cx()][p.cy()]=t[p.cx()][p.cy()]-1 ;
    }
    /*************************************************
    *  affiche les tas sur le terrain
    * ***********************************************/
  private void montreLeTas(Pos p){
    int R=COTETAS/2;
      for(int i=p.cx()-R;i<=p.cx()+R-1;i++){
        for(int j=p.cy()-R;j<=p.cy()+R-1;j++){
          int c=NUANCE * Math.abs(t[i][j]);
          Fourmiliere.afficheur.pixel(i,j,c,c,c);
        }//for j
      }//for i
  }

   /**************************************************************
     * affiche sur le terrain les tas (plus tard les obstacles)
     * ************************************************************/
   public void seMontre(){
    for (int k = 0;k<nbTas;k++){
      Pos p = lesTas[k];
       montreLeTas(p);
     }
   }
   /***************************************************************
    * transforme en String la Zone : dimension - nid - tas 
    * @return l'objet Zone transform� en String
    * ******************************************************************/
    public String toString(){
      String resul = "dim="+this.dim+"\n";
      for(int i=0; i<nbTas;i++){
        resul += lesTas[i] +" ";
      }
      return resul;
    }  
    /********************************************************************
      * pose de la ph�romone � la position p, direction d
      * @param p la position
      * @param d la direction
      * *****************************************************************/
    public void posePhero(Pos p, int d){
      t[p.cx()][p.cy()]= - (d+1);//-1 � -8 pb si 0
    }
}


     