package proyection;

import shapes.lines.Line;
import utilities.PointXY;
import utilities.PointXYZ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Foco extends JFrame {

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    private ArrayList<PointXYZ> pointsXYZ = new ArrayList<PointXYZ>();
    private ArrayList<PointXY> pointsXY = new ArrayList<PointXY>();
    private PointXYZ position;

    public Foco()
    {
        pointsXYZ.add(new PointXYZ(0,0,0));
        pointsXYZ.add(new PointXYZ(0,-2,0));
        pointsXYZ.add(new PointXYZ(2,-2,0));
        pointsXYZ.add(new PointXYZ(2,0,0));
        pointsXYZ.add(new PointXYZ(0,0,2));
        pointsXYZ.add(new PointXYZ(0,-2,2));
        pointsXYZ.add(new PointXYZ(2,-2,2));
        pointsXYZ.add(new PointXYZ(2,0,2));

        color = Color.BLUE;
        setTitle("Un punto de fuga");
        setSize(450, 450);
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

    public void DrawProyection()
    {
        position = new PointXYZ(2, 3, 4);

        for (int i = 0; i < pointsXYZ.size(); i++)
        {
            float aux = pointsXYZ.get(i).z - position.z;
            float u = (-position.z) / aux;
            float x = position.x + ((pointsXYZ.get(i).x - position.x)*(u));
            float y = position.y + ((pointsXYZ.get(i).y - position.y)*(u));

            System.out.println("X: " + (int)x + " Y: " + (int)y);
            pointsXY.add(new PointXY((int) (x * 15)+200, (int) (y * 15)+200));
        }

        for (PointXY point: pointsXY)
        {
            PutPixel(point.x, point.y);
            System.out.println("x:" + point.x + " y:" + point.y);
        }
        /*
        *
        * A - 0
        * B - 1
        * C - 2
        * D - 3
        * E - 4
        * F - 5
        * G - 6
        * H - 7
        *
        * */

        Line lineAB = new Line(pointsXY.get(0), pointsXY.get(1));
        Line lineBC = new Line(pointsXY.get(1), pointsXY.get(2));
        Line lineCD = new Line(pointsXY.get(2), pointsXY.get(3));
        Line lineAD = new Line(pointsXY.get(0), pointsXY.get(3));

        Line lineCG = new Line(pointsXY.get(2), pointsXY.get(6));
        Line lineDH = new Line(pointsXY.get(3), pointsXY.get(7));
        Line lineAE = new Line(pointsXY.get(0), pointsXY.get(4));
        Line lineBF = new Line(pointsXY.get(1), pointsXY.get(5));

        Line lineGH = new Line(pointsXY.get(6), pointsXY.get(7));
        Line lineHE = new Line(pointsXY.get(4), pointsXY.get(7));
        Line lineEF = new Line(pointsXY.get(4), pointsXY.get(5));
        Line lineFG = new Line(pointsXY.get(5), pointsXY.get(6));

        ArrayList<PointXY> points = lineAB.LineToPointsXY();
        points.addAll(lineBC.LineToPointsXY());
        points.addAll(lineCD.LineToPointsXY());
        points.addAll(lineAE.LineToPointsXY());
        points.addAll(lineAD.LineToPointsXY());
        points.addAll(lineCG.LineToPointsXY());
        points.addAll(lineDH.LineToPointsXY());
        points.addAll(lineAE.LineToPointsXY());
        points.addAll(lineBF.LineToPointsXY());
        points.addAll(lineGH.LineToPointsXY());
        points.addAll(lineHE.LineToPointsXY());
        points.addAll(lineEF.LineToPointsXY());
        points.addAll(lineFG.LineToPointsXY());

        for (PointXY point: points)
        {
            PutPixel(point.x, point.y);
        }
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        DrawProyection();
    }
}
