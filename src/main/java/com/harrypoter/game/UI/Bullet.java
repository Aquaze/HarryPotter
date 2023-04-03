package com.harrypoter.game.UI;

import com.harrypoter.game.AbstractSpell;

import java.awt.*;

public class Bullet extends Entity {
    private Entity shooter;

    public AbstractSpell spell;

    public Bullet(Graphics manager, Entity shooter) {
        super(manager);
        movementSpeed = 60;
        color = Color.MAGENTA;
        this.shooter = shooter;
        if(shooter instanceof Player) {
            setSpell(((Player) shooter).character.getCurrentSpell());
        }
    }

    @Override
    public void onHitLimit() {
        super.onHitLimit();
        existing = false;
    }

    public void setSpell(AbstractSpell spell) {
        this.spell = spell;
    }

    public void setShooter(Entity shooter) {
        this.shooter = shooter;
    }

    public Entity getShooter() {
        return shooter;
    }



    public void onNewFrame() {
        super.onNewFrame();
        move();
    }
}
