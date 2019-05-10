import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {

	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Shop> shops = new ArrayList<Shop>();
	Base base = new Base(0, 0, 20, 600);
	Player p;

	int scale = 1;

	int dmgLevel = 1;
	int accLevel = 1;
	int spdLevel = 1;
	int frtLevel = 1;

	int dmgPrice = 100;
	int accPrice = 100;
	int spdPrice = 100;
	int frtPrice = 100;

	int speed = 2;
	int playerSize = 30;
	int damage = 10;
	int health = 100;
	int accuracy = 1;
	int money = 0;
	int firerate = 150;

	int bulletSize = 1;
	int bulletSpeed = 4;
	int knockback = 3;

	int enemySpeed = 1;
	int enemySpeedOG = 1;
	int enemySize = 10;
	int enemySizeOG = 10;
	int enemyHealth = 100;
	int enemyHealthOG = 100;

	int bulletOffset = 19;
	int bulletYTime = 40;

	boolean firing = false;
	boolean gameState = false;

	long surviveTimer;
	long surviveTimer2 = System.currentTimeMillis();
	
	long bulletTimer = System.currentTimeMillis();
	int enemySpawnTime = 1500;
	int enemySpawnTimeOG = 1500;
	long enemySpawnTimer = System.currentTimeMillis();
	long gameScaleTimer = System.currentTimeMillis();
	int gameScaleTime = 10000;

	ObjectManager(Player p) {
		this.p = p;
	}

	void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	void addEnemy(Enemy enemy) {
		enemies.add(enemy);

		enemy.healthBar = enemy.width;
		enemy.health = enemy.healthBar;
	}

	void addShop(Shop shop) {
		shops.add(shop);

	}

	void scaleManager() {

		if (gameState) {
			if (System.currentTimeMillis() - gameScaleTimer >= gameScaleTime) {
				scale++;

				enemyHealth = enemyHealthOG * (scale / 4);

				if (enemySize < 80) {
					enemySize = enemySizeOG * (scale / 2);
					if (enemySize > 80) {
						enemySize = 80;
					}
				}

				if (gameScaleTime == 10000) {
					if (enemySpeed < 8) {
						enemySpeed += enemySpeedOG * (scale / 3);
						if (enemySpeed > 9) {
							enemySpeed = 8;
						}
					} else if (gameScaleTime == 15000) {
						if (enemySpeed < 8) {
							enemySpeed += enemySpeedOG * (scale / 3);
							if (enemySpeed > 8) {
								enemySpeed = 8;
							}
						}
					} else if (gameScaleTime == 10000) {
						if (enemySpeed < 6) {
							enemySpeed += enemySpeedOG * (scale / 3);
							if (enemySpeed > 6) {
								enemySpeed = 6;
							}
						}
					}
				}

				if (enemySpawnTime > 100) {
					enemySpawnTime = enemySpawnTimeOG - (100 * scale);
					if (enemySpawnTime < 100) {
						enemySpawnTime = 100;
					}
				}

				// System.out.println(enemySpawnTime);
				// System.out.println(enemyHealth);
				// System.out.println(enemySpeed);
				// System.out.println(enemySize);

				gameScaleTimer = System.currentTimeMillis();
			}
		}

	}

	void checkCollision() {

		for (Bullet bullet : bullets) {
			for (Enemy enemy : enemies) {
				if (bullet.collisionBox.intersects(enemy.collisionBox)) {
					enemy.health -= damage;
					enemy.x += knockback;
					enemy.healthBar -= damage;
					bullet.isAlive = false;
				}
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).collisionBox.intersects(base.collisionBox)) {
				base.health -= enemySpeed;
				enemies.remove(enemies.get(i));
			}
		}
	}

	void isAlive() {
		for (int i = 0; i < enemies.size(); i++) {
			if (!enemies.get(i).isAlive) {
				enemies.remove(enemies.get(i));
				money += 25;
			}
		}

		for (int i = 0; i < bullets.size(); i++) {
			if (!bullets.get(i).isAlive) {
				bullets.remove(bullets.get(i));
			}
		}
	}

	void update() {

		if (firing) {
			if (System.currentTimeMillis() - bulletTimer >= firerate) {
				addBullet(new Bullet(p.x + 15, p.y + bulletOffset + 5, bulletSize, bulletSize));
				bulletTimer = System.currentTimeMillis();
			}
		}

		if (gameState) {
			if (System.currentTimeMillis() - enemySpawnTimer >= enemySpawnTime) {
				addEnemy(new Enemy(601, (new Random().nextInt(550)) + 10, enemySize, enemySize));
				enemySpawnTimer = System.currentTimeMillis();
			}

			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).update(bulletSpeed, bulletYTime);
				if (bullets.get(i).y <= 0 || bullets.get(i).y >= 600 || bullets.get(i).x <= 0
						|| bullets.get(i).x >= 600) {
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

		for (Shop shop : shops) {
			shop.update();
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
