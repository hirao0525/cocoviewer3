package coco.view;

import java.awt.BasicStroke;

import javax.swing.JButton;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import coco.model.CCCompileErrorList;

public class CCErrorElementButton extends JButton implements ChartMouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int ERRORBUTTONWIDTH = 100;
	private static final int ERRORBUTTONHEIGHT = 100;

	private CCCompileErrorList list;

	public CCErrorElementButton(CCCompileErrorList list) {
		// super(list.getErrors().size() + " : " + list.getMessage());
		// super.setBackground(Color.YELLOW);
		this.list = list;
		super.setLayout(null);
		// initialize(list);
		makeGraph();
	}

	// private void initialize(final CCCompileErrorList list) {
	// // クリック次の動作の決定
	// this.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// CCGraphFrame frame = new CCGraphFrame();
	// frame.initialize(list);
	// }
	// });
	// }

	// 暫定でShowGraphFrameとほぼ一緒のメソッド
	private void makeGraph() {
		// 日本語が文字化けしないテーマ
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		// グラフデータを設定する
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.getErrors().size(); i++) {
			dataset.addValue(list.getErrors().get(i).getCorrectTime(), "修正時間",
					Integer.toString(i + 1));
		}

		// グラフの生成
		JFreeChart chart = ChartFactory.createLineChart(list.getMessage(),
				"修正回数", "修正時間", dataset, PlotOrientation.VERTICAL, false,
				false, false);

		// 背景色のセット
		chart.setBackgroundPaint(ChartColor.WHITE);

		CategoryPlot plot = chart.getCategoryPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesPaint(0, ChartColor.RED);
		renderer.setSeriesStroke(0, new BasicStroke(1));
		renderer.setSeriesShapesVisible(0, true);

		ChartPanel chartpanel = new ChartPanel(chart);
		chartpanel.addChartMouseListener(this);
		chartpanel.setBounds(0, 0, ERRORBUTTONWIDTH, ERRORBUTTONHEIGHT);

		// ToolTipが上手く表示できない
		chartpanel.setToolTipText("gomiggomi");
		chartpanel.setDisplayToolTips(true);
		System.out.println(chartpanel.getToolTipText()); // debug

		add(chartpanel);
	}

	public void chartMouseClicked(ChartMouseEvent e) {
		CCGraphFrame frame = new CCGraphFrame();
		frame.initialize(list);
	}

	public void chartMouseMoved(ChartMouseEvent e) {

	}

}