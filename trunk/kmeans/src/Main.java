/**
 *
 * @author : LEBON Eric
 * @version : 1.0
 * @date : 1 févr. 2012 
 */
package src;

/**
 * Main
 *Contains the main function
 */
public class Main {
	public static void main(String[] args) {
		
		int value = IOTools.askDistanceType();
		Kmeans kmeans = IOTools.readFile(value);
		int number = IOTools.ask
		
		kmeans.compute(number);
		ACPProjection acp = new ACPProjection(kmeans);
		acp.plotACP();
	}
}
