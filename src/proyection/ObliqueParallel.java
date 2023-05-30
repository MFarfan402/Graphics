package proyection;

import shapes.Rectangle;
import shapes.lines.Line;
import utilities.HelperFuncs;
import utilities.PointXY;
import utilities.PointXYZ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ObliqueParallel extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    private ArrayList<PointXY> pointsXY = new ArrayList<PointXY>();
    private ArrayList<PointXYZ> pointsXYZ = new ArrayList<PointXYZ>();
    private PointXYZ vector;
    public ObliqueParallel()
    {
        color = Color.BLUE;
        setTitle("Proyección paralela oblicua");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);

        pointsXYZ.add(new PointXYZ(50, 150, 150)); // A
        pointsXYZ.add(new PointXYZ(150, 150, 150)); // B
        pointsXYZ.add(new PointXYZ(50, 250, 150)); // C
        pointsXYZ.add(new PointXYZ(150, 250, 150)); // D

        pointsXYZ.add(new PointXYZ(50, 150, 250)); // E
        pointsXYZ.add(new PointXYZ(150, 150, 250)); // F
        pointsXYZ.add(new PointXYZ(50, 250, 250)); // G
        pointsXYZ.add(new PointXYZ(150, 250, 250)); // H

        vector = new PointXYZ(-2,-1,6);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
    }
    private void PutPixel(int x, int y)
    {
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    private void DrawCube()
    {
        for (int i = 0; i < pointsXYZ.size(); i++)
        {
            float u =  (float) (-1 * pointsXYZ.get(i).z) / vector.z;
            float x = pointsXYZ.get(i).x + (vector.x * u);
            float y = pointsXYZ.get(i).y + (vector.y * u);

            pointsXY.add(new PointXY((int) x, (int) y));
        }
        for (PointXY point: pointsXY)
        {
            PutPixel(point.x, point.y);
            System.out.println("x:" + point.x + " y:" + point.y);
        }

        Line lineAB = new Line(pointsXY.get(0), pointsXY.get(1));
        Line lineAC = new Line(pointsXY.get(0), pointsXY.get(2));
        Line lineAE = new Line(pointsXY.get(0), pointsXY.get(4));

        Line lineBD = new Line(pointsXY.get(1), pointsXY.get(3));
        Line lineBF = new Line(pointsXY.get(1), pointsXY.get(5));

        Line lineEG = new Line(pointsXY.get(4), pointsXY.get(6));
        Line lineEF = new Line(pointsXY.get(4), pointsXY.get(5));

        Line lineCG = new Line(pointsXY.get(2), pointsXY.get(6));
        Line lineCD = new Line(pointsXY.get(2), pointsXY.get(3));

        Line lineHG = new Line(pointsXY.get(7), pointsXY.get(6));
        Line lineHF = new Line(pointsXY.get(7), pointsXY.get(5));
        Line lineHD = new Line(pointsXY.get(7), pointsXY.get(3));

        ArrayList<PointXY> points = lineAB.LineToPointsXY();
        points.addAll(lineAC.LineToPointsXY());
        points.addAll(lineAC.LineToPointsXY());
        points.addAll(lineAE.LineToPointsXY());
        points.addAll(lineBD.LineToPointsXY());
        points.addAll(lineBF.LineToPointsXY());
        points.addAll(lineEG.LineToPointsXY());
        points.addAll(lineEF.LineToPointsXY());
        points.addAll(lineCG.LineToPointsXY());
        points.addAll(lineCD.LineToPointsXY());
        points.addAll(lineHG.LineToPointsXY());
        points.addAll(lineHF.LineToPointsXY());
        points.addAll(lineHD.LineToPointsXY());

        for (PointXY point: points)
        {
            PutPixel(point.x, point.y);
        }

    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        DrawCube();
    }

}
