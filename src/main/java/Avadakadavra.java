

public class Avadakadavra extends AbstractSpell {
    public Avadakadavra() {
        this.name = "Avadakadavra";
    }
    @Override
    public void useSpell(Character target) {
        target.hit(1000, "Avadakadavra");
    }
}
