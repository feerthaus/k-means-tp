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
		
		InfinitePoint g1 = new InfinitePoint(0.1, 0.5);
		InfinitePoint g2 = new InfinitePoint(0.9, 0.5);
		List<InfinitePoint> gravityCenters = new LinkedList<InfinitePoint>();
		gravityCenters.add(g1);
		gravityCenters.add(g2);
		kmeans =  new Kmeans(list, gravityCenters);
	}

	@Test
	public void testConstructor() throws DataFormatException {
		boolean ok = true;
		
		List<InfinitePoint> points = kmeans.getData();
		List<InfinitePoint> gravityCenters = kmeans.getGravityCenters();
		
		ok = kmeans.getDimension() == 2;
		ok = ok && points.size() == 4;
		ok = ok && points.get(0).isEqualTo(new InfinitePoint(0.,0.));
		ok = ok && points.get(1).isEqualTo(new InfinitePoint(1.,0.));
		ok = ok && points.get(2).isEqualTo(new InfinitePoint(1.,1.));
		ok = ok && points.get(3).isEqualTo(new InfinitePoint(0.,1.));
		
		ok = ok && gravityCenters.size() == 2;
		ok = ok && gravityCenters.get(0).isEqualTo(new InfinitePoint(0.1,0.5));
		ok = ok && gravityCenters.get(1).isEqualTo(new InfinitePoint(0.9,0.5));
		
		assertTrue(ok);	
	}
	
	@Test
	public void testReallocation() throws DataFormatException{
		kmeans.reallocation();
		List<LinkedList<InfinitePoint>> groups = kmeans.getGroups();
		boolean ok = groups.size() == 2;
		ok = ok && groups.get(0).size() == 2;
		ok = ok && groups.get(1).size() == 2;
		
		ok = ok && (groups.get(0).getFirst().isEqualTo(new InfinitePoint(0,0))
				||groups.get(0).getFirst().isEqualTo(new InfinitePoint(0,1)));
		ok = ok && (groups.get(0).getLast().isEqualTo(new InfinitePoint(0,0))
				||groups.get(0).getLast().isEqualTo(new InfinitePoint(0,1)));

		ok = ok && ((groups.get(1).getFirst().isEqualTo(new InfinitePoint(1,0))
						&&groups.get(1).getLast().isEqualTo(new InfinitePoint(1,1)))
						
				|| (groups.get(1).getFirst().isEqualTo(new InfinitePoint(1,1))
						&&groups.get(1).getLast().isEqualTo(new InfinitePoint(1,0))));
				
	}
	
	@Test
	public void testRecentering() throws DataFormatException{
		kmeans.reallocation();
		kmeans.recentering();
		
		List<InfinitePoint> centers = kmeans.getGravityCenters();
		
		boolean ok = centers.size()== 2;
				
		ok = ok && ((centers.get(0).isEqualTo(new InfinitePoint(0,0.5))
				&& centers.get(1).isEqualTo(new InfinitePoint(1,0.5)))
				
		||(centers.get(0).isEqualTo(new InfinitePoint(1,0.5))
				&& centers.get(1).isEqualTo(new InfinitePoint(0,0.5))));
		
		assertTrue(ok);
	}
	
	
}
