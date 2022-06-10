package inputs;

import main.Game;
import main.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameState.MainGame;
import static helperMethods.constant.Directions.*;


public class KeyboardInputs implements KeyListener {
    private Game game;

    public KeyboardInputs(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (GameState.gameState == MainGame) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    game.getMainGame().getPlayer().setPlayerDirection(UP);
                    break;
                case KeyEvent.VK_DOWN:
                    game.getMainGame().getPlayer().setPlayerDirection(DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    game.getMainGame().getPlayer().setPlayerDirection(LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    game.getMainGame().getPlayer().setPlayerDirection(RIGHT);
                    break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (GameState.gameState == MainGame) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    game.getMainGame().getPlayer().setMoving(false);
                    break;
            }
        }
    }
}
