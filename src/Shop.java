import java.awt.Graphics;

public class Shop extends GameObject {

	String text;
	int price;
	int textx;
	int texty;
	

	Shop(int x, int y, int width, int height, String text, int textx, int texty, int dmgPrice) {
		
		// TODO Auto-generated constructor stub
		super(x, y, width, height);
		this.text = text;
		this.textx = textx;
		this.texty = texty;
		this.price = dmgPrice;
		

	}

	void draw(Graphics g) {
		g.drawRect(x, y, width, height);
		g.drawString(text, textx, texty);
		
		
		

	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

}
