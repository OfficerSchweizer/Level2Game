import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	int mouseX;
	int mouseY;

	Player(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update(int speed, int playerSize) {

		if (y < mouseY) {
			y += speed;

		}

		if (y > mouseY) {
			y -= speed;
		}

		if (y < 0) {
			y = 0;
		}

		if (y > 600 - playerSize) {
			y = 600 - playerSize;
		}

	}

	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x-15, y, width - 10, height + 5);
		g.fillRect(x + 10, y + 15, 20, 15);

	}
}
