package scenes;

import shapes.MidPointCircle;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.curves.ParametricCurve;
import shapes.lines.Line;
import utilities.HelperFuncs;
import utilities.PointXY;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.*;

public class Animation extends JFrame implements Runnable
{
    private BufferedImage buffer, bufferSingle;
    private Graphics2D graphics;
    private Thread thread;

    private PointXY planePoint;
    private boolean leftDirection, sceneReady, cabinComplete;
    private int height, clockHandAngle, steeringWheelAngle;
    private float escalationCounter;

    public Animation()
    {
        buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        bufferSingle = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
        planePoint = new PointXY(50, 70);
        escalationCounter = 1;
        height = 300;
        clockHandAngle = 0;
        steeringWheelAngle = 0;
        setTitle("Proyecto");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        setSize(500, 300);
        sceneReady = false;
        repaint();
        while (clockHandAngle < 360)
        {
            try
            {
                repaint();
                steeringWheelAngle += (clockHandAngle < 70 || (clockHandAngle > 210 && clockHandAngle < 320)) ? 1 : -1;
                clockHandAngle++;
                thread.sleep(80);
            } catch (InterruptedException e) {}
        }

        cabinComplete = true;
        while (escalationCounter < 100)
        {
            try
            {
                repaint();
                escalationCounter += 0.1;
                if (height < 500)
                    height++;
                setSize(500, height);
                thread.sleep(10);
            }
            catch (InterruptedException e) {}
        }

        sceneReady = true;
        leftDirection = false;
        int limitX = planePoint.x + 40;
        for (int i = planePoint.x; i < limitX; i++)
        {
            try {
                repaint();
                planePoint.x++;
                thread.sleep(50);
            }
            catch (InterruptedException e) {}
        }
//
        limitX = planePoint.x + 160;
        for (int i = planePoint.x, j = planePoint.y; i < limitX; i++, j++)
        {
            try
            {
                repaint();
                planePoint.x++;
                planePoint.y++;
                thread.sleep(50);
            }
            catch (InterruptedException e) {}
        }

        limitX = planePoint.x + 300;
        for (int i = planePoint.x; i < limitX; i++)
        {
            try
            {
                repaint();
                planePoint.x++;
                thread.sleep(30);
            }
            catch (InterruptedException e) {}
        }

        leftDirection = true;
        planePoint = new PointXY(500, 420);
        for (int i = planePoint.x; i > -80; i--)
        {
            try
            {
                repaint();
                planePoint.x--;
                thread.sleep(30);
            }
            catch (InterruptedException e) {}
        }
    }

