import java.awt.Color;
import java.awt.Graphics;

public class Base extends GameObject {

	int health = 100;

	Base(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.collisionBox.setBounds(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawLine(15, 0, 15, 600);
		
	}

}
