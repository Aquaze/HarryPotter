package com.harrypoter.game;

public class Monster extends Character {
    private AbstractSpell attack;

    public Monster(String name, double health) {
        super(name, health);
    }

    @Override
    public void randomAttack(Character target) {
        attack.useSpell(target);
    }
}