    public void paint(Graphics g)
    {
        super.paint(graphics);

        if (sceneReady)
        {
            PaintClouds();
            PaintPlane();
        }
        else
        {
            PaintCabinComponents();
            if (cabinComplete)
                PaintBackgroundCabin();
            else
                AnimateCabinComponents();
        }
        g.drawImage(buffer, 0,0, this);
        g.dispose();
    }
    public void AnimateCabinComponents()
    {
        ArrayList<PointXY> points = Rectangle.RectangleToPointsXY(new PointXY(170, 145), new PointXY(200, 149));
        points = HelperFuncs.CenterRotation(clockHandAngle, points, new PointXY(185, 147));
        for (PointXY point : points)
            PutPixel(point.x, point.y, Color.RED);
        Rellenar(185, 147, Color.RED, Color.RED);

        points= Rectangle.RectangleToPointsXY(new PointXY(232, 145), new PointXY(250, 149));
        points = HelperFuncs.CenterRotation(clockHandAngle + 45, points, new PointXY(234, 147));
        for (PointXY point : points)
            PutPixel(point.x, point.y, Color.RED);
        Rellenar(234, 147, Color.RED, Color.RED);

        if (clockHandAngle > 100 && clockHandAngle < 200 && clockHandAngle % 5 != 0)
            fillCircle(new PointXY(160, 238), 13, Color.ORANGE);

        if (clockHandAngle > 200 && clockHandAngle < 300 && clockHandAngle % 5 != 0)
            fillCircle(new PointXY(160, 238), 7, Color.GREEN);

//        points = Rectangle.RectangleToPointsXY(new PointXY(30, 220), new PointXY(70, 260));
//        points = HelperFuncs.CenterRotation(clockHandAngle, points, new PointXY(50, 240));
//        for (PointXY point : points)
//            PutPixel(point.x, point.y, Color.BLACK);
//        Rellenar(50, 240, Color.BLACK, Color.BLACK);
//
//        points = Rectangle.RectangleToPointsXY(new PointXY(35, 225), new PointXY(65, 255));
//        points = HelperFuncs.CenterRotation(clockHandAngle, points, new PointXY(50, 240));
//        for (PointXY point : points)
//            PutPixel(point.x, point.y, MyColor.cabin);
//        Rellenar(50, 240, MyColor.cabin, MyColor.cabin);
    }
    public void PaintBackgroundCabin()
    {
        ArrayList<PointXY> newPoint = new ArrayList<PointXY>();
        newPoint.add(new PointXY(5, 499));

        newPoint = HelperFuncs.Escalation(escalationCounter, 1, newPoint);

        System.out.println(newPoint.get(0).x + " : " + newPoint.get(0).y);

        ArrayList<PointXY> result = new ArrayList<PointXY>();
        result.add(new PointXY(0, 0));
        result.add(new PointXY(0, 499));
        result.add(newPoint.get(0));
        result.add(new PointXY(newPoint.get(0).x,0));

        drawPolygon(result, MyColor.sky);
        Rellenar(2, 2, MyColor.sky, MyColor.sky);
    }

