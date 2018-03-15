import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;
import java.time.LocalDate;
import java.util.NoSuchElementException;

public class HighScoreDialog extends JDialog {


    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;
    private JTextArea highScoreTextArea;



    public HighScoreDialog(JFrame owner, String highScore) {
        super(owner, "Choose game mode", true);
        setLayout(new BorderLayout());
        highScoreTextArea = new JTextArea();
        highScoreTextArea.setEditable(false);
        add(highScoreTextArea, BorderLayout.CENTER);
        setSize(getPrefferedSize());
        setVisible(true);
    }

    public Dimension getPrefferedSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
