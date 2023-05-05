package net.magnusfrater.tds.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public BufferedImage load (String fileName) {
        try {

            return ImageIO.read(getClass().getResource(fileName));

        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe);
        }

        return null;
    }
}
