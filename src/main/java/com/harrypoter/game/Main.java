package com.harrypoter.game;

import com.harrypoter.game.UI.Game;

import java.util.Scanner;

public class Main {
    public static String name = "";
    public static void main(String[] args) {
        System.out.println("Hello wizard, welcome to hogwarts. Ready to discover the mysteries of magic? What is your name?");
        System.out.println("Choose a name");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        new Game();
    }
}
