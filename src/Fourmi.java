import java.awt.Color;
/*http://java.sun.com/j2se/1.5.0/docs/api/java/awt/Color.html*/
/**
 * La classe Fourmi modï¿½lise une fourmi "ï¿½lï¿½mentaire" qui servira
 * dans la suite de l'application ï¿½ dï¿½finir par hï¿½ritage des fourmis
 * plus ï¿½laborï¿½es
 * ***********************************************************************/
public class Fourmi{ 
	protected Pos p;
	/** indique si la fourmi a pris de la nourriture */
	protected boolean charge;
	/** la couleur d'une fourmi de base */
	protected Color couleur; 
	protected static  Color COULEURVIDE = Color.PINK;
	protected static  Color COULEURCHARGE = Color.RED;

	/**************************************************************
	 * constructeur qui crï¿½ï¿½ une fourmi de base sans nourriture
	 * @param pf sa position
	 * ***********************************************/
	public Fourmi(Pos pf){
		// DONE
		this.p = pf;
		this.charge = false;
		this.couleur = COULEURVIDE;
	}

	/**************************************************************
	 * constructeur qui crï¿½ï¿½ une fourmi de base sans nourriture
	 * ï¿½ une position alï¿½atoire
	 * ***********************************************/
	public Fourmi(){
		// DONE
		this.p = new Pos((int)(Math.random()*600), (int)(Math.random()*600));
		this.charge = false;
		this.couleur = COULEURVIDE;
	}
	/*************************************************
	 * transforme en String
	 * @return l'objet Fourmi transformï¿½ en String
	 * ***********************************************/
	public String toString(){
		return "Fourmi : "+this.p+" charge "+this.charge+" \n";
	}
	/******************************************************
	 * affiche la fourmi sur le terrain 3 pixels x 3 pixels 
	 * avec une couleur choisie 
	 * ****************************************************/
	public void seMontre(){
		Fourmiliere.afficheur.carreCentre(p.cx(),p.cy(),3,couleur);
	}

	/******************************************************
	 * efface la position courante de la fourmi
	 * sauf s'il reste de la nourriture ï¿½ cet endroit
	 * ****************************************************/
	public void sEfface(){
		Fourmiliere.afficheur.carreCentre(p.cx(),p.cy(),3,new Color(0,0,0));
		Fourmiliere.zone.seMontre(); //la fourmi pouvait ï¿½tre sur un tas + ralentit l'exï¿½cution
	}
	/***********************************************************************
	 * prend une unitï¿½ de nourriture ï¿½ la position courante
	 * passe ï¿½ l'ï¿½tat charge = vrai
	 * change de couleur
	 * ********************************************************************/
	public void prend(){
		//DONE
		this.charge = true;
		this.couleur = COULEURCHARGE;
	}
	/*******************************************************************
	 * avance de faï¿½on alï¿½atoire sur une des 8 positions voisines :
	 * si la position est valide 
	 *  si la fourmi est ï¿½ vide et la position contient de la nourriture => prend
	 *  si la fourmi est chargï¿½e et sa position sur le nid =>pose la nourriture
	 *  la position change
	 * *********************************************************************/
	public void bouge(Zone z){
		//DONE
		Pos newp = this.p.posVoisine();	//aléatoire
		while(!z.posValide(newp)) {
			newp = this.p.posVoisine();
		}
		this.sEfface();
		this.p = newp;
		
		if(z.getQuantite(this.p) != 0 && this.charge == false) {
			z.diminue(this.p);
			this.prend();
		}
		
		this.seMontre();
		
	}//bouge
}

