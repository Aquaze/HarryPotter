package com.harrypoter.game;

public class SwordSpell extends AbstractSpell {
    public SwordSpell() {
        this.name = "SwordSpell";
    }
    @Override
    public void useSpell(Character target) {
        target.hit(1000, "SwordSpell");
    }
}
