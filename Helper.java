
import java.awt.*;
import java.awt.image.*;

public class Helper {
    public static BufferedImage applyOpacity(BufferedImage i, float a) {
        BufferedImage target = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = target.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a));
        g.drawImage(i, 0, 0, null);
        g.dispose();
        return target;
    }

    public static BufferedImage rotate(BufferedImage i, int angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
               cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = i.getWidth();
        int h = i.getHeight();
        int neww = (int) Math.floor(w*cos + h*sin),
            newh = (int) Math.floor(h*cos + w*sin);
        BufferedImage rotated = new BufferedImage(neww, newh, i.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww-w)/2, (newh-h)/2);
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawRenderedImage(i, null);
        graphic.dispose();
        return rotated;
    }
}
