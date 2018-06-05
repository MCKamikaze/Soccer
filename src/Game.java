import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		DrawPanel dp = new DrawPanel();
		
		f.add(dp);
		f.setSize(800, 500);
		f.setLocation(150, 50);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
