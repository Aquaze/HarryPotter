import java.util.Hashtable;
import java.util.Random;
import java.util.ArrayList;
public abstract class Character {
    private double health;
    private double currentHealth;
    private String name;
    private Hashtable<String, Double> spellResistance = new Hashtable<String, Double>();

    private double probOfProtego = 0 ; //chances of avoiding a hit

    public Character(String name, double health){
        this.name = name;
        this.health = health;
        this.currentHealth = health;
    }

    public void hit(double damage, String spell){
        Random rand = new Random();
        if(rand.nextFloat()<probOfProtego){ // first we see if the hit was blocked
            System.out.println("Protego !");
        }
        else{ // if we have some resistance to the spell we want to modify the value of the hit
            if (spellResistance.get(spell) != null)
                this.health -= damage * spellResistance.get(spell);
            else
                this.health -= damage;
        }
    }

    public void setSpellResistance(Hashtable<String, Double> spellResistance) {
        this.spellResistance = spellResistance;
    }

    public Hashtable<String, Double> getSpellResistance() {
        return spellResistance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }
    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        this.health = health;
    }
    public void setProbOfProtego(double probOfProtego){
        this.probOfProtego = probOfProtego;
    }


    public abstract void randomAttack(Character target);


}
