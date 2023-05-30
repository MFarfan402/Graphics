package proyection;

import shapes.Rectangle;
import shapes.lines.Line;
import utilities.HelperFuncs;
import utilities.PointXY;
import utilities.PointXYZ;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Rotation3D extends JFrame implements Runnable {
    private Color color;
    private Graphics graphics;

    private Image background, buffer;
    private Thread thread;
    private int counter;

    private boolean centerRotation;

    public Rotation3D(boolean centerRotation)
    {
        this.centerRotation = centerRotation;
        color = Color.RED;
        String title = centerRotation ? "Rotacion del centro de figura" : "Rotacion del origen";
        setTitle(title);
        setSize(450, 450);
        setLayout(null);
        setVisible(true);
        counter = 0;
        thread = new Thread(this);
        thread.start();

        pointsXYZ.add(new PointXYZ(50, 150, 150)); // A
        pointsXYZ.add(new PointXYZ(150, 150, 150)); // B
        pointsXYZ.add(new PointXYZ(50, 250, 150)); // C
        pointsXYZ.add(new PointXYZ(150, 250, 150)); // D

        pointsXYZ.add(new PointXYZ(50, 150, 250)); // E
        pointsXYZ.add(new PointXYZ(150, 150, 250)); // F
        pointsXYZ.add(new PointXYZ(50, 250, 250)); // G
        pointsXYZ.add(new PointXYZ(150, 250, 250)); // H
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                repaint();
                thread.sleep(10);
                if (++counter == 360) counter = 0;
            }
            catch (InterruptedException e) {}
        }
    }

    private ArrayList<PointXY> pointsXY = new ArrayList<PointXY>();
    private ArrayList<PointXYZ> pointsXYZ = new ArrayList<PointXYZ>();
    private PointXYZ vector;

    public void update(Graphics graphics)
    {
        ArrayList<PointXY> points = Rectangle.RectangleToPointsXY(new PointXY(100, 100), new PointXY(200, 200));

        int width = getWidth();
        int height = getHeight();

        graphics.setClip(0, 0, width, height);

        buffer = createImage(width, height);
        Graphics graphicBuffer = buffer.getGraphics();
        graphicBuffer.setClip(0, 0, width, height);
        graphicBuffer.drawImage(background, 0, 0, this);

        vector = new PointXYZ(-2,-1,6);

        for (int i = 0; i < pointsXYZ.size(); i++)
        {
            float u =  (float) (-1 * pointsXYZ.get(i).z) / vector.z;
            float x = pointsXYZ.get(i).x + (vector.x * u);
            float y = pointsXYZ.get(i).y + (vector.y * u);

            pointsXY.add(new PointXY((int) x, (int) y));
        }

        pointsXY = centerRotation ?
                HelperFuncs.CenterRotation(counter, pointsXY, new PointXY(166, 233)) :
                HelperFuncs.Rotation(counter, pointsXY);

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
        pointsXY.clear();

        ArrayList<PointXY> finalPoints = lineAB.LineToPointsXY();
        finalPoints.addAll(lineAC.LineToPointsXY());
        finalPoints.addAll(lineAC.LineToPointsXY());
        finalPoints.addAll(lineAE.LineToPointsXY());
        finalPoints.addAll(lineBD.LineToPointsXY());
        finalPoints.addAll(lineBF.LineToPointsXY());
        finalPoints.addAll(lineEG.LineToPointsXY());
        finalPoints.addAll(lineEF.LineToPointsXY());
        finalPoints.addAll(lineCG.LineToPointsXY());
        finalPoints.addAll(lineCD.LineToPointsXY());
        finalPoints.addAll(lineHG.LineToPointsXY());
        finalPoints.addAll(lineHF.LineToPointsXY());
        finalPoints.addAll(lineHD.LineToPointsXY());

        for (PointXY point : finalPoints)
            graphicBuffer.drawLine(point.x, point.y, point.x, point.y);

        graphics.drawImage(buffer, 0, 0, this);
    }

    public void paint(Graphics graphics)
    {
        update(graphics);
    }
}
