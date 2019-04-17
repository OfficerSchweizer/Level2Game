import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject {

	long enemyMoveTimer = System.currentTimeMillis();
	int enemyMoveTime = 50;

	Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update(int speed) {

		if (System.currentTimeMillis() - enemyMoveTimer >= enemyMoveTime) {
			enemyMoveTimer = System.currentTimeMillis();
			x -= speed;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}

}
