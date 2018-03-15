import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Highscore {

    public static void addNewScore(String playersName, int turns) {
        /**
         * Updates Highscores with new score
         */

        List<String> highscores = new ArrayList<String>();
        highscores = getHighscores();
        highscores.add(0, createPlayerScoreLine(playersName, turns));
        saveTop10Scores(highscores);

    }

    private static List<String> getHighscores() {
        /**
         * Loads highscores from file
         */
        List<String> highscores = new ArrayList<String>();
        File file = new File("scores.csv");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                highscores.add(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return highscores;
    }

    private static String createPlayerScoreLine(String name, int turns) {
        /**
         * Creates line to save with given name and turns
         */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String playerScoreLine = String.join(",", name, date, turns + "");

        return playerScoreLine;
    }

    private static void saveTop10Scores(List<String> highscores) {
        /**
         * Saves to file no more that 10 scores
         */

        highscores = sortHighscores(highscores);

        try {
            PrintWriter writer = new PrintWriter("scores.csv", "UTF-8");
            for (int i = 0; i < highscores.size() && i < 10; i++) {
                writer.println(highscores.get(i));
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static List<String> sortHighscores(List<String> highscores) {
        /**
         * Sorting algorithm for highscores
         */
        boolean notDone = true;

        while (notDone) {
            notDone = false;

            for (int i = 0; i < highscores.size() - 1; i++) {

                int turnsAtI = Integer.parseInt(highscores.get(i).split(",")[2]);
                int turnsAtNext = Integer.parseInt(highscores.get(i + 1).split(",")[2]);

                if (turnsAtNext < turnsAtI) {
                    notDone = true;
                    String temp = highscores.get(i);
                    highscores.set(i, highscores.get(i + 1));
                    highscores.set(i + 1, temp);
                }
            }
        }

        return highscores;
    }

    public static List<String> getDisplayableHighscoreList() {
        /**
         * Returns list of strings to display
         */
        List<String> highscores = getHighscores();
        List<String> displayable = new ArrayList<String>();

        for (String highscoreLine : highscores) {
            displayable.add(getDisplayableLine(highscoreLine));
        }

        return displayable;
    }

    private static String getDisplayableLine(String highscoreLine) {
        /**
         * Creates displayable line
         */

        String[] highscoreParts = highscoreLine.split(",");
        return String.join(" | ", highscoreParts);

    }
}