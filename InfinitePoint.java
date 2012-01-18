import java.util.LinkedList;


public class InfinitePoint {
	LinkedList<Double> variables = new LinkedList<Double>();

	public InfinitePoint(int size){
		for (int i = 0; i < size; i++)
			variables.add(0.);
	}
	
	public InfinitePoint(LinkedList<Double>variables){
		this.variables = new LinkedList<Double>(variables);
	}

	/**
	 * Distance euclidienne
	 */
	public double  distanceEuclidienne(InfinitePoint p){
		
		if (variables.size() != p.getVariables().size() )
			return -1;

		double distance =0;
		for(int i = 0; i < variables.size(); i++ ){
			distance += (variables.get(i)- p.getVariables().get(i))
					*(variables.get(i)- p.getVariables().get(i));
		}
		distance = Math.sqrt(distance);
		return distance;
	}

	public LinkedList<Double> getVariables() {
		return variables;
	}
	
	/**
	 * Ajoute un autre point de même taille au point.
	 * @param p : point à ajouter
	 */
	public void add(InfinitePoint p){
		if (variables.size() != p.getVariables().size() ){
			System.out.println("Attention! Points de tailles différentes!!");
			return;
		}
		for (int i = 0; i < variables.size(); i++){
			variables.set(i, variables.get(i) + p.getVariables().get(i));
		}
		
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
	
	public int getDimension(){
		return  variables.size();
	}
	
}
