package launcher;

import java.awt.EventQueue;

import ui.MainFrame;

public class Main {

	public static void main(String[] args) {
		initFrame();
	}

	private static void initFrame() {
		MainFrame frm = new MainFrame();
		EventQueue.invokeLater( () -> frm.setVisible(true));
	}

}
