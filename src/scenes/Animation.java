package scenes;

import shapes.MidPointCircle;
import shapes.Rectangle;
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
    private boolean leftDirection;

    public Animation()
    {
        buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        bufferSingle = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
        planePoint = new PointXY(50, 70);


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
                repaint();           planePoint.x--;
                thread.sleep(30);
            }
            catch (InterruptedException e) {}
        }
    }

    public void paint(Graphics g)
    {
        super.paint(graphics);

        PaintClouds();
        PaintPlane();

        g.drawImage(buffer, 0,0, this);
        g.dispose();
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
    public void drawPolygon(ArrayList<PointXY> points, Color fillColor)
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

//
//        line = new Line(new PointXY(500, 0), new PointXY(500, 500));
//        points2 = line.LineToPointsXY();
//        for (int i = 0; i < points2.size(); i++)
//            PutPixel(points2.get(i).x, points2.get(i).y, MyColor.planeBlue);

//        line = new Line(new PointXY(500, 400), new PointXY(500, 500));
//        points2 = line.LineToPointsXY();
//        for (int i = 0; i < points2.size(); i++)
//            PutPixel(points2.get(i).x, points2.get(i).y, MyColor.planeBlue);
    }
    public void PaintClouds()    {
        for (int y = 0; y < buffer.getHeight() - 1; y++)
            for (int x = 0;x <= buffer.getWidth() - 1; x++)
                PutPixel(x, y, MyColor.sky);
        PaintClouds(Cloud.FirstCloud());
        PaintClouds(Cloud.SecondCloud());
        PaintClouds(Cloud.ThirdCloud());
    }

    public void SetBounds()
    {

    }
}