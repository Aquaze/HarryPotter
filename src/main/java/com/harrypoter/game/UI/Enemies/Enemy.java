package com.harrypoter.game.UI.Enemies;

import com.harrypoter.game.UI.Entity;
import com.harrypoter.game.UI.Graphics;

public class Enemy extends Entity {
    public Enemy(Graphics manager) {
        super(manager);
        movementSpeed = 150;
        scale = 2;
    }

    public String generateMouvement() {
        int x = (int) (Math.random() * 4);
        String direction = "";
        switch (x) {
            case 0:
                direction = "U";
                break;
            case 1:
                direction = "D";
                break;
            case 2:
                direction = "L";
                break;
            case 3:
                direction = "R";
                break;
        }
        move(direction);
        return direction;
    }

    @Override
    public void onNewFrame() {
        super.onNewFrame();
        if (canMove()) {
            this.direction = generateMouvement();
        }
    }
}
