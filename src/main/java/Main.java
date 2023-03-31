import enums.Pet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        System.out.println("Choisi un nom");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        /**
        System.out.println("Choisi une classe : 1 - Gryffondor, 2 - Serpentard, 3 - Poufsouffle, 4 - Serdaigle");
        int houseID = InputHandler.getValidInput(1,4)-1;

        System.out.println("Choisi un core pour ta baguette : 1 -Pheonix, 2 - Dragon, 3 - Corne de licorne, 4 - Bois de cerf");
        int core = InputHandler.getValidInput(1,4);
        System.out.println("Choisi une longueur pour ta baguette : 5cm - 30cm");
        int length = InputHandler.getValidInput(5,30);

        System.out.println("Choisi un Animal de compagnie : 1 - Owl , 2 - Rat, 3 - Cat, 4 - Toad");
        Pet pet = Pet.values()[InputHandler.getValidInput(1,4)-1];
        Wand wand = new Wand(core, length);
        House house = new House(houseID);
         **/


        Wizard player = new Wizard(name, Pet.OWL, new Wand(1, 10), new House(0));
        player.setProbOfProtego(0);

        player.addSpell(new Stuppefix());
        player.addSpell(new WingardiumLeviosa());

        System.out.println(player);
        Wizard wizard2 = new Wizard("Troll", Pet.OWL, new Wand(1, 10), new House(0));
        wizard2.addSpell(new Stuppefix());
        wizard2.addSpell(new WingardiumLeviosa());

        wizard2.getSpellResistance().put("Stuppefix", 3.0); // faiblesse au sort stuppefix
        wizard2.getSpellResistance().put("WingardiumLeviosa", 0.5); // resistance au sort levitation

        if(CombatHandler.fight(player, wizard2)){
            System.out.println("You win");
        }
        else {
            System.out.println("You lose");
        }




    }
}