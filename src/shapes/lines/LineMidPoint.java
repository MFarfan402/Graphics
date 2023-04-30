package shapes.lines;

import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LineMidPoint extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public PointXY pointA, pointB;
    public LineMidPoint(PointXY pointA, PointXY pointB)
    {
        this.pointA = pointA;
        this.pointB = pointB;

        color = Color.RED;

        setTitle("Linea punto medio");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    private void DrawLine()
    {
        int dy = pointB.y - pointA.y;
        int dx = pointB.x - pointA.x;
        int positions = dx + 1;
        ArrayList<PointXY> points = new ArrayList<>();
        points.add(pointA);
        if (dy > 0)
        {
            dx = pointB.x - pointA.x;
            positions = dx + 1;
            int y = pointA.y;
            float criteria = (2 * dy) - dx;
            for (int i = 1, x = pointA.x + 1; i < positions; i++, x++)
            {
                int yToAdd = criteria >= 0 ? ++y : y;
                criteria = criteria >= 0 ?
                    criteria + (2 * dy) - (2 * dx) :
                    criteria + (2 * dy);
                points.add(new PointXY(x, yToAdd));
            }
        }
        else
        {
            positions = Math.abs(dy) + 1;

            int x = pointA.x;
            float criteria = (2 * dx) - dy;
            for (int i = 1, y = pointA.y; i < positions; i++, y--)
            {
                int xToAdd = criteria >= 0 ? --x : x;
                criteria = criteria >= 0 ?
                        criteria - (2 * dx) + (2 * dy) :
                        criteria - (2 * dx);
                points.add(new PointXY(xToAdd, y));
            }
        }
        PrintPoints(points);
    }

    private void PrintPoints(ArrayList<PointXY> points)
    {
        for (PointXY point : points)
            PutPixel(point.x, point.y);
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
        DrawLine();
    }

}
