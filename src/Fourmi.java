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
	protected Dir dir;
	protected Colonie colonie;
	
	/**************************************************************
	 * constructeur qui crï¿½ï¿½ une fourmi de base sans nourriture
	 * @param pf sa position
	 * ***********************************************/
	public Fourmi(Colonie c, Pos pf){
		// DONE
		this.p = pf;
		this.charge = false;
		this.couleur = COULEURVIDE;
		this.dir = new Dir();
		this.colonie = c;
	}

	/**************************************************************
	 * constructeur qui crï¿½ï¿½ une fourmi de base sans nourriture
	 * ï¿½ une position alï¿½atoire
	 * ***********************************************/
	public Fourmi(Colonie c){
		// DONE
		this.p = new Pos((int)(Math.random()*600), (int)(Math.random()*600));
		this.charge = false;
		this.couleur = COULEURVIDE;
		this.dir = new Dir();
		this.colonie = c;
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
	
	public void lache() {
		this.charge = false;
		this.couleur = COULEURVIDE;
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
		if(this.charge == true) {
			this.bougeCharge(z);
		}else{
			//On regarde si on trouve des phéromones
			if(z.getQuantite(this.p) < 0 && z.getQuantite(this.p) != -10) {
				this.bougePheromone(z);
			}else{
				this.bougeVide(z);
			}
		}
	}
	
	public void bougeCharge(Zone z) {
		//On rentre au nid ! 
		this.sEfface();
		this.dir = new Dir(this.p, this.colonie.getPNid());
		Pos newp = new Pos(this.p.cx()+this.dir.dx(), this.p.cy()+this.dir.dy());
		if(!z.posValide(newp)) {
			newp = this.dir.posConvenable(z, this.p);
		}
		this.p = newp;
		
		//On regarde s'il y a déjà une phéromone, on en pose une sinon. 
		if(z.getQuantite(this.p) == 0) {
			z.posePhero(this.p, this.dir.dirOppose());
		}
		this.seMontre();
	}
	
	public void bougePheromone(Zone z) {
		this.sEfface();
		this.dir = new Dir(-1*(z.getQuantite(this.p)+1));
		this.p = new Pos(this.p.cx()+this.dir.dx(), this.p.cy()+this.dir.dy()); //On suppose que pos est valide...
		this.seMontre();
	}
	
	public void bougeVide(Zone z) {
		//On bouge de manière "aléatoire" sans trop dévier
		Pos newp = this.dir.posVoisine(this.p);
		if(!z.posValide(newp)) { //On est hors de l'écran ou on va dans un mur
			//On fait 10 essais pour valider qu'on est bloqués, donc face à un mur (on teste les trois positions voisines possibles)
			//Méthode discutable (perte de temps...) mais on est surs de ne pas faire de contournement si on peut faire autrement. Ou alors on a vraiment pas la chance avec nous. 
			for(int b = 0;b<10;b++) {
				if(!z.posValide(newp)) {
					newp = this.dir.posVoisine(this.p);
				}
			}
			//Si après ces dix essais on est toujours bloqué, on contourne. 
			if(!z.posValide(newp)) {
				newp = this.dir.posConvenable(z, this.p);
			}
		}
		this.sEfface();
		this.dir = new Dir(this.p, newp);
		this.p = newp;
		//On regarde si on trouve de la nourriture
		if(z.getQuantite(this.p) > 0) {	
			z.diminue(this.p);
			this.prend();
		}
		this.seMontre();
	}
}

