import enums.Pet;

import java.util.Random;
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
        Random rand = new Random();
        int random = rand.nextInt(4);
        House randomHouse = new House(random);
        Wizard player = new Wizard(name, randomPet , new Wand(0, 0), new House(random));
        player.setProbOfProtego(0);

        player.addSpell(new Stuppefix());
        player.addSpell(new WingardiumLeviosa());

        String[] monsters = {"troll", "basilic", "voldemort"};

        System.out.println(player);
        Wizard enemy = null;
        for (int i = 0; i < monsters.length ; i++) {

            player.setProbOfProtego(0.2);
            String monster = monsters[i];
            System.out.println("Mission " + (i+1));
            System.out.println("Oh no \033[31m"+player.getName()+"\033[0m a " + monster.toLowerCase() +" appeared in the girls' bathroom, save Hermione!");
            System.out.println("Fight him!!!");

            switch (monster) {
                case "troll": {
                    enemy = new Wizard(monster.toLowerCase(), Pet.OWL, new Wand(1, 10), new House(0));
                    enemy.addSpell(new Hit());
                    enemy.getSpellResistance().put("Stuppefix", 0.3); // resistance au sort stuppefix
                    enemy.getSpellResistance().put("WingardiumLeviosa", 5.0); // faiblesse au sort levitation
                }
                default: {

                    enemy = new Wizard(monster.toLowerCase(), Pet.OWL, new Wand(1, 10), new House(0));
                    enemy.addSpell(new Hit());
                    enemy.getSpellResistance().put("Stuppefix", 0.3); // resistance au sort stuppefix
                    enemy.getSpellResistance().put("WingardiumLeviosa", 5.0); // faiblesse au sort levitation
                }
            }

            if(CombatHandler.fight(player, enemy)){
                System.out.println("You win");
            }
            else {
                System.out.println("You lose");
            }

        }
        //mission 1



        //mission 2


    }
}