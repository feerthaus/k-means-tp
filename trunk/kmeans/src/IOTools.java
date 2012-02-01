package src;

import javax.swing.JFileChooser;

import java.awt.Button;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;


final public class IOTools {
	private IOTools(){};

	//	public static void main(String args[]) throws IOException{
	//		 
	//		readFile();
	//		 	 
	//		 
	//	 }

	static Kmeans readFile(int distance) throws IOException, DataFormatException{
		JFileChooser  win = new JFileChooser();
		Component parent = new Button();
		win.showDialog(parent, "Ouvrir");

		File file = win.getSelectedFile();
		//		 
		//		 String path = file.getAbsolutePath();
		//		 
		//		 System.out.println(path);
		
		String name = file.getName();

		List<InfinitePoint> data = new LinkedList<InfinitePoint>();
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = null;
		//			  int height = 0;
		//			  int width = 0;
		while ((line=reader.readLine()) != null) {
			lineReader(line, data, distance);
		}

		//		        System.out.println(height);
		//		        System.out.println(width);
		reader.close();
		
		List<InfinitePoint> gravityCenters = new LinkedList<InfinitePoint>();
		
		Kmeans km = new Kmeans(name, data, gravityCenters);

		return km;
	}

	public static void lineReader(String line, List<InfinitePoint> data, int Distance){
		//		 height++;
		String[] splitted = line.split("	");
		//     	width = splitted.length;
		List<Double>myCoordinates = new LinkedList<Double>();
		for (int i = 0; i < splitted.length; i++) {
			//System.out.println(splitted[i]);
			double myDouble = Double.parseDouble(splitted[i]);//doubletostring
			myCoordinates.add(myDouble);
		} 

		InfinitePoint myPoint = new InfinitePoint(myCoordinates, Distance);
		data.add(myPoint);
		//System.out.println(splitted[0]);
	}
	
	public static int askGravityCenter(Kmeans km) throws java.util.InputMismatchException{
		Scanner sc = new Scanner(System.in);
		System.out.println("entrez le nombre de centres de gravite");
		int i = sc.nextInt();
		int imax = km.getData().size();
		while (i>imax){
			System.out.println("nombre de centres trop grand - entrez a nouveau le nombre de centres de gravite");
			i = sc.nextInt();
		}
		return i;
	}

}


