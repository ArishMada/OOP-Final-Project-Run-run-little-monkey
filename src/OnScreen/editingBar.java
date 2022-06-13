package OnScreen;

import Objects.Tile;
import helperMethods.loadSave;
import scenes.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static main.GameState.Menu;
import static main.GameState.SetGameState;

public class editingBar extends Bar {
    private Editing editing;
    private OnScreenBtn menuBtn, saveBtn, grassBtn, waterBtn, sandBtn, lavaBtn, roadBtn, cornerBtn, rockBtn, graveBtn;
    private OnScreenBtn endBtn, startBtn, threeWaysBtn, twoWaysBtn, chosenBtn;

    private Tile tileToBePlaced;
    private int currentIndex = 0;

    private BufferedImage start, end; //images for the special buttons end and start

    // to access the tiles that can be rotated
    private Map<OnScreenBtn, ArrayList<Tile>> tileMap = new HashMap<>();

    public editingBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;

        initalizeBtn();
        initSpecialBtns();
    }

    private void initSpecialBtns() {
        start = loadSave.getStartImg();
        end = loadSave.getEndImg();
    }

    private void initalizeBtn() {
        menuBtn = new OnScreenBtn(0, 640, 100, 60, "Main Menu");
        saveBtn = new OnScreenBtn(924, 640, 100, 60, "Save");

        int w = 40;
        int h = 40;
        int OriginX = 120;
        int OriginY = 645;
        int inBetween = (int) (w * 1.1f);

        int n = 0;
//        for(Tile tile : editing.getGame().getTileManager().tiles){
//            tileButtons.add(new OnScreenBtn(OriginX + inBetween * n, OriginY, w, h, null, n));
//            n++;
//        }
        grassBtn = new OnScreenBtn(OriginX, OriginY, w, h, "grass", n++);
        waterBtn = new OnScreenBtn(OriginX + inBetween, OriginY, w, h, "water", n++);
        sandBtn = new OnScreenBtn(OriginX + inBetween * 2, OriginY, w, h, "sand", n++);
        lavaBtn = new OnScreenBtn(OriginX + inBetween * 3, OriginY, w, h, "lava", n++);
        rockBtn = new OnScreenBtn(OriginX + inBetween * 4, OriginY, w, h, "rock", n++);
        graveBtn = new OnScreenBtn(OriginX + inBetween * 5, OriginY, w, h, "grave", n++);
        threeWaysBtn = new OnScreenBtn(OriginX + inBetween * 6, OriginY, w, h, "3ways", n++);

        initTileButton(roadBtn, editing.getGame().getTileManager().getRoads(), OriginX, OriginY, inBetween, w, h, n++, "road"); // the roads as a button
        initTileButton(cornerBtn, editing.getGame().getTileManager().getCorners(), OriginX, OriginY, inBetween, w, h, n++, "corner"); // the corners as a btn
        initTileButton(twoWaysBtn, editing.getGame().getTileManager().getTwoWays(), OriginX, OriginY, inBetween, w, h, n++, "2ways"); // two ways intersection button

        startBtn = new OnScreenBtn(OriginX + inBetween * 11, OriginY, w, h, "start", n++);
        endBtn = new OnScreenBtn(OriginX + inBetween * 12, OriginY, w, h, "end", n++);
    }

    private void initTileButton(OnScreenBtn btn1, ArrayList<Tile> list, int x, int y, int inBtw, int w, int h, int id, String name) {
        btn1 = new OnScreenBtn(x + inBtw * id, y, w, h, name, id);
        tileMap.put(btn1, list);
    }

    public void saveLevel() {
        editing.saveLvl();
    }

    public void draw(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(x, y, width, height);
        drawBtns(g);
    }

    private void drawBtns(Graphics g) {
        menuBtn.draw(g);
        saveBtn.draw(g);

        drawSpecialBtns(g, startBtn, start);
        drawSpecialBtns(g, endBtn, end);

        drawSingleBtn(g, grassBtn); //draw single direction buttons
        drawSingleBtn(g, waterBtn);
        drawSingleBtn(g, sandBtn);
        drawSingleBtn(g, lavaBtn);
        drawSingleBtn(g, rockBtn);
        drawSingleBtn(g, graveBtn);
        drawSingleBtn(g, threeWaysBtn);

        drawMapBtns(g);

        drawTileToBePlaced(g);
    }

    private void drawSpecialBtns(Graphics g, OnScreenBtn btn, BufferedImage img) {
        g.drawImage(img, btn.getBoundaries().x, btn.getBoundaries().y, btn.getBoundaries().width, btn.getBoundaries().height, null);
        buttonEffect(g, btn);
    }

    private void drawSingleBtn(Graphics g, OnScreenBtn btn) {
        g.drawImage(getBuffImg(btn.getId()), btn.getBoundaries().x, btn.getBoundaries().y, btn.getBoundaries().width, btn.getBoundaries().height, null);
        buttonEffect(g, btn);
        }

    private void drawMapBtns(Graphics g) {
        for (Map.Entry<OnScreenBtn, ArrayList<Tile>> entry : tileMap.entrySet()) { //return key value pairs
            OnScreenBtn btn = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite(); // get the image of the first element

            g.drawImage(img, btn.getBoundaries().x, btn.getBoundaries().y, btn.getBoundaries().width, btn.getBoundaries().height, null);

            buttonEffect(g, btn);
        }
    }

    private void buttonEffect(Graphics g, OnScreenBtn btn) {
        //effect when hovered
        if (btn.isHovered()) {
            g.setColor(Color.white);
            g.drawString(btn.getText(), btn.getBoundaries().x + 5, 696);
            g.setColor(Color.white);
        } else {
            g.setColor(Color.black);
        }
        g.drawRect(btn.getBoundaries().x, btn.getBoundaries().y, btn.getBoundaries().width, btn.getBoundaries().height);

        //effect when clicked
        if (btn.isPressed()) {
            g.setColor(Color.white);
            g.drawRect(btn.getBoundaries().x - 1, btn.getBoundaries().y - 1, btn.getBoundaries().width + 2, btn.getBoundaries().height + 2);
            g.drawRect(btn.getBoundaries().x - 2, btn.getBoundaries().y - 2, btn.getBoundaries().width + 4, btn.getBoundaries().height + 4);
        }
    }

    private void drawTileToBePlaced(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Selected:", 800, 675);
        if (tileToBePlaced != null) {
            g.drawImage(tileToBePlaced.getSprite(), 850, 650, 40, 40, null);
            g.setColor(Color.black);
            g.drawRect(850, 650, 40, 40);
        }
    }

    public BufferedImage getBuffImg(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

    public void rotateSprite() { //rotating will just mean going to the next index in the array
        if (tileMap.containsKey(chosenBtn)) {
            currentIndex++;
            if (currentIndex >= tileMap.get(chosenBtn).size()) { //if the index goes out of bounds set it back to 0
                currentIndex = 0;
            }
            tileToBePlaced = tileMap.get(chosenBtn).get(currentIndex);
            editing.setTileToBePlaced(tileToBePlaced);
        }
    }

    public void mouseClicked(int x, int y) {
        chosenBtn = null;
        tileToBePlaced = null;
        editing.setTileToBePlaced(null);
        if (menuBtn.getBoundaries().contains(x, y)){
            editing.loadLvl(); // reset the tiles as per the file if the data are not saved
            SetGameState(Menu);
            editing.game.getSound().setFile(2);
            editing.game.getSound().loop();
        } else if (saveBtn.getBoundaries().contains(x,y)){
            saveLevel();
        } else if (grassBtn.getBoundaries().contains(x, y)){
            chosenBtn = grassBtn;
            tileToBePlaced = editing.getGame().getTileManager().getTile(grassBtn.getId());
            editing.setTileToBePlaced(tileToBePlaced);
        } else if (waterBtn.getBoundaries().contains(x, y)) {
            chosenBtn = waterBtn;
            tileToBePlaced = editing.getGame().getTileManager().getTile(waterBtn.getId());
            editing.setTileToBePlaced(tileToBePlaced);
        } else if (sandBtn.getBoundaries().contains(x, y)) {
            chosenBtn = sandBtn;
            tileToBePlaced = editing.getGame().getTileManager().getTile(sandBtn.getId());
            editing.setTileToBePlaced(tileToBePlaced);
        } else if (lavaBtn.getBoundaries().contains(x, y)) {
            chosenBtn = lavaBtn;
            tileToBePlaced = editing.getGame().getTileManager().getTile(lavaBtn.getId());
            editing.setTileToBePlaced(tileToBePlaced);
        } else if (rockBtn.getBoundaries().contains(x, y)) {
            chosenBtn = rockBtn;
            tileToBePlaced = editing.getGame().getTileManager().getTile(rockBtn.getId());
            editing.setTileToBePlaced(tileToBePlaced);
        } else if (graveBtn.getBoundaries().contains(x, y)) {
            chosenBtn = graveBtn;
            tileToBePlaced = editing.getGame().getTileManager().getTile(graveBtn.getId());
            editing.setTileToBePlaced(tileToBePlaced);
        } else if (threeWaysBtn.getBoundaries().contains(x, y)) {
            chosenBtn = threeWaysBtn;
            tileToBePlaced = editing.getGame().getTileManager().getTile(threeWaysBtn.getId());
            editing.setTileToBePlaced(tileToBePlaced);

        } else if (startBtn.getBoundaries().contains(x, y)) {
            chosenBtn = startBtn;
            tileToBePlaced = new Tile(start, -1, -1);
            editing.setTileToBePlaced(tileToBePlaced);
        } else if (endBtn.getBoundaries().contains(x, y)) {
            chosenBtn = endBtn;
            tileToBePlaced = new Tile(end, -2, -2);
            editing.setTileToBePlaced(tileToBePlaced);
        }
        else {
            for (OnScreenBtn btn : tileMap.keySet()){
                if (btn.getBoundaries().contains(x, y)) {
                    chosenBtn = btn;
                    tileToBePlaced = tileMap.get(btn).get(0);
                    editing.setTileToBePlaced(tileToBePlaced);
                    currentIndex = 0;
                }
            }
        }
    }


    public void mouseMoved(int x, int y) {
        //always reset the hover
        menuBtn.hovering(false);
        saveBtn.hovering(false);
        grassBtn.hovering(false);
        waterBtn.hovering(false);
        sandBtn.hovering(false);
        lavaBtn.hovering(false);
        rockBtn.hovering(false);
        graveBtn.hovering(false);
        threeWaysBtn.hovering(false);

        startBtn.hovering(false);
        endBtn.hovering(false);

        for (OnScreenBtn btn : tileMap.keySet()) {
            btn.hovering(false);
        }

        if (menuBtn.getBoundaries().contains(x, y)) {
            menuBtn.hovering(true);
        } else if (saveBtn.getBoundaries().contains(x,y)) {
            saveBtn.hovering(true);
        } else if (grassBtn.getBoundaries().contains(x, y)){
            grassBtn.hovering(true);
        } else if (waterBtn.getBoundaries().contains(x, y)) {
            waterBtn.hovering(true);
        } else if (sandBtn.getBoundaries().contains(x, y)) {
            sandBtn.hovering(true);
        } else if (lavaBtn.getBoundaries().contains(x, y)) {
            lavaBtn.hovering(true);
        } else if (rockBtn.getBoundaries().contains(x, y)) {
            rockBtn.hovering(true);
        } else if (graveBtn.getBoundaries().contains(x, y)) {
            graveBtn.hovering(true);
        } else if (threeWaysBtn.getBoundaries().contains(x, y)) {
            threeWaysBtn.hovering(true);
        } else if (startBtn.getBoundaries().contains(x, y)) {
            startBtn.hovering(true);
        } else if (endBtn.getBoundaries().contains(x, y)) {
            endBtn.hovering(true);
        } else {
            for (OnScreenBtn btn : tileMap.keySet()) {
                if (btn.getBoundaries().contains(x, y)) {
                    btn.hovering(true);
                }
            }
        }
    }


    public void mousePressed(int x, int y) {
        if (menuBtn.getBoundaries().contains(x, y)) {
            menuBtn.pressed(true);
        } else if (saveBtn.getBoundaries().contains(x,y)) {
            saveBtn.pressed(true);
        } else if (grassBtn.getBoundaries().contains(x, y)){
            grassBtn.pressed(true);
        } else if (waterBtn.getBoundaries().contains(x, y)) {
            waterBtn.pressed(true);
        } else if (sandBtn.getBoundaries().contains(x, y)) {
            sandBtn.pressed(true);
        } else if (lavaBtn.getBoundaries().contains(x, y)) {
            lavaBtn.pressed(true);
        } else if (rockBtn.getBoundaries().contains(x, y)) {
            rockBtn.pressed(true);
        } else if (graveBtn.getBoundaries().contains(x, y)) {
            graveBtn.pressed(true);
        } else if (threeWaysBtn.getBoundaries().contains(x, y)) {
            threeWaysBtn.pressed(true);
        } else if (startBtn.getBoundaries().contains(x, y)){
            startBtn.pressed(true);
        }else if (endBtn.getBoundaries().contains(x, y)) {
            endBtn.pressed(true);
        } else {
            for (OnScreenBtn btn : tileMap.keySet()) {
                if (btn.getBoundaries().contains(x, y)) {
                    btn.pressed(true);
                }
            }
        }
    }


    public void mouseReleased(int x, int y) {
        menuBtn.reset();
        saveBtn.reset();
        grassBtn.reset();
        waterBtn.reset();
        sandBtn.reset();
        lavaBtn.reset();
        rockBtn.reset();
        graveBtn.reset();
        threeWaysBtn.reset();
        startBtn.reset();
        endBtn.reset();

        for (OnScreenBtn btn : tileMap.keySet()) {
            btn.reset();
        }
    }

    // in order to be able to use these images in editing
    public BufferedImage getStart() {
        return start;
    }

    public BufferedImage getEnd() {
        return end;
    }
}
