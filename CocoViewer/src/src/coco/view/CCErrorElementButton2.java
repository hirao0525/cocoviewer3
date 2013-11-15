package src.coco.view;

import java.awt.BasicStroke;
import java.awt.Font;

import javax.swing.JButton;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import src.coco.model.CCCompileErrorList;
import clib.common.filesystem.CDirectory;

public class CCErrorElementButton2 extends JButton implements
		ChartMouseListener {

	/**
	 * minigraph��\������ chartPanel��ActionListener�ɑΉ����Ă��Ȃ��̂ŁAMouseListener�Ŏ���
	 */

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;

	private CCCompileErrorList list;
	private CDirectory baseDir;

	public CCErrorElementButton2(CCCompileErrorList list, int width,
			int height, CDirectory baseDir) {
		this.list = list;
		this.width = width;
		this.height = height;
		this.baseDir = baseDir;
		super.setLayout(null);
		makeGraph();
	}

	private void makeGraph() {
		// ���{�ꂪ�����������Ȃ��e�[�}(�t�H���g�w��Ŕ������̂Ŏg��Ȃ�)
		// ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());

		// �O���t�f�[�^��ݒ肷��
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.getErrors().size(); i++) {
			dataset.addValue(list.getErrors().get(i).getCorrectTime(), "�C������",
					Integer.toString(i + 1));
		}

		// TODO: �O���t�̐��� message����������ꍇ�A�Z�����鏈�����ǂ����邩
		// 10�������炢�ŋ�؂��āAToolTip�ŕ⊮�����i���l����
		String message = list.getMessage();
		if (list.getMessage().length() > 10) {
			message = message.substring(0, 9) + "...";
		}

		JFreeChart chart = ChartFactory.createLineChart(message, "�C����",
				"�C������", dataset, PlotOrientation.VERTICAL, false, false, false);
		// �t�H���g�w�肵�Ȃ��ƕ�����������
		chart.getTitle().setFont(new Font("Font2DHandle", Font.PLAIN, 20));

		// �w�i�F�̃Z�b�g
		chart.setBackgroundPaint(new CCGraphBackgroundColor().graphColor(list
				.getRare()));

		// Plot�N���X������
		CategoryPlot plot = chart.getCategoryPlot();

		// �c���̐ݒ� �E ���͐����l�݂̂��w���悤�ɂ���
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberAxis.setVerticalTickLabels(false);
		numberAxis.setAutoRangeStickyZero(true);
		numberAxis.setRangeWithMargins(0, 60);
		numberAxis.setLabelFont(new Font("Font2DHandle", Font.PLAIN, 16));

		// x���̐ݒ�
		CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("Font2DHandle", Font.PLAIN, 16));

		// �v���b�g�̐ݒ�
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesPaint(0, ChartColor.RED);
		renderer.setSeriesStroke(0, new BasicStroke(1));
		renderer.setSeriesShapesVisible(0, true);

		ChartPanel chartpanel = new ChartPanel(chart);
		chartpanel.addChartMouseListener(this);
		chartpanel.setBounds(0, 0, width, height);

		// TODO: ToolTip����肭�\���ł��Ȃ�
		chartpanel.setToolTipText(list.getErrors().size() + " : "
				+ list.getMessage());
		chartpanel.setDisplayToolTips(true);
		// �f�o�b�O�p
		// System.out.println(chartpanel.getToolTipText());

		add(chartpanel);
	}

	@Override
	public void chartMouseClicked(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub
		CCGraphFrame frame = new CCGraphFrame(list, baseDir);
		frame.openGraph();
		frame.setVisible(true);
	}

	@Override
	public void chartMouseMoved(ChartMouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}