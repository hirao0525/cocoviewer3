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
	// TODO: ボタン生成の際にグラフを書くなら引き渡す設計にした方がいいかも
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
		// rootPanel のレイアウトをリセットする
		rootPanel.setLayout(null);

		// titleなどの設定
		frameSetting();

		// 全体のコンパイル数表示
		setCompileErrorNumber();

		// ボタンを配置する
		setCompileErrorButton();
		setMiniGraphButton();

		// レイアウトした配置でコンテンツを追加
		getContentPane().add(rootPanel, BorderLayout.CENTER);
	}

	private void frameSetting() {
		// EXIT_ON_CLOSE は実質 exit(0) → システムが全部落ちる
		// なのでDISPOSE_ON_CLOSE を使う
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(APP_NAME + " " + VERSION);
	}

	private void setCompileErrorNumber() {
		JLabel label = new JLabel();
		String string = "あなたのこれまでの総コンパイルエラー数 ： " + manager.getTotalErrorCount();
		label.setBounds(10, 10, 300, 15);
		label.setText(string);
		// label の背景を設定する場合は背景を不透明にする処理を加えること
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

		// 配列初期化
		// Arrays.fill(errorExistNums, 0);

		// 配列にエラーIDの情報を書き込む
		// for (int i = 0; i < cedsSize; i++) {
		// errorExistNums[ceds.get(i).getErrorID()]++;
		// }

		// エラーIDごとの数値を書き込み、ボタンを実装する
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

		// ボタンを配置する
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

	// // コンパイルエラーログのリストから一番大きいコンパイルエラーIDを持ってくる
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

	// クリックできないボタンを作成
	private void setEmptyPanel(int x, int y) {
		JButton emptyButton = new JButton("まだ発生していないエラーです");
		emptyButton.setEnabled(false);
		emptyButton.setBackground(Color.CYAN);
		emptyButton.setBounds(x, y, ERRORBUTTONWIDTH, ERRORBUTTONHEIGHT);
		rootPanel.add(emptyButton);
	}

	// // コンパイルエラーの総合計数を求める
	// private int sumCompileError() {
	// // System.out.println(ceds.size());
	// return ceds.size();
	// }
}