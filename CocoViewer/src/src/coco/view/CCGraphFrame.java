package src.coco.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolTip;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import src.coco.model.CCCompileError;
import src.coco.model.CCCompileErrorList;
import clib.common.filesystem.CDirectory;
import clib.common.filesystem.CFile;
import clib.common.filesystem.CFileElement;
import clib.common.filesystem.CPath;

public class CCGraphFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int width = 680;
	private int height = 560;

	private JPanel rootPanel = new JPanel();
	private CCCompileErrorList list;

	private CDirectory baseDir;

	// default
	public CCGraphFrame(CCCompileErrorList list, CDirectory baseDir) {
		this.list = list;
		this.baseDir = baseDir;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) (d.width * 0.75);
		height = (int) (d.height * 0.75);
		initialize();
	}

	public void openGraph() {
		makeGraph();
		makeSourceList();
		add(rootPanel);
		getContentPane().add(rootPanel, BorderLayout.CENTER);
		pack();
	}

	private void initialize() {
		// rootPanel.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(CCMainFrame2.APP_NAME + " " + CCMainFrame2.VERSION + " - "
				+ list.getMessage() + " �̏ڍ�");
	}

	private void makeGraph() {
		// ���{�ꂪ�����������Ȃ��e�[�}
		// ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		// �O���t�f�[�^��ݒ肷��
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.getErrors().size(); i++) {
			dataset.addValue(list.getErrors().get(i).getCorrectTime(), "�C������",
					Integer.toString(i + 1));
		}

		// �O���t�̐���
		JFreeChart chart = ChartFactory.createLineChart(list.getMessage()
				+ "�̏C������   ���A�x: " + list.getRare(), "�C����", "�C������", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		// �t�H���g�w�肵�Ȃ��ƕ�����������
		chart.getTitle().setFont(new Font("Font2DHandle", Font.PLAIN, 20));
		chart.getLegend().setItemFont(new Font("Font2DHandle", Font.PLAIN, 16));

		// �w�i�F�̃Z�b�g
		chart.setBackgroundPaint(new CCGraphBackgroundColor().graphColor(list
				.getRare()));

		// TODO: CategoryPlot���p�����ăN���b�N�\�ɂ��Ďg������𑝂₷����
		CategoryPlot plot = chart.getCategoryPlot();

		// y���̐ݒ� �E ���͐����l�݂̂��w���悤�ɂ���
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberAxis.setVerticalTickLabels(false);
		numberAxis.setAutoRangeStickyZero(true);
		numberAxis.setRange(0, 120);
		numberAxis.setLabelFont(new Font("Font2DHandle", Font.PLAIN, 16));

		// x���̐ݒ�
		CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("Font2DHandle", Font.PLAIN, 16));

		// �v���b�g�̐ݒ�
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setSeriesPaint(0, ChartColor.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2));
		renderer.setSeriesShapesVisible(0, true);

		// �O���t��JPanel��ɔz�u����
		ChartPanel chartpanel = new ChartPanel(chart);
		// chartpanel.setBounds(0, 0, width - 15, height - 40);

		// TODO: TIPS�\������Ȃ�
		JToolTip tooltip = new JToolTip();
		chartpanel.setToolTipText(list.getErrors().size() + " : "
				+ list.getMessage());
		tooltip.setComponent(chartpanel);
		chartpanel.setDisplayToolTips(true);

		rootPanel.add(chartpanel, BorderLayout.WEST);
		// rootPanel.add(chartpanel);
	}

	// TODO ���X�g�����̎���
	private void makeSourceList() {
		// java7����DefaultListModel�Ɋi�[����N���X���w�肵�Ȃ���΂Ȃ�Ȃ�
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < list.getErrors().size(); i++) {
			model.addElement((i + 1) + " ��ڂ̏C������ �F "
					+ list.getErrors().get(i).getCorrectTime() + "�b");
		}

		final JList<String> jlist = new JList<String>(model);
		jlist.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// ���N���b�N���ŃI�[�v������
				if (e.getButton() == MouseEvent.BUTTON1
						&& e.getClickCount() >= 2) {
					// �I�����ꂽ�v�f�����X�g�̉��Ԗڂł���̂����擾���C���̎��̃R���p�C���G���[�����擾
					int index = jlist.getSelectedIndex();
					CCCompileError compileError = list.getErrors().get(index);

					// �t�@�C���p�X�ɕK�v�ȗv�f�̎��o��
					String projectname = compileError.getProjectname();
					String beginTime = String.valueOf(compileError
							.getBeginTime());
					String filename = compileError.getFilename();

					// �R���p�C���G���[�������̃t�@�C���p�X��ݒ�
					// TODO Eclipse�Ή��ł��ĂȂ�
					CPath path = new CPath("\\ppv.data\\cash\\hoge\\"
							+ projectname + "\\" + beginTime
							+ "\\ProjectBase\\" + filename);

					// �_�v������̋N����z��CCocoViewer�݂̂ł�baseDir��null
					if (baseDir == null) {
						System.out.println("baseDir null");
					} else {
						// �v���O�����\�[�X��{���C���ꂪnull�łȂ����Ɓ{�t�@�C���ł��邱�Ƃ��m�F
						CFileElement fileElement = baseDir.findChild(path);
						if (fileElement.isFile() && fileElement != null) {
							CFile file = (CFile) fileElement;
							System.out.println("find!  "
									+ list.getErrors().get(index)
											.getBeginTime());
							// �v���O�����t�@�C���̓��e��\������
							StringBuffer buf = new StringBuffer();
							String line = "";
							if ((line = file.loadText()) != null) {
								buf.append(line);
								buf.append("\n");
								System.out.println(line);
							}

							// TODO RESourceViewer���g���ĕ\���ł���悤�ɂ��܂��傤
							JTextPane textPane = new JTextPane();
							textPane.setText(buf.toString());
							textPane.setCaretPosition(0);
							JFrame frame = new JFrame();
							frame.add(textPane, BorderLayout.CENTER);
							frame.pack();
							frame.setVisible(true);
						}
					}
				}
			}
		});

		// TODO �X�N���[���p�l���̃T�C�Y����
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.getViewport().setView(jlist);

		rootPanel.add(scrollPanel, BorderLayout.EAST);
	}
}