
/**
 * Cette classe modï¿½lise le territoire sur lequel se dï¿½place les fourmis :
 *  les tas de nourriture,les phï¿½romones, les obstacles ...
 * */
 
public class Zone {
  private static final int TASMAX = 10; // nombre maximum de tas
  private static final int QUANTITE = 10; // quantitï¿½ de nourriture dï¿½posï¿½e
  private static int NUANCE = 10; //coefficient multiplicateur pour l'affichage du tas
  private static final int COTETAS = 10; 
  private int [][] t; // le territoire des fourmis
  private int dim;      // la dimension du territoire
  private Pos[] lesTas = new Pos[TASMAX]; // les tas de nourriture
  private Pos[] lesMurs = new Pos[20];
  private int nbTas = 0; // le nombre de tas
  private int nbMurs = 0; // le nombre de murs
  
  /*************************************************
   * constructeur de la Zone 
   * @param dim  la dimension du terrain
   * <ul>une zone est modelisee par ces informations :
   * <li>   le tableau t representant le territoire des fourmis 
   * <li>    - t[i][j] = Integer.MAX_VALUE => obstacle
   * <li>    - t[i][j] > 0 => quantitï¿½ de nourriture
   * <li>    - t[i][j] = 0 => libre
   * <li>    - t[i][j] < 0 => phï¿½romone (pour la suite)
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
    * teste si une position est valide ( situï¿½e sur la Zone)
    * @param p  la position ï¿½ tester
    * @return vrai si OK
    * ********************************************************************/
  public boolean posValide(Pos p){
    return (p.cx()>0 && p.cx()<t.length && p.cy()>0 && p.cy()<t.length && this.getQuantite(p) != -10) ;
   }

   /********************************************************
    *  installe un tas de nourriture carrï¿½ 
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
  
  public void metMur(Pos p) { //On met un mur et on le montre
	  int R = COTETAS/2;
	    for(int i=p.cx()-R;i<=p.cx()+R-1;i++){
	      for(int j=p.cy()-R;j<=p.cy()+R-1;j++){
	        t[i][j]=-10;
	        Fourmiliere.afficheur.pixel(i, j, 0, 128, 255);
	      }//for j
	    }//for i
	   lesMurs[nbMurs] = p;
	   nbMurs++;
  }
 
   /***********************************************************************
    * fonction d'accï¿½s ï¿½ la quantitï¿½ de nourriture ï¿½ une position donnï¿½e
    * @param p  la position 
    * @return la quantitï¿½ 
    * ********************************************************************/
  // rend la valeur situee ï¿½ la position p
   public int getQuantite(Pos p){
      return t[p.cx()][p.cy()];
    }

   /***********************************************************************
    * diminue la quantitï¿½ de nourriture d'une unitï¿½ ï¿½ la position p
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
  
  private void montreLeMur(Pos p) {
	  int R=COTETAS/2;
      for(int i=p.cx()-R;i<=p.cx()+R-1;i++){
        for(int j=p.cy()-R;j<=p.cy()+R-1;j++){
          Fourmiliere.afficheur.pixel(i,j,0,255,255);
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
    for(int i = 0;i<this.nbMurs;i++) {
    	Pos p = lesMurs[i];
    	montreLeMur(p);
    }
   }
   /***************************************************************
    * transforme en String la Zone : dimension - nid - tas 
    * @return l'objet Zone transformï¿½ en String
    * ******************************************************************/
    public String toString(){
      String resul = "dim="+this.dim+"\n";
      for(int i=0; i<nbTas;i++){
        resul += lesTas[i] +" ";
      }
      return resul;
    }  
    /********************************************************************
      * pose de la phï¿½romone ï¿½ la position p, direction d
      * @param p la position
      * @param d la direction
      * *****************************************************************/
    public void posePhero(Pos p, int d){
      t[p.cx()][p.cy()]= - (d+1);//-1 à -8 pb si 0
    }
}


     