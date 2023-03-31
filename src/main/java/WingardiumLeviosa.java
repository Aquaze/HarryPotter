public class WingardiumLeviosa extends AbstractSpell {
    public WingardiumLeviosa() {
        this.name = "Wingardium Leviosa";
    }
    @Override
    public void useSpell(Character target) {
        target.hit(0.06, "WingardiumLeviosa");
        System.out.println(target.getName() + "has a rock falling towards him");

    }
}
