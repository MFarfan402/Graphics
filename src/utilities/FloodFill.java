package utilities;

import shapes.MidPointCircle;
import shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FloodFill extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public FloodFill()
    {
        color = Color.MAGENTA;

        setTitle("Relleno inundacion.");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(450, 450, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    private void PutPixel(int x, int y)
    {
        buffer.setRGB(x, y, color.getRGB());
        this.getGraphics().drawImage(buffer, 0, 0, this);
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        DrawCircle();
        Fill(150, 150);
    }

    private void Fill(int x, int y)
    {
        int myColor = buffer.getRGB(x, y);
        Color mySecondColor = new Color(myColor);

        if (!mySecondColor.equals(Color.MAGENTA))
        {
            PutPixel(x, y);
            Fill(x + 1, y);
            Fill(x - 1, y);
            Fill(x, y - 1);
            Fill(x, y + 1);
        }
    }

    private void DrawCircle()
    {
        MidPointCircle circle = new MidPointCircle(
                new PointXY(150, 150), 50);
        DrawPoints(circle.CircleToPointsXY());
    }

    private void DrawPoints(ArrayList<PointXY> points)
    {
        for (PointXY point : points)
            PutPixel(point.x, point.y);
    }

}
