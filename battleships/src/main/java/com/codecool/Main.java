package com.codecool;

import com.codecool.view.BattleshipFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                BattleshipFrame battleshipFrame = new BattleshipFrame();
                battleshipFrame.setTitle("Battleship");
                battleshipFrame.setLocationRelativeTo(null);
                battleshipFrame.setResizable(false);
                battleshipFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                battleshipFrame.setVisible(true);
            }

        });
    }
}