    public void PaintCabinComponents()
    {
        for (int y = 0; y < buffer.getHeight() - 1; y++)
            for (int x = 0;x <= buffer.getWidth() - 1; x++)
                PutPixel(x, y, MyColor.cabin);

        fillRectangle(new PointXY(100, 50), new PointXY(270, 200), MyColor.cabinClear);
        fillRectangle(new PointXY(140, 55), new PointXY(230, 65), MyColor.cabin);

        fillCircle(new PointXY(70, 100), 20, MyColor.cabinBlack);
        fillRectangle(new PointXY(55, 93), new PointXY(85, 108), MyColor.cabinClear);

        fillCircle(new PointXY(70, 150), 10, MyColor.cabinBlack);
        fillRectangle(new PointXY(0, 170), new PointXY(90, 200), MyColor.cabinClear);

        fillCircle(new PointXY(440, 100), 15, MyColor.cabinBlack);
        fillCircle(new PointXY(440, 135), 15, MyColor.cabinBlack);
        fillCircle(new PointXY(440, 170), 15, MyColor.cabinBlack);

        fillRectangle(new PointXY(280, 80), new PointXY(360, 140), MyColor.cabinBlack);
        fillRectangle(new PointXY(285, 85), new PointXY(355, 135), MyColor.cabin);
        fillRectangle(new PointXY(295, 95), new PointXY(345, 125), MyColor.cabinBlack);

        fillRectangle(new PointXY(280, 150), new PointXY(360, 165), MyColor.cabinClear);
        fillRectangle(new PointXY(283, 153), new PointXY(357, 162), MyColor.cabinBlack);
        fillRectangle(new PointXY(280, 185), new PointXY(360, 200), MyColor.cabinClear);
        fillRectangle(new PointXY(283, 188), new PointXY(357, 197), MyColor.cabinBlack);

        fillRectangle(new PointXY(0, 210), new PointXY(499, 280), MyColor.cabinMid);
        fillRectangle(new PointXY(0, 279), new PointXY(499, 499), MyColor.cabinBlack2);

        fillCircle(new PointXY(105, 55), 3, MyColor.cabinMid);
        fillCircle(new PointXY(265, 55), 3, MyColor.cabinMid);
        fillCircle(new PointXY(105, 195), 3, MyColor.cabinMid);
        fillCircle(new PointXY(265, 195), 3, MyColor.cabinMid);

        fillCircle(new PointXY(320, 175), 5, MyColor.cabinMid);
        fillCircle(new PointXY(280, 65), 7,MyColor.cabinMid);
        fillCircle(new PointXY(360, 65), 7, MyColor.cabinMid);

        fillRectangle(new PointXY(135, 220), new PointXY(235, 260), MyColor.cabinBlack);
        fillRectangle(new PointXY(255, 220), new PointXY(390, 260), MyColor.cabinBlack);

        fillRectangle(new PointXY(138, 223), new PointXY(183, 257), MyColor.myGray);
        fillRectangle(new PointXY(186, 223), new PointXY(232, 257), MyColor.myGray);
        for (int i = 258; i < 380; i+=13)
            fillRectangle(new PointXY(i, 223), new PointXY(i + 10, 257), MyColor.myGray);

        fillRectangle(new PointXY(375, 70), new PointXY(410, 120), MyColor.cabinClear);
        fillCircle(new PointXY(392, 95), 12, MyColor.myGray);
        fillRectangle(new PointXY(375, 130), new PointXY(410, 200), MyColor.myGray);

        for (int i = 385; i <= 400; i+=5)
            fillRectangle(new PointXY(i, 140), new PointXY(i+2, 160), MyColor.cabinClear);


        // 2
        fillCircle(new PointXY(185, 97), 22, MyColor.cabinBlack);
        fillCircle(new PointXY(185, 97), 18, MyColor.cabinBlue);

        // 1
        fillCircle(new PointXY(135, 97), 22, MyColor.cabinBlack);
        fillCircle(new PointXY(135, 97), 18, MyColor.myGray);

        // 3
        fillCircle(new PointXY(234, 97), 22, MyColor.cabinBlack);
        fillCircle(new PointXY(234, 97), 18, MyColor.myGray);
        fillCircle(new PointXY(234, 97), 2, MyColor.cabinClear);

        // 5
        fillCircle(new PointXY(185, 147), 22, MyColor.cabinBlack);
        fillCircle(new PointXY(185, 147), 18, MyColor.myGray);
        fillCircle(new PointXY(185, 147), 2, MyColor.cabinClear);

        // 4
        fillCircle(new PointXY(135, 147), 22, MyColor.cabinBlack);
        fillCircle(new PointXY(135, 147), 18, MyColor.myGray);

        // 6
        fillCircle(new PointXY(234, 147), 22, MyColor.cabinBlack);
        fillCircle(new PointXY(234, 147), 18, MyColor.myGray);
        fillCircle(new PointXY(234, 147), 2, MyColor.cabinClear);

        ArrayList<PointXY> pointsXY = ParametricCurve.DrawInfinite(20);
        for (PointXY point : pointsXY)
            PutPixel(point.x + 320, point.y + 110, Color.RED);

        fillRectangle(new PointXY(120, 145), new PointXY(150, 149), MyColor.cabinClear);
        fillRectangle(new PointXY(134, 132), new PointXY(137, 162), MyColor.cabinClear);
    }
    public void PaintPlane()
    {
        fillRectangle(planePoint, new PointXY(planePoint.x + 60, planePoint.y + 22), MyColor.plane);
        fillRectangle(new PointXY(planePoint.x, planePoint.y + 15), new PointXY(planePoint.x + 60, planePoint.y + 19), MyColor.planeBlue);

        if (leftDirection)
        {
            drawTriangle(
                    new PointXY(planePoint.x, planePoint.y),
                    new PointXY(planePoint.x - 20, planePoint.y + 10),
                    new PointXY(planePoint.x, planePoint.y + 20),
                    MyColor.plane
            );

            if ((planePoint.x - 5) > 0)
                Rellenar(planePoint.x - 5, planePoint.y + 5, MyColor.plane, MyColor.plane);

            ArrayList<PointXY> blueTail = new ArrayList<PointXY>();
            blueTail.add(new PointXY(planePoint.x + 60, planePoint.y));
            blueTail.add(new PointXY(planePoint.x + 70, planePoint.y));
            blueTail.add(new PointXY(planePoint.x + 77, planePoint.y + 10));
            blueTail.add(new PointXY(planePoint.x + 70, planePoint.y + 20));
            blueTail.add(new PointXY(planePoint.x + 60, planePoint.y + 20));
            drawPolygon(blueTail, MyColor.planeBlue);
            if (((planePoint.x+75) < 500) && ((planePoint.x+75) > 0))
               Rellenar(planePoint.x + 62, planePoint.y + 2, MyColor.planeBlue, MyColor.planeBlue);

            ArrayList<PointXY> tail = new ArrayList<PointXY>();
            tail.add(new PointXY(planePoint.x + 60, planePoint.y));
            tail.add(new PointXY(planePoint.x + 70, planePoint.y - 20));
            tail.add(new PointXY(planePoint.x + 80, planePoint.y - 20));
            tail.add(new PointXY(planePoint.x + 70, planePoint.y));

            drawPolygon(tail, MyColor.plane);
            PutPixel(planePoint.x + 65, planePoint.y - 5, Color.RED);
            if ((planePoint.x + 75) < 500 && ((planePoint.x+75) > 0))
                Rellenar(planePoint.x + 65, planePoint.y - 5, MyColor.plane, MyColor.plane);
        }
        else
        {
            drawTriangle(
                    new PointXY(planePoint.x + 60, planePoint.y),
                    new PointXY(planePoint.x + 80, planePoint.y + 10),
                    new PointXY(planePoint.x + 60, planePoint.y + 20),
                    MyColor.plane
            );

            if ((planePoint.x + 65) < 500)
                Rellenar(planePoint.x + 65, planePoint.y + 5, MyColor.plane, MyColor.plane);

            ArrayList<PointXY> blueTail = new ArrayList<PointXY>();
            blueTail.add(new PointXY(planePoint.x, planePoint.y));
            blueTail.add(new PointXY(planePoint.x - 10, planePoint.y));
            blueTail.add(new PointXY(planePoint.x - 17, planePoint.y + 10));
            blueTail.add(new PointXY(planePoint.x - 10, planePoint.y + 20));
            blueTail.add(new PointXY(planePoint.x, planePoint.y + 20));

            drawPolygon(blueTail, MyColor.planeBlue);
            if ((planePoint.x) < 500)
                Rellenar(planePoint.x - 5, planePoint.y + 5, MyColor.planeBlue, MyColor.planeBlue);

            ArrayList<PointXY> tail = new ArrayList<PointXY>();
            tail.add(new PointXY(planePoint.x, planePoint.y));
            tail.add(new PointXY(planePoint.x - 10, planePoint.y - 20));
            tail.add(new PointXY(planePoint.x - 20, planePoint.y - 20));
            tail.add(new PointXY(planePoint.x - 10, planePoint.y));

            drawPolygon(tail, MyColor.plane);
            if ((planePoint.x)< 500)
                Rellenar(planePoint.x - 5, planePoint.y - 5, MyColor.plane, MyColor.plane);
        }

        fillRectangle(new PointXY(planePoint.x + 10, planePoint.y + 5), new PointXY(planePoint.x + 15, planePoint.y + 19), MyColor.windowPlane);
        fillRectangle(new PointXY(planePoint.x + 50, planePoint.y + 5), new PointXY(planePoint.x + 55, planePoint.y + 19), MyColor.windowPlane);

        for (int i = 18; i < 40; i+=7)
            fillRectangle(new PointXY(planePoint.x + i, planePoint.y + 5), new PointXY(planePoint.x + i + 5, planePoint.y + 12), MyColor.windowPlane);

    }
    private void PutPixel(int x, int y, Color color)
    {
        bufferSingle.setRGB(0, 0, color.getRGB());
        graphics.drawImage(bufferSingle, x, y, this);
    }
    public int getPixel(int x, int y) {
        return buffer.getRGB(x, y);
    }
  public void fillCircle(PointXY center, int radius, Color fillColor) {
        int x = center.x - radius;
        int y = center.y - radius;
        int diameter = radius * 2;

        for (int row = y; row < y + diameter; row++)
        {
            int left = (int) Math.round(center.x - Math.sqrt(radius * radius - (row - center.y) * (row - center.y)));
            int right = (int) Math.round(center.x + Math.sqrt(radius * radius - (row - center.y) * (row - center.y)));
            for (int col = left; col <= right; col++)
                PutPixel(col, row, fillColor);
        }
    }
    public void fillRectangle(PointXY pointA, PointXY pointB, Color fillColor)
    {
        for (int y = pointA.y; y < pointB.y - 1; y++)
            for (int x = pointA.x; x <= pointB.x- 1; x++)
                PutPixel(x, y, fillColor);
    }
    public void drawTriangle(PointXY pointA, PointXY pointB, PointXY pointC, Color fillColor)
    {
        Line line = new Line(pointA, pointB);
        ArrayList<PointXY> points = line.LineToPointsXY();

        line = new Line(pointB, pointC);
        points.addAll(line.LineToPointsXY());

        line = new Line(pointC, pointA);
        points.addAll(line.LineToPointsXY());

        for (int i = 0; i < points.size(); i++)
        {
            PutPixel(points.get(i).x, points.get(i).y, fillColor);
        }
    }
    public void Rellenar (int x, int y,Color color, Color figura) {
        int limiteIzquierdo = x;
        int limiteDerecho = x;

        while (getPixel(limiteIzquierdo - 1, y) != figura.getRGB()) {
            limiteIzquierdo--;
        }
        while (getPixel(limiteDerecho + 1, y) != figura.getRGB()) {
            limiteDerecho++;
        }

        for (int i = limiteIzquierdo; i <= limiteDerecho; i++) {
                PutPixel(i, y, color);
        }

        for (int i = limiteIzquierdo; i <= limiteDerecho; i++) {
            if (getPixel(i , y - 1) != figura.getRGB() && getPixel(i , y - 1) != color.getRGB()) {
                Rellenar(i, y - 1,color,figura);
            }
            if (getPixel(i , y + 1) != figura.getRGB() && getPixel(i , y + 1) != color.getRGB()) {
                Rellenar(i, y + 1,color,figura);
            }
        }
    }
     void drawPolygon(ArrayList<PointXY> points, Color fillColor)
    {
        ArrayList<PointXY> newPoints = new ArrayList<PointXY>();
        for (int i = 0; i < points.size(); i++)
        {
            Line line = (i == points.size() - 1) ?
                new Line(points.get(i), points.get(0)):
                new Line(points.get(i), points.get(i+1));
            newPoints.addAll(line.LineToPointsXY());
        }

        for (int i = 0; i < newPoints.size(); i++)
        {
            PutPixel(newPoints.get(i).x, newPoints.get(i).y, fillColor);
        }
    }
    public void PaintClouds(ArrayList<PointXY> points)
    {
        int threadNumber = points.size();
        Thread threads[] = new Thread[threadNumber];
        for (int i = 0; i < threadNumber; i++)
        {
            int finalI = i;
            int radius = i < 4 ? 15 : 13;
            threads[i] = new Thread(() -> fillCircle(points.get(finalI), radius, Color.white));
            threads[i].start();
        }
        try {
            for (int i = 0; i < threadNumber; i++)
                threads[i].join();
        } catch (InterruptedException e) {}

        Line line = new Line(new PointXY(0, 500), new PointXY(500, 500));
        ArrayList<PointXY> points2 = line.LineToPointsXY();
        for (int i = 0; i < points2.size(); i++)
            PutPixel(points2.get(i).x, points2.get(i).y, MyColor.plane);

        line = new Line(new PointXY(0, 0), new PointXY(0, 500));
        points2 = line.LineToPointsXY();
        for (int i = 0; i < points2.size(); i++)
            PutPixel(points2.get(i).x, points2.get(i).y, MyColor.plane);
    }
    public void PaintClouds()
    {
        for (int y = 0; y < buffer.getHeight() - 1; y++)
            for (int x = 0;x <= buffer.getWidth() - 1; x++)
                PutPixel(x, y, MyColor.sky);
        PaintClouds(Cloud.FirstCloud());
        PaintClouds(Cloud.SecondCloud());
        PaintClouds(Cloud.ThirdCloud());
    }

}