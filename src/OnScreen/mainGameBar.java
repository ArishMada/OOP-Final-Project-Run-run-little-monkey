package OnScreen;
import scenes.MainGame;
import java.awt.*;

import static main.GameState.Menu;
import static main.GameState.SetGameState;

public class mainGameBar extends Bar{
    private OnScreenBtn menuBtn, restartBtn;
    private MainGame mainGame;


    public mainGameBar(int x, int y, int width, int height, MainGame mainGame) {
        super(x, y, width, height);
        this.mainGame = mainGame;

        initalizeBtn();
    }

    private void initalizeBtn() {
        menuBtn = new OnScreenBtn(0, 640, 100, 60, "Main Menu");
        restartBtn = new OnScreenBtn(924, 640, 100, 60, "Restart");
    }

    private void drawBtns(Graphics g) {
        menuBtn.draw(g);
        restartBtn.draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(x, y, width, height);
        drawBtns(g);
    }

    public void mouseClicked(int x, int y) {
        mainGame.game.getSound().setFile(1);
        if (menuBtn.getBoundaries().contains(x, y)){
            mainGame.game.getSound().play();
            mainGame.game.getMusic().stop();
            mainGame.game.getMusic().setFile(1);
            mainGame.game.getMusic().loop();
            SetGameState(Menu);
        } else if (restartBtn.getBoundaries().contains(x,y)){
            mainGame.game.getSound().play();
            mainGame.reset();
        }
    }


    public void mouseMoved(int x, int y) {
        menuBtn.hovering(false); //always reset the hover
        restartBtn.hovering(false);
        if (menuBtn.getBoundaries().contains(x, y)) {
            menuBtn.hovering(true);
        } else if (restartBtn.getBoundaries().contains(x,y)) {
            restartBtn.hovering(true);
        }
    }


    public void mousePressed(int x, int y) {
        if (menuBtn.getBoundaries().contains(x, y)) {
            menuBtn.pressed(true);
        } else if (restartBtn.getBoundaries().contains(x,y)) {
            restartBtn.pressed(true);
        }
    }


    public void mouseReleased(int x, int y) {
        menuBtn.reset();
        restartBtn.reset();
    }
}
