package com.harrypoter.game;

public abstract class AbstractSpell {
    protected String name;
    public abstract void useSpell(Character target);
    public String getName() {
        return name;
    }
}
