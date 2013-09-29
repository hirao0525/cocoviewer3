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
	// // �N���b�N���̓���̌���
	// this.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// CCGraphFrame frame = new CCGraphFrame();
	// frame.initialize(list);
	// }
	// });
	// }

	// �b���ShowGraphFrame�Ƃقڈꏏ�̃��\�b�h
	private void makeGraph() {
		// ���{�ꂪ�����������Ȃ��e�[�}
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		// �O���t�f�[�^��ݒ肷��
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.getErrors().size(); i++) {
			dataset.addValue(list.getErrors().get(i).getCorrectTime(), "�C������",
					Integer.toString(i + 1));
		}

		// �O���t�̐���
		JFreeChart chart = ChartFactory.createLineChart(list.getMessage(),
				"�C����", "�C������", dataset, PlotOrientation.VERTICAL, false,
				false, false);

		// �w�i�F�̃Z�b�g
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

		// ToolTip����肭�\���ł��Ȃ�
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