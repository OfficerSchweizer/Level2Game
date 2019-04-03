import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	int speed = 5;

	boolean moveLeft;
	boolean moveRight;
	boolean moveUp;
	boolean moveDown;
	boolean aimLeft;
	boolean aimRight;
	boolean aimUp;
	boolean aimDown;

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
		if (aimLeft) {
			g.fillRect(x - 30, y + 15, width - 10, 10);
		}

		if (aimRight) {
			g.fillRect(x + 40, y + 15, width - 10, 10);
		}

		if (aimUp) {
			g.fillRect(x + 15, y - 30, 10, width - 10);
		}

		if (aimDown) {
			g.fillRect(x + 15, y + 40, 10, width - 10);
		}
	}

}
