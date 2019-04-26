import java.awt.Color;
import java.awt.Graphics;

public class Base extends GameObject {

	Base(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawLine(50, 0, 50, 600);
	}

}
