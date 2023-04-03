package com.harrypoter.game.UI.Enemies;

import com.harrypoter.game.UI.Entity;
import com.harrypoter.game.UI.Graphics;
import com.harrypoter.game.UI.Bullets.PatronusBullet;

import java.awt.*;

public class Detraqueur extends Troll {
    public Detraqueur(Graphics manager) {
        super(manager);

        color = Color.BLACK;
    }

    // moves away from PatronusBullet
    @Override
    public String generateMouvement() {
        if (getEntities().size() == 0)
            return super.generateMouvement();
        Entity closest = null;
        for (Entity bullet : getEntities()) {
            if (!(bullet instanceof PatronusBullet))
                continue;
            if (closest == null) {
                closest = bullet;
                continue;
            }
            if (Math.abs(bullet.posX - posX) + Math.abs(bullet.posY - posY) < Math.abs(closest.posX - posX)
                    + Math.abs(closest.posY - posY)) {
                closest = bullet;
            }
        }
        if (closest == null)
            return super.generateMouvement();
        String direction = "";
        if (closest.posX < posX)
            direction += "L";
        else if (closest.posX > posX)
            direction += "R";
        if (closest.posY > posY)
            direction += "U";
        else if (closest.posY < posY)
            direction += "D";
        move(direction);
        return direction;
    }
}
