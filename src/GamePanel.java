import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int SHOP_STATE = 99999;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font subtitleFont;
	Timer timer;
	Player p = new Player(100, 250, 30, 30);
	ObjectManager objectManager = new ObjectManager(p);

	GamePanel() {
		titleFont = new Font("Arial", Font.BOLD, 36);
		subtitleFont = new Font("Arial", Font.PLAIN, 20);
		timer = new Timer((1000 / 60), this);

	}

	void startGame() {
		timer.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		Point point = MouseInfo.getPointerInfo().getLocation();
		p.update(objectManager.speed, objectManager.playerSize);
		p.mouseY = point.y - 65;
		objectManager.update();

		// System.out.println(point.x + ", " + point.y);
	}

	void updateShopState() {

		Point point = MouseInfo.getPointerInfo().getLocation();
		p.mouseY = point.y;
		p.mouseX = point.x;

		objectManager.addShop(new Shop(50, 150, 500, 50, "Upgrade damage", 60, 170));
		objectManager.addShop(new Shop(50, 250, 500, 50, "Upgrade health", 60, 270));
		objectManager.addShop(new Shop(50, 350, 500, 50, "Upgrade speed", 60, 370));
		objectManager.addShop(new Shop(50, 450, 500, 50, "Upgrade firerate", 60, 470));
		objectManager.update();

		// System.out.println(point.x + ", " + point.y);
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
		objectManager.draw(g);
		g.setFont(subtitleFont);
		g.drawString("Money: $" + objectManager.money, 485, 30);

	}

	void drawShopState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 600, 600);
		g.setFont(titleFont);
		g.setColor(Color.black);
		g.drawString("UPGRADES", 190, 100);
		g.setFont(subtitleFont);
		for (Shop shop : objectManager.shops) {
			shop.draw(g);
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

		if (currentState == SHOP_STATE) {
			drawShopState(g);
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

		if (currentState == SHOP_STATE) {
			updateShopState();
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
				p = new Player(100, 250, objectManager.playerSize, objectManager.playerSize);
				objectManager = new ObjectManager(p);
				// shop = new Shop(p);
			}
			currentState++;
			if (currentState > END_STATE) {
				currentState = MENU_STATE;
			}

		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (currentState == GAME_STATE) {
				currentState = SHOP_STATE;
			} else {

				if (currentState == SHOP_STATE) {
					currentState = GAME_STATE;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (currentState == GAME_STATE) {
			objectManager.firing = true;
			objectManager.shoot();
		}

		if (currentState == SHOP_STATE) {
			// dmg
			if (p.mouseX > 50 && p.mouseX < 550 && p.mouseY < 250 && p.mouseY > 200) {

			}
			// vit
			if (p.mouseX > 50 && p.mouseX < 550 && p.mouseY < 350 && p.mouseY > 300) {

			}
			// spd
			if (p.mouseX > 50 && p.mouseX < 550 && p.mouseY < 450 && p.mouseY > 400) {

			}
			// firerate
			if (p.mouseX > 50 && p.mouseX < 550 && p.mouseY < 550 && p.mouseY > 500) {

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		objectManager.firing = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
