package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Circle extends JFrame {

    public int xCenter, yCenter, radius;
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public Circle(int xCenter, int yCenter, int radius)
    {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
        color = Color.RED;

        setTitle("Circulo");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }


    public void DrawCircle()
    {
        int radiusSquared = radius * radius;
        for (int x = xCenter - radius; x <= xCenter + radius; x++)
        {
            int partial = (x - xCenter);
            partial *= partial;

            partial = radiusSquared - partial;
            double result = Math.sqrt(partial);

            PutPixel(x, (int)(yCenter + result));
            PutPixel(x, (int)(yCenter - result));
        }
    }

    private void PutPixel(int x, int y)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);

    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        DrawCircle();
    }


}
