  /**  les 8 directions 0 ->(-1,-1), 1->(-1,0),...
   * <li>   0    7    6 
   * <li>   
   * <li>      \ | /
   * <li>   1     -    5
   * <li>      / | \  
   * <li>
   * <li>   2    3    4
   */ 
public class Dir {

  private int  coded; // entier entre 0 et 7
  private final static int[] ddx ={-1,-1,-1, 0, 1, 1, 1, 0};
  private final static int[] ddy ={-1, 0, 1, 1, 1, 0,-1,-1};
  
    /**************************************************************
    * constructeur construit une direction dont on donne le code
    * @param cd le code de la direction entre 0 et 7
    * ***********************************************************/
  public Dir(int cd){
	  //DONE
	  this.coded = cd;
  }
    /****************************************************
    * construit une direction alï¿½atoire entre 0 et 7
    * *************************************************/
  public Dir(){
	  //DONE
	  this.coded = (int)(Math.random()*8);	//entre 0 et 7
  }
  
  /************************************************************
   * construit la direction pour aller de p1 ï¿½ p2
   * @param p1 position de depart
   * @param p2 position arrivee
   *************************************************************/
  public Dir(Pos p1,Pos p2){
	  //DONE?
	  
	  //By MBertier
	  int x = p2.cx()-p1.cx();
	    int y = p2.cy()-p1.cy();
	    int i = 0;
	    coded = i;
	    double vc = Math.acos(cos(x,y,ddx[i],ddy[i]));
	    for (i=1; i<8;i++){
	      if (Math.acos(cos(x,y,ddx[i],ddy[i])) < vc){
	        vc = Math.acos(cos(x,y,ddx[i],ddy[i]));
	        coded = i;
	      }
	    }
	  
	  //By us
	  /*
	  //On se place en p1, on va en p2 !!!!!!!!!!!!!!!!!!!!
	  //On définit les déplacements verticaux et horizontaux
	  final double dx = p2.cx()-p1.cx();
	  final double dy = p2.cy()-p1.cy();
	  final double h = Math.sqrt(dx*dx+dy*dy);
	  
	  //On définit les cosinus et sinus de l'angle
	  
	  final double cos = dx/h;
	  final double sin = dy/h;
	  
	  final double pi = Math.PI;
	  
	  //On choisit la direction la plus adaptée
	  
	  if(cos<Math.cos(7*pi/8)) {
		  this.coded = 1;
	  }else if(cos>=Math.cos(7*pi/8)&&cos<Math.cos(5*pi/8)) {
		  this.coded = (sin>0) ? 0 : 2;
	  }else if(cos>=Math.cos(5*pi/8)&&cos<Math.cos(3*pi/8)) {
		  this.coded = (sin>0) ? 7 : 3;
	  }else if(cos>=Math.cos(3*pi/8)&&cos<Math.cos(pi/8)) {
		  this.coded = (sin>0) ? 6 : 4;
	  }else {
		  this.coded = 5;
	  }
	  */
	  
  }
  
    /*************************************************
    * transforme en String
    * @return l'objet Dir  transformï¿½ en String
    * ***********************************************/
  public String toString(){
    return "Dir : "+coded;
  } 
    /*************************************************
    * accesseur qui rend la composante x de la direction
    * @return la composante x (-1,0 ou 1)
    * ***********************************************/
  public int dx(){
    return ddx[coded];
  }
   /*************************************************
    * accesseur qui rend la composante y de la direction
    * @return la composante y (-1, 0 ou 1)
    * ***********************************************/ 
  public int dy(){
    return ddy[coded];
  } 
   /*********************************************************************
    * rend une direction voisine alï¿½atoire sans changer la direction
    * courante :
    * @return la position voisine
    * ******************************************************************/
  public Dir dirVoisine(){		//testé et approuvé
	  // DONE
	  int d = (int)(Math.random()*3)-1;	//d entre -1 et 1
	  //On gère 0 et 7
	  if(this.coded==7&&d==1) {
		  return new Dir(0);
	  }
	  if(this.coded == 0 && d == -1) {
		  return new Dir(7);
	  }
	  return new Dir(this.coded+d);
  }
  
  /**************************************************
   * Trouve une direction pour contourner un mur quand on est bloqué
   * @param z la zone
   * @param p la position actuelle
   * @return la position pour contourner l'obstacle
   */
  
  public Pos posConvenable(Zone z, Pos p) {
	  Pos newp = new Pos(p.cx()+Dir.ddx[this.coded], p.cy()+Dir.ddy[this.coded]);
	  while(!z.posValide(newp)) {
		  this.coded++;
		  if(this.coded == 8) {
			  this.coded = 0;
		  }
		  newp = new Pos(p.cx()+Dir.ddx[this.coded], p.cy()+Dir.ddy[this.coded]);
	  }
	  return new Pos(p.cx()+Dir.ddx[this.coded], p.cy()+Dir.ddy[this.coded]);
  }
  
     /*********************************************************************
    * rend une direction opposée ï¿½ la direction courante
    * @return la position voisine
    * ******************************************************************/
   public int dirOppose(){
    int dop = (coded+4)%8;
    return dop;
  }

   /***************************************************************
     * donne la position voisine de la Position indiquée 
     * @param p la position
     * @return la position
     * ***************************************************************/
   public Pos posVoisine(Pos p){
   	//DONE
	   Dir voisine = this.dirVoisine();
	   return new Pos(p.cx()+Dir.ddx[voisine.coded], p.cy()+Dir.ddy[voisine.coded]);
   }
   
  /* ce qui suit peut sans doute ï¿½tre dï¿½portï¿½ dans
   * une boite ï¿½ outils
   */
  private  double ps(int a, int b, int c, int d){
    return a*c + b*d;
  }
  
  public  double norme(int a,int b){
    return Math.sqrt(a*a+b*b);
  }
  
  public  double cos(int a, int b, int c, int d){
    return ps(a,b,c,d)/(norme(a,b)*norme(c,d));
  }
    

       
}

