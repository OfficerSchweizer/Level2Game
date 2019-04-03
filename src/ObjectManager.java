import java.util.ArrayList;

public class ObjectManager {

	Player p;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	ObjectManager(Player p) {
		p = new Player(250, 250, 40, 40);
	}

	void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	void update() {

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
			if (bullets.get(i).y <= 0 || bullets.get(i).y >= 600 || bullets.get(i).x <= 0 || bullets.get(i).x >= 600) {
				bullets.remove(bullets.get(i));
			}
		}
	}

}
