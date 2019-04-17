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

	int speed = 2;
	int playerSize = 40;
	int bulletSize = 1;
	int bulletSpeed = 4;
	int enemySpeed = 1;
	int enemySize = 20;
	int damage = 10;
	int health = 100;
	int accuracy = 1;
	int money = 0;
	int firerate = 150;
	int bulletOffset = 19;
	int bulletYTime = 15;
	int enemyHealth = 100;
	boolean firing = false;
	boolean gameState = false;
	long bulletTimer = System.currentTimeMillis();
	int enemySpawnTime = 1500;
	long enemySpawnTimer = System.currentTimeMillis();

	ObjectManager(Player p) {
		this.p = p;
	}

	void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	void addShop(Shop shop) {
		shops.add(shop);

	}

	void update() {

		if (firing) {
			if (System.currentTimeMillis() - bulletTimer >= firerate) {
				addBullet(new Bullet(p.x, p.y + bulletOffset, bulletSize, bulletSize));
				bulletTimer = System.currentTimeMillis();
			}
		}

		if (gameState) {
			if (System.currentTimeMillis() - enemySpawnTimer >= enemySpawnTime) {
				addEnemy(new Enemy(601, (new Random().nextInt(550)) + 10, enemySize, enemySize));
				enemySpawnTimer = System.currentTimeMillis();
			}
		}

		for (Shop shop : shops) {
			shop.update();
		}

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(bulletSpeed, bulletYTime);
			if (bullets.get(i).y <= 0 || bullets.get(i).y >= 600 || bullets.get(i).x <= 0 || bullets.get(i).x >= 600) {
				bullets.remove(bullets.get(i));
			}

		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update(enemySpeed);
			if (enemies.get(i).x <= 0) {
				enemies.remove(enemies.get(i));
			}
		}
	}

	void draw(Graphics g) {

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
	}

}
