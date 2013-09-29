package coco.view;

import java.awt.BasicStroke;

import javax.swing.JFrame;
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

public class CCGraphFrame extends JFrame {

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
		// makeGraph2(ceds);

		setVisible(true);
	}

	private void setTitleData() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(APP_NAME + " " + VERSION + " - " + list.getMessage() + " �̏ڍ�");
	}

	// �Ȃ񂩃C�}�C�`�Ȃ̂Ŏ����ō쐬�����O���t���g���\��E�b��ł�����
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
		JFreeChart chart = ChartFactory.createLineChart(list.getMessage()
				+ "�̏C������", "�C����", "�C������", dataset, PlotOrientation.VERTICAL,
				true, true, false);

		// �w�i�F�̃Z�b�g
		chart.setBackgroundPaint(ChartColor.WHITE);

		CategoryPlot plot = chart.getCategoryPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesPaint(0, ChartColor.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2));
		renderer.setSeriesShapesVisible(0, true);

		ChartPanel cpanel = new ChartPanel(chart);
		cpanel.setBounds(0, 0, width - 15, height - 50);
		cpanel.setDisplayToolTips(true);
		rootPanel.add(cpanel);
		add(rootPanel);
	}

	// �����Ő��삵���O���t
	// private void makeGraph2(CompileErrorDate ced) {
	// JPanel graphPanel = new JPanel();
	// JLabel graphTitle = new JLabel(ced.getMassage());
	// graphPanel.setBounds(width / 2, height / 2, width, height);
	// graphPanel.add(graphTitle);
	// graphTitle.setBounds(width / 2, 10, 600, 28);
	// add(graphPanel);
	//
	// }

	/************************
	 * for window size test
	 ***********************/
	// public static void main(String[] args) {
	// CCGraphFrame sg = new CCGraphFrame();
	// sg.run();
	// }
	//
	// private void run() {
	// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	// setSize(width, height);
	// setTitle(APP_NAME + " " + VERSION + " error ");
	// setVisible(true);
	// }
}