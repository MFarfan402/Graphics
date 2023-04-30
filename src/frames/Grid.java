package frames;

import shapes.PolarCircle;
import shapes.lines.LineStraight;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Grid extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public int[] verticalConstraints, horizontalConstraints;
    boolean circleIntersections;

    public Grid(int[] verticalConstraints, int[] horizontalConstraints, boolean circleIntersections)
    {
        color = Color.RED;
        this.horizontalConstraints = horizontalConstraints;
        this.verticalConstraints = verticalConstraints;
        this.circleIntersections = circleIntersections;

        setTitle("Mallado rectangular");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    private void DrawBorder()
    {
        PointXY first = new PointXY(50, 50);
        PointXY second = new PointXY(300, 300);

        LineStraight line = new LineStraight(first, new PointXY(second.x, first.y), false);
        DrawPoints(Color.RED, line.LineToPointsXY());

        line = new LineStraight(first, new PointXY(first.x, second.y), true);
        DrawPoints(Color.RED, line.LineToPointsXY());

        line = new LineStraight(second, new PointXY(second.x, first.y), true);
        DrawPoints(Color.RED, line.LineToPointsXY());

        line = new LineStraight(second, new PointXY(first.x, second.y), false);
        DrawPoints(Color.RED, line.LineToPointsXY());
    }

    private void DrawGrid()
    {
        for (int i = 0; i < verticalConstraints.length; i++)
        {
            LineStraight line = new LineStraight(
                    new PointXY(verticalConstraints[i], 51),
                    new PointXY(verticalConstraints[i], 299), true);
            DrawPoints(Color.BLUE, line.LineToPointsXY());
        }

        for (int i = 0; i < horizontalConstraints.length; i++)
        {
            LineStraight line = new LineStraight(
                    new PointXY(51, horizontalConstraints[i]),
                    new PointXY(299, horizontalConstraints[i]), false);
            DrawPoints(Color.BLUE, line.LineToPointsXY());
        }
    }

    private void DrawIntersections()
    {
        for (int i = 0; i < verticalConstraints.length; i++)
        {
            for (int j = 0; j < horizontalConstraints.length; j++)
            {
                ArrayList<PointXY> circlePoints = PolarCircle.LineToPointsXY(new PointXY(verticalConstraints[i], horizontalConstraints[j]), 3);
                DrawPoints(Color.BLACK, circlePoints);
            }
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
        DrawBorder();
        DrawGrid();
        if (circleIntersections)
            DrawIntersections();
    }

    private void DrawPoints(Color color, ArrayList<PointXY> points)
    {
        this.color = color;
        for (PointXY point : points)
            PutPixel(point.x, point.y);
    }
}
