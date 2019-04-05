import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject {

	int speed = 20;

	Bullet(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update() {
		// collisionBox.setBounds(x, y, width, height);
		x += speed;

	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);

	}

}
