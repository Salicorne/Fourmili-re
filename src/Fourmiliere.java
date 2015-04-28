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
       Pos p1 = new Pos(500,520);
       Pos p2 = new Pos(400,320);
       zone.metTas(p1);
       zone.metTas(p2);
       zone.metTas(p2);
       zone.seMontre();     
       zone.diminue(p1);
       zone.diminue(p1);
       System.out.println(zone.getQuantite(p1));
       System.out.println(zone);
       Fourmi f1= new Fourmi(p1);
       Fourmi f2= new Fourmi(new Pos(400,400));
       Fourmi f3= new Fourmi();
       f1.seMontre();
       f2.seMontre();
       f3.seMontre();
       System.out.println(f1+"    "+zone.getQuantite(p1));
       f1.prend();
       System.out.println(f1+"    "+zone.getQuantite(p1));
       f1.seMontre();
       System.out.println(f1+"-"+f2+"-"+f3);
       for(int i=0; i<1000;i++){
         f1.bouge();
         f2.bouge();
         f3.bouge();
       }
       System.out.println(f1+"-"+f2+"-"+f3);
       System.out.println(f1);
    }
}
