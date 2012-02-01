package src;


/**
 * Projet tp K-means, pour la matière ANREC
 * 
 * Classe contenant le type de données (un point de dimension n inconnue à ce point)
 * 
 * @author eric
 */
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;


public class InfinitePoint {
	private List<Double> variables;
	static final double EPSILON = 1e-6;
	static public final int EUCLIDIAN = 2;
	static public final int L1 = 1;
	private int distance = EUCLIDIAN;
	
	/* -------------------------------Constructors------------------------*/
	
	

	@SuppressWarnings("unused")
	private InfinitePoint(){};
	
	/**
	 * Generic constructor : 
	 * @param size : dimension of the point. The coordinates are initialysed to O.0
	 */
	public InfinitePoint(int size,int distance){
		init(distance);
		 variables = new LinkedList<Double>();
		for (int i = 0; i < size; i++){
			variables.add(0.);
		}
	}
	
	/**
	 * Constructor 
	 * @param variables : list of coordinates
	 */
	public InfinitePoint(List<Double>variables , int distance){
		init(distance);
		this.variables = new LinkedList<Double>(variables);
	}
	
	/**
	 * Constructor : create the point (abs,ord)
	 * @param abs
	 * @param ord
	 */
	public InfinitePoint(double abs, double ord, int distance){
		init(distance);
		this.variables.add(abs);
		this.variables.add(ord);
	}
	
	/**
	 * Constructor : create the point (abs,ord,cote)
	 * @param abs
	 * @param ord
	 */
	public InfinitePoint(double abs, double ord, double cote, int distance){
		init(distance);
		this.variables.add(abs);
		this.variables.add(ord);
		this.variables.add(cote);
	}
  
	/**
	 * initialyze the attributes
	 */
	private void init(int distance){
		variables = new LinkedList<Double>();
		this.distance = distance; 
	}
	/*-----------------------------Accessors-----------------------------*/
	public List<Double> getVariables() {
		return variables;
	}
	
	public static double getEpsilon(){
		return EPSILON;
	}
	
	/* -------------------------------Methods----------------------------*/
	
	public double distance(InfinitePoint p) throws DataFormatException{
		if (distance == EUCLIDIAN){
			return distanceEuclidienne(p);
		}else{
			return distanceL1(p);
		}
	}
	
	
	/**
	 * Distance euclidienne
	 * @throws DataFormatException  
	 */
	public double  distanceEuclidienne(InfinitePoint p) throws DataFormatException{
		
		if (variables.size() != p.getVariables().size() ){
			throw new DataFormatException("Les points n'ont pas la même taille");
		}else{
		double distance =0;
		for(int i = 0; i < variables.size(); i++ ){
			distance += (variables.get(i) - p.getVariables().get(i))
					*(variables.get(i)- p.getVariables().get(i));
		}
		distance = Math.sqrt(distance);
		return distance;
		}
	}
	
	public double distanceL1(InfinitePoint p) throws DataFormatException{
		
			
			if (variables.size() != p.getVariables().size() ){
				throw new DataFormatException("Les points n'ont pas la même taille");
			}else{
			double distance =0;
			for(int i = 0; i < variables.size(); i++ ){
				distance += Math.abs(variables.get(i) - p.getVariables().get(i));
			}
			
			return distance;
			}
		
	}

	
	/**
	 * Ajoute un autre point de même taille au point.
	 * @param p : point à ajouter
	 * @throws DataFormatException 
	 */
	public int add(InfinitePoint p) throws DataFormatException{
		if (variables.size() != p.getVariables().size() ){
			throw new DataFormatException("Points de dimensions différentes!");
		}
		
		for (int i = 0; i < variables.size(); i++){
			variables.set(i, variables.get(i) + p.getVariables().get(i));
		}
		return 0;
		
	}
	
	
	/**
	 * Met à l'echelle le point
	 * @param d : facteur d'echelle
	 */
	public void scale(Double d){
		for (int i = 0; i < variables.size(); i++){
			variables.set(i, variables.get(i) *d);
		}
	}
	
	
	/**
	 * 
	 * @return la dimension (ie le nombre de variables)
	 */
	public int getDimension(){
		return  variables.size();
	}
	
	/**
	 * affichage texte pour des besoins de debug.
	 */
	public String toString(){
		String s = "[";
		for (Double d :variables){
			s+= d+", ";
		}
		if(s.length()>1){
			s=s.substring(0, s.length()-2);
		}
		s+=  "]";
				return s;
	}

	public boolean isEqualTo(InfinitePoint p) throws DataFormatException{
		return this.distanceEuclidienne(p)< EPSILON;
	}
	
	public InfinitePoint clone(){
		List<Double> coordinates = new LinkedList<Double>();
		for (double d : this.getVariables())
			coordinates.add(d);
			
		
		InfinitePoint p = new InfinitePoint(coordinates, this.distance);
		
		return p;
	}
	
	public int getDistance() {
		return distance;
	}
}
