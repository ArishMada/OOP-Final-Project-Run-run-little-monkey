package OnScreen;

import java.awt.*;

public class OnScreenBtn {
    private int x, y, width, height, id;
    private String text;
    private Rectangle boundaries; //this class acts as a mouse position checker, it returns true if it is withing the boundaries;
    private boolean hovered, pressed;
    private Font textFont1;

    public OnScreenBtn(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.id = -500; //random value to avoid bugs

        this.textFont1 =  new Font("SansSerif", Font.BOLD, 18);

        this.boundaries = new Rectangle(x, y, width, height);
    }

    //overload for tile buttons
    public OnScreenBtn(int x, int y, int width, int height, String text, int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.id = id;

        this.boundaries = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g, int arc) {
        //background
        if(hovered){
            g.setColor(Color.gray); //change color when hovered
        } else {
            g.setColor(Color.yellow);
        }

        g.fillRoundRect(x, y, width, height, arc, arc);


        //border
        if (pressed) {
            g.setColor(Color.black);
            g.drawRoundRect(x, y, width, height, 15, 15);
            g.drawRoundRect(x-1, y-1, width+2, height+2, 13, 13);
            g.drawRoundRect(x-2, y-2, width+4, height+4, 11, 11);
        } else {
            g.setColor(Color.black);
            g.drawRoundRect(x, y, width, height, 15, 15);
        }

        //text
        g.setColor(Color.black);
        g.setFont(textFont1);
        int w = g.getFontMetrics().stringWidth(text); // get the text's width
        int h = g.getFontMetrics().getHeight(); // get the text's height
        g.drawString(text, x - (w / 2) + (width / 2), y + (height / 2) + (h/2) - 2); // draw text in the middle
    }

    public void draw(Graphics g) { // Overload to have different kind of buttons
        //background
        if(hovered){
            g.setColor(Color.gray); //change color when hovered
        } else {
            g.setColor(Color.white);
        }
        g.fillRect(x, y, width, height);


        //border
        if (pressed) {
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
            g.drawRect(x-1, y-1, width+2, height+2);
            g.drawRect(x-2, y-2, width+4, height+4);
        } else {
            g.setColor(Color.black);
            g.drawRect(x, y, width, height);
        }

        //text
        g.setColor(Color.black);
        int w = g.getFontMetrics().stringWidth(text); // get the text's width
        int h = g.getFontMetrics().getHeight(); // get the text's height
        g.drawString(text, x - (w / 2) + (width / 2), y + (height / 2) + 5); // draw text in the middle
    }

    public void reset() {
        this.pressed = false;
        this.hovered = false;
    }

    public Rectangle getBoundaries() {
        return boundaries;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public boolean isHovered() {
        return hovered;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void hovering(boolean hovered){
        this.hovered = hovered;
    }

    public void pressed(boolean pressed) {
        this.pressed = pressed;
    }
}
