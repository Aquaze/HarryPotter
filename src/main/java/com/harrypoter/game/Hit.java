package com.harrypoter.game;

public class Hit extends AbstractSpell {
    public Hit() {
        this.name = "com.harrypoter.game.Hit";
    }
    @Override
    public void useSpell(Character target) {
        target.hit(0.05, "com.harrypoter.game.Hit");
    }
}
