package src;



import javax.swing.JFileChooser;

import java.awt.Button;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


final public class IOTools {
	private IOTools(){};

	//	public static void main(String args[]) throws IOException{
	//		 
	//		readFile();
	//		 	 
	//		 
	//	 }

	static void readFile() throws IOException{
		JFileChooser  win = new JFileChooser();

		Component parent = new Button();

		win.showDialog(parent, "Ouvrir");

		File file = win.getSelectedFile();
		//		 
		//		 String path = file.getAbsolutePath();
		//		 
		//		 System.out.println(path);

		List<InfinitePoint> data = new LinkedList<InfinitePoint>();


		BufferedReader reader;

		reader = new BufferedReader(new FileReader(file));


		String line = null;
		//			  int height = 0;
		//			  int width = 0;
		while ((line=reader.readLine()) != null) {
			lineReader(line, data);
		}

		//		        System.out.println(height);
		//		        System.out.println(width);
		reader.close();



	}


	public static void lineReader(String line, List<InfinitePoint> data){
		//		 height++;
		String[] splitted = line.split("	");
		//     	width = splitted.length;
		List<Double>myCoordinates = new LinkedList<Double>();
		for (int i = 0; i < splitted.length; i++) {
			//System.out.println(splitted[i]);
			double myDouble = Double.parseDouble(splitted[i]);//doubletostring
			myCoordinates.add(myDouble);
		} 

		InfinitePoint myPoint = new InfinitePoint(myCoordinates);
		data.add(myPoint);
		//System.out.println(splitted[0]);
	}



	
}


