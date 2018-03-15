import java.awt.*;
import javax.swing.*;


public class HighScoreDialog extends JDialog {


    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;
    private JTextArea highScoreTextArea;



    public HighScoreDialog(JFrame owner, String highScore) {
        super(owner, "Choose game mode", true);
        setLayout(new BorderLayout());
        highScoreTextArea = new JTextArea();
        highScoreTextArea.setText(highScore);
        highScoreTextArea.setEditable(false);
        add(highScoreTextArea, BorderLayout.CENTER);
        setSize(getPrefferedSize());
        setVisible(true);
    }

    public Dimension getPrefferedSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
