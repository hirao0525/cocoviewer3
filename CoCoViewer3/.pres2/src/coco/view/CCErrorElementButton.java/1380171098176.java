package coco.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import coco.model.CCCompileErrorList;

public class CCErrorElementButton extends JButton implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CCErrorElementButton(CCCompileErrorList list) {
		super(list.getErrors().size() + " : " + list.getMessage());
		super.setBackground(Color.YELLOW);
		initialize(list);
	}

	private void initialize(final CCCompileErrorList list) {
		// ƒNƒŠƒbƒNŸ‚Ì“®ì‚ÌŒˆ’è
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CCGraphFrame frame = new CCGraphFrame();
				frame.initialize(list);
			}
		});
	}

}