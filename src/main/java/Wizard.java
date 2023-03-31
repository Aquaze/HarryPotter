import enums.Pet;
import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Character {
    private Pet pet;
    private Wand wand;
    private House house;
    private ArrayList<AbstractSpell> spells;
    private ArrayList<AbstractPotion> potions;

    public Wizard(String name, Pet pet, Wand wand, House house) {
        super(name, 1);
        this.pet = pet;
        this.wand = wand;
        this.house = house;
        this.spells = new ArrayList<>();
        this.potions = new ArrayList<>();
    }

    public void addSpell(AbstractSpell spell) {
        this.spells.add(spell);
    }

    public void addPotion(AbstractPotion potion) {
        this.potions.add(potion);
    }

    public ArrayList<AbstractSpell> getSpells() {
        return this.spells;
    }
    public AbstractSpell getSpell(int index){
        return this.spells.get(index);
    }
    public ArrayList<AbstractPotion> getPotions() {
        return this.potions;
    }

    @Override
    public void randomAttack(Character target) {
        Random rand = new Random();
        spells.get(rand.nextInt(spells.size())).useSpell(target);
    }

    public int dispAvailableSpells(){
        int i = 0;
        for(AbstractSpell spell : spells){
            System.out.println(spell.getName() + " : " + i);
            i++;
        }
        return InputHandler.getValidInput(0, spells.size() - 1);
    }

    public String toString() {
        return "Wizard: " + getName() + " Health : " + getHealth() + "/" + getCurrentHealth() +
                " pet : " + this.pet + " "
                + House.getName() + " " + Wand.getLength() + Wand.getCore()+
                " Spells :" + this.spells + " Potions : " + this.potions;

    }

}
