

public class Stuppefix extends AbstractSpell {
    public Stuppefix() {
        this.name = "Stuppefix";
    }
    @Override
    public void useSpell(Character target) {
        target.hit(0.2, "Stuppefix");
    }
}

