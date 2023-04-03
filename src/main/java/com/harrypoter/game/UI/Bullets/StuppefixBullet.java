package com.harrypoter.game.UI.Bullets;

import com.harrypoter.game.UI.Bullet;
import com.harrypoter.game.UI.Entity;
import com.harrypoter.game.UI.Graphics;

import java.util.ArrayList;
import java.util.List;

public class StuppefixBullet extends Bullet {

    private int timeToExplode = 1000;

    public StuppefixBullet(Graphics manager, Entity shooter) {
        super(manager, shooter);
    }

    @Override
    public void onNewFrame() {
        super.onNewFrame();
        if (timeToExplode == 0) {
            explode();
        }
        timeToExplode--;
    }

    public void explode() {
        if (!existing)
            return;
        existing = false;
        // spawn multiple bullets in 8 directions LRUD and diagonals

        // this but in a list

        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new Bullet(manager, this.getShooter()));
        bullets.add(new Bullet(manager, this.getShooter()));
        bullets.add(new Bullet(manager, this.getShooter()));
        bullets.add(new Bullet(manager, this.getShooter()));
        bullets.add(new Bullet(manager, this.getShooter()));
        bullets.add(new Bullet(manager, this.getShooter()));
        bullets.add(new Bullet(manager, this.getShooter()));
        bullets.add(new Bullet(manager, this.getShooter()));

        // for each bullet in the list, set the direction to the corresponding direction

        bullets.get(0).direction = "L";
        bullets.get(1).direction = "R";
        bullets.get(2).direction = "U";
        bullets.get(3).direction = "D";
        bullets.get(4).direction = "LU";
        bullets.get(5).direction = "LD";
        bullets.get(6).direction = "RU";
        bullets.get(7).direction = "RD";

        // for each bullet in the list, set the position to the position of the shooter

        for (Bullet bullet : bullets) {
            bullet.posX = this.posX;
            bullet.posY = this.posY;
        }

    }
}
