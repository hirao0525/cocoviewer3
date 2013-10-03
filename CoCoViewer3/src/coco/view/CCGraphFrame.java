package coco.view;

import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import coco.model.CCCompileErrorList;

public class CCGraphFrame extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String APP_NAME = "CoCo Viewer";
	public static final String VERSION = "0.0.3";

	private int width = 680;
	private int height = 560;

	private JPanel rootPanel = new JPanel();
	private CCCompileErrorList list;

	// default
	public CCGraphFrame() {

	}

	public void initialize(CCCompileErrorList list) {
		this.list = list;
		// rootPanel のレイアウトをリセットする
		rootPanel.setLayout(null);

		setTitleData();

		makeGraph();

		setVisible(true);
	}

	private void setTitleData() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(APP_NAME + " " + VERSION + " - " + list.getMessage() + " の詳細");
	}

	private void makeGraph() {
		// 日本語が文字化けしないテーマ
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		// グラフデータを設定する
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.getErrors().size(); i++) {
			dataset.addValue(list.getErrors().get(i).getCorrectTime(), "修正時間",
					Integer.toString(i + 1));
			System.out.println(dataset.getValue(0, i));
		}

		// グラフの生成
		JFreeChart chart = ChartFactory.createLineChart(list.getMessage()
				+ "の修正時間", "修正回数", "修正時間", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		// 背景色のセット
		chart.setBackgroundPaint(ChartColor.WHITE);

		// TODO: CategoryPlotを継承してクリック可能にして使える情報を増やすこと
		CategoryPlot plot = chart.getCategoryPlot();

		// 縦軸の設定 ・ 軸は整数値のみを指すようにする
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberAxis.setVerticalTickLabels(false);
		numberAxis.setAutoRangeStickyZero(true);
		numberAxis.setRange(0, 120);

		// プロットの設定
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesPaint(0, ChartColor.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2));
		renderer.setSeriesShapesVisible(0, true);

		// グラフをJPanel上に配置する
		ChartPanel chartpanel = new ChartPanel(chart);
		chartpanel.setBounds(0, 0, width - 15, height - 50);
		chartpanel.addMouseListener(this);

		JToolTip tooltip = new JToolTip();
		chartpanel.setToolTipText(list.getErrors().size() + " : "
				+ list.getMessage());
		tooltip.setComponent(chartpanel);
		chartpanel.setDisplayToolTips(true);

		chartpanel.setDisplayToolTips(true);
		rootPanel.add(chartpanel);
		add(rootPanel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Can click!");
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}