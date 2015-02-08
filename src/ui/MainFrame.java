package ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements WindowListener {
	private ScheduledExecutorService scheduler;

	public MainFrame() {
		initUI();
	}

	private void initUI() {
		Board board = new Board();
		add(board);
		pack();
		setMinimumSize(getSize());
		addWindowListener(this);
		
		scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(() -> {
			SwingUtilities.invokeLater(() -> { 
				board.rotate();
			});
		}, 0, 140, TimeUnit.MILLISECONDS);
		setTitle("Anim");
		//setBackground(Color.BLACK);
		setLocationRelativeTo(null);
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		removeWindowListener(this);
		scheduler.shutdownNow();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	
	
}
