public class House {
    private String name;
    private int index;
    private String[] names = {"Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin"};

    public House(int index) {
        this.index = index;
        this.name = names[index];
    }

}
