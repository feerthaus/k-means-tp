/**
 *
 * @author : LEBON Eric
 * @version : 1.0
 * @date : 1 févr. 2012 
 */
package src;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Main
 *Contains the main function
 */
public class Main {
	public static void main(String[] args) throws IOException, DataFormatException {
		
		int value = IOTools.askDistance();
		Kmeans kmeans = IOTools.readFile(value);
		int number = IOTools.askGravityCenter(kmeans);
		
		kmeans.compute(number);
		ACPProjection acp = new ACPProjection(kmeans);
		acp.plotACP();
		
		
	}
}
