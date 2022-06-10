package inputs;

import main.Game;
import main.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private Game game;

    public MouseInputs(Game g){
        this.game = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            switch (GameState.gameState) {
                case Menu:
                    game.getMenu().mouseClicked(e.getX(), e.getY());
                break;
                case MainGame:
                    game.getMainGame().mouseClicked(e.getX(), e.getY());
                    break;
                case Editing:
                    game.getEditing().mouseClicked(e.getX(), e.getY());
                    break;
                case WiningScreen:
                    game.getWiningScreen().mouseClicked(e.getX(), e.getY());
                    break;
                case Instructions:
                    game.getInstructions().mouseClicked(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        } else if(e.getButton() == MouseEvent.BUTTON3){
            switch (GameState.gameState) {
                case Editing:
                    game.getEditing().mouseClicked2(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.gameState) {
            case Menu:
                game.getMenu().mousePressed(e.getX(), e.getY());
                break;
            case MainGame:
                game.getMainGame().mousePressed(e.getX(), e.getY());
                break;
            case Editing:
                game.getEditing().mousePressed(e.getX(), e.getY());
                break;
            case WiningScreen:
                game.getWiningScreen().mousePressed(e.getX(), e.getY());
                break;
            case Instructions:
                game.getInstructions().mousePressed(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.gameState) {
            case Menu:
                game.getMenu().mouseReleased(e.getX(), e.getY());
                break;
            case MainGame:
                game.getMainGame().mouseReleased(e.getX(), e.getY());
                break;
            case Editing:
                game.getEditing().mouseReleased(e.getX(), e.getY());
                break;
            case WiningScreen:
                game.getWiningScreen().mouseReleased(e.getX(), e.getY());
                break;
            case Instructions:
                game.getInstructions().mouseReleased(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameState.gameState) {
            case Editing:
                game.getEditing().mouseDragged(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
            switch (GameState.gameState) {
                case Menu:
                    game.getMenu().mouseMoved(e.getX(), e.getY());
                    break;
                case MainGame:
                    game.getMainGame().mouseMoved(e.getX(), e.getY());
                    break;
                case Editing:
                    game.getEditing().mouseMoved(e.getX(), e.getY());
                    break;
                case WiningScreen:
                    game.getWiningScreen().mouseMoved(e.getX(), e.getY());
                    break;
                case Instructions:
                    game.getInstructions().mouseMoved(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }
    }
