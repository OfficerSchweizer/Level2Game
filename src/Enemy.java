import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject {

	long enemyMoveTimer = System.currentTimeMillis();
	int enemyMoveTime = 50;
	int healthBar = 50;
	int health = 50;
	int enemySize;
	boolean isAlive = true;

	Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void setEnemySizeHealth(int enemySize, int enemyHealth) {
		this.enemySize = enemySize;
		this.health = enemyHealth;
	}

	void update(int speed) {

		if (health <= 0) {
			isAlive = false;
		}

		if (System.currentTimeMillis() - enemyMoveTimer >= enemyMoveTime) {
			enemyMoveTimer = System.currentTimeMillis();
			x -= speed;

			this.collisionBox.setBounds(x, y, width, height);

		}
	}

	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);

		g.setColor(Color.white);
		g.drawRect(x + 3, y + 3, width - 6, height - 6);

		g.setColor(Color.red);
		g.fillRect(x, y - 5, width, 2);

		g.setColor(Color.GREEN);

		g.fillRect(x, y - 5, healthBar, 2);
	}

}
