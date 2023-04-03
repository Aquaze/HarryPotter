package com.harrypoter.game;

public class Patronus extends AbstractSpell {
    public Patronus() {
        this.name = "Patronus";
    }
    @Override
    public void useSpell(Character target) {
        target.hit(1000, "Patronus");
    }
}
