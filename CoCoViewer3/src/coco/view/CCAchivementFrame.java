package coco.view;

import javax.swing.JFrame;

import coco.model.CCCompileErrorManager;

public class CCAchivementFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String APP_NAME = "CoCo Viewer";
	public static final String VERSION = "0.0.5";

	private int width = 720;
	private int height = 480;

	private CCCompileErrorManager manager;

	public CCAchivementFrame(CCCompileErrorManager manager) {
		super();
		this.manager = manager;
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);
		setTitle(APP_NAME + " " + VERSION);
	}

	public void openFrame() {
		System.out.println("Achivement Open");
	}
}
