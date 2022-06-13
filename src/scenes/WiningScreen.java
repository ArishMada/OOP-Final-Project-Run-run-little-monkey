package scenes;

import Objects.PathPoint;
import OnScreen.OnScreenBtn;
import helperMethods.loadSave;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameState.Menu;
import static main.GameState.SetGameState;

public class WiningScreen extends GameScene {

    private MainGame mainGame;
    private int[][] level;
    private OnScreenBtn menuBtn;
    private PathPoint start, end;

    private Font textFont1 = new Font("SansSerif", Font.BOLD, 30);
    private Font textFont2 = new Font("SansSerif", Font.BOLD, 18);
    private long finalTime;

    private BufferedImage startImg, endImg;

    public WiningScreen(Game game) {
        super(game);
        mainGame = new MainGame(game);

        level = loadSave.getLevelData("new_level");
        ArrayList<PathPoint> pathPoints = loadSave.getLevelPathpoints("new_level");
        start = pathPoints.get(0);
        end = pathPoints.get(1);

        startImg = loadSave.getStartImg();
        endImg = loadSave.getEndImg();

        menuBtn = new OnScreenBtn(0, 640, 100, 60, "Main Menu");
    }

    public void render(Graphics g) {
        for(int y = 0; y < level.length; y++){
            for(int x = 0; x < level[y].length; x++){
                int lvlId = level[y][x];
                g.drawImage(game.getTileManager().getSprite(lvlId), x*32, y*32, null);
            }
        }

        g.setColor(Color.darkGray);
        g.fillRect(0, 640, 1024, 60);
        menuBtn.draw(g);

        g.setColor(Color.white);
        g.setFont(textFont1);
        g.drawString("CONGRATS YOU WON!", 350, 670);

        g.setFont(textFont2);

        finalTime = game.getMainGame().getPlayer().finalTime;
        long secondsDisplay = finalTime % 60;
        long elapsedMinutes = finalTime / 60;
        if (secondsDisplay < 10) {
            g.drawString("Your time: " + elapsedMinutes + ":0" + secondsDisplay, 450, 690);
        } else {
            g.drawString("Your time: " + elapsedMinutes + ":" + secondsDisplay, 450, 690);
        }
        drawPathPoint(g);
    }

    public void setLevel(int[][] lvl){
        this.level = lvl;
    }

    public void setPoints(PathPoint start, PathPoint end) {
        this.start = start;
        this.end = end;
    }

    private void drawPathPoint(Graphics g) {
        if (start != null) { //
            g.drawImage(startImg, start.getX()*32, start.getY()*32, 32, 32, null);
        }

        if (end != null) {
            g.drawImage(endImg, end.getX()*32, end.getY()*32, 32, 32, null);
        }
    }

    public void mouseClicked(int x, int y) {
        if (menuBtn.getBoundaries().contains(x, y)) {
            game.getSound().setFile(1);
            game.getSound().play();
            mainGame.reset(); //reset the position of the player
            SetGameState(Menu);
            game.getMusic().setFile(1);
            game.getMusic().loop();
        }
    }

    public void mouseMoved(int x, int y) {
            menuBtn.hovering(false); //always reset the hover
            if (menuBtn.getBoundaries().contains(x, y)) {
                menuBtn.hovering(true);
            }
        }

    public void mousePressed(int x, int y) {
        if (menuBtn.getBoundaries().contains(x, y)) {
            menuBtn.pressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        menuBtn.reset();
    }
}
