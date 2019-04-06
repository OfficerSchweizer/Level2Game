import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {

	Player p;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Shop> shops = new ArrayList<Shop>();
	int speed = 10;
	int bulletSize = 2;
	int playerSize = 100;
	int money = 0;
	int firerate = 10;
	boolean firing = false;
	long bulletTimer = System.currentTimeMillis();

	ObjectManager(Player p) {
		p = new Player(250, 250, 40, 40);
	}

	void shoot() {
		if (firing) {
			System.out.println("asdf");
//			if (System.currentTimeMillis() - bulletTimer >= firerate) {
//				
//				bulletTimer = System.currentTimeMillis();
//			}
			addBullet(new Bullet(p.x, p.y + 19, bulletSize, bulletSize));
		}
	}
	
	void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	void addShop(Shop shop) {
		shops.add(shop);

	}

	

	void update() {

		for (Shop shop : shops) {
			shop.update();
		}

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
			if (bullets.get(i).y <= 0 || bullets.get(i).y >= 600 || bullets.get(i).x <= 0 || bullets.get(i).x >= 600) {
				bullets.remove(bullets.get(i));
			}
		}
	}

	void draw(Graphics g) {

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
	}

}
