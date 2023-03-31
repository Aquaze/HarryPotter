import enums.Pet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Hello wizard, welcome to hogwarts. Ready to discover the mysteries of magic? What is your name?");
        System.out.println("Choose a name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Your ID");

        Pet[] possiblePets = {Pet.CAT, Pet.RAT, Pet.TOAD, Pet.OWL};
        int randomIndex = (int) (Math.random() * possiblePets.length);
        Pet randomPet = possiblePets[randomIndex];
        Wizard player = new Wizard(name, randomPet , new Wand(1, 10), new House(0));
        player.setProbOfProtego(0);

        player.addSpell(new Stuppefix());
        player.addSpell(new WingardiumLeviosa());

        System.out.println(player);
        Wizard wizard2 = new Wizard("Troll", Pet.OWL, new Wand(1, 10), new House(0));
        wizard2.addSpell(new Hit());

        wizard2.getSpellResistance().put("Stuppefix", 0.3); // resistance au sort stuppefix
        wizard2.getSpellResistance().put("WingardiumLeviosa", 5.0); // faiblesse au sort levitation

        if(CombatHandler.fight(player, wizard2)){
            System.out.println("You win");
        }
        else {
            System.out.println("You lose");
        }




    }
}