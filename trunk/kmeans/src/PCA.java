/**
 *
 * @author : LEBON Eric
 * @version : 1.0
 * @date : 29 janv. 2012 
 */
package src;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
/**
 * <p>Description : Principal Component Analysis</p>
 * <p>Copyright : GPL</p>
 * @author Yann RICHET
 */

public class PCA {

	private Matrix covariance;
	private Matrix EigenVectors;
	private Matrix EigenValues;

	public PCA(Matrix X) {
		covariance = getCovariance(X);
		EigenvalueDecomposition e = covariance.eig();
		EigenVectors = e.getV();
		EigenValues = e.getD();
	}

	public Matrix getVectors() {
		return EigenVectors;
	}

	public Matrix getValues() {
		return EigenValues;
	}
	public Matrix getCovariance(Matrix X){
		int rowsNumber = X.getRowDimension();
		Matrix covariance = new Matrix(rowsNumber,rowsNumber);
		double[] means = getlineMeans(X);
		double[][] prodMeans = getProdMeans(X);
		for (int i = 0; i < rowsNumber; i++){
		
			for (int j = 0; j < rowsNumber; j++){
				covariance.set(i, j, prodMeans[i][j] - means[i]*means[j]);
			}
		}
		return covariance;
	}
	
	/**
	 * @param x
	 * @return
	 */
	private double[][] getProdMeans(Matrix x) {
		int lines = x.getRowDimension();
		int columns = x.getColumnDimension();
		double[][] prodMeans = new double[lines][lines];
		for (int i = 0; i < lines; i ++)
			for (int j = 0; j < lines; j++){
				prodMeans[i][j] = 0;
				for (int k = 0; k < columns; k++){
					prodMeans[i][j] += x.get(i,k)*x.get(j,k);	
			}
				prodMeans[i][j] = prodMeans[i][j]/columns;
		}
		
		return prodMeans;
	}

	public double[] getlineMeans(Matrix X){
		double []means = new double[X.getRowDimension()];
		for (int i = 0; i < X.getRowDimension(); i++){
			means[i]= 0;
			for (int j = 0; j < X.getColumnDimension(); j++){
				means[i] += X.get(i, j);
			}
			means[i] = means[i]/X.getColumnDimension();
			}
	return means;
	}
}
