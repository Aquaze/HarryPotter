import enums.Core;
public class Wand {
    private static Core core;
    private static int length;

    public Wand(int core, int length) {
        this.core = Core.values()[core];
        this.length = length;
        System.out.println(core);
        System.out.println(length);
    }

    public static Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public static int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
