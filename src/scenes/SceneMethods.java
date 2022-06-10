package scenes;

import java.awt.*;

public interface SceneMethods { //to make sure all the scenes have the required methods
    void render(Graphics g);
    void mouseClicked(int x, int y);
    void mouseMoved(int x, int y);
    void mousePressed(int x, int y);
    void mouseReleased(int x, int y);
}
