import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Level2Game {
	JFrame frame;
	GamePanel gamePanel;
	public final int WIDTH = 600;
	public final int HEIGHT = 600;

	Level2Game() {
		frame = new JFrame();
		gamePanel = new GamePanel();
	}

	void setup() {
		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		frame.getContentPane().setPreferredSize(new Dimension(600, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		gamePanel.startGame();

	}

	public static void main(String[] args) {

		Level2Game game = new Level2Game();
		game.setup();
	}

}
