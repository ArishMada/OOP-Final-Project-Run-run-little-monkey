package scenes;

import OnScreen.OnScreenBtn;
import helperMethods.loadSave;
import main.Game;
import java.awt.*;
import java.awt.image.BufferedImage;
import static main.GameState.*;

public class Menu extends GameScene implements SceneMethods {

    private BufferedImage Background;

    private OnScreenBtn btnPlay, btnEditing, btnInstructions, btnQuit;

    private Font titleFont;

    public Menu(Game game) {
        super(game);
        initializeBtns();

        titleFont = new Font("AvantGarde", Font.BOLD, 70);
    }

    private void initializeBtns() {
        int w = 204;
        int h = 60;
        int x1 = (1024 / 4) - w / 2;//first column of buttons
        int x2 = 3 * (1024 / 4) - w / 2; //second column
        int x3 = 1024 / 2 - w / 2; //middle
        int y = 300;
        int inBetween = 100;

        //first line
        btnPlay = new OnScreenBtn(x1, y, w, h, "Play");
        btnEditing = new OnScreenBtn(x2, y, w, h, "Edit");

        //second line
        btnInstructions = new OnScreenBtn(x3, y + inBetween, w, h, "Instructions");

        //third line
        btnQuit = new OnScreenBtn(x3, y + inBetween * 2, w, h, "Quit");

    }

    @Override
    public void render(Graphics g) {
        Background = loadSave.getBackground();
        g.drawImage(Background, 0, 0, null);
        btnPlay.draw(g, 15);
        btnInstructions.draw(g, 15);
        btnQuit.draw(g, 15);
        btnEditing.draw(g, 15);

        g.setFont(titleFont);
        g.setColor(Color.black);
        g.drawString("Run Run little monkey", 150, 250);
    }

    @Override
    public void mouseClicked(int x, int y) {
        game.getSound().setFile(1);
        if (btnPlay.getBoundaries().contains(x, y)) {
            game.getMainGame().reset(); // reset the timer and the player position
            game.getMusic().stop();
            game.getMusic().setFile(0);
            game.getMusic().loop();
            game.getSound().play();
            SetGameState(MainGame);
        } else if (btnEditing.getBoundaries().contains(x, y)) {
            game.getMusic().stop();
            game.getSound().play();
            SetGameState(Editing);
        }else if (btnInstructions.getBoundaries().contains(x, y)) {
            game.getSound().play();
            SetGameState(Instructions);
        } else if (btnQuit.getBoundaries().contains(x, y)) {
            game.getSound().play();
            System.exit(0);
        }

    }

    @Override
    public void mouseMoved(int x, int y) {
        //always reset the hover
        btnPlay.hovering(false);
        btnInstructions.hovering(false);
        btnQuit.hovering(false);
        btnEditing.hovering(false);

        if (btnPlay.getBoundaries().contains(x, y)) {
            btnPlay.hovering(true);
        } else if (btnEditing.getBoundaries().contains(x, y)){
            btnEditing.hovering(true);
        } else if (btnInstructions.getBoundaries().contains(x, y)) {
            btnInstructions.hovering(true);
        } else if (btnQuit.getBoundaries().contains(x, y)) {
            btnQuit.hovering(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (btnPlay.getBoundaries().contains(x, y)) {
            btnPlay.pressed(true);
        } else if (btnEditing.getBoundaries().contains(x, y)) {
            btnEditing.pressed(true);
        } else if (btnInstructions.getBoundaries().contains(x, y)) {
            btnInstructions.pressed(true);
        } else if (btnQuit.getBoundaries().contains(x, y)) {
            btnQuit.pressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        btnPlay.reset();
        btnInstructions.reset();
        btnQuit.reset();
        btnEditing.reset();
    }
}
