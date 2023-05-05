package net.magnusfrater.ArpaSpeak;

import java.awt.*;
import java.io.InputStream;

public class ResourceLoader {

    static ResourceLoader rl = new ResourceLoader();

    public static Image getImage (String fileName) {
        return Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource("res/images/" + fileName));
    }

    public static InputStream getInputStream (String fileName) {
        return Toolkit.getDefaultToolkit().getClass().getResourceAsStream("res/sounds/" + fileName);
    }
}
