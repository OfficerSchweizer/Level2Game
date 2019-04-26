import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
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
	Font shopFont;
	Timer timer;
	Player p = new Player(50, 250, 40, 40);
	Base base = new Base(0, 0, 20, 600);
	ObjectManager objectManager = new ObjectManager(p);

	GamePanel() {
		titleFont = new Font("Arial", Font.BOLD, 36);
		subtitleFont = new Font("Arial", Font.PLAIN, 20);
		timer = new Timer((5), this);

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
		objectManager.isAlive();
		objectManager.checkCollision();
		objectManager.scaleManager();
	}

	void updateShopState() {

		Point point = MouseInfo.getPointerInfo().getLocation();
		p.mouseY = point.y;
		p.mouseX = point.x;

		objectManager.addShop(new Shop(50, 150, 500, 50, "Upgrade damage", 60, 180));
		objectManager.addShop(new Shop(50, 250, 500, 50, "Upgrade accuracy", 60, 280));
		objectManager.addShop(new Shop(50, 350, 500, 50, "Upgrade speed", 60, 380));
		objectManager.addShop(new Shop(50, 450, 500, 50, "Upgrade firerate", 60, 480));
		objectManager.addShop(new Shop(180, 530, 201, 45, "Repair base", 190, 560));
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
		objectManager.draw(g);
		base.draw(g);
		g.setFont(subtitleFont);
		g.setColor(Color.black);
		g.drawString("Money: $" + objectManager.money, 90, 30);

	}

	void drawShopState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 600, 600);
		g.setFont(titleFont);
		g.setColor(Color.black);
		g.drawString("UPGRADES", 190, 100);
		g.setFont(subtitleFont);
		g.drawString("Money: $" + objectManager.money, 90, 30);

		for (Shop shop : objectManager.shops) {
			shop.draw(g);
		}

		g.drawString("Level: " + objectManager.dmgLevel, 370, 180);
		g.drawString("Level: " + objectManager.accLevel, 370, 280);
		g.drawString("Level: " + objectManager.spdLevel, 370, 380);
		g.drawString("Level: " + objectManager.frtLevel, 370, 480);

		if (objectManager.dmgLevel < 10) {
			g.drawString("$" + objectManager.dmgPrice, 460, 180);
		} else {
			g.drawString("MAXED", 460, 180);
		}
		if (objectManager.accLevel < 10) {
			g.drawString("$" + objectManager.accPrice, 460, 280);
		} else {
			g.drawString("MAXED", 460, 280);
		}
		if (objectManager.spdLevel < 10) {
			g.drawString("$" + objectManager.spdPrice, 460, 380);
		} else {
			g.drawString("MAXED", 460, 380);
		}
		if (objectManager.frtLevel < 10) {
			g.drawString("$" + objectManager.frtPrice, 460, 480);
		} else {
			g.drawString("MAXED", 460, 480);
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
			objectManager.gameState = true;
		} else {
			updateGameState();
			objectManager.gameState = false;
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

		if (e.getKeyCode() == KeyEvent.VK_M) {
			objectManager.money += 1000;
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

		}

		if (currentState == SHOP_STATE) {

			// dmg
			if (p.mouseX > 50 && p.mouseX < 550 && p.mouseY < 250 && p.mouseY > 200) {
				if (objectManager.dmgLevel < 10) {
					if (objectManager.money > objectManager.dmgPrice) {

						objectManager.bulletSize++;
						objectManager.damage += 5;
						objectManager.bulletOffset--;

						objectManager.dmgLevel++;

						objectManager.money -= objectManager.dmgPrice;
						objectManager.dmgPrice += 100;
					}
				}
			}

			// acc
			if (p.mouseX > 50 && p.mouseX < 550 && p.mouseY < 350 && p.mouseY > 300) {
				if (objectManager.accLevel < 10) {
					if (objectManager.money > objectManager.accPrice) {
						objectManager.accuracy++;

						objectManager.accLevel++;

						objectManager.money -= objectManager.accPrice;
						objectManager.accPrice += 100;
						objectManager.bulletSpeed++;
					}
				}
			}
			// spd
			if (p.mouseX > 50 && p.mouseX < 550 && p.mouseY < 450 && p.mouseY > 400) {
				if (objectManager.spdLevel < 10) {
					if (objectManager.money > objectManager.spdPrice) {
						objectManager.speed++;

						objectManager.spdLevel++;

						objectManager.money -= objectManager.spdPrice;
						objectManager.spdPrice += 100;
					}
				}
			}
			// firerate
			if (p.mouseX > 50 && p.mouseX < 550 && p.mouseY < 550 && p.mouseY > 500) {
				if (objectManager.frtLevel < 10) {
					if (objectManager.money > objectManager.frtPrice) {
						objectManager.firerate -= 20;

						objectManager.frtLevel++;

						objectManager.money -= objectManager.frtPrice;
						objectManager.frtPrice += 100;
					}
				}
			}

			// repair
			if (p.mouseX > 180 && p.mouseX < 381 && p.mouseY < 620 && p.mouseY > 575) {

			}

			// 180, 530, 201, 45, "Repair base", 190, 550)
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
