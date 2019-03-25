import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	int speed = 10;

	boolean moveLeft;
	boolean moveRight;
	boolean moveUp;
	boolean moveDown;

	Player(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update() {
		if (moveLeft) {
			x -= speed;
		}

		if (moveRight) {
			x += speed;
		}

		if (moveUp) {
			y -= speed;
		}

		if (moveDown) {
			y += speed;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}

}
