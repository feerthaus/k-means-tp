package tests;



import static org.junit.Assert.*;
import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.*;

import src.InfinitePoint; 





/**
 * testInfinitePoint
 *
 */
public class testInfinitePoint extends TestCase {
	private InfinitePoint p;
	
	@Before
	public void setUp(){
		LinkedList<Double> variables = new LinkedList<Double>();
		variables.add(1.);
		variables.add(2.);
		variables.add(3.);
		variables.add(4.);
		variables.add(5.);
		p = new InfinitePoint(variables);
	}
	
	
	@Test
	public void testConstructorSize() {
		int size = 20;
		p = new InfinitePoint(size);
		boolean ok = true;
		for (int i = 0; i < size; i ++){
			ok &= p.getVariables().get(i)== 0;
		}
		assertTrue(ok);
	}

	@Test
	public void testConstructorVariables() {
		int size = 20;
		List<Double> list = new LinkedList<Double>();
		for (int i = 0; i < size; i++){
			list.add(i*1.0);
		}
		
		p= new InfinitePoint(list);
		boolean ok = true;
		for (int i = 0; i < size; i ++){
			ok &= p.getVariables().get(i)== i;
		}
		assertTrue(ok);
	}
	
	@Test
	public void testConstructorDD() {
		p = new InfinitePoint(3,4);
		boolean ok = true;
		ok &= p.getDimension() ==2;
		ok &= p.getVariables().get(0) == 3;
		ok &= p.getVariables().get(1) == 4;
		assertTrue(ok);
	}
	
	@Test
	public void testGetDimension() {
		p = new InfinitePoint(20);
		assertTrue(p.getDimension() == 20 );
	}
 
	@Test
	public void testAdd(){
		InfinitePoint wrongPoint = new InfinitePoint(4);
		boolean exceptionThrown= false;
		try {
			p.add(wrongPoint);
		} catch (DataFormatException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	
	}
	
	@Test
	public void testAddValues() throws DataFormatException{
		
		List<Double> variables = new LinkedList<Double>();
		for(double d : p.getVariables())
			variables.add(d);
		
		InfinitePoint plocal = new InfinitePoint(variables);
		
		
		
		boolean equals = (p.add(plocal) == 0);
		for (int i = 0; i < variables.size(); i++){
			equals = equals && (2 * variables.get(i) == p.getVariables().get(i)); 
		}
		
		assertTrue(equals);
	}
	
	
	@Test
	public void testscaleSize(){
		
		LinkedList<Double> variables = new LinkedList<Double>();
		double scale = 2;
		for (double d : p.getVariables())
			variables.add(d);
		
		p.scale(scale);
		boolean equals = (p.getVariables().size() == variables.size());
		assertTrue(equals);
	}
	
	@Test
	public void testscaleValues(){
		
		LinkedList<Double> variables = new LinkedList<Double>();
		double scale = 2;
		for (double d : p.getVariables())
			variables.add(d);
		
		p.scale(scale);
		boolean equals = (p.getVariables().size() == variables.size());
		for (int i = 0; i < p.getVariables().size(); i++)
			equals = equals && (Math.abs(p.getVariables().get(i)-variables.get(i)) < InfinitePoint.getEpsilon());
	}
	
	@Test
	public void testDistanceEuclidienne(){
		InfinitePoint wrongPoint = new InfinitePoint(4);
		boolean exceptionThrown= false;
		try {
			p.distanceEuclidienne(wrongPoint);
		} catch (DataFormatException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	
	}
	
	@Test
	public void testDistanceEuclidienne2() throws DataFormatException{
		List<Double> variables = new LinkedList<Double>();
		for(double d : p.getVariables())
			variables.add(d);
		InfinitePoint Point = new InfinitePoint(variables);
				
		assertTrue(p.distanceEuclidienne(Point)< InfinitePoint.getEpsilon());
	
	}
	
	@Test
	public void testDistanceEuclidienne3() throws DataFormatException{
		List<Double> variables = new LinkedList<Double>();
		for(double d : p.getVariables())
			variables.add(d+1);
		
				InfinitePoint Point = new InfinitePoint(variables);
				
		assertTrue(p.distanceEuclidienne(Point) - Math.sqrt(Point.getDimension())< InfinitePoint.getEpsilon());
	
	}
	
	@Test
	public void testString(){
		assertTrue(p.toString().contentEquals("[1.0, 2.0, 3.0, 4.0, 5.0]"));
		
	}
	public void testString2(){
		p= new InfinitePoint(0);
		assertTrue(p.toString().contentEquals("[]"));
		
	}
	 
	
}

