package src;


/**
 * Projet tp K-means, pour la matière ANREC
 * 
 * Classe contenant l'algorithme général
 * 
 * @author eric
 */

import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;



public class Kmeans {
	private List<InfinitePoint> data;
	private List<InfinitePoint> gravityCenters;
	public List<InfinitePoint> getGravityCenters() {
		return gravityCenters;
	}

	private List<LinkedList<InfinitePoint>>groups;

	private int dimension;

	
	/* -------------------------------Constructors------------------------*/
	/**
	 * deactivated constructor
	 */
	@SuppressWarnings("unused")
	private Kmeans(){};

	/**
	 * Constructor
	 * @param data
	 * @param gravityCenters
	 * @throws DataFormatException 
	 */
	public Kmeans(List<InfinitePoint> data, List<InfinitePoint> gravityCenters) throws DataFormatException{
		
		init();

		for (int i = 0; i < data.size(); i++){
			InfinitePoint p = data.get(i);
			if(this.dimension == -1){
				dimension = p.getDimension();
			}else if (p.getDimension() != this.dimension){
				throw new DataFormatException("Les points ont des dimensions différentes!");
			}
			this.data.add(p);
			
		}
		
		for (int i = 0; i < gravityCenters.size(); i++){
			InfinitePoint p = gravityCenters.get(i);
			if(this.dimension == -1){
				dimension = p.getDimension();
			}else if (p.getDimension() != this.dimension){
				throw new DataFormatException("Les points ont des dimensions différentes!");
			}
			this.gravityCenters.add(p);
			
		}
		

	}

	private void init(){
		this.data =new LinkedList<InfinitePoint>();
		this.gravityCenters = new LinkedList<InfinitePoint>();
		this.groups = null;

		dimension = -1;
	}
	/* -----------------------------Accessors----------------------------*/
	public List<InfinitePoint> getData() {
		return data;
	}

	public List<LinkedList<InfinitePoint>> getGroups() {
		return groups;
	}
	
	public int getDimension() throws DataFormatException{
		if (dimension == -1){
			throw new DataFormatException("Erreur de dimension");
		}
		return dimension;
	}

	/* -------------------------------Main methods------------------------*/
	/**
	 * reallocation step for k-means method
	 * @throws DataFormatException 
	 */
	public void reallocation() throws DataFormatException{

		groups = new LinkedList<LinkedList<InfinitePoint>>();
		for (InfinitePoint g: gravityCenters ){
			groups.add(new LinkedList<InfinitePoint>());
		}
		for (InfinitePoint p : data){
			int closestGravityCenterId = -1;
			double minDistance = Double.MAX_VALUE;
			for (int i = 0; i < gravityCenters.size(); i++){
				InfinitePoint g = gravityCenters.get(i);
				double currentDistance = g.distanceEuclidienne(p);
				if (currentDistance< minDistance){
					minDistance = currentDistance;
					closestGravityCenterId = i;
				}


			}
			groups.get(closestGravityCenterId).add(p);
		}
	}

	/**
	 * recentering step for k-means method
	 * @throws DataFormatException 
	 */
	public void recentering() throws DataFormatException{
		if (gravityCenters.size() != groups.size()){
			throw new DataFormatException("Please do reallocation first!");

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
	 * @throws DataFormatException 
	 */
	private InfinitePoint getGravityCenter(List<InfinitePoint> points) throws DataFormatException{
		InfinitePoint gravityCenter = new InfinitePoint(points.get(0).getDimension());
		for (InfinitePoint p : points){
			gravityCenter.add(p);
		}

		gravityCenter.scale(1./points.size());
		return gravityCenter; 
	}
	


}
