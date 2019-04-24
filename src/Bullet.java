import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bullet extends GameObject {

	Random rand = new Random();
	long yTimer = System.currentTimeMillis();
	int randInt = rand.nextInt(4) - 2;
	boolean isAlive = true;

	Bullet(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update(int speed, int yTime) {

		collisionBox.setBounds(x, y, width, height);
		
		if (System.currentTimeMillis() - yTimer >= yTime) {
			yTimer = System.currentTimeMillis();
			y += randInt;
		}
		x += speed;

	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);

	}

}
