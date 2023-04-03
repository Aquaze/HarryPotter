package com.harrypoter.game.UI.Enemies;

import com.harrypoter.game.UI.Graphics;
import com.harrypoter.game.UI.Player;

import java.awt.*;

public class Troll extends Enemy {

    public Troll(Graphics manager) {
        super(manager);
        color = new Color(37, 144, 58);
        movementSpeed = 500;
        scale = 3;
    }

    @Override
    public String generateMouvement() {
        // moves thowards the player
        Player player = manager.getPlayer();

        String direction = "";
        if(player.posX < posX) direction += "R";
        if(player.posX > posX) direction += "L";
        if(player.posY > posY) direction += "D";
        if(player.posY < posY) direction += "U";
        move(direction);
        return direction;

    }
}
