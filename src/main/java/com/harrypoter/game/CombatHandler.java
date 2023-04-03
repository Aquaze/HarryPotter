package com.harrypoter.game;

public class CombatHandler {
    //this class is used to orchestrate a fight

    private static String dispHealth(Character C){
        String res = "";
        int length = 25;
        int health = (int) (C.getHealth() * (double) length);
        for (int i = 0; i < length ; i++){
            if(i<health){
                res += "❤";
            }
            else {
                res += "-";
            }
        }
        return res;
    }
    public static boolean fight(Wizard player, Character ennemy){
        while(true){
            System.out.println("\n\n_______________________________________________________");
            System.out.println("Player : " + player.getName() + " Health " + dispHealth(player));
            System.out.println("Ennemy : " + ennemy.getName() + " Health " + dispHealth(ennemy));
            int chosenSpell = player.dispAvailableSpells();
            player.getSpell(chosenSpell).useSpell(ennemy);
            ennemy.randomAttack(player);
            if (ennemy.getHealth()<=0)return true;
            if (player.getHealth()<=0)return false;
        }
    }

}
