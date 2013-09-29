package coco.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coco.model.CCCompileErrorList;
import coco.model.CCCompileErrorManager;

public class CCMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String APP_NAME = "CoCo Viewer";
	public static final String VERSION = "0.0.3";

	// private static final String FILE_NAME = "compileError.log";
	// private static final String DIR_PATH = "MyProjects/.LookBack/";
	// private static final String FILE_PATH = DIR_PATH + FILE_NAME;

	// private static final String DIR_PATH =
	// "C:\\Users\\student\\workspace\\CoCoViewer\\testbase\\";
	// private static final String DATA_FILE_NAME = "compileError.log";
	// private static final String DATA_FILE_PATH = DIR_PATH + DATA_FILE_NAME;
	// private static final String EXCHANGE_FILE_NAME = "exchange.txt";
	// private static final String EXCHANGE_FILE_PATH = DIR_PATH
	// + EXCHANGE_FILE_NAME;

	// Button Size
	// TODO: �{�^�������̍ۂɃO���t�������Ȃ�����n���݌v�ɂ���������������
	private static final int ERRORBUTTONWIDTH = 300;
	private static final int ERRORBUTTONHEIGHT = 25;

	// Dialog size
	private int width = 1120;
	private int height = 720;

	// Compile Error Date
	private CCCompileErrorManager manager;

	// For GUI
	private JPanel rootPanel = new JPanel();

	public CCMainFrame(CCCompileErrorManager manager) {
		this.manager = manager;
		this.height = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().height - 25;
		initialize();
	}

	private void initialize() {
		// rootPanel �̃��C�A�E�g�����Z�b�g����
		rootPanel.setLayout(null);

		// title�Ȃǂ̐ݒ�
		frameSetting();

		// �S�̂̃R���p�C�����\��
		setCompileErrorNumber();

		// �{�^����z�u����
		setCompileErrorButton();
		setMiniGraphButton();

		// ���C�A�E�g�����z�u�ŃR���e���c��ǉ�
		getContentPane().add(rootPanel, BorderLayout.CENTER);
	}

	private void frameSetting() {
		// EXIT_ON_CLOSE �͎��� exit(0) �� �V�X�e�����S��������
		// �Ȃ̂�DISPOSE_ON_CLOSE ���g��
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(APP_NAME + " " + VERSION);
	}

	private void setCompileErrorNumber() {
		JLabel label = new JLabel();
		String string = "���Ȃ��̂���܂ł̑��R���p�C���G���[�� �F " + manager.getTotalErrorCount();
		label.setBounds(10, 10, 300, 15);
		label.setText(string);
		// label �̔w�i��ݒ肷��ꍇ�͔w�i��s�����ɂ��鏈���������邱��
		// label.setBackground(Color.yellow);
		// label.setOpaque(true);
		rootPanel.add(label);
	}

	private void setCompileErrorButton() {
		// int max = calcMaxErrorID() + 1;
		// int cedsSize = ceds.size();

		// int[] errorExistNums = new int[max];
		// ArrayList<JLabel> jls = new ArrayList<JLabel>();
		ArrayList<CCErrorElementButton> buttons = new ArrayList<CCErrorElementButton>();

		// �z�񏉊���
		// Arrays.fill(errorExistNums, 0);

		// �z��ɃG���[ID�̏�����������
		// for (int i = 0; i < cedsSize; i++) {
		// errorExistNums[ceds.get(i).getErrorID()]++;
		// }

		// �G���[ID���Ƃ̐��l���������݁A�{�^������������
		for (CCCompileErrorList list : manager.getAllLists()) {
			CCErrorElementButton button = new CCErrorElementButton(list);
			buttons.add(button);
		}

		// for (int i = 0, j = 0; i < max; i++) {
		// if (errorExistNums[i] > 0) {
		// jls.add(new JLabel(errorExistNums[i] + " : "
		// + eids.getMessage(i)));
		// CCErrorElementButton tmpeeb = new CCErrorElementButton(i, ceds,
		// eids.getMessage(i));
		// tmpeeb.add(jls.get(j));
		// elementButtons.add(tmpeeb);
		// j++;
		// }
		// }

		// �{�^����z�u����
		int i = 0;
		for (int x = 0; x < width - ERRORBUTTONWIDTH; x += ERRORBUTTONWIDTH) {
			for (int y = 30; y < height - ERRORBUTTONHEIGHT; y += ERRORBUTTONHEIGHT) {
				if (i < buttons.size()) {
					if (manager.getList(i + 1).getErrors().size() > 0) {
						buttons.get(i).setBounds(x, y, ERRORBUTTONWIDTH,
								ERRORBUTTONHEIGHT);
						rootPanel.add(buttons.get(i));
					} else {
						setEmptyPanel(x, y);
					}
					i++;
				} else {
					setEmptyPanel(x, y);
				}
			}
		}
	}

	// // �R���p�C���G���[���O�̃��X�g�����ԑ傫���R���p�C���G���[ID�������Ă���
	// private int calcMaxErrorID() {
	// int maxNum = 0;
	// for (int i = 0; i < ceds.size(); i++) {
	// if (maxNum < ceds.get(i).getErrorID()) {
	// maxNum = ceds.get(i).getErrorID();
	// }
	// }
	// return maxNum;
	// }

	private void setMiniGraphButton() {
		ArrayList<CCErrorElementButton> buttons = new ArrayList<CCErrorElementButton>();

		for (CCCompileErrorList list : manager.getAllLists()) {
			CCErrorElementButton button = new CCErrorElementButton(list);
			buttons.add(button);
		}

		int i = 0;
		for (int x = 30; x < width - ERRORBUTTONWIDTH; x += ERRORBUTTONWIDTH) {
			for (int y = 30; y < height - ERRORBUTTONHEIGHT - 30; y += ERRORBUTTONHEIGHT) {
				if (i < buttons.size()) {
					if (manager.getList(i + 1).getErrors().size() > 0) {
						buttons.get(i).setBounds(x, y, ERRORBUTTONWIDTH,
								ERRORBUTTONHEIGHT);
						rootPanel.add(buttons.get(i));
					} else {
						setEmptyPanel(x, y);
					}
					i++;
				} else {
					setEmptyPanel(x, y);
				}
			}
		}
	}

	// �N���b�N�ł��Ȃ��{�^�����쐬
	private void setEmptyPanel(int x, int y) {
		JButton emptyButton = new JButton("�܂��������Ă��Ȃ��G���[�ł�");
		emptyButton.setEnabled(false);
		emptyButton.setBackground(Color.CYAN);
		emptyButton.setBounds(x, y, ERRORBUTTONWIDTH, ERRORBUTTONHEIGHT);
		rootPanel.add(emptyButton);
	}

	// // �R���p�C���G���[�̑����v�������߂�
	// private int sumCompileError() {
	// // System.out.println(ceds.size());
	// return ceds.size();
	// }
}