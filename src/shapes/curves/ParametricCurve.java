package shapes.curves;

import shapes.PolarCircle;
import shapes.lines.Line;
import utilities.HelperFuncs;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ParametricCurve extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public int radius, type;

    public ParametricCurve(int type, int radius)
    {
        color = Color.BLUE;
        this.radius = radius;
        this.type = type;

        setTitle("Curvas paramétricas");
        setSize(900, 900);
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
        switch (type)
        {
            case 1 -> DrawInfinite();
            case 2 -> DrawFlower();
            case 3 -> DrawSun();
            default -> { }
        }
    }

    private void DrawInfinite()
    {
        ArrayList<PointXY> pointsXY = new ArrayList<>();
        int x, y;
        for (int t = 0; t < 360; t++)
        {
            double radian = HelperFuncs.CalculateRad(t);
            double cos = Math.cos(radian), sin = Math.sin(radian);

            x = (int) ((radius * sin) / (1 + (cos * cos)));
            y = (int) ((radius * sin * cos) / (1 + (cos * cos)));

            PutPixel(x + 450, y + 450);
            pointsXY.add(new PointXY(x + 450, y + 450));
        }
        DrawLines(pointsXY);
    }

    public static ArrayList<PointXY> DrawInfinite(int radius)
    {
        ArrayList<PointXY> pointsXY = new ArrayList<>();
        int x, y;
        for (int t = 0; t < 360; t++)
        {
            double radian = HelperFuncs.CalculateRad(t);
            double cos = Math.cos(radian), sin = Math.sin(radian);

            x = (int) ((radius * sin) / (1 + (cos * cos)));
            y = (int) ((radius * sin * cos) / (1 + (cos * cos)));
            pointsXY.add(new PointXY(x, y));
        }
        return pointsXY;
    }


    private void DrawFlower()
    {
        ArrayList<PointXY> pointsXY = new ArrayList<>();
        int x, y;
        for (int t = 0; t < 360; t++)
        {
            double radian = HelperFuncs.CalculateRad(t);
            double doublex = (Math.cos(radian) + (.5 * Math.cos(7 * radian)) + (.33 * Math.sin(17 * radian))) * 100;
            double doubley = (Math.sin(radian) + (.5 * Math.sin(7 * radian)) + (.33 * Math.cos(17 * radian))) * 100;

            x = (int) doublex;
            y = (int) doubley;
            PutPixel(x + 300, y + 300);
            pointsXY.add(new PointXY(x + 300, y + 300));
        }
        DrawLines(pointsXY);
        ArrayList<PointXY> lastPoint = new ArrayList<>();
        lastPoint.add(pointsXY.get(0));
        lastPoint.add(pointsXY.get(pointsXY.size()-1));
        DrawLines(lastPoint);
    }

    private void DrawSun()
    {
        ArrayList<PointXY> pointsXY = new ArrayList<>();
        int x, y;
        for (int t = 0; t < 2520; t++)
        {
            double radian = HelperFuncs.CalculateRad(t);
            double doublex = (17 * Math.cos(radian) + (7 * Math.cos(2.4286 * radian))) * 10;
            double doubley = (17 * Math.sin(radian) - (7 * Math.sin(2.4286 * radian))) * 10;

            x = (int) doublex;
            y = (int) doubley;
            PutPixel(x + 300, y + 300);
            pointsXY.add(new PointXY(x + 300, y + 300));
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
            Line line = new Line(pointsXY.get(i), pointsXY.get(i+1));
            ArrayList<PointXY> polyLinePoints = line.LineToPointsXY();
            DrawPoints(Color.BLUE, polyLinePoints);
            polyLinePoints.clear();
        }
    }
}
