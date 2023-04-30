package shapes;

import shapes.lines.LineStraight;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Rectangle extends JFrame {

    PointXY first, second;
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public Rectangle(PointXY first, PointXY second)
    {
        this.first = first;
        this.second = second;

        color = Color.BLUE;

        setTitle("Rectangulo 2 puntos");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    public void DrawRectangle()
    {
        DrawLine(new LineStraight(first, new PointXY(second.x, first.y), false));
        DrawLine(new LineStraight(first, new PointXY(first.x, second.y), true));
        DrawLine(new LineStraight(second, new PointXY(second.x, first.y), true));
        DrawLine(new LineStraight(second, new PointXY(first.x, second.y), false));
    }

    public static ArrayList<PointXY> RectangleToPointsXY(PointXY first, PointXY second)
    {
        ArrayList<PointXY> points;
        LineStraight line = new LineStraight(first, new PointXY(second.x, first.y), false);
        points = line.LineToPointsXY();

        line = new LineStraight(first, new PointXY(first.x, second.y), true);
        points.addAll(line.LineToPointsXY());

        line = new LineStraight(second, new PointXY(second.x, first.y), true);
        points.addAll(line.LineToPointsXY());

        line = new LineStraight(second, new PointXY(first.x, second.y), false);
        points.addAll(line.LineToPointsXY());
        return points;
    }

    private void DrawLine(LineStraight line)
    {
        ArrayList<PointXY> points = line.LineToPointsXY();
        for (PointXY point : points) PutPixel(point.x, point.y);
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
        DrawRectangle();
    }
}
