package com.codecool.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class HighScore {

    static void addNewScore(String playersName, int turns) {
        List<String> highScores = getHighScores();
        highScores.add(0, createPlayerScoreLine(playersName, turns));
        saveTop10Scores(highScores);

    }

    private static List<String> getHighScores() {
        List<String> highScores = new ArrayList<>();
        File file = new File("scores.csv");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;

            while ((text = reader.readLine()) != null) {
                highScores.add(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return highScores;
    }

    private static String createPlayerScoreLine(String name, int turns) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        return String.join(",", name, date, turns + "");
    }

    private static void saveTop10Scores(List<String> highScores) {

        sortHighScores(highScores);

        try {
            PrintWriter writer = new PrintWriter("scores.csv", "UTF-8");
            for (int i = 0; i < highScores.size() && i < 10; i++) {
                writer.println(highScores.get(i));
            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static List<String> sortHighScores(List<String> highScores) {
        boolean notDone = true;

        while (notDone) {
            notDone = false;

            for (int i = 0; i < highScores.size() - 1; i++) {

                int turnsAtI = Integer.parseInt(highScores.get(i).split(",")[2]);
                int turnsAtNext = Integer.parseInt(highScores.get(i + 1).split(",")[2]);

                if (turnsAtNext < turnsAtI) {
                    notDone = true;
                    String temp = highScores.get(i);
                    highScores.set(i, highScores.get(i + 1));
                    highScores.set(i + 1, temp);
                }
            }
        }

        return highScores;
    }

    static List<String> getDisplayableHighScoreList() {
        List<String> highScores = getHighScores();
        List<String> displayable = new ArrayList<>();

        for (String highScoreLine : highScores) {
            displayable.add(getDisplayableLine(highScoreLine));
        }

        return displayable;
    }

    private static String getDisplayableLine(String highScoreLine) {
        String[] highScoreParts = highScoreLine.split(",");
        return String.join(" | ", highScoreParts);

    }
}