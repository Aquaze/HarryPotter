package com.harrypoter.game.UI.Enemies;

import java.awt.Color;

import com.harrypoter.game.UI.Graphics;

public class Basilic extends Troll {
    public Basilic(Graphics manager) {
        super(manager);
        // dark green
        color = new Color(0, 100, 0);
        scale = 1;
        movementSpeed = 250;
    }

}
