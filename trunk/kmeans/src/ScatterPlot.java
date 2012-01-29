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
import org.jfree.ui.RefineryUtilities;

public class ScatterPlot extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3;

	public ScatterPlot(ACPProjection acp) {

		super("Analyse en Composantes Principales des résultats de "+acp.getName());

		XYSeriesCollection dataset = new XYSeriesCollection();
		List<LinkedList<InfinitePoint>> groups = acp.getGroups(); 
		for(int i = 0; i < groups.size(); i++){
			XYSeries series1 = new XYSeries("Groupe "+i);
			for (int j = 0; j < groups.get(i).size(); j++){
				InfinitePoint p = groups.get(i).get(j);
				series1.add(p.getVariables().get(0), p.getVariables().get(1));
			}
			dataset.addSeries(series1);
		}


		JFreeChart chart = ChartFactory.createScatterPlot(
				"Scatter Plot", // title
				"X", "Y", // axis labels
				dataset, // dataset
				PlotOrientation.VERTICAL,
				true, // legend? yes
				true, // tooltips? yes
				false // URLs? no
				);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}


}
