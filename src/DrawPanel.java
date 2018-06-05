import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private SoccerField sf;
	private Team t1, t2;
	private boolean hasStarted;
	private Ball b;
	private Timer t;

	public DrawPanel() {
		sf = new SoccerField();
		t1 = new Team(Color.YELLOW, 0);// 0 means left side
		t2 = new Team(Color.RED, 1); // 1 means right side
		hasStarted = false;
		b = new Ball();
		t = new Timer();
		t.schedule(new MyTimerTask(), 1000, 30);
		addKeyListener(new MyKeyboardAdapter());
		setFocusable(true); // to get focus for keyboard
		requestFocusInWindow();
		addMouseListener(new MouseHelper());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int h = getHeight(), w = getWidth();

		sf.setSize(w, h);
		sf.drawMe(g);

		if (!hasStarted) {
			t1.reArrange(w, h);
			t2.reArrange(w, h);
			b.goToInitialPosition(w, h);
		}

		t1.drawMe(g);
		t2.drawMe(g);
		b.drawMe(g);
	}

	private class MyTimerTask extends TimerTask {
		public void run() {
			hasStarted = true;
			b.checkAngle(sf); // check borders
			// check players
			t1.checkBall(b);
			t2.checkBall(b);

			b.move();
			t1.move(b); // use AI
			// t2.move(b);
			// t2.getOrder(b,null); // partially use AI and partially use Orders
			repaint();
		}
	}

	private class MyKeyboardAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			t2.getOrder(b, e);
			// only t2 is controlled by keyboard
			/*
			 * if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP ||
			 * e.getKeyCode() == KeyEvent.VK_LEFT ||)
			 */ }
	}

	private class MouseHelper extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int x, y;
			x = e.getX();
			y = e.getY();
			b.setCenter(x, y);
		}
	}

}
