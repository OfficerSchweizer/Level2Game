import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Timer timer;
	Player p = new Player(300, 300, 100, 100);

	GamePanel() {
		timer = new Timer((1000 / 60), this);
	}

	void startGame() {
		timer.start();
	}

	void updateMenuState() {
		p.update();

	}

	void updateGameState() {

	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		p.draw(g);

	}

	void drawGameState(Graphics g) {

	}

	void drawEndState(Graphics g) {

	}

	public void paintComponent(Graphics g) {

		if (currentState == MENU_STATE) {
			drawMenuState(g);
		}

		if (currentState == GAME_STATE) {
			drawGameState(g);
		}

		if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		
		if (currentState == MENU_STATE) {
			updateMenuState();
		}

		if (currentState == GAME_STATE) {
			updateGameState();
		}

		if (currentState == END_STATE) {
			updateEndState();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub


		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("asdf");
			p.moveLeft = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("asdf");
			p.moveRight = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p.moveUp = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p.moveDown = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			p.moveLeft = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p.moveRight = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p.moveUp = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p.moveDown = false;
		}
	}

}
