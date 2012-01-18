import java.util.LinkedList;


public class InfinitePoint {
	LinkedList<Double> variables = new LinkedList<Double>();

	public InfinitePoint(LinkedList<Double>variables){
		this.variables = new LinkedList<Double>(variables);
	}

	/**
	 * Distance euclidienne
	 */
	public double  distanceEuclidienne(InfinitePoint p){
		
		if (variables == null
				|| p.getVariables() == null
				|| variables.size() != p.getVariables().size() )
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
	
	
}
