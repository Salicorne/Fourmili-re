/**
 * La classe Fourmiliere est la classe qui sert de test à l'application
 * C'est ici que se construit le scénario qui dirige l'application
 ***************************************************************************/
public class Fourmiliere{
    /**
   * Attribut Zone car informations utiles pour les autres classes
   * ***********************************************************/
  public static Zone zone;
  /**
   * Attribut afficheur utile pour pouvoir afficher les pixels
   * à partir des autres classes : Zone, Fourmi,...
   * ***********************************************************/
   public static Afficheur afficheur =  new Afficheur(601,601,"Fourmiliere ");
   public static void main(String[] args){
       zone = new Zone(500);
       Pos p1 = new Pos(200,220);
       Pos p2 = new Pos(250,240);
       Pos p3 = new Pos(160,180); 
       Pos pNid = new Pos(200,200);
       zone.metTas(p1);
       zone.metTas(p2);
       zone.metTas(p3);
       zone.seMontre();
       Colonie c = new Colonie(pNid,500);
       System.out.println(c);
       c.bouge(zone, 1000);
       c.seMontre();
       System.out.println(c);
	   //Tests textuels : 
	   Pos pp1 = new Pos(200, 200);
	   
    }
}
