package scenes;


import Objects.PathPoint;
import Objects.Tile;
import OnScreen.editingBar;
import helperMethods.loadSave;
import main.Game;

import java.awt.*;
import java.util.ArrayList;

public class Editing extends GameScene implements SceneMethods {

    private int[][] level;
    private Tile tileToBePlaced;
    private int mouseXcor, mouseYcor, lastTileX, lasTileY, lasTileId;
    private boolean drawnTileToBePlaced;
    private editingBar editingBar;
    private PathPoint start, end;

    public Editing(Game game) {
        super(game);
        loadLvl(); //load the default level
        editingBar = new editingBar(0, 640, 1024, 60, this);
    }

    public void loadLvl() {
        level = loadSave.getLevelData("new_level");
        ArrayList<PathPoint> pathPoints = loadSave.getLevelPathpoints("new_level");
        start = pathPoints.get(0);
        end = pathPoints.get(1);
    }

    @Override
    public void render(Graphics g) {
        for(int y = 0; y < level.length; y++){
            for(int x = 0; x < level[y].length; x++){
                int lvlId = level[y][x];
                g.drawImage(game.getTileManager().getSprite(lvlId), x*32, y*32, null);
            }
        }
        editingBar.draw(g);
        drawTileToBePlaced(g);
        drawPathPoint(g);
    }

    private void drawPathPoint(Graphics g) {
        if (start != null) { //
            g.drawImage(editingBar.getStart(), start.getX()*32, start.getY()*32, 32, 32, null);
        }

        if (end != null) {
            g.drawImage(editingBar.getEnd(), end.getX()*32, end.getY()*32, 32, 32, null);
        }
    }

    private void drawTileToBePlaced(Graphics g) {
        if (tileToBePlaced != null && drawnTileToBePlaced){
            g.drawImage(tileToBePlaced.getSprite(), mouseXcor, mouseYcor, 32, 32, null); //draw the selected tile where the mouse is
        }
    }

    public void saveLvl(){
        loadSave.SaveF("new_level", level, start, end);
        game.getMainGame().setLevel(level);
        game.getWiningScreen().setLevel(level);
        game.getMainGame().setPathPoints(start, end);
        game.getWiningScreen().setPoints(start, end);
    }

    public void setTileToBePlaced(Tile tile){
        this.tileToBePlaced = tile;
        this.drawnTileToBePlaced = true;
    }

    private void changeTile(int x, int y){
        if (tileToBePlaced != null) {
            int tileXcor = x / 32;
            int tileYcor = y / 32;

            if (tileToBePlaced.getId() > -1) { //if the selected is the end/start or not
                if(lastTileX == tileXcor && lasTileY == tileYcor && lasTileId == tileToBePlaced.getId()) {
                    return;
                }
                lastTileX = tileXcor;
                lasTileY = tileYcor;
                lasTileId = tileToBePlaced.getId();

                level[tileYcor][tileXcor] = tileToBePlaced.getId();
            } else {
                int id = level[tileYcor][tileXcor];
                if (5 < id && id < 17) { // if it's a road
                    if(tileToBePlaced.getId() == -1) {
                        if (tileXcor != end.getX() || tileYcor != end.getY()) { // to make sure hte end and start statement won't be on the same spot
                            start = new PathPoint(tileXcor, tileYcor);// to make sure we have only one end point and one starting point
                        }
                    } else {
                        if (tileXcor != start.getX() || tileYcor != start.getY()) {
                            end = new PathPoint(tileXcor, tileYcor);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            editingBar.mouseClicked(x, y);
        } else {
            changeTile(mouseXcor, mouseYcor);
        }
    }
    public void mouseClicked2(int x, int y) {
        editingBar.rotateSprite();
    }


    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            editingBar.mouseMoved(x, y);
            drawnTileToBePlaced = false;
        } else {
            drawnTileToBePlaced = true;
            mouseXcor = (x/32) * 32; //to make the program show where a tile could be placed by sticking it there
            mouseYcor = (y/32) *32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            editingBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
            editingBar.mouseReleased(x, y);
    }


    public void mouseDragged(int x, int y) {
        if ( y < 640 && y > 0 && x < 1024 && x > 0) {
            changeTile(x, y);
        }
    }
}
