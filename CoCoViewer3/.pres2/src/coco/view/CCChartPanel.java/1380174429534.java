package coco.view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import coco.model.CCCompileErrorList;

public class CCChartPanel extends ChartPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CCCompileErrorList list;

	public CCChartPanel(JFreeChart chart, CCCompileErrorList list) {
		this.list = list;
		initialize(chart);
	}

}
