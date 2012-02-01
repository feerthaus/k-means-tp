/**
 *
 * @author : LEBON Eric
 * @version : 1.0
 * @date : 28 janv. 2012 
 */
package src;

import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;
import Jama.Matrix;



/**
 * Analyse en composante principale des points pour obtenir une proojection 
 *
 */
public class ACPProjection {
	protected List<InfinitePoint> ACPGravityCenters;
	protected List<LinkedList<InfinitePoint>> groups;
	private String name;
	private List<Double> CP1AxisRatios;
	private List<Double> CP2AxisRatios;
	double CP1Influence;
	double CP2Influence;
	
	public ACPProjection(Kmeans k) throws DataFormatException{
		this.name = k.getName();
		if (k.getDimension() == 2){
			groups = k.getGroups();
			ACPGravityCenters = k.getGravityCenters();
			//TODO
		}else{
			doACP(k);
		}
	}
	public List<InfinitePoint> getACPGravityCenters() {
		return ACPGravityCenters;
	}
	public List<LinkedList<InfinitePoint>> getGroups() {
		return groups;
	}
	public String getName() {
		return name;
	}
	public double getCP1Influence() {
		return CP1Influence;
	}
	public double getCP2Influence() {
		return CP2Influence;
	}
	public void doACP(Kmeans kmeans) throws DataFormatException{
		int lines = kmeans.getDimension();
		int col = kmeans.getData().size();
		List<InfinitePoint>variables = kmeans.getData(); 
		Matrix X = new Matrix(lines, col);
		for (int i = 0; i < col; i++){
			for (int j = 0; j < lines; j++){
				X.set(j, i, variables.get(i).getVariables().get(j));
			}
		}
		PCA pca = new PCA(X);
		Matrix components = pca.getVectors();
		Matrix values = pca.getValues();
		double sommeValues = 0;
		for (int i= 0; i < lines; i++)
			sommeValues += values.get(i,i);
		
		//Première composante principale
		CP1AxisRatios = MatrixColumntoList(components, lines-1);
		CP1Influence = values.get(lines -1, lines-1)/sommeValues;
		
		//Seconde composante principale
		CP2AxisRatios = MatrixColumntoList(components, lines-2);
		CP2Influence = values.get(lines -2, lines-2)/sommeValues;
		
		buildProjection(kmeans);
	}
	
	public double getAcpPrecision(){
		return CP1Influence+CP2Influence;
	}
	/**
	 * Construit la projection 2D à partir des composantes principales calculées précédemment.
	 * @param kmeans
	 * @throws DataFormatException 
	 */
	private void buildProjection(Kmeans kmeans) throws DataFormatException {
		groups = new LinkedList<LinkedList<InfinitePoint>>();
		ACPGravityCenters = new LinkedList<InfinitePoint>();
		
		List<LinkedList<InfinitePoint>> Kgroups = kmeans.getGroups();
		List<InfinitePoint> KGravityCenters = kmeans.getGravityCenters();
		
		for(int i = 0; i < Kgroups.size(); i++){
			groups.add(new LinkedList<InfinitePoint>());
			for (int j = 0; j < Kgroups.get(i).size(); j ++){
				groups.get(groups.size()-1).add(doProjection(Kgroups.get(i).get(j)));
			}
		}
		for (int j = 0; j < KGravityCenters.size(); j ++){
			ACPGravityCenters.add(doProjection(KGravityCenters.get(j)));
		}
		
		
	}
	
	public InfinitePoint doProjection(InfinitePoint p){
		return new InfinitePoint(getCP1Coordinate(p), getCP2Coordinate(p), p.getDistance());
	}
	/**
	 * 
	 * @return
	 */
	public double getCP1Coordinate(InfinitePoint p){
		double coordinate = 0;
		for (int i = 0; i < p.getDimension(); i++){
			coordinate += CP1AxisRatios.get(i) * p.getVariables().get(i);
		}
		return coordinate;
	}
	
	public double getCP2Coordinate(InfinitePoint p){
		double coordinate = 0;
		for (int i = 0; i < p.getDimension(); i++){
			coordinate += CP2AxisRatios.get(i) * p.getVariables().get(i);
		}
		return coordinate;
	}
	
	public static void main(String[] args) throws DataFormatException {
		List<InfinitePoint> vars = new LinkedList<InfinitePoint>();
		vars.add(new InfinitePoint(0,4,4));
		vars.add(new InfinitePoint(0,3,3));
		vars.add(new InfinitePoint(0,2,2));
		vars.add(new InfinitePoint(0,4,3));
		
		List<InfinitePoint> centers = new LinkedList<InfinitePoint>();
		centers.add(new InfinitePoint(0,0,0));
		centers.add(new InfinitePoint(0,4,4));
		
		Kmeans kmeans = new Kmeans("test", vars, centers);
		
		kmeans.reallocation();
		kmeans.recentering();
		kmeans.reallocation();
		kmeans.recentering();
		kmeans.reallocation();
		kmeans.recentering();
		kmeans.reallocation();
		ACPProjection proj = new ACPProjection(kmeans);
		
		
		proj.plotACP();
	}
	
	public void plotACP(){
		ScatterPlot plot = new ScatterPlot(this);
		plot.pack();
		plot.setVisible(true);
	}
	
	
	/**
	 * For debug purposes
	 * @param X
	 */
	public void printMatrix(Matrix X){
		String s = "[\n";
		int lines= X.getRowDimension();
		int col= X.getColumnDimension();
		for (int i = 0; i < lines; i++){
			s+="[";
			for (int j = 0; j < col; j++){
				s+= X.get(i,j)+ " , ";
			}
			s+="]\n";
		}
		s+= "]";
		
		System.out.println(s);
	}
	
	public List<Double> MatrixColumntoList(Matrix X, int column){
		List<Double> vector = new LinkedList<Double>();
		for(int i = 0; i < X.getRowDimension(); i++){
			vector.add(X.get(i,column));
		}
		
		return vector;
	}
}

