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
		// ÉNÉäÉbÉNéüÇÃìÆçÏÇÃåàíË
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CCGraphFrame frame = new CCGraphFrame();
				frame.initialize(list);
			}
		});
	}

	public void mouseClicked(MouseEvent e) {
		model.addElement("mouseClicked");
		list.ensureIndexIsVisible(model.getSize() - 1);
	}

	public void mouseEntered(MouseEvent e) {
		model.addElement("mouseEntered");
		list.ensureIndexIsVisible(model.getSize() - 1);
	}

	public void mouseExited(MouseEvent e) {
		model.addElement("mouseExited");
		list.ensureIndexIsVisible(model.getSize() - 1);
	}

	public void mousePressed(MouseEvent e) {
		model.addElement("mousePressed");
		list.ensureIndexIsVisible(model.getSize() - 1);
	}

	public void mouseReleased(MouseEvent e) {
		model.addElement("mouseReleased");
		list.ensureIndexIsVisible(model.getSize() - 1);
	}

}