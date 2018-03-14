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

    
}