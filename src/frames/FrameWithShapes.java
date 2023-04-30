package frames;

import shapes.Ellipse;
import shapes.MidPointCircle;
import shapes.Rectangle;
import shapes.lines.Line;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FrameWithShapes extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    public FrameWithShapes()
    {
        color = Color.BLACK;

        setTitle("Frame con figuras");
        setSize(450, 250);
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

    private void DrawShapes()
    {
        Line line = new Line(new PointXY(50, 50), new PointXY(100, 90));
        DrawPoints(line.LineToPointsXY());

        line = new Line(new PointXY(120, 65), new PointXY(170, 65));
        DrawPoints(line.LineToPointsXY());

        line = new Line(new PointXY(240, 50), new PointXY(190, 90));
        DrawPoints(line.LineToPointsXY());

        line = new Line(new PointXY(310, 65), new PointXY(260, 65));
        DrawPoints(line.LineToPointsXY());

        MidPointCircle circle = new MidPointCircle(new PointXY(75,150), 4);
        DrawPoints(circle.CircleToPointsXY());

        circle = new MidPointCircle(new PointXY(75,150), 14);
        DrawPoints(circle.CircleToPointsXY());

        circle = new MidPointCircle(new PointXY(75,150), 24);
        DrawPoints(circle.CircleToPointsXY());

        circle = new MidPointCircle(new PointXY(75,150), 34);
        DrawPoints(circle.CircleToPointsXY());

        DrawPoints(Rectangle.RectangleToPointsXY(new PointXY(150,130), new PointXY(250, 180)));
        DrawPoints(Rectangle.RectangleToPointsXY(new PointXY(165,145), new PointXY(235, 165)));

        DrawPoints(Ellipse.EllipseToPointsXY(new PointXY(350, 150), 30, 10));
        DrawPoints(Ellipse.EllipseToPointsXY(new PointXY(350, 150), 40, 20));
        DrawPoints(Ellipse.EllipseToPointsXY(new PointXY(350, 150), 50, 30));
        DrawPoints(Ellipse.EllipseToPointsXY(new PointXY(350, 150), 60, 40));
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        DrawShapes();
    }

    private void DrawPoints(ArrayList<PointXY> points)
    {
        for (PointXY point : points) PutPixel(point.x, point.y);
    }
}
