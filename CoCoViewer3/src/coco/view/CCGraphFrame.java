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
		// rootPanel �̃��C�A�E�g�����Z�b�g����
		rootPanel.setLayout(null);

		setTitleData();

		makeGraph();

		setVisible(true);
	}

	private void setTitleData() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(APP_NAME + " " + VERSION + " - " + list.getMessage() + " �̏ڍ�");
	}

	private void makeGraph() {
		// ���{�ꂪ�����������Ȃ��e�[�}
		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		// �O���t�f�[�^��ݒ肷��
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.getErrors().size(); i++) {
			dataset.addValue(list.getErrors().get(i).getCorrectTime(), "�C������",
					Integer.toString(i + 1));
			System.out.println(dataset.getValue(0, i));
		}

		// �O���t�̐���
		JFreeChart chart = ChartFactory.createLineChart(list.getMessage()
				+ "�̏C������", "�C����", "�C������", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		// �w�i�F�̃Z�b�g
		chart.setBackgroundPaint(ChartColor.WHITE);

		// TODO: CategoryPlot���p�����ăN���b�N�\�ɂ��Ďg������𑝂₷����
		CategoryPlot plot = chart.getCategoryPlot();

		// �c���̐ݒ� �E ���͐����l�݂̂��w���悤�ɂ���
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberAxis.setVerticalTickLabels(false);
		numberAxis.setAutoRangeStickyZero(true);
		numberAxis.setRange(0, 120);

		// �v���b�g�̐ݒ�
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesPaint(0, ChartColor.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2));
		renderer.setSeriesShapesVisible(0, true);

		// �O���t��JPanel��ɔz�u����
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