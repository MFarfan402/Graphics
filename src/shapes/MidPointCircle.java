package shapes;

import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MidPointCircle extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public PointXY center;
    public int radius;
    public boolean show;

    private ArrayList<PointXY> points;

    public MidPointCircle(PointXY center, int radius, boolean show)
    {
        this.center = center;
        this.radius = radius;
        this.show = show;

        color = Color.BLACK;

        setTitle("Ventana");
        setSize(500, 500);
        setLayout(null);
        setVisible(show);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    public MidPointCircle(PointXY center, int radius)
    {
        this.center = center;
        this.radius = radius;
        show = false;
        points = DrawCircle(false);
    }

    public static ArrayList<PointXY> CircleToPointsXY2(PointXY center, int radius)
    {
        ArrayList<PointXY> points = new ArrayList<PointXY>();
        int x = 0;
        int y = radius;
        int p = 1 - radius;

        // Draw the circle using Midpoint Circle Algorithm
        while (x <= y) {
            points.add(new PointXY(center.x + x, center.y + y));
            points.add(new PointXY(center.x - x, center.y + y));
            points.add(new PointXY(center.x + x, center.y - y));
            points.add(new PointXY(center.x - x, center.y - y));
            points.add(new PointXY(center.x + y, center.y + x));
            points.add(new PointXY(center.x - y, center.y + x));
            points.add(new PointXY(center.x + y, center.y - x));
            points.add(new PointXY(center.x - y, center.y - x));
            x++;

            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
        }
        return points;
    }

    private ArrayList<PointXY> DrawCircle(boolean print)
    {
        points = new ArrayList<PointXY>();
        int x = 0;
        int y = radius;
        int p = 1 - radius;

        // Draw the circle using Midpoint Circle Algorithm
        while (x <= y) {
            points.add(new PointXY(center.x + x, center.y + y));
            points.add(new PointXY(center.x - x, center.y + y));
            points.add(new PointXY(center.x + x, center.y - y));
            points.add(new PointXY(center.x - x, center.y - y));
            points.add(new PointXY(center.x + y, center.y + x));
            points.add(new PointXY(center.x - y, center.y + x));
            points.add(new PointXY(center.x + y, center.y - x));
            points.add(new PointXY(center.x - y, center.y - x));
            x++;

            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
        }
        if (print)
            PrintCircle();
        return points;
    }

    private void PrintCircle()
    {
        for (PointXY point : points)
            PutPixel(point.x, point.y);
    }

    public ArrayList<PointXY> CircleToPointsXY() { return points; }

    private void PutPixel(int x, int y)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        DrawCircle(true);
    }
}
