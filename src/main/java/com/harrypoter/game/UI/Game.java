package com.harrypoter.game.UI;

import javax.swing.*;

public class Game extends JFrame {

    public Game() {
        this.add(new Graphics());
        this.setTitle("Harry potteur at Home");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}