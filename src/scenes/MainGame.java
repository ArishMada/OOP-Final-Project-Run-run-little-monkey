package scenes;

import Objects.PathPoint;
import Objects.Player;
import OnScreen.mainGameBar;
import helperMethods.loadSave;
import main.Game;

import java.awt.*;

import java.util.ArrayList;

import static helperMethods.constant.Directions.*;


public class MainGame extends GameScene implements SceneMethods {

    private int[][] level;
    private mainGameBar bottomMainGameBar;

    private Player player;
    private PathPoint start, end;
    private long startTime;
    private long elapsedTime, elapsedSeconds,  secondsDisplay, elapsedMinutes;
    private Font textFont;

//    private int widthFromPlayerToEnd;
//    private int heightFromPlayerToEnd;

    public MainGame(Game game) {
        super(game);
        loadLvl(); //load the default level
        bottomMainGameBar = new mainGameBar(0, 640, 1024, 60, this);
        player = new Player(this);

        startTime = System.currentTimeMillis();
        textFont = new Font("SansSerif", Font.BOLD, 16);
    }



    private void loadLvl() {
        level = loadSave.getLevelData("new_level");
        ArrayList<PathPoint> pathPoints = loadSave.getLevelPathpoints("new_level");
        start = pathPoints.get(0);
        end = pathPoints.get(1);
    }

    public void setLevel(int[][] lvl){
        this.level = lvl;
    }

    public void setPathPoints(PathPoint start, PathPoint end) {
        this.start = start;
        this.end = end;
    }

    public void update() {
        player.update();
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = elapsedTime / 1000;
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
    }

    public long getFinalTime(){
        return elapsedSeconds;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1024, 640);
        int lvlID, nextID, prevID;
        if (player.getPlayerDirection() == DOWN) {
            lvlID = level[(player.getY()+18)/32][(player.getX() + 15)/32];
        } else if (player.getPlayerDirection() == RIGHT) {
            lvlID = level[(player.getY()+18)/32][(player.getX() + 15)/32];
        } else if (player.getPlayerDirection() == LEFT) {
            lvlID = level[(player.getY()+18)/32][(player.getX() + 13)/32];
        } else {
            lvlID = level[(player.getY() + 15)/32][(player.getX()+10)/32];
        }

        g.drawImage(game.getTileManager().getSprite(lvlID), player.getX(), player.getY(), null);
//
//        for(int y = 0; y < level.length; y++){
//            for(int x = 0; x < level[y].length; x++){
//                int lvlId = level[y][x];
//                g.drawImage(game.getTileManager().getSprite(lvlId), x*32, y*32, null);
//            }
//        }

//        if (player.getPlayerDirection() == DOWN || player.getPlayerDirection() == RIGHT) {
//            g.drawRect(player.getX() + 15, player.getY()+18, 32, 32);
//        } else if (player.getPlayerDirection() == LEFT) {
//           g.drawRect(player.getX() + 13, player.getY()+18, 32, 32);
//        } else {
//            g.drawRect(player.getX()+ 10, player.getY() + 15, 32, 32);
//        }

//        for(int y = 0; y < player.getY()-32; y++) {
//            for (int x = 0; x < player.getX()-32; x++) {
//                System.out.println(x + " " + y);
//                g.fillRect(x, y, 32, 32);
//            }
//        }

        bottomMainGameBar.draw(g);

        player.render(g);

        g.setColor(Color.white);
        g.setFont(textFont);
        if (secondsDisplay < 10) {
            g.drawString(elapsedMinutes + ":0" + secondsDisplay, 980, 15);
        } else {
            g.drawString(elapsedMinutes + ":" + secondsDisplay, 980, 15);
        }

    }

    public Player getPlayer() {
        return player;
    }

    public int getTileType(int x, int y) {
        if (x <= 1024 && x >= 0 && y <= 640 && y >= 16) {
            int id = level[y / 32][x / 32];
//            System.out.println("y: " + y/32);
//            System.out.println("x: " + x/32);
            return game.getTileManager().getTile(id).getType();
        } else {
            return 0;
        }
    }

    public void reset(){
        player.resetPos();
        startTime = System.currentTimeMillis();
    }

    public PathPoint getStart() {
        return start;
    }

    public PathPoint getEnd() {
        return end;
    }

    //we only need to check the bottom bar because that's where the buttons will be
    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            bottomMainGameBar.mouseClicked(x, y);
        }
    }


    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            bottomMainGameBar.mouseMoved(x, y);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            bottomMainGameBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
            bottomMainGameBar.mouseReleased(x, y);
    }
}
