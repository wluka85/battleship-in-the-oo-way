import java.time.LocalDateTime;
public class Highscore {

    public void manageHighscore(String playersName, int points) {
        
        String[] highscores = getHighscores();
        highscores = addCurrentScore(playersName, points);
        saveTop10Scores(highscores);
    
    }

}