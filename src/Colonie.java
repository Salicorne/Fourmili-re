import java.awt.Color;
/**
 * La classe Colonie contient toutes les fourmis pr�sentes
 * dans la fourmili�re dans cette version il ya des fourmiDir
 * et des fourmiPhero
 * *********************************************************/
public class Colonie{
  private Fourmi[] population;
  private int nb;
  private Pos pNid;     // la position du nid
  private int quantiteNid;
 
   /*************************************************************
    * constructeur Colonie qui cr�e un ensemble de fourmis 
    * avec une position donn�e  : le Nid 
    * @param p la position du nid
    * @param taille  le nombre de fourmis
    * **********************************************************/
  public Colonie(Pos p,int taille){
	  //
	  if(taille>0) {
		  this.population = new Fourmi[taille];
		  for(int i = 0;i<taille;i++) {
			  this.population[i] = new Fourmi(this, p);
		  }
	  }
	  this.nb = taille;
	  this.pNid = p;
	  this.quantiteNid = 0;	//Pas encore de nourriture dans le nid ?
  }
   /***********************************************************************
    * rend la position du Nid
    * @return Pos la position 
    * ********************************************************************/  
    public Pos getPNid() {
      return pNid;
   }
  
  /***********************************************************************
    * teste si une position correspond � la position du Nid
    * @param p  la position � tester
    * @return vrai si OK
    * ********************************************************************/
    public boolean estNid(Pos p){
      return p.equals(pNid);
    }  
   /**********************************************************************
    * ajoute une unit� de nourriture dans le nid
    * *****************************************************************/
    public void poseNid(){
      this.quantiteNid++;
    }
    
   /********************************************************
    *  affiche le nid  sur le terrain carr� Jaune
    * la taille augmente avec la quantit� de nourriture
    * *******************************************************/ 
   private void montreLeNid(){
     int cote = 10+(quantiteNid/20);
     Fourmiliere.afficheur.carreCentre(this.pNid.cx(),this.pNid.cy(),cote,Color.YELLOW);
   }
   /***********************************************************
    * La colonie se bouge d'un pas
    * ************************************************************/
  public void bouge(Zone z){
	  //DONE
	  if(this.nb > 0) {
		  for(int i = 0;i<this.nb;i++) {
			  this.population[i].bouge(z);
			  if(this.estNid(this.population[i].p)) {
				  this.poseNid();
				  this.population[i].lache();
			  }
		  }
		  this.montreLeNid();
	  }
  }
    /***********************************************************
    * La colonie se bouge de n pas 
    * @param n le nombre de pas
    * ************************************************************/
 public void bouge(Zone z, int n){
	 //DONE
	 if(this.nb > 0) {
		 for(int i = 0;i<n;i++) {
			 this.bouge(z);
			 //System.out.println(this.quantiteNid);
		 }
	 }
 }
 
 public void vit(Zone z) {
	 if(this.nb > 0) {
		 while(true) {
			 this.bouge(z);
			 //System.out.println(this.quantiteNid);
		 }
	 }
 }

  /**********************************************************
    * Transforme en objet String une colonie
    * *******************************************************/
  public String toString(){
    String resul = "Colonie : "+"nb = "+this.nb +"le nid"+this.pNid+" fourmis\n";
     for(int i=0; i<this.nb; i++){
      resul +=  this.population[i];
    }
     return resul;
  }
  
   /**********************************************************
    * Affiche la colonie sur l'�cran graphique 
    * *******************************************************/
  public void seMontre(){
     for(int i=0; i<this.nb; i++){
      this.population[i].seMontre();
    }
     montreLeNid();
  }
}
      
