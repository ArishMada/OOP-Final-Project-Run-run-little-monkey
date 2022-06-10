package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private Dimension size;
    private Game game;

    private MouseInputs myMouseListener;
    private KeyboardInputs myKeyboardListener;


    public GameScreen(Game game) {
        this.game = game;

        setScreenSize();
    }

    public void initializeInputs() {
        myKeyboardListener = new KeyboardInputs(game);
        myMouseListener = new MouseInputs(game);

        addKeyListener(myKeyboardListener);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);

        requestFocus(); //focus on the inputs
    }

    private void setScreenSize() {
        size = new Dimension(1024, 700); // to set the size of the screen
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.getRender().render(g);
    }
}
