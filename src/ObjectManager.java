import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {

	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Shop> shops = new ArrayList<Shop>();
	Player p;

	int dmgLevel = 1;
	int accLevel = 1;
	int spdLevel = 1;
	int frtLevel = 1;

	int dmgPrice = 100;
	int accPrice = 100;
	int spdPrice = 100;
	int frtPrice = 100;

	int speed = 1;
	int bulletSize = 1;
	int bulletSpeed = 4;
	int damage = 10;
	int playerSize = 30;
	int health = 100;
	int accuracy = 1;
	int money = 0;
	int firerate = 150;
	int bulletOffset = 14;
	int enemyTime = 500;
	boolean firing = false;
	boolean gameState = false;
	long bulletTimer = System.currentTimeMillis();
	long enemyTimer = System.currentTimeMillis();

	ObjectManager(Player p) {
		this.p = p;
	}

	void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	void addShop(Shop shop) {
		shops.add(shop);

	}

	void update() {

		if (firing) {
			if (System.currentTimeMillis() - bulletTimer >= firerate) {
				addBullet(new Bullet(p.x, p.y + bulletOffset, bulletSize + 5, bulletSize));
				bulletTimer = System.currentTimeMillis();
			}
		}

		for (Shop shop : shops) {
			shop.update();
		}

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(bulletSpeed);
			if (bullets.get(i).y <= 0 || bullets.get(i).y >= 600 || bullets.get(i).x <= 0 || bullets.get(i).x >= 600) {
				bullets.remove(bullets.get(i));
			}
		}

		if (gameState) {
			if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
				addAlien(new Alien((new Random().nextInt(350) + 50), 0, 50, 50));
				enemyTimer = System.currentTimeMillis();
			}
		}
	}

	void draw(Graphics g) {

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
	}

}
