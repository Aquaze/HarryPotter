package com.harrypoter.game.UI;

import com.harrypoter.game.Patronus;
import com.harrypoter.game.Stuppefix;
import com.harrypoter.game.SwordSpell;
import com.harrypoter.game.WingardiumLeviosa;
import com.harrypoter.game.UI.Bullets.PatronusBullet;
import com.harrypoter.game.UI.Bullets.StuppefixBullet;
import com.harrypoter.game.UI.Bullets.SwordBullet;
import com.harrypoter.game.UI.Bullets.WingardiumLeviosaBullet;
import com.harrypoter.game.Wizard;

public class Player extends Entity {

    Wizard character;
    int shootDelay = 100;
    int shootCountdown = 0;

    public Player(Graphics manager) {
        super(manager);
        scale = 2;
    }

    @Override
    public void onNewFrame() {
        super.onNewFrame();
        if (shootCountdown > 0)
            shootCountdown--;
    }

    public void shoot(String direction) {
        if (shootCountdown > 0)
            return;

        if (character.getCurrentSpell() == null)
            return;
        Bullet bullet;
        if (character.getCurrentSpell() instanceof Stuppefix) {
            bullet = new StuppefixBullet(manager, this);
            shootCountdown = 200;
        } else if (character.getCurrentSpell() instanceof WingardiumLeviosa) {
            bullet = new WingardiumLeviosaBullet(manager, this);
            shootCountdown = 500;
        } else if (character.getCurrentSpell() instanceof SwordSpell) {
            bullet = new SwordBullet(manager, this);
            shootCountdown = 100;
        } else if (character.getCurrentSpell() instanceof Patronus) {
            bullet = new PatronusBullet(manager, this);
            shootCountdown = 2000;
        } else
            bullet = new Bullet(this.manager, this);
        bullet.spell = character.getCurrentSpell();
        bullet.setDirection(direction);
        bullet.setPosX(posX);
        bullet.setPosY(posY);
    }
}
