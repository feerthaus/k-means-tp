import java.util.LinkedList;


public class InfinitePoint {
	LinkedList<Double> variables = new LinkedList<Double>();

	public InfinitePoint(LinkedList<Double>variables){
		this.variables = new LinkedList<Double>(variables);
	}

	/**
	 * Distance euclidienne
	 */
	public double  distance(InfinitePoint p){
		if (variables == null || variables.size() < 1)
			return -1;

		double distance =0;
		for(Double d : variables){
			distance += d*d;
		}
		return distance;
	}
}
