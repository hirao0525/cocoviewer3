package coco.view;

import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import coco.model.CCCompileErrorList;

public class CCErrorElementButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int ERRORBUTTONWIDTH = 100;
	private static final int ERRORBUTTONHEIGHT = 100;
	private JPanel buttonRoot;

	private CCCompileErrorList list;

	public CCErrorElementButton(CCCompileErrorList list) {
		// super(list.getErrors().size() + " : " + list.getMessage());
		// super.setBackground(Color.YELLOW);
		this.list = list;
		initialize(list);
		add(buttonRoot);
	}

	private void initialize(final CCCompileErrorList list) {
		// クリック次の動作の決定
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CCGraphFrame frame = new CCGraphFrame();
				frame.initialize(list);
			}
		});
	}

	// なんかイマイチなので自分で作成したグラフを使う予定・暫定でこっち
	private void makeGraph() {
		// 日本語が文字化けしないテーマ
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		// グラフデータを設定する
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.getErrors().size(); i++) {
			dataset.addValue(list.getErrors().get(i).getCorrectTime(), "修正時間",
					Integer.toString(i));
		}

		// グラフの生成
		JFreeChart chart = ChartFactory.createLineChart(list.getMessage()
				+ "の修正時間", "修正回数", "修正時間", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		// 背景色のセット
		chart.setBackgroundPaint(ChartColor.WHITE);

		CategoryPlot plot = chart.getCategoryPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesPaint(0, ChartColor.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2));
		renderer.setSeriesShapesVisible(0, true);

		ChartPanel cpanel = new ChartPanel(chart);
		cpanel.setBounds(50, 50, ERRORBUTTONWIDTH, ERRORBUTTONHEIGHT);
		buttonRoot.add(cpanel);
	}
}