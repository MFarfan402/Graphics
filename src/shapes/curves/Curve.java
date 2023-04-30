package shapes.curves;

import shapes.PolarCircle;
import shapes.lines.Line;
import shapes.lines.LineDDA;
import utilities.HelperFuncs;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Curve extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public int points, type;
    public boolean showPoints;

    public Curve(int points, int type, boolean showPoints)
    {
        color = Color.RED;
        this.points = points;
        this.type = type;
        this.showPoints = showPoints;

        setTitle("Curvas");
        setSize(900, 450);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
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
        switch (type) {
            case 1 -> DrawParable();
            case 2 -> DrawSmoke();
            default -> { }
        }
    }

    private void DrawParable()
    {
        ArrayList<PointXY> pointsXY = new ArrayList<>();
        int y;
        for (int x = 0; x < 180; x+=(180/points))
        {
            double radian = HelperFuncs.CalculateRad(x);
            y = (int) (Math.sin(radian) * -100);
            PutPixel(x + 150, y + 150);
            pointsXY.add(new PointXY(x + 150, y + 150));
        }
        DrawLines(pointsXY);
    }

    private void DrawSmoke()
    {
        ArrayList<PointXY> pointsXY = new ArrayList<>();
        int x;
        for (int y = 0; y > -360; y--)
        {
            double radian = HelperFuncs.CalculateRad(4 * y);
            x = (int) (Math.cos(radian) * -y);
            PutPixel(x + 400, y + 400);
            pointsXY.add(new PointXY(x + 400, y + 400));
        }
        DrawLines(pointsXY);
    }

    private void DrawPoints(Color color, ArrayList<PointXY> points)
    {
        this.color = color;
        for (PointXY point : points)
            PutPixel(point.x, point.y);
    }

    private void DrawLines(ArrayList<PointXY> pointsXY)
    {
        for (int i = 0; i < pointsXY.size()-1; i++)
        {
            if (showPoints)
            {
                ArrayList<PointXY> circlePoints = PolarCircle.LineToPointsXY(pointsXY.get(i), 3);
                DrawPoints(Color.BLUE, circlePoints);
            }

            Line line = new Line(pointsXY.get(i), pointsXY.get(i+1));
            ArrayList<PointXY> polyLinePoints = line.LineToPointsXY();
            DrawPoints(Color.DARK_GRAY, polyLinePoints);
        }
        if (showPoints)
        {
            ArrayList<PointXY> circlePoints = PolarCircle.LineToPointsXY(pointsXY.get(pointsXY.size() - 1), 3);
            DrawPoints(Color.BLUE, circlePoints);
        }
    }
}
