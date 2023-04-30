package shapes;

import utilities.HelperFuncs;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ellipse extends JFrame {

    public int radiusX, radiusY;
    public PointXY center;
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public Ellipse(PointXY center, int radiusX, int radiusY)
    {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.center = center;
        color = Color.RED;

        setTitle("Ventana");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    public static ArrayList<PointXY> EllipseToPointsXY(PointXY center, int radiusX, int radiusY)
    {
        ArrayList<PointXY> points = new ArrayList<PointXY>();
        for (int i = 0; i < 360; i++)
        {
            double radian = HelperFuncs.CalculateRad(i);
            int x = center.x + (int) (radiusX * Math.cos(radian));
            int y = center.y + (int) (radiusY * Math.sin(radian));
            points.add(new PointXY(x, y));
        }
        return points;
    }
    public void DrawEllipse()
    {
        for (int i = 0; i < 360; i++)
        {
            double radian = HelperFuncs.CalculateRad(i);
            int x = center.x + (int) (radiusX * Math.cos(radian));
            int y = center.y + (int) (radiusY * Math.sin(radian));
            PutPixel(x, y);
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
        DrawEllipse();
    }
}
