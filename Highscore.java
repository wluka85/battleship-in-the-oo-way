import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Highscore {

    public void manageHighscore(String playersName, int points) {

        List<String> highscores = new ArrayList<String>();
        highscores = getHighscores();
        highscores = addCurrentScore(playersName, points);
        saveTop10Scores(highscores);

    }

    private List<String> getHighscores() {
        List<String> highscores = new ArrayList<String>();
        File file = new File("scores.csv");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(text);
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
}