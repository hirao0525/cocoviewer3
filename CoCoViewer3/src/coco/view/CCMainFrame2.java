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

public class CCMainFrame2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String APP_NAME = "CoCo Viewer";
	public static final String VERSION = "0.0.5";

	// Button Size
	private static final int ERRORBUTTONWIDTH = 100;
	private static final int ERRORBUTTONHEIGHT = 100;

	// Dialog size
	private int width = 1120;
	private int height = 720;

	// Compile Error Date
	private CCCompileErrorManager manager;

	// For GUI
	private JPanel rootPanel = new JPanel();

	public CCMainFrame2(CCCompileErrorManager manager) {
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
		setMiniGraphButton();

		// ���C�A�E�g�����z�u�ŃR���e���c��ǉ�
		getContentPane().add(rootPanel, BorderLayout.CENTER);
	}

	private void frameSetting() {
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

	private void setMiniGraphButton() {
		ArrayList<CCErrorElementButton2> buttons = new ArrayList<CCErrorElementButton2>();

		// �G���[ID���Ƃ̐��l���������݁A�{�^������������
		for (CCCompileErrorList list : manager.getAllLists()) {
			CCErrorElementButton2 button = new CCErrorElementButton2(list,
					ERRORBUTTONWIDTH, ERRORBUTTONHEIGHT);
			buttons.add(button);
		}

		// �{�^����z�u����
		int i = 1;
		for (int x = 0; x < width - ERRORBUTTONWIDTH; x += ERRORBUTTONWIDTH) {
			for (int y = 30; y < height - ERRORBUTTONHEIGHT; y += ERRORBUTTONHEIGHT) {
				if (manager.getAllLists().size() >= i) {
					if (manager.getList(i).getErrors().size() > 0) {
						buttons.get(i - 1).setBounds(x, y, ERRORBUTTONWIDTH,
								ERRORBUTTONHEIGHT);
						rootPanel.add(buttons.get(i - 1));
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
		JButton emptyButton = new JButton("������");
		emptyButton.setEnabled(false);
		emptyButton.setToolTipText("�������ł���");
		emptyButton.setBackground(Color.GRAY);
		emptyButton.setBounds(x, y, ERRORBUTTONWIDTH, ERRORBUTTONHEIGHT);
		rootPanel.add(emptyButton);
	}
}