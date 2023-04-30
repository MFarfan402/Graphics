package shapes;

import utilities.HelperFuncs;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PolarCircle extends JFrame {

    public int xCenter, yCenter, radius;
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public PointXY center;
    public boolean show;

    public PolarCircle(PointXY center, int radius, boolean show)
    {
        this.center = center;
        this.radius = radius;
        this.show = show;
        color = Color.BLUE;

        setTitle("Circulo polar");
        setSize(500, 500);
        setLayout(null);
        setVisible(show);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    public void DrawCircle()
    {
        for (int i = 0; i < 360; i++)
        {
            double radian = HelperFuncs.CalculateRad(i);
            int x = center.x + (int)(radius * Math.sin(radian));
            int y = center.y + (int)(radius * Math.cos(radian));
            PutPixel(x, y);
        }
    }

    public static ArrayList<PointXY> LineToPointsXY(PointXY center, int radius)
    {
        ArrayList<PointXY> points = new ArrayList<>();
        for (int i = 0; i < 360; i++)
        {
            double radian = HelperFuncs.CalculateRad(i);
            int x = center.x + (int)(radius * Math.sin(radian));
            int y = center.y + (int)(radius * Math.cos(radian));
            points.add(new PointXY(x, y));
        }
        return points;
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
        if (show)
            DrawCircle();
    }
}