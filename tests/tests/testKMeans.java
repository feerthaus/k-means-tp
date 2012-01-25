/**
 *
 * @author : LEBON Eric
 * @version : 1.0
 * @date : 24 janv. 2012 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.Before;
import org.junit.Test;

import src.InfinitePoint;
import src.Kmeans;
import src.InfinitePoint;

/**
 * testKMeans
 *
 */
public class testKMeans {
	Kmeans kmeans;
	InfinitePoint p;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List<InfinitePoint> list = new LinkedList<InfinitePoint>();
		InfinitePoint p1 = new InfinitePoint(0,0);
		InfinitePoint p2 = new InfinitePoint(1,0);
		InfinitePoint p3 = new InfinitePoint(1,1);
		InfinitePoint p4 = new InfinitePoint(0,1);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		
		InfinitePoint g1 = new InfinitePoint(0.5, 0.5);
		List<InfinitePoint> gravityCenters = new LinkedList<InfinitePoint>();
		gravityCenters.add(g1);
		kmeans =  new Kmeans(list, new LinkedList<InfinitePoint>());
	}

	@Test
	public void testConstructor() throws DataFormatException {
		boolean ok = true;
		
		List<InfinitePoint> points = kmeans.getData();
		List<InfinitePoint> gravityCenters = kmeans.getGravityCenters();
		
		ok = kmeans.getDimension() = 2;
		ok = ok && points.size() == 4;
		ok = ok && points.get(0).distanceEuclidienne(
				new InfinitePoint(0.,0.)) 
				< InfinitePoint.getEpsilon() ;
		ok = ok && points.get(1).distanceEuclidienne(
				new InfinitePoint(1.,0.)) 
				< InfinitePoint.getEpsilon() ;
		ok = ok && points.get(2).distanceEuclidienne(
				new InfinitePoint(1.,1.)) 
				< InfinitePoint.getEpsilon() ;
		ok = ok && points.get(3).distanceEuclidienne(
				new InfinitePoint(0.,1.)) 
				< InfinitePoint.getEpsilon() ;
		
		ok = ok && gravityCenters.size() == 1;
		ok = ok && gravityCenters.get(0).distanceEuclidienne(
				new InfinitePoint(0.5,0.5)) 
				< InfinitePoint.getEpsilon() ;
		
		assertTrue(ok);	
	}
	

}
