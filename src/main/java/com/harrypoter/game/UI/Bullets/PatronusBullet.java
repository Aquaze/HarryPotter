package com.harrypoter.game.UI.Bullets;

import com.harrypoter.game.UI.Bullet;
import com.harrypoter.game.UI.Entity;
import com.harrypoter.game.UI.Graphics;
import com.harrypoter.game.UI.Enemies.Enemy;

public class PatronusBullet extends Bullet {

    public PatronusBullet(Graphics manager, Entity shooter) {
        super(manager, shooter);
        // light blue
        timeToLive = 3000;
        color = new java.awt.Color(173, 216, 230);
        movementSpeed = 500;
        scale = 2;
    }

    // follow closest enemy
    @Override
    public void onNewFrame() {
        super.onNewFrame();

        if (true) {
            if (getEntities().size() == 0) {
                existing = false;
                return;
            }
            Entity closest = null;
            for (Entity enemy : getEntities()) {
                if (!(enemy instanceof Enemy))
                    continue;
                if (closest == null) {
                    closest = enemy;
                    continue;
                }
                if (Math.abs(enemy.posX - posX) + Math.abs(enemy.posY - posY) < Math.abs(closest.posX - posX)
                        + Math.abs(closest.posY - posY)) {
                    closest = enemy;
                }
            }
            if (closest == null)
                return;
            String direction = "";
            if (closest.posX < posX)
                direction += "R";
            else if (closest.posX > posX)
                direction += "L";
            if (closest.posY > posY)
                direction += "D";
            else if (closest.posY < posY)
                direction += "U";

            setDirection(direction);
        }
    }

}
