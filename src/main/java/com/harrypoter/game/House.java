package com.harrypoter.game;

public class House {
    private static String name;
    private int index;
    private String[] names = {"Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin"};

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public House(int index) {
        this.index = index;
        this.name = names[index];
    }

}
