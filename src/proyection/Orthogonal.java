package proyection;

import shapes.lines.Line;
import utilities.PointXY;
import utilities.PointXYZ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Orthogonal extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    private ArrayList<PointXYZ> pointsXYZ = new ArrayList<PointXYZ>();
    private ArrayList<PointXY> pointsXY = new ArrayList<PointXY>();
    private PointXYZ vector;

    public Orthogonal()
    {
        color = Color.BLUE;
        setTitle("Proyección ortogonal");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);

        pointsXYZ.add(new PointXYZ(4, 8, 4)); // A
        pointsXYZ.add(new PointXYZ(4, 8, 6)); // B
        pointsXYZ.add(new PointXYZ(2, 8, 4)); // C
        pointsXYZ.add(new PointXYZ(2, 8, 6)); // D

        pointsXYZ.add(new PointXYZ(4, 2, 4)); // E
        pointsXYZ.add(new PointXYZ(4, 2, 6)); // F
        pointsXYZ.add(new PointXYZ(2, 2, 4)); // G
        pointsXYZ.add(new PointXYZ(2, 2, 6)); // H

        pointsXYZ.add(new PointXYZ(4, 6, 6)); // I
        pointsXYZ.add(new PointXYZ(4, 6, 8)); // J
        pointsXYZ.add(new PointXYZ(2, 6, 6)); // K
        pointsXYZ.add(new PointXYZ(2, 6, 8)); // L

        pointsXYZ.add(new PointXYZ(4, 4, 6)); // M
        pointsXYZ.add(new PointXYZ(4, 4, 8)); // N
        pointsXYZ.add(new PointXYZ(2, 4, 6)); // O
        pointsXYZ.add(new PointXYZ(2, 4, 8)); // P

        vector = new PointXYZ(0,0,0);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }

    private void PutPixel(int x, int y)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    private void DrawProyection()
    {
        for (int i = 0; i < pointsXYZ.size(); i++)
        {
            float u = 0;
            float x = pointsXYZ.get(i).x + (vector.x * u);
            float y = pointsXYZ.get(i).y + (vector.y * u);
            float z = pointsXYZ.get(i).z + (vector.z * u);

            pointsXY.add(new PointXY((int) y * 15, (int) z * 15));
        }
        for (PointXY point: pointsXY)
        {
            PutPixel(point.x, point.y);
            System.out.println("x:" + point.x + " y:" + point.y);
        }

        Line lineAB = new Line(pointsXY.get(0), pointsXY.get(1));
        Line lineAE = new Line(pointsXY.get(0), pointsXY.get(4));
        Line lineBI = new Line(pointsXY.get(1), pointsXY.get(8));
        Line lineIJ = new Line(pointsXY.get(8), pointsXY.get(9));
        Line lineJN = new Line(pointsXY.get(9), pointsXY.get(13));
        Line lineNM = new Line(pointsXY.get(13), pointsXY.get(12));
        Line lineMF = new Line(pointsXY.get(12), pointsXY.get(5));
        Line lineFE = new Line(pointsXY.get(5), pointsXY.get(4));

        ArrayList<PointXY> points = lineAB.LineToPointsXY();
        points.addAll(lineAE.LineToPointsXY());
        points.addAll(lineBI.LineToPointsXY());
        points.addAll(lineIJ.LineToPointsXY());
        points.addAll(lineJN.LineToPointsXY());
        points.addAll(lineNM.LineToPointsXY());
        points.addAll(lineMF.LineToPointsXY());
        points.addAll(lineFE.LineToPointsXY());

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
