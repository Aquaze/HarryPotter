public class Hit extends AbstractSpell {
    public Stuppefix() {
        this.name = "Hit";
    }
    @Override
    public void useSpell(Character target) {
        target.hit(0.05, "Hit");
    }
}
