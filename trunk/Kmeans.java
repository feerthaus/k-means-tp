/**
 * Projet tp K-means, pour la matière ANREC
 * 
 * @author eric
 */

import java.util.LinkedList;


public class Kmeans {
	protected  LinkedList<InfinitePoint> data =new LinkedList<InfinitePoint>();
	protected  LinkedList<InfinitePoint> gravityCenters = new LinkedList<InfinitePoint>();
	LinkedList<LinkedList<InfinitePoint>>groups = null;

	/* -------------------------------Constructors------------------------*/
	/**
	 * Constructor
	 * @param data
	 * @param gravityCenters
	 */
	public Kmeans(LinkedList<InfinitePoint> data, LinkedList<InfinitePoint> gravityCenters){
		if (data != null)
			this.data = data;

		if (gravityCenters != null)
			this.gravityCenters = gravityCenters;

	}

	/* -------------------------------Main methods------------------------*/
	/**
	 * reallocation step for k-means method
	 */
	public void reallocation(){

		groups = new LinkedList<LinkedList<InfinitePoint>>();
		for (InfinitePoint g: gravityCenters ){
			groups.add(new LinkedList<InfinitePoint>());
		}
		for (InfinitePoint p : data){
			int closest_gravityCenterId = -1;
			double min_distance = Double.MAX_VALUE;
			for (int i = 0; i < gravityCenters.size(); i++){
				InfinitePoint g = gravityCenters.get(i);
				double current_distance = g.distanceEuclidienne(p);
				if (current_distance< min_distance){
					min_distance = current_distance;
					closest_gravityCenterId = i;
				}

				groups.get(i).add(p);
			}
		}
	}

	/**
	 * recentering step for k-means method
	 */
	public void recentering(){
		if (gravityCenters.size() != groups.size()){
			System.out.println("Please do reallocation first");
			return;
		}
		for (int i = 0; i < gravityCenters.size(); i++){
			gravityCenters.set(i, getGravityCenter(groups.get(i)));
		}
	}

	/* -------------------------------Tools------------------------*/

	/**
	 * calcule le centre de gravité d'un ensemble de points
	 * @param points
	 * @return
	 */
	private InfinitePoint getGravityCenter(LinkedList<InfinitePoint> points){
		InfinitePoint gravityCenter = new InfinitePoint(points.getFirst().getDimension());
		for (InfinitePoint p : points){
			gravityCenter.add(p);
		}

		gravityCenter.scale(1./points.size());
		return gravityCenter; 
	}

}
