package com.harrypoter.game;

public class WingardiumLeviosa extends AbstractSpell {
    public WingardiumLeviosa() {
        this.name = "Wingardium Leviosa";
    }
    @Override
    public void useSpell(Character target) {
        target.hit(0.06, "com.harrypoter.game.WingardiumLeviosa");
        System.out.println(target.getName() + " has a rock falling towards him");

    }
}
