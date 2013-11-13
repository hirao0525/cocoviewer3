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
		setTitle(CCMainFrame2.APP_NAME + " " + CCMainFrame2.VERSION + " ���щ��");
	}

	public void openFrame() {
		tableSetting();
		add(jpanel);
	}

	private void tableSetting() {
		// TODO: ��Ńf�[�^�����������񂾃N���X�����H
		// TODO: �̍��𔻒肷��v���O����������
		CCAchivementData data = new CCAchivementData();
		JTable table = new JTable(data.getTabelData(), data.getColumnNames());
		table.setEnabled(false);
		JScrollPane scrollpanel = new JScrollPane(table);
		scrollpanel.setPreferredSize(new Dimension(660, 140));

		jpanel.add(scrollpanel);
		jpanel.setBounds(10, 10, 680, 150);
	}

	class CCAchivementData {
		private String[][] tabledata = { { "�R���p�C���G���[���S��", "���߂ăR���p�C���G���[���o������" },
				{ "�R���p�C���G���[������", "�R���p�C���G���[��30�o������" },
				{ "�R���p�C���G���[�㋉��", "�R���p�C���G���[��60�o������" },
				{ "�R���p�C���G���[�}�X�^�[", "�R���p�C���G���[��100�o������" }, };

		private String[] columnNames = { "�̍�", "�̍����e" };

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
