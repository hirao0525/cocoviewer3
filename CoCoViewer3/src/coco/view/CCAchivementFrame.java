package coco.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import coco.model.CCCompileErrorManager;

public class CCAchivementFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int width = 720;
	private int height = 480;

	private CCCompileErrorManager manager;
	private JPanel jpanel = new JPanel();

	public CCAchivementFrame(CCCompileErrorManager manager) {
		super();
		this.manager = manager;
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(CCMainFrame2.APP_NAME + " " + CCMainFrame2.VERSION + " 実績画面");
	}

	public void openFrame() {
		tableSetting();
		add(jpanel);
	}

	private void tableSetting() {
		// TODO: 後でデータだけ書き込んだクラスを作る？
		// TODO: 称号を判定するプログラムを書く
		CCAchivementData data = new CCAchivementData();
		JTable table = new JTable(data.getTabelData(), data.getColumnNames());
		table.setEnabled(false);
		JScrollPane scrollpanel = new JScrollPane(table);
		scrollpanel.setPreferredSize(new Dimension(660, 140));

		jpanel.add(scrollpanel);
		jpanel.setBounds(10, 10, 680, 150);
	}

	class CCAchivementData {
		private String[][] tabledata = { { "コンパイルエラー初心者", "初めてコンパイルエラーを経験した" },
				{ "コンパイルエラー中級者", "コンパイルエラーを30個経験した" },
				{ "コンパイルエラー上級者", "コンパイルエラーを60個経験した" },
				{ "コンパイルエラーマスター", "コンパイルエラーを100個経験した" }, };

		private String[] columnNames = { "称号", "称号内容" };

		public CCAchivementData() {

		}

		public String[][] getTabelData() {
			return tabledata;
		}

		public String[] getColumnNames() {
			return columnNames;
		}
	}
}
