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
       Pos p3 = new Pos(350,350); 
       Pos p4 = new Pos(325,325); 
       Pos p5 = new Pos(325,335);
       Pos p6 = new Pos(335, 325);
       Pos p7 = new Pos(270, 260);
       Pos p8 = new Pos(290, 260);
       Pos pNid1 = new Pos(300,300);
       zone.metTas(p1);
       zone.metTas(p2);
       zone.metTas(p3);
       zone.metMur(p4);
       zone.metMur(p5);
       zone.metMur(p6);
       zone.metMur(p7);
       zone.metMur(p8);
       for(int i=0;i<10;i++) {
    	   zone.metMur(new Pos(250+10*i, 260));
       }
       
       zone.seMontre();
       Colonie c1 = new Colonie(pNid1,50);
       //System.out.println(c1);
       c1.bouge(zone, 100000);
       c1.seMontre();
       System.out.println(c1);
    }
}
