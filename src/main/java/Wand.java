import enums.Core;

public class Wand {
    private Core core;
    private int length;

    public Wand(int core, int length) {
        this.core = Core.values()[core];
        this.length = length;
    }
}
