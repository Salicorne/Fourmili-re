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
       zone = new Zone(600);
       Pos p1 = new Pos(200,320);
       Pos p2 = new Pos(250,240);
       Pos p3 = new Pos(360,380); 
       Pos pNid1 = new Pos(300,300);
       Pos pNid2 = new Pos(200,400);
       zone.metTas(p1);
       zone.metTas(p2);
       zone.metTas(p3);
       zone.seMontre();
       Colonie c1 = new Colonie(pNid1,500);
       System.out.println(c1);
       c1.bouge(zone, 1000);
       c1.seMontre();
       System.out.println(c1); 
       Colonie c2 = new Colonie(pNid2,800);
       
       for(int i=0;i<2000;i++){
         c1.bouge(zone);
         c2.bouge(zone);
         c1.seMontre();
         c2.seMontre();
       }
    }
}
