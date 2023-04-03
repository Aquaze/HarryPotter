package com.harrypoter.game.UI.Bullets;

import java.awt.Color;

import com.harrypoter.game.UI.Bullet;
import com.harrypoter.game.UI.Entity;
import com.harrypoter.game.UI.Graphics;

public class WingardiumLeviosaBullet extends Bullet {
    public WingardiumLeviosaBullet(Graphics manager, Entity shooter) {
        super(manager, shooter);
        posY = 0;
        direction = "D";
        // brown
        color = new Color(139, 69, 19);
    }

    @Override
    public void setPosY(int posY) {
        
    }

    @Override
    public void setDirection(String direction) {
    
    }
}
