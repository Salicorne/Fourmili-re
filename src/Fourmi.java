import java.awt.Color;
/*http://java.sun.com/j2se/1.5.0/docs/api/java/awt/Color.html*/
/**
 * La classe Fourmi mod�lise une fourmi "�l�mentaire" qui servira
 * dans la suite de l'application � d�finir par h�ritage des fourmis
 * plus �labor�es
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
	 * constructeur qui cr�� une fourmi de base sans nourriture
	 * @param pf sa position
	 * ***********************************************/
	public Fourmi(Pos pf){
		this.p = pf;
		this.charge = false;
		this.couleur = COULEURVIDE;
	}
	
	/**************************************************************
	 * constructeur qui cr�� une fourmi de base sans nourriture
	 * � une position al�atoire
	 * ***********************************************/
	public Fourmi(){
		this.p = new Pos((int)(Math.random()*600),(int)(Math.random()*600));
		this.charge = false;
		this.couleur = COULEURVIDE;
	}
	/*************************************************
	 * transforme en String
	 * @return l'objet Fourmi transform� en String
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
	 * sauf s'il reste de la nourriture � cet endroit
	 * ****************************************************/
	public void sEfface(){
		Fourmiliere.afficheur.carreCentre(p.cx(),p.cy(),3,new Color(0,0,0));
		Fourmiliere.zone.seMontre(); //la fourmi pouvait �tre sur un tas + ralentit l'ex�cution
	}
	/***********************************************************************
	 * prend une unit� de nourriture � la position courante
	 * passe � l'�tat charge = vrai
	 * change de couleur
	 * ********************************************************************/
	public void prend(){
		this.charge = true;
		this.couleur = COULEURCHARGE;
	}
	/*******************************************************************
	 * avance de fa�on al�atoire sur une des 8 positions voisines :
	 * si la position est valide 
	 *  si la fourmi est � vide et la position contient de la nourriture => prend
	 *  si la fourmi est charg�e et sa position sur le nid =>pose la nourriture
	 *  la position change
	 * *********************************************************************/
	public void bouge(Zone Monde){
		Pos Nouvelle = this.p.posVoisine();
		while(!Monde.posValide(Nouvelle))
		{
			Nouvelle = this.p.posVoisine();
		}
		if(Monde.getQuantite(Nouvelle)>0 && !this.charge)
		{
				this.prend();
				Monde.diminue(Nouvelle);

		}
		this.sEfface();
		this.p = Nouvelle;
		this.seMontre();
		
	}//bouge
	
}

