package helperMethods;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class ImageChange {
    //rotating an image
    public static BufferedImage getRotatedImage(BufferedImage img, int angle) {
        int w =img.getWidth();
        int h = img.getHeight();

        BufferedImage newImg = new BufferedImage(w, h, img.getType());
        Graphics2D g2d = newImg.createGraphics(); //allow scaling and rotating
        g2d.rotate(Math.toRadians(angle), w/2, h/2); //spot of rotation will be in the middle of the image
        g2d.drawImage(img, 0, 0, null); //draw the old image on top of the new
        g2d.dispose(); // get rid of the memory used

        return newImg;
    }

    //mix two sprites to allow props
    public static BufferedImage makeProps(BufferedImage[] imgs) {
        int w =imgs[0].getWidth();
        int h = imgs[0].getHeight();

        BufferedImage newImg = new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = newImg.createGraphics();
        for(BufferedImage img : imgs){
            g2d.drawImage(img, 0, 0, null); // one image on top of the other
        }
        g2d.dispose();
        return newImg;
    }

}
