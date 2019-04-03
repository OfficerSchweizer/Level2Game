import java.awt.Color;
import java.awt.Font;
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
	Font titleFont;
	Timer timer;
	Player p = new Player(250, 250, 40, 40);
	ObjectManager objectManager = new ObjectManager(p);

	GamePanel() {
		titleFont = new Font("Arial", Font.BOLD, 36);
		timer = new Timer((1000 / 60), this);
	}

	void startGame() {
		timer.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		p.update();
		objectManager.update();
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.black);
		g.setFont(titleFont);
		g.drawString("Game", 200, 100);

	}

	void drawGameState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 600, 600);
		p.draw(g);
		for (Bullet bullet : objectManager.bullets) {
			bullet.draw(g);
		}

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.white);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 175, 100);
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

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END_STATE) {
				p = new Player(250, 250, 50, 50);
			}
			currentState++;
			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			if (p.aimLeft) {
				objectManager.addBullet(new Bullet(p.x, p.y + 18, 5, 5));

			}

			if (p.aimRight) {
				objectManager.addBullet(new Bullet(p.x, p.y + 18, 5, 5));
			}

			if (p.aimUp) {
				objectManager.addBullet(new Bullet(p.x + 18, p.y, 5, 5));
			}

			if (p.aimDown) {
				objectManager.addBullet(new Bullet(p.x + 18, p.y, 5, 5));
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			p.moveLeft = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			p.moveRight = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_W) {
			p.moveUp = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			p.moveDown = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			p.aimLeft = true;
			p.aimRight = false;
			p.aimUp = false;
			p.aimDown = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			p.aimLeft = false;
			p.aimRight = true;
			p.aimUp = false;
			p.aimDown = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_W) {
			p.aimLeft = false;
			p.aimRight = false;
			p.aimUp = true;
			p.aimDown = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			p.aimLeft = false;
			p.aimRight = false;
			p.aimUp = false;
			p.aimDown = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A) {
			p.moveLeft = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			p.moveRight = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_W) {
			p.moveUp = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			p.moveDown = false;
		}
	}

}
