package main;

import java.awt.*;

public class Rendering {
    private Game game;

    public Rendering(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {
        switch(GameState.gameState) {
            case Menu:
                game.getMenu().render(g);
                break;
            case MainGame:
                game.getMainGame().render(g);
                break;
            case Editing:
                game.getEditing().render(g);
                break;
            case WiningScreen:
                game.getWiningScreen().render(g);
                break;
            case Instructions:
                game.getInstructions().render(g);
                break;
        }
    }
}
