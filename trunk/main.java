import javax.swing.JApplet;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class main {
	 static public void main(String args[]){
		 
		 JFileChooser  win = new JFileChooser();
		 
		 Component parent = new Button();
		 
		 win.showDialog(parent, "bwak");
		 
		 File platypus = win.getSelectedFile();
		 
		 String bwak = platypus.getAbsolutePath();
		 
		 System.out.println(bwak);
		 
		 LinkedList<InfinitePoint> data = new LinkedList<InfinitePoint>();
		 
		 try {
			BufferedReader reader = new BufferedReader(new FileReader(platypus));
			
			  String line = null;
			  int height = 0;
			  int width = 0;
		        while ((line=reader.readLine()) != null) {
		        	height++;
		        	String[] splitted = line.split("	");
		        	width = splitted.length;
		        	LinkedList<Double >myCoordinates = new LinkedList();
		        	for (int i = 0; i < splitted.length; i++) {
						//System.out.println(splitted[i]);
		        		myDouble = //doubletostring
		        		myCoordinates.add(myDouble);
					} 
		        	
		        	InfinitePoint myPoint = new InfinitePoint(myCoordinates);
		        	//System.out.println(splitted[0]);
		        }
		       
		        System.out.println(height);
		        System.out.println(width);
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 	 
		 
	 }
}
