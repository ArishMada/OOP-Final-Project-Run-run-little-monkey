package scenes;

import OnScreen.OnScreenBtn;
import helperMethods.loadSave;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.GameState.Menu;
import static main.GameState.SetGameState;

public class Instructions extends GameScene implements SceneMethods {

    private OnScreenBtn menuBtn;

    private Font titleFont;
    private Font subtitleFont;
    private Font textFont;

    public Instructions(Game game) {
        super(game);

        menuBtn = new OnScreenBtn(924, 640, 100, 60, "Main Menu");
        titleFont = new Font("SansSerif", Font.BOLD, 35);
        subtitleFont = new Font("SansSerif", Font.BOLD, 22);
        textFont = new Font("SansSerif", Font.ITALIC, 18);
    }



    @Override
    public void render(Graphics g) {
        for(int y = 0; y < 22; y++){
            for(int x = 0; x < 32; x++){
                g.drawImage(game.getTileManager().getSprite(0), x*32, y*32, null);
            }
        }

        //draw menu button
        menuBtn.draw(g);

        //draw title
        g.setColor(Color.black);
        g.setFont(titleFont);
        g.drawString("Instructions", 410, 60);

        //draw subtitle1
        g.setColor(Color.white);
        g.setFont(subtitleFont);
        g.drawString("Editing", 50, 120);

        //draw text1
        g.setFont(textFont);
        g.drawString("Set your level as you want using the different types of tiles", 50, 150);

        g.drawString("Grass: the basic tile, you cannot walk on it", 100, 190);
        g.drawRect(50, 165, 32, 32);

        g.drawString("Water: stepping on this tile will teleport you to a random walkable tile on the map", 100, 230);
        g.drawImage(getSprite(loadSave.getSpriteBeach(), 1, 2), 50, 205, 32, 32, null);

        g.drawString("Sand: stepping on this tile will slow you down", 100, 270);
        g.drawImage(getSprite(loadSave.getSpriteBeach(), 0, 2), 50, 245, 32, 32, null);

        g.drawString("Lava: stepping on this tile will teleport you back to the start point", 100, 310);
        g.drawImage(getSprite(loadSave.getSpriteGround(), 1, 8), 50, 285, 32, 32, null);

        g.drawString("Road: this is the normal where you have to walk", 100, 350);
        g.drawImage(getSprite(loadSave.getSpriteGround(), 6, 7), 50, 325, 32, 32, null);

        g.drawString("Rotating tiles: these are the other road tiles that can be rotated using the right button of your mouse", 100, 390);
        g.drawImage(getSprite(loadSave.getSpriteGround(), 4, 8), 50, 365, 32, 32, null);

        g.drawString("Props: these are only decoration for your level, the player will only see them at the end", 100, 430);
        g.drawImage(getSprite(loadSave.getSpriteProps(), 4, 15), 50, 405, 32, 32, null);

        g.drawString("Hit save and let your friend try your master piece", 50, 470);



        //draw subtitle2
        g.setColor(Color.white);
        g.setFont(subtitleFont);
        g.drawString("Main Game", 50, 520);

        //draw text2
        g.setFont(textFont);
        g.drawString("Use the arrows to move your little monkey", 50, 560);
        g.drawImage(loadSave.getSpritePlayer().getSubimage(16, 7*16, 16, 16), 560, 570, 40, 40, null);
        g.drawString("Your time is shown in the top right corner of the screen", 50, 600);
        g.drawString("The one and only rule, get out as soon as possible", 50, 640);
    }

    private BufferedImage getSprite(BufferedImage sprite, int x, int y){
        return sprite.getSubimage(x*32, y*32, 32, 32);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (menuBtn.getBoundaries().contains(x, y)){ //reset the position of the player
            SetGameState(Menu);
            game.getSound().setFile(2);
            game.getSound().loop();
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        menuBtn.hovering(false); //always reset the hover
        if (menuBtn.getBoundaries().contains(x, y)) {
            menuBtn.hovering(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (menuBtn.getBoundaries().contains(x, y)) {
            menuBtn.pressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        menuBtn.reset();
    }
}
