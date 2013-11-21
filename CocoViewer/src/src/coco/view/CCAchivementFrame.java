package src.coco.view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.coco.controller.CCAchivementLoader;
import src.coco.model.CCAchivementData;
import src.coco.model.CCCompileErrorManager;

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
		// TODO: �̍��𔻒肷��v���O����������
		ArrayList<CCAchivementData> data = new ArrayList<CCAchivementData>();
		CCAchivementLoader loader = new CCAchivementLoader(data);

		// TODO hardcoding
		String filename = "";
		loader.load(filename);

		compileErrorAchivementsCheck(data);
	}

	private void compileErrorAchivementsCheck(ArrayList<CCAchivementData> data) {
		for (CCAchivementData datum : data) {
			if (datum.getProperty() == 1) {
				compileErrorCountAchive(datum);
			} else if (datum.getProperty() == 2) {
				compileErrorKindsAchive(datum);
			} else if (datum.getProperty() == 3) {
				compileErrorCorrectTimeAchive(datum);
			}
		}
	}

	private void compileErrorCountAchive(CCAchivementData data) {
		// �R���p�C���G���[�������񐔂ɂ���V
		int count = manager.getTotalErrorCount();
		if (data.getThreshold() > count) {
			System.out.println("ErrorCount : " + data.getHirotitle() + " : "
					+ data.getExplanation());
		}
	}

	private void compileErrorKindsAchive(CCAchivementData data) {
		// �R���p�C���G���[��ނɂ���V
		int kinds = manager.getAllLists().size();
		if (data.getThreshold() > kinds) {
			System.out.println("ErrorKinds : " + data.getHirotitle() + " : "
					+ data.getExplanation());
		}
	}

	private void compileErrorCorrectTimeAchive(CCAchivementData data) {
		// �R���p�C���G���[�C�����Ԃɂ���V
		int correctTime = manager.getAllCorrectTime();
		if (data.getThreshold() > correctTime) {
			System.out.println("CorrectTime : " + data.getHirotitle() + " : "
					+ data.getExplanation());
		}
	}
}
