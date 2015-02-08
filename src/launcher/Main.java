package launcher;

import javax.swing.SwingUtilities;

import ui.MainFrame;

public class Main {

	public static void main(String[] args) {
		initFrame();
	}

	private static void initFrame() {
		SwingUtilities.invokeLater( () -> {
			MainFrame frm = new MainFrame();
			frm.setVisible(true);
		});
	}

}
