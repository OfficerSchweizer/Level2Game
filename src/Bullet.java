import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject {

	Bullet(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update(int speed) {
		// collisionBox.setBounds(x, y, width, height);
		x += speed;

	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);

	}

}
