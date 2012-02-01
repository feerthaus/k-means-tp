/**
 *
 * @author : LEBON Eric
 * @version : 1.0
 * @date : 28 janv. 2012 
 */
package src;

/**
 * ScatterPlot
 *
 */
import java.util.LinkedList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;


public class ScatterPlot extends ApplicationFrame {

	final private int width = 600;
	final private int height = 600;
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3;

	
	/**
	 * build and prompt a plot with the ACP points and gravity centers.
	 * @param acp
	 */
	public ScatterPlot(ACPProjection acp) {

		super("Analyse en Composantes Principales des résultats de "+acp.getName());

		XYSeriesCollection dataset = new XYSeriesCollection();
		List<LinkedList<InfinitePoint>> groups = acp.getGroups(); 
		List<InfinitePoint> gravitycenters = acp.getACPGravityCenters();
		
		XYSeries series1 = new XYSeries("GravityCenters ");
		for (int j = 0; j < gravitycenters.size(); j++){
			InfinitePoint p = gravitycenters.get(j);
			series1.add(p.getVariables().get(0), p.getVariables().get(1));
		}

		dataset.addSeries(series1);
		
		
		for(int i = 0; i < groups.size(); i++){
			series1 = new XYSeries("Groupe "+i);
			for (int j = 0; j < groups.get(i).size(); j++){
				InfinitePoint p = groups.get(i).get(j);
				series1.add(p.getVariables().get(0), p.getVariables().get(1));
			}
			dataset.addSeries(series1);
		}
		
		
		
		dataset.setIntervalPositionFactor(0.01);
		
		
		
		JFreeChart chart = ChartFactory.createScatterPlot(
				"Analyse en Composante principale de "+acp.getName()+" ("+Math.round(acp.getAcpPrecision()*1000)/10.+"%)", // title
				"Composante principale 1 ("+Math.round(acp.getCP1Influence()*100)+"%)",
				"Composante Principale 2("+Math.round(acp.getCP2Influence()*100)+"%)", // axis labels
				dataset, // dataset
				PlotOrientation.VERTICAL,
				true, // legend? yes
				true, // tooltips? yes
				false // URLs? no
				);
		
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(width, height));
		setContentPane(chartPanel);

	}


}
