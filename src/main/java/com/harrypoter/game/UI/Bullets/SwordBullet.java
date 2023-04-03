package com.harrypoter.game.UI.Bullets;

import com.harrypoter.game.UI.Bullet;
import com.harrypoter.game.UI.Entity;
import com.harrypoter.game.UI.Graphics;

public class SwordBullet extends Bullet {

    private int moved = 0;

    public SwordBullet(Graphics manager, Entity shooter) {
        super(manager, shooter);
        // gray
        color = new java.awt.Color(128, 128, 128);
        movementSpeed = 500;
        movementDelay = 0;
        scale = 2;
    }

    @Override
    public void hasMoved() {
        System.out.println("moved " + moved);
        if (moved < 2) {
            moved++;
            return;
        }
        ;
        if (moved == 2) {
            moved++;
            super.hasMoved();
            return;
        }
        existing = false;
    }
}
